package model;

import java.io.Serializable;

public class TheLoai implements Serializable {
    String tenTheLoai;
    int hinhTheLoai;

    public TheLoai(String tenTheLoai, int hinhTheLoai) {
        this.tenTheLoai = tenTheLoai;
        this.hinhTheLoai = hinhTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public int getHinhTheLoai() {
        return hinhTheLoai;
    }

    public void setHinhTheLoai(int hinhTheLoai) {
        this.hinhTheLoai = hinhTheLoai;
    }
}
