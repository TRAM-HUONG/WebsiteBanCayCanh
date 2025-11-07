package model;

import jakarta.persistence.*;

@Entity
@Table(name = "HinhAnh")   // ⚠️ phải trùng với tên bảng trong SQL
public class HinhAnh {

    @Id
    @Column(name = "MaHinh")
    private String maHinh;

    @Column(name = "TenHinh")
    private String tenHinh;

    @Column(name = "MaSP")
    private String maSP;

    // Getter - Setter
    public String getMaHinh() {
        return maHinh;
    }

    public void setMaHinh(String maHinh) {
        this.maHinh = maHinh;
    }

    public String getTenHinh() {
        return tenHinh;
    }

    public void setTenHinh(String tenHinh) {
        this.tenHinh = tenHinh;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }
}
