package model;

import jakarta.persistence.*;

@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @Column(name = "MaND", length = 6)
    private String maND;

    @Column(name = "TenDangNhap", nullable = false, unique = true)
    private String tenDangNhap;

    @Column(name = "MatKhau", nullable = false)
    private String matKhau;

    @Column(name = "VaiTro", nullable = false)
    private String vaiTro = "KhachHang";

    // Getter & Setter
    public String getMaND() { return maND; }
    public void setMaND(String maND) { this.maND = maND; }

    public String getTenDangNhap() { return tenDangNhap; }
    public void setTenDangNhap(String tenDangNhap) { this.tenDangNhap = tenDangNhap; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public String getVaiTro() { return vaiTro; }
    public void setVaiTro(String vaiTro) { this.vaiTro = vaiTro; }
}
