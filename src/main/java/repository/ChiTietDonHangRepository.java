package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.ChiTietDonHang;
import model.ChiTietDonHangId;

public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, ChiTietDonHangId> {}
