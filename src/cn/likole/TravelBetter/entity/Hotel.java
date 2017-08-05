package cn.likole.TravelBetter.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by likole on 8/5/17.
 */
@Entity
public class Hotel {
    private int hid;
    private String title;
    private String score;
    private String address;

    @Id
    @Column(name = "hid")
    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "score")
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (hid != hotel.hid) return false;
        if (title != null ? !title.equals(hotel.title) : hotel.title != null) return false;
        if (score != null ? !score.equals(hotel.score) : hotel.score != null) return false;
        if (address != null ? !address.equals(hotel.address) : hotel.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hid;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
