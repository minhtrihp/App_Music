package model;

import java.io.Serializable;

public class Playlist implements Serializable {
    private String name;
    private int backGroundImage;
    private int icon;

    public Playlist(String name, int backGroundImage, int icon) {
        this.name = name;
        this.backGroundImage = backGroundImage;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBackGroundImage() {
        return backGroundImage;
    }

    public void setBackGroundImage(int backGroundImage) {
        this.backGroundImage = backGroundImage;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
