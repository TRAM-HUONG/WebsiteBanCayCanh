package model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GioHangChiTietId implements Serializable {

    @Column(name = "MaGH")
    private String maGH;

    @Column(name = "MaSP")
    private String maSP;

    public GioHangChiTietId() {}

    public GioHangChiTietId(String maGH, String maSP) {
        this.maGH = maGH;
        this.maSP = maSP;
    }

    public String getMaGH() {
        return maGH;
    }

    public void setMaGH(String maGH) {
        this.maGH = maGH;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GioHangChiTietId)) return false;
        GioHangChiTietId that = (GioHangChiTietId) o;
        return maGH.equals(that.maGH) && maSP.equals(that.maSP);
    }

    @Override
    public int hashCode() {
        return maGH.hashCode() + maSP.hashCode();
    }
}
