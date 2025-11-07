package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.SanPham;
import repository.SanPhamRepository;

@RestController
public class SitemapController {

    @Autowired
    private SanPhamRepository sanPhamRepo;

    @GetMapping(value = "/sitemap.xml", produces = "application/xml")
    public String generateFullSitemap() {

        String today = LocalDate.now().toString();

        // 1️⃣ Các trang chính (tĩnh)
        String staticUrls =
                "  <url>\n" +
                "    <loc>https://websitecaycanh.onrender.com/</loc>\n" +
                "    <lastmod>" + today + "</lastmod>\n" +
                "    <priority>1.0</priority>\n" +
                "  </url>\n" +
                "  <url>\n" +
                "    <loc>https://websitecaycanh.onrender.com/sanpham</loc>\n" +
                "    <lastmod>" + today + "</lastmod>\n" +
                "    <priority>0.9</priority>\n" +
                "  </url>\n" +
                "  <url>\n" +
                "    <loc>https://websitecaycanh.onrender.com/giohang</loc>\n" +
                "    <priority>0.8</priority>\n" +
                "  </url>\n" +
                "  <url>\n" +
                "    <loc>https://websitecaycanh.onrender.com/donhang</loc>\n" +
                "    <priority>0.8</priority>\n" +
                "  </url>\n" +
                "  <url>\n" +
                "    <loc>https://websitecaycanh.onrender.com/lienhe</loc>\n" +
                "    <priority>0.6</priority>\n" +
                "  </url>\n" +
                "  <url>\n" +
                "    <loc>https://websitecaycanh.onrender.com/dangnhap</loc>\n" +
                "    <priority>0.5</priority>\n" +
                "  </url>\n" +
                "  <url>\n" +
                "    <loc>https://websitecaycanh.onrender.com/dangky</loc>\n" +
                "    <priority>0.5</priority>\n" +
                "  </url>\n";

        // 2️⃣ Các trang sản phẩm (động)
        List<SanPham> sanphams = sanPhamRepo.findAll();
        String productUrls = sanphams.stream()
            .map(sp -> "  <url>\n" +
                       "    <loc>https://websitecaycanh.onrender.com/sanpham/" + sp.getMaSP().trim() + "</loc>\n" +
                       "    <lastmod>" + today + "</lastmod>\n" +
                       "    <priority>0.8</priority>\n" +
                       "  </url>")
            .collect(Collectors.joining("\n"));

        // 3️⃣ Ghép lại thành file XML hoàn chỉnh
        String xml =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n" +
            staticUrls + productUrls + "\n</urlset>";

        return xml;
    }
}
