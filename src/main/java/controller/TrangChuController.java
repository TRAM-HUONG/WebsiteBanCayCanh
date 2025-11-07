package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

import model.SanPham;
import repository.SanPhamRepository;

@Controller
public class TrangChuController {

    @Autowired
    private SanPhamRepository sanPhamRepo;

    @GetMapping("/")
    public String trangChu(Model model) {
        // Lấy 4 sản phẩm đầu tiên (hoặc ít hơn nếu database nhỏ)
        List<SanPham> sanPhamNoiBat = sanPhamRepo.findAll()
                                                 .stream()
                                                 .limit(3)
                                                 .toList();
        model.addAttribute("sanPhamNoiBat", sanPhamNoiBat);
        return "index"; // templates/index.html
    }
    @GetMapping("/gioithieu")
    public String gioiThieu() {
        return "gioithieu"; // Trả về file gioithieu.html trong /templates
    }

}
