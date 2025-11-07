package model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ChiTietDonHang")
public class ChiTietDonHang implements Serializable {

    @EmbeddedId
    private ChiTietDonHangId id;

    @ManyToOne
    @MapsId("maDH")
    @JoinColumn(name = "MaDH")
    private DonHang donHang;

    @ManyToOne
    @MapsId("maSP")
    @JoinColumn(name = "MaSP")
    private SanPham sanPham;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "DonGia")
    private double donGia;

    // Getter & Setter
    public ChiTietDonHangId getId() { return id; }
    public void setId(ChiTietDonHangId id) { this.id = id; }

    public DonHang getDonHang() { return donHang; }
    public void setDonHang(DonHang donHang) { this.donHang = donHang; }

    public SanPham getSanPham() { return sanPham; }
    public void setSanPham(SanPham sanPham) { this.sanPham = sanPham; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
}
