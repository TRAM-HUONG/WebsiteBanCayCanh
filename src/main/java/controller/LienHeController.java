package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LienHeController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/lienhe")
    public String hienThiTrangLienHe() {
        return "lienhe";
    }

    @PostMapping("/gui-lien-he")
    public String guiLienHe(
            @RequestParam String hoTen,
            @RequestParam String email,
            @RequestParam String soDienThoai,
            @RequestParam String noiDung,
            Model model) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("yourcompanyemail@gmail.com"); // 📩 Email nhận liên hệ
            message.setSubject("📬 Liên hệ mới từ: " + hoTen);
            message.setText(
                    "Họ tên: " + hoTen +
                    "\nEmail: " + email +
                    "\nSố điện thoại: " + soDienThoai +
                    "\n\nNội dung liên hệ:\n" + noiDung
            );

            mailSender.send(message);
            model.addAttribute("success", "✅ Gửi liên hệ thành công! Chúng tôi sẽ phản hồi sớm nhất.");
        } catch (Exception e) {
            model.addAttribute("error", "❌ Gửi thất bại! Vui lòng thử lại sau.");
        }

        return "lienhe";
    }
}
