package cn.likole.TravelBetter.service;

import cn.likole.TravelBetter.dao.FindLikeDao;
import cn.likole.TravelBetter.dao.FindDao;
import cn.likole.TravelBetter.dao.UserDao;
import cn.likole.TravelBetter.dto.FindDto;
import cn.likole.TravelBetter.entity.Find;
import cn.likole.TravelBetter.entity.FindLike;
import cn.likole.TravelBetter.entity.FindPicture;
import cn.likole.TravelBetter.entity.User;
import cn.likole.TravelBetter.util.TimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by likole on 8/3/17.
 */
@Service
@Transactional
public class FindService {


    @Autowired
    FindDao findDao;
    @Autowired
    UserDao userDao;
    @Autowired
    FindLikeDao findLikeDao;

    /**
     * 将 Find 转化为 FindDto
     *
     * @param find
     * @return
     */
    private FindDto find2findDto(Find find) {
        FindDto findDto = new FindDto();
        findDto.setFid(find.getFid());
        findDto.setTitle(find.getTitle());
        findDto.setContent(find.getContent());
        findDto.setNickname(find.getUser().getNickname());
        findDto.setAvatar(find.getUser().getAvatar());
        findDto.setTime(find.getTime());
        findDto.setTimeFormatted(TimeFormatUtil.formatTime(find.getTime()));
        findDto.setLike_num(find.getLikeNum());
        findDto.setFindPictures(find.getFindPictures());
        findDto.setLiked(false);
        findDto.setSelf(false);
        return findDto;
    }

    /**
     * 将 Find 转化为 FindDto
     *
     * @param find
     * @return
     */
    private FindDto find2findDto(Find find, String token) {
        User user = userDao.getByToken(token);

        FindDto findDto = new FindDto();
        findDto.setFid(find.getFid());
        findDto.setTitle(find.getTitle());
        findDto.setContent(find.getContent());
        findDto.setNickname(find.getUser().getNickname());
        findDto.setAvatar(find.getUser().getAvatar());
        findDto.setTime(find.getTime());
        findDto.setTimeFormatted(TimeFormatUtil.formatTime(find.getTime()));
        findDto.setLike_num(find.getLikeNum());
        findDto.setFindPictures(find.getFindPictures());
        findDto.setLiked(findLikeDao.exist(findDto.getFid(), user.getUid()));
        findDto.setSelf(find.getUser().getUid() == user.getUid());
        return findDto;
    }

    /**
     * 获取发现列表
     *
     * @param offset
     * @param num
     * @return
     */
    public List<FindDto> getList(int offset, int num, String token) {
        List<Find> finds = findDao.getList(offset, num);
        List<FindDto> findDtos = new ArrayList<>();
        for (Find find : finds) {
            if (token != null && !"".equals(token))
                findDtos.add(find2findDto(find, token));
            else
                findDtos.add(find2findDto(find));
        }
        return findDtos;
    }

    /**
     * 根据id获取发现
     *
     * @param fid
     * @return
     */
    public FindDto getByFid(int fid) {
        return find2findDto(findDao.getByFid(fid));
    }


    /**
     * 根据用户获取发现
     *
     * @param token
     * @return
     */
    public List<FindDto> getByToken(String token) {
        User user = userDao.getByToken(token);
        if (user == null) return null;
        List<Find> finds = findDao.getByUser(user);
        List<FindDto> findDtos = new ArrayList<>();
        for (Find find : finds) {
            findDtos.add(find2findDto(find));
        }
        return findDtos;
    }

    /**
     * 喜欢
     *
     * @param token
     * @param fid
     * @return
     */
    public int like(String token, int fid) {
        User user = userDao.getByToken(token);
        if (user == null) return 104;
        Find find = findDao.getByFid(fid);
        if (find == null) return 113;
        if (findLikeDao.exist(fid, user.getUid())) return 112;
        FindLike findLike = new FindLike();
        findLike.setFid(fid);
        findLike.setUid(user.getUid());
        findLikeDao.save(findLike);
        find.setLikeNum(find.getLikeNum() + 1);
        return 0;
    }

    /**
     * 添加发现
     *
     * @param title
     * @param content
     * @param token
     * @return
     */
    public int addFind(String title, String content, String token) {
        Find find = new Find();
        find.setTitle(title);
        find.setUser(userDao.getByToken(token));
        find.setTime(new Date());

        findDao.save(find);
        return find.getFid();
    }

    /**
     * 添加图片
     *
     * @param token
     * @param fid
     * @param path
     * @return
     */
    public int addPicture(String token, int fid, String path) {
        Find find = findDao.getByFid(fid);
        if (find == null) return 113;
        if (!find.getUser().getToken().equals(token)) return 114;
        FindPicture findPicture = new FindPicture();
        findPicture.setPath(path);
        find.getFindPictures().add(findPicture);
        return 0;
    }

}
