package cn.likole.TravelBetter.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by likole on 9/18/17.
 */
@Entity
public class Other {
    private String name;
    private String address;
    private String phone;
    private String score;
    private int a;
    private double b;
    private int c;
    private int oid;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    @Column(name = "a")
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Basic
    @Column(name = "b")
    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Basic
    @Column(name = "c")
    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    @Id
    @Column(name = "oid")
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Other other = (Other) o;

        if (a != other.a) return false;
        if (Double.compare(other.b, b) != 0) return false;
        if (c != other.c) return false;
        if (oid != other.oid) return false;
        if (name != null ? !name.equals(other.name) : other.name != null) return false;
        if (address != null ? !address.equals(other.address) : other.address != null) return false;
        if (phone != null ? !phone.equals(other.phone) : other.phone != null) return false;
        if (score != null ? !score.equals(other.score) : other.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + a;
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + c;
        result = 31 * result + oid;
        return result;
    }
}
