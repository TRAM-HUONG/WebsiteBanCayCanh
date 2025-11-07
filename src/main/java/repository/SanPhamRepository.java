package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.SanPham;
import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, String> {
    List<SanPham> findByMaDM(String maDM);
    List<SanPham> findByTenSPContainingIgnoreCase(String keyword);
}
