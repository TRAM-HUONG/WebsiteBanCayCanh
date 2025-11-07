package model;

import jakarta.persistence.*;

@Entity
@Table(name = "GioHangChiTiet")
public class GioHangChiTiet {

    // 🔹 Khóa chính ghép
    @EmbeddedId
    private GioHangChiTietId id;

    // 🔹 Quan hệ đến bảng GioHang
    @ManyToOne
    @MapsId("maGH") // nối khóa chính MaGH trong ID với cột MaGH ở đây
    @JoinColumn(name = "MaGH")
    private GioHang gioHang;

    // 🔹 Quan hệ đến bảng SanPham
    @ManyToOne
    @JoinColumn(name = "MaSP", insertable = false, updatable = false)
    private SanPham sanPham;

    @Column(name = "SoLuong")
    private int soLuong;

    public GioHangChiTiet() {}

    // --- Getter & Setter ---
    public GioHangChiTietId getId() {
        return id;
    }

    public void setId(GioHangChiTietId id) {
        this.id = id;
    }

    public GioHang getGioHang() {
        return gioHang;
    }

    public void setGioHang(GioHang gioHang) {
        this.gioHang = gioHang;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
