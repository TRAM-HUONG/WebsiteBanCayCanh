package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.ThanhToan;

public interface ThanhToanRepository extends JpaRepository<ThanhToan, String> {}
