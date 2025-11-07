package model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "GioHang")
public class GioHang {

    @Id
    @Column(name = "MaGH", length = 6)
    private String maGH;

    @ManyToOne
    @JoinColumn(name = "MaKH")
    private KhachHang khachHang;

    @Column(name = "NgayTao")
    private Date ngayTao = new Date();

    @OneToMany(mappedBy = "gioHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GioHangChiTiet> chiTietList = new ArrayList<>();

    // ===== GETTER / SETTER =====
    public String getMaGH() {
        return maGH;
    }

    public void setMaGH(String maGH) {
        this.maGH = maGH;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public List<GioHangChiTiet> getChiTietList() {
        return chiTietList;
    }

    public void setChiTietList(List<GioHangChiTiet> chiTietList) {
        this.chiTietList = chiTietList;
    }
 // Liên kết đến bảng GioHangChiTiet
    @OneToMany(mappedBy = "gioHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GioHangChiTiet> chiTietGioHang;

    public List<GioHangChiTiet> getChiTietGioHang() {
        return chiTietGioHang;
    }

    public void setChiTietGioHang(List<GioHangChiTiet> chiTietGioHang) {
        this.chiTietGioHang = chiTietGioHang;
    }

}
