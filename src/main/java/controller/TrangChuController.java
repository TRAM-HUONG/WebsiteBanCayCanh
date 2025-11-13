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
        List<SanPham> sanPhamNoiBat = sanPhamRepo.findAll()
                                                 .stream()
                                                 .limit(3)
                                                 .toList();
        model.addAttribute("sanPhamNoiBat", sanPhamNoiBat);
        return "trangchu/index";
    }
    @GetMapping("/gioithieu")
    public String gioiThieu() {
        return "gioithieu"; 
    }
}
