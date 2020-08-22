package model;

import java.io.Serializable;

public class Album implements Serializable {
    String tenAlbum, tenCaSiAlbum;
    int hinhAlbum;

    public Album(String tenAlbum, String tenCaSiAlbum, int hinhAlbum) {
        this.tenAlbum = tenAlbum;
        this.tenCaSiAlbum = tenCaSiAlbum;
        this.hinhAlbum = hinhAlbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        this.tenAlbum = tenAlbum;
    }

    public String getTenCaSiAlbum() {
        return tenCaSiAlbum;
    }

    public void setTenCaSiAlbum(String tenCaSiAlbum) {
        this.tenCaSiAlbum = tenCaSiAlbum;
    }

    public int getHinhAlbum() {
        return hinhAlbum;
    }

    public void setHinhAlbum(int hinhAlbum) {
        this.hinhAlbum = hinhAlbum;
    }
}
