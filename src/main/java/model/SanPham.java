package model;

import jakarta.persistence.*;

@Entity
@Table(name = "SanPham")
public class SanPham {

    @Id
    @Column(name = "MaSP")
    private String maSP;

    @Column(name = "TenSP")
    private String tenSP;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "DonGia")
    private double donGia;

    @Column(name = "SoLuongTon")
    private int soLuongTon;

    @Column(name = "AnhChinh")
    private String anhChinh;

    @Column(name = "MaDM")
    private String maDM;

    // Getter - Setter
    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }

    public String getAnhChinh() { return anhChinh; }
    public void setAnhChinh(String anhChinh) { this.anhChinh = anhChinh; }

    public String getMaDM() { return maDM; }
    public void setMaDM(String maDM) { this.maDM = maDM; }
}
