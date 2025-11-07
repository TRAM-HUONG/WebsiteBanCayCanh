package controller;

import jakarta.servlet.http.HttpSession;
import model.GioHang;
import model.GioHangChiTiet;
import model.KhachHang;
import model.DonHang;
import model.ChiTietDonHang;
import model.ChiTietDonHangId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.GioHangRepository;
import repository.GioHangChiTietRepository;
import repository.KhachHangRepository;
import repository.DonHangRepository;
import repository.ChiTietDonHangRepository;

import java.util.*;

@Controller
@RequestMapping("/giohang")
public class ThanhToanController {

    @Autowired
    private GioHangRepository gioHangRepo;
    @Autowired
    private GioHangChiTietRepository gioHangCTRepo;
    @Autowired
    private KhachHangRepository khRepo;
    @Autowired
    private DonHangRepository donHangRepo;
    @Autowired
    private ChiTietDonHangRepository chiTietDHRepo;

    // 🟢 Hiển thị form thanh toán (GET)
    @GetMapping("/thanhtoan")
    public String hienThiThanhToan(@RequestParam("maKH") String maKH, Model model) {
        GioHang gioHang = gioHangRepo.findByKhachHang_MaKH(maKH);
        List<GioHangChiTiet> listCT = gioHangCTRepo.findByGioHang_MaGH(gioHang.getMaGH());
        model.addAttribute("gioHang", gioHang);
        model.addAttribute("chiTietList", listCT);
        return "thanhtoan";
    }

    // 🟢 Xử lý thanh toán (POST)
    @PostMapping("/thanhtoan")
    public String xuLyThanhToan(@RequestParam("maKH") String maKH,
                                @RequestParam("diaChiGiao") String diaChiGiao,
                                @RequestParam("phuongThuc") String phuongThuc,
                                Model model) {

        // Lấy giỏ hàng của KH
        GioHang gioHang = gioHangRepo.findByKhachHang_MaKH(maKH);
        if (gioHang == null) {
            throw new RuntimeException("Không tìm thấy giỏ hàng cho khách hàng " + maKH);
        }

        // Tạo đơn hàng mới
        DonHang dh = new DonHang();
        dh.setMaDH("DH" + UUID.randomUUID().toString().substring(0, 3).toUpperCase());
        dh.setKhachHang(gioHang.getKhachHang());
        dh.setNgayDat(new Date());
        dh.setDiaChiGiao(diaChiGiao);
        dh.setPhuongThuc(phuongThuc);
        dh.setTrangThai("Đang xử lý");
        donHangRepo.save(dh);

        // Lấy chi tiết giỏ hàng để tạo chi tiết đơn hàng
        List<GioHangChiTiet> listCT = gioHangCTRepo.findByGioHang_MaGH(gioHang.getMaGH());
        for (GioHangChiTiet ct : listCT) {
            ChiTietDonHang ctdh = new ChiTietDonHang();
            ctdh.setId(new ChiTietDonHangId(dh.getMaDH(), ct.getSanPham().getMaSP()));
            ctdh.setDonHang(dh);
            ctdh.setSanPham(ct.getSanPham());
            ctdh.setSoLuong(ct.getSoLuong());
            ctdh.setDonGia(ct.getSanPham().getDonGia());
            chiTietDHRepo.save(ctdh);
        }

        // Xóa giỏ hàng sau thanh toán
        gioHangCTRepo.deleteAll(listCT);

        model.addAttribute("donHang", dh);
        return "hoanthanh"; // ✅ chuyển đến trang hoanthanh.html
    }
}
