package model;

import java.io.Serializable;

public class ChuDe implements Serializable {
    String tenChuDe;
    int hinhChuDe;

    public ChuDe(String tenChuDe, int hinhChuDe) {
        this.tenChuDe = tenChuDe;
        this.hinhChuDe = hinhChuDe;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public int getHinhChuDe() {
        return hinhChuDe;
    }

    public void setHinhChuDe(int hinhChuDe) {
        this.hinhChuDe = hinhChuDe;
    }
}
