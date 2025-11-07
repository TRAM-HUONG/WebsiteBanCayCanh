package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "KhachHang")
public class KhachHang {

    @Id
    @Column(name = "MaKH", length = 6)
    private String maKH;

    @Column(name = "HoTen", nullable = false)
    private String hoTen;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "Email")
    private String email;

    @Column(name = "DiaChi")
    private String diaChi;
 // ✅ Constructor mặc định (bắt buộc Hibernate cần)
    public KhachHang() {}

    // ✅ Constructor nhận mã khách hàng (để tạo nhanh đối tượng khi biết mã)
    public KhachHang(String maKH) {
        this.maKH = maKH;
    }


    @ManyToOne
    @JoinColumn(name = "MaND") // khóa ngoại đến NguoiDung
    private NguoiDung nguoiDung;

    // Liên kết ngược lại với GioHang (1 khách có thể có nhiều giỏ hàng)
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
    private List<GioHang> gioHangs;

    // ===== Getter - Setter =====
    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public NguoiDung getNguoiDung() { return nguoiDung; }
    public void setNguoiDung(NguoiDung nguoiDung) { this.nguoiDung = nguoiDung; }

    public List<GioHang> getGioHangs() { return gioHangs; }
    public void setGioHangs(List<GioHang> gioHangs) { this.gioHangs = gioHangs; }
}
