package controller;

import model.SanPham;
import model.HinhAnh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.SanPhamRepository;
import repository.HinhAnhRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sanpham")
public class SanPhamController {

    @Autowired
    private SanPhamRepository sanPhamRepo;

    @Autowired
    private HinhAnhRepository hinhAnhRepo;

    // ✅ Hiển thị danh sách sản phẩm
    @GetMapping
    public String danhSachSanPham(Model model,
                                  @RequestParam(required = false) String keyword,
                                  @RequestParam(required = false) String madm) {
        List<SanPham> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = sanPhamRepo.findByTenSPContainingIgnoreCase(keyword);
        } else if (madm != null && !madm.isEmpty()) {
            list = sanPhamRepo.findByMaDM(madm);
        } else {
            list = sanPhamRepo.findAll();
        }
        model.addAttribute("sanphams", list);
        return "sanpham";
    }

    // ✅ Trang chi tiết sản phẩm
    @GetMapping("/chi-tiet/{maSP}")
    public String chiTietSanPham(@PathVariable("maSP") String maSP, Model model) {
        Optional<SanPham> sp = sanPhamRepo.findById(maSP);
        if (sp.isEmpty()) {
            return "redirect:/sanpham";
        }

        // Lấy ảnh phụ nếu có
        List<HinhAnh> hinhAnhList = hinhAnhRepo.findByMaSP(maSP);

        model.addAttribute("sp", sp.get());
        model.addAttribute("hinhAnhs", hinhAnhList);

        return "chitiet";
    }
}
