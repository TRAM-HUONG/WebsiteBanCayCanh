package controller;

import model.GioHang;
import model.GioHangChiTiet;
import model.GioHangChiTietId;
import model.KhachHang;
import model.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repository.GioHangChiTietRepository;
import repository.GioHangRepository;
import repository.KhachHangRepository;
import repository.SanPhamRepository;

import java.util.*;

@Controller
@RequestMapping("/giohang")
public class GioHangController {

    @Autowired
    private GioHangRepository gioHangRepo;

    @Autowired
    private GioHangChiTietRepository gioHangCTRepo;

    @Autowired
    private KhachHangRepository khRepo;

    @Autowired
    private SanPhamRepository spRepo;

    // 🛒 HIỂN THỊ GIỎ HÀNG
    @GetMapping
    public String hienThiGio(@RequestParam("maKH") String maKH, Model model) {
        GioHang gioHang = gioHangRepo.findByKhachHang_MaKH(maKH);

        if (gioHang == null) {
            KhachHang kh = khRepo.findById(maKH).orElse(null);
            if (kh == null) {
                throw new RuntimeException("Không tìm thấy khách hàng có mã: " + maKH);
            }
            gioHang = new GioHang();
            gioHang.setMaGH("GH" + UUID.randomUUID().toString().substring(0, 4).toUpperCase());
            gioHang.setNgayTao(new Date());
            gioHang.setKhachHang(kh); // ✅ Fix lỗi NULL MaKH
            gioHangRepo.save(gioHang);
        }

        List<GioHangChiTiet> chiTietList = gioHangCTRepo.findByGioHang_MaGH(gioHang.getMaGH());
        model.addAttribute("gioHang", gioHang);
        model.addAttribute("chiTietList", chiTietList);
        return "giohang"; // -> resources/templates/giohang.html
    }

    // ➕ THÊM VÀO GIỎ
    @PostMapping("/them")
    public String themVaoGio(@RequestParam("maKH") String maKH,
                             @RequestParam("maSP") String maSP,
                             @RequestParam(defaultValue = "1") int soLuong,
                             RedirectAttributes redirectAttributes) {

        GioHang gioHang = gioHangRepo.findByKhachHang_MaKH(maKH);

        if (gioHang == null) {
            KhachHang kh = khRepo.findById(maKH).orElse(null);
            if (kh == null) {
                throw new RuntimeException("Không tìm thấy khách hàng có mã: " + maKH);
            }
            gioHang = new GioHang();
            gioHang.setMaGH("GH" + UUID.randomUUID().toString().substring(0, 4).toUpperCase());
            gioHang.setNgayTao(new Date());
            gioHang.setKhachHang(kh);
            gioHangRepo.save(gioHang);
        }

        GioHangChiTietId id = new GioHangChiTietId(gioHang.getMaGH(), maSP);
        Optional<GioHangChiTiet> existing = gioHangCTRepo.findById(id);

        if (existing.isPresent()) {
            GioHangChiTiet ct = existing.get();
            ct.setSoLuong(ct.getSoLuong() + soLuong);
            gioHangCTRepo.save(ct);
        } else {
            SanPham sp = spRepo.findById(maSP).orElse(null);
            if (sp != null) {
                GioHangChiTiet ct = new GioHangChiTiet();
                ct.setId(id);
                ct.setGioHang(gioHang);
                ct.setSanPham(sp);
                ct.setSoLuong(soLuong);
                gioHangCTRepo.save(ct);
            }
        }

        redirectAttributes.addAttribute("maKH", maKH);
        return "redirect:/giohang";
    }

    // 💳 MUA NGAY
    @PostMapping("/muangay")
    public String muaNgay(@RequestParam("maKH") String maKH,
                          @RequestParam("maSP") String maSP,
                          RedirectAttributes redirectAttributes) {

        // thêm sản phẩm vào giỏ trước
        themVaoGio(maKH, maSP, 1, redirectAttributes);

        // rồi chuyển đến trang thanh toán
        redirectAttributes.addAttribute("maKH", maKH);
        return "redirect:/giohang/thanhtoan";
    }

    // ❌ XÓA MỘT SẢN PHẨM KHỎI GIỎ
    @GetMapping("/xoa")
    public String xoaKhoiGio(@RequestParam("maKH") String maKH,
                             @RequestParam("maSP") String maSP,
                             RedirectAttributes redirectAttributes) {
        GioHang gioHang = gioHangRepo.findByKhachHang_MaKH(maKH);
        if (gioHang != null) {
            GioHangChiTietId id = new GioHangChiTietId(gioHang.getMaGH(), maSP);
            gioHangCTRepo.deleteById(id);
        }
        redirectAttributes.addAttribute("maKH", maKH);
        return "redirect:/giohang";
    }
}
