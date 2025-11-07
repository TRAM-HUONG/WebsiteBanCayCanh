package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.DanhMuc;

public interface DanhMucRepository extends JpaRepository<DanhMuc, String> {
}
