package cn.likole.TravelBetter.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * Created by likole on 8/3/17.
 */
@Entity
public class Find {
    private int fid;
    private String title;
    private String content;
    private Date time;
    private int likeNum;
    private Set<FindPicture> findPictures;
    private User user;
    private int uid;

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "fid")
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "like_num")
    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="fid")
    public Set<FindPicture> getFindPictures() {
        return findPictures;
    }

    public void setFindPictures(Set<FindPicture> findPictures) {
        this.findPictures = findPictures;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Find find = (Find) o;

        if (fid != find.fid) return false;
        if (likeNum != find.likeNum) return false;
        if (title != null ? !title.equals(find.title) : find.title != null) return false;
        if (content != null ? !content.equals(find.content) : find.content != null) return false;
        if (time != null ? !time.equals(find.time) : find.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fid;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + likeNum;
        return result;
    }

    @Basic
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
