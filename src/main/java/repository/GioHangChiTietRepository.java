package repository;

import model.GioHangChiTiet;
import model.GioHangChiTietId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, GioHangChiTietId> {
    List<GioHangChiTiet> findByGioHang_MaGH(String maGH);
    Optional<GioHangChiTiet> findByGioHang_MaGHAndSanPham_MaSP(String maGH, String maSP);
}
