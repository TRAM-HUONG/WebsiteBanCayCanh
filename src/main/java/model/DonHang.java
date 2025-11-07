package model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "DonHang")
public class DonHang {

    @Id
    @Column(name = "MaDH")
    private String maDH;

    @ManyToOne
    @JoinColumn(name = "MaKH")
    private KhachHang khachHang;

    @Column(name = "NgayDat")
    private Date ngayDat;

    @Column(name = "DiaChiGiao")
    private String diaChiGiao;

    @Column(name = "PhuongThuc")
    private String phuongThuc;

    @Column(name = "TrangThai")
    private String trangThai;

    // Quan hệ với chi tiết đơn hàng
    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    private List<ChiTietDonHang> chiTietDonHangs;

    // GETTER & SETTER
    public String getMaDH() { return maDH; }
    public void setMaDH(String maDH) { this.maDH = maDH; }

    public KhachHang getKhachHang() { return khachHang; }
    public void setKhachHang(KhachHang khachHang) { this.khachHang = khachHang; }

    public Date getNgayDat() { return ngayDat; }
    public void setNgayDat(Date ngayDat) { this.ngayDat = ngayDat; }

    public String getDiaChiGiao() { return diaChiGiao; }
    public void setDiaChiGiao(String diaChiGiao) { this.diaChiGiao = diaChiGiao; }

    public String getPhuongThuc() { return phuongThuc; }
    public void setPhuongThuc(String phuongThuc) { this.phuongThuc = phuongThuc; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public List<ChiTietDonHang> getChiTietDonHangs() { return chiTietDonHangs; }
    public void setChiTietDonHangs(List<ChiTietDonHang> chiTietDonHangs) { this.chiTietDonHangs = chiTietDonHangs; }
}
