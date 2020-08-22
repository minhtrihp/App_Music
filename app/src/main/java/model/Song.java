package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    String songName;
    int picture;
    String singer;
    int songLink;

    public Song(String songName, int picture, String singer, int songLink) {
        this.songName = songName;
        this.picture = picture;
        this.singer = singer;
        this.songLink = songLink;
    }

    protected Song(Parcel in) {
        songName = in.readString();
        picture = in.readInt();
        singer = in.readString();
        songLink = in.readInt();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getSongLink() {
        return songLink;
    }

    public void setSongLink(int songLink) {
        this.songLink = songLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songName);
        dest.writeInt(picture);
        dest.writeString(singer);
        dest.writeInt(songLink);
    }
}
