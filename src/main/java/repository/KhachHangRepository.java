package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.KhachHang;

public interface KhachHangRepository extends JpaRepository<KhachHang, String> {

    // ✅ Tìm khách hàng theo mã người dùng (MaND trong bảng NguoiDung)
    KhachHang findByNguoiDung_MaND(String maND);
}
