package cn.likole.TravelBetter.service;

import cn.likole.TravelBetter.dao.UserDao;
import cn.likole.TravelBetter.dto.UserDto;
import cn.likole.TravelBetter.entity.User;
import cn.likole.TravelBetter.util.MD5Util;
import cn.likole.TravelBetter.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by likole on 8/2/17.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserDao userDao;

    /**
     * 注册用户
     *
     * @param username
     * @param password
     * @return
     */
    public int register(String username, String password) {
        if (username == null || "".equals(username)) return 101;
        if (username.contains("@")) return 106;
//        if (password.length() != 32) return 102;
        if (userDao.exist(username)) return 103;
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname("用户" + username);
        user.setAvatar("default.png");
        user.setToken(UUID.randomUUID().toString());
        userDao.save(user);
        return 0;
    }


    /**
     * 登陆
     *
     * @param usernameOrEmail
     * @param password
     * @return
     */
    public int login(String usernameOrEmail, String password) {
        if (usernameOrEmail == null || "".equals(usernameOrEmail)) return 101;
//        if (password.length() != 32) return 102;
        User user = userDao.getByUsernameOrEmail(usernameOrEmail);
        if (user == null) return 104;
        if (!user.getPassword().equals(password)) return 105;
        return 0;
    }


    /**
     * 获取token值
     *
     * @param usernameOrEmail
     * @return
     */
    public String getToken(String usernameOrEmail) {
        return userDao.getByUsernameOrEmail(usernameOrEmail).getToken();
    }


    /**
     * 设置头像
     *
     * @param token
     * @param avatar
     */
    public int setAvatar(String token, String avatar) {
        userDao.getByToken(token).setAvatar(avatar);
        return 0;
    }


    /**
     * 更改密码
     *
     * @param token
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public int changePassword(String token, String oldPassword, String newPassword) {
        if (newPassword.length() != 32) return 102;
        User user = userDao.getByToken(token);
        if (user == null) return 104;
        if (!user.getPassword().equals(oldPassword)) return 107;
        user.setPassword(newPassword);
        return 0;
    }


    /**
     * 更改个人信息
     *
     * @param token
     * @param gender
     * @param nickname
     * @param birthday
     * @param address
     * @return
     * @throws ParseException
     */
    public int changeInfo(String token, int gender, String nickname, Date birthday, String address) throws ParseException {
        User user = userDao.getByToken(token);
        if (user == null) return 104;
        user.setGender(gender);
        user.setNickname(nickname);
        //user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        user.setBirthday(birthday);
        user.setAddress(address);
        return 0;
    }


    /**
     * 更改签名
     *
     * @param token
     * @param sign
     * @return
     */
    public int changeSign(String token, String sign) {
        User user = userDao.getByToken(token);
        if (user == null) return 104;
        user.setSign(sign);
        return 0;
    }


    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    public UserDto getInfo(String token) {
        User user = userDao.getByToken(token);
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setGender(user.getGender());
        userDto.setNickname(user.getNickname());
        userDto.setSign(user.getSign());
        userDto.setAvatar(user.getAvatar());
        userDto.setEmail(user.getEmail());
        userDto.setBirthday(user.getBirthday());
        userDto.setAddress(user.getAddress());

        return userDto;
    }


    /**
     * 发送验证邮件
     *
     * @param token
     * @param email
     * @return
     * @throws Exception
     */
    public int sendEmail(String token, String email) throws Exception {
        if (email == null || "".equals(email)) return 109;
        if (userDao.exist(email)) return 110;
        User user = userDao.getByToken(token);
        if (user == null) return 104;

        String content = "?token=" + token + "&email=" + email + "&ak=" + MD5Util.encode(token + email + "verify");

        MailUtil.sendMessage(content, email);

        return 0;
    }


    public int bindEmail(String token, String email, String ak) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (email == null || "".equals(email)) return 109;
        if (userDao.exist(email)) return 110;
        User user = userDao.getByToken(token);
        if (user == null) return 104;

        if (!MD5Util.encode(token + email + "verify").equals(ak)) return 111;

        user.setEmail(email);
        return 0;
    }
}
