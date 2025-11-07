package controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.DonHang;
import model.GioHang;
import model.KhachHang;
import repository.DonHangRepository;
import repository.GioHangRepository;

@Controller
@RequestMapping("/donhang")
public class DonHangController {

	@Autowired
	private DonHangRepository donHangRepo;

	@Autowired
	private GioHangRepository gioHangRepo;

	@GetMapping
	public String hienThiDonHang(@RequestParam("maKH") String maKH, Model model) {
		GioHang gioHang = gioHangRepo.findByKhachHang_MaKH(maKH);
		model.addAttribute("gioHang", gioHang);
		return "donhang"; // gọi file donhang.html trong /templates
	}

	// Xử lý thanh toán
	@PostMapping("/thanhtoan")
	public String thanhToan(@RequestParam String maKH, RedirectAttributes ra) {
		DonHang dh = new DonHang();
		// đặt mã đơn hàng tự động (5 ký tự ngẫu nhiên)
		dh.setMaDH("DH" + UUID.randomUUID().toString().substring(0, 5).toUpperCase());

		dh.setNgayDat(new Date());

		dh.setPhuongThuc("Tiền mặt khi nhận hàng");
		dh.setDiaChiGiao("12 Nguyễn Huệ, TP.HCM");
		dh.setTrangThai("Đã đặt hàng");
		dh.setKhachHang(new KhachHang(maKH));
		donHangRepo.save(dh);

		ra.addFlashAttribute("maDon", dh.getMaDH());
		ra.addFlashAttribute("diaChiGiao", dh.getDiaChiGiao());
		ra.addFlashAttribute("phuongThuc", dh.getPhuongThuc());
		return "redirect:/giohang/thanhtoan";
	}
}
