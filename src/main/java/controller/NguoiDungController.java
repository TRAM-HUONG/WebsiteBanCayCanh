package controller;

import jakarta.servlet.http.HttpSession;
import model.KhachHang;
import model.NguoiDung;
import repository.KhachHangRepository;
import repository.NguoiDungRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class NguoiDungController {

    @Autowired
    private NguoiDungRepository ndRepo;

    @Autowired
    private KhachHangRepository khRepo;

    // ==== HIỂN THỊ TRANG ĐĂNG NHẬP ====
    @GetMapping("/dangnhap")
    public String dangNhapForm() {
        return "dangnhap";
    }

    // ==== XỬ LÝ ĐĂNG NHẬP ====
    @PostMapping("/dangnhap")
    public String dangNhap(@RequestParam String tenDangNhap,
                           @RequestParam String matKhau,
                           HttpSession session,
                           Model model) {

        NguoiDung nd = ndRepo.findByTenDangNhap(tenDangNhap);
        if (nd == null || !nd.getMatKhau().equals(matKhau)) {
            model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            return "dangnhap";
        }

        // ✅ Lưu thông tin vào session
        session.setAttribute("maND", nd.getMaND());
        session.setAttribute("tenDangNhap", nd.getTenDangNhap());
        session.setAttribute("vaiTro", nd.getVaiTro());

        // ✅ Lấy mã khách hàng tương ứng
        KhachHang kh = khRepo.findByNguoiDung_MaND(nd.getMaND());
        if (kh != null) {
            session.setAttribute("maKH", kh.getMaKH());
            session.setAttribute("tenKH", kh.getHoTen());
        }

        return "redirect:/";
    }

    // ==== HIỂN THỊ TRANG ĐĂNG KÝ ====
    @GetMapping("/dangky")
    public String dangKyForm() {
        return "dangky";
    }

    // ==== XỬ LÝ ĐĂNG KÝ ====
    @PostMapping("/dangky")
    public String dangKy(@RequestParam String tenDangNhap,
                         @RequestParam String matKhau,
                         @RequestParam String hoTen,
                         @RequestParam String email,
                         @RequestParam String soDienThoai,
                         HttpSession session,
                         Model model) {

        if (ndRepo.findByTenDangNhap(tenDangNhap) != null) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "dangky";
        }

        String maND = "ND" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        String maKH = "KH" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        // ✅ Tạo người dùng
        NguoiDung nd = new NguoiDung();
        nd.setMaND(maND);
        nd.setTenDangNhap(tenDangNhap);
        nd.setMatKhau(matKhau);
        nd.setVaiTro("KhachHang");
        ndRepo.save(nd);

        // ✅ Tạo khách hàng liên kết với người dùng
        KhachHang kh = new KhachHang();
        kh.setMaKH(maKH);
        kh.setHoTen(hoTen);
        kh.setEmail(email);
        kh.setSoDienThoai(soDienThoai);
        kh.setNguoiDung(nd); // 👈 gán đối tượng NguoiDung vào KhachHang
        khRepo.save(kh);

        model.addAttribute("success", "Đăng ký thành công! Mời bạn đăng nhập.");
        return "dangnhap";
    }

    // ==== ĐĂNG XUẤT ====
    @GetMapping("/dangxuat")
    public String dangXuat(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
