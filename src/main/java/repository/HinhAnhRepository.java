package repository;

import model.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HinhAnhRepository extends JpaRepository<HinhAnh, String> {
    List<HinhAnh> findByMaSP(String maSP);
}
