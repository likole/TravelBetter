package cn.likole.TravelBetter.dto;

import cn.likole.TravelBetter.entity.FindPicture;

import java.util.Date;
import java.util.Set;

/**
 * Created by likole on 8/3/17.
 */
public class FindDto {
    int fid;
    String title;
    String content;
    String nickname;
    String avatar;
    Date time;
    String timeFormatted;
    int like_num;
    int comment_num;
    boolean liked;
    boolean self;
    Set<FindPicture> findPictures;

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public Set<FindPicture> getFindPictures() {
        return findPictures;
    }

    public void setFindPictures(Set<FindPicture> findPictures) {
        this.findPictures = findPictures;
    }

    public String getTimeFormatted() {
        return timeFormatted;
    }

    public void setTimeFormatted(String timeFormatted) {
        this.timeFormatted = timeFormatted;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }
}
