package repository;

import model.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GioHangRepository extends JpaRepository<GioHang, String> {
    GioHang findByKhachHang_MaKH(String maKH);
}
