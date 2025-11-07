package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ThanhToan")
public class ThanhToan {

    @Id
    @Column(name = "MaTT", length = 6)
    private String maTT;

    @OneToOne
    @JoinColumn(name = "MaDH")
    private DonHang donHang;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NgayTT")
    private Date ngayTT;

    @Column(name = "PhuongThuc", columnDefinition = "NVARCHAR(50)")
    private String phuongThuc;

    @Column(name = "TrangThai", columnDefinition = "NVARCHAR(50)")
    private String trangThai;

    // Getter & Setter
    public String getMaTT() { return maTT; }
    public void setMaTT(String maTT) { this.maTT = maTT; }

    public DonHang getDonHang() { return donHang; }
    public void setDonHang(DonHang donHang) { this.donHang = donHang; }

    public Date getNgayTT() { return ngayTT; }
    public void setNgayTT(Date ngayTT) { this.ngayTT = ngayTT; }

    public String getPhuongThuc() { return phuongThuc; }
    public void setPhuongThuc(String phuongThuc) { this.phuongThuc = phuongThuc; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
