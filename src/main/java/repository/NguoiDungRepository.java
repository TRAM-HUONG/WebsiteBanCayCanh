package repository;


import org.springframework.data.jpa.repository.JpaRepository;

import model.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {
    NguoiDung findByTenDangNhap(String tenDangNhap);
}
