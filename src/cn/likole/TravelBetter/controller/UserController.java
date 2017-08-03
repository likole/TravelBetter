package cn.likole.TravelBetter.controller;

import cn.likole.TravelBetter.dao.UserDao;
import cn.likole.TravelBetter.entity.User;
import cn.likole.TravelBetter.service.UserService;
import cn.likole.TravelBetter.util.MD5Util;
import cn.likole.TravelBetter.util.MailUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by likole on 8/2/17.
 */
@Controller
public class UserController extends ActionSupport implements ModelDriven<User> {
    Map<String, Object> map = new HashMap<>();
    User user = new User();
    String usernameOrEmail;
    String oldPassword;
    File file;
    String fileFileName;
    String fileContentType;
    String ak;

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Autowired
    UserService userService;

    @Override
    public User getModel() {
        return user;
    }

    private void setMessage(int rsCode) {
        map.put("status", rsCode);
        map.put("message", ErrorController.getMessage(rsCode));
    }

    /**
     * 注册用户
     *
     * @return
     */
    public String register() {
        int rsCode = userService.register(user.getUsername(), user.getPassword());
        setMessage(rsCode);
        if (rsCode == 0) map.put("token", userService.getToken(user.getUsername()));
        return SUCCESS;
    }

    /**
     * 登陆
     *
     * @return
     */
    public String login() {
        setMessage(userService.login(usernameOrEmail, user.getPassword()));
        map.put("token", userService.getToken(usernameOrEmail));
        return SUCCESS;
    }

    /**
     * 设置头像
     *
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String setAvatar() throws IOException, NoSuchAlgorithmException {
        if (file.length() > 1000000) {
            setMessage(108);
            return SUCCESS;
        }
        String path = ServletActionContext.getServletContext().getRealPath("/avatar");
        String fileName = MD5Util.encode(UUID.randomUUID().toString()) + fileFileName.substring(fileFileName.lastIndexOf('.'));
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new FileOutputStream(new File(path, fileName));

        byte[] bytes = new byte[1024];

        int len = 0;

        while (-1 != (len = inputStream.read(bytes, 0, bytes.length))) {
            outputStream.write(bytes);
        }

        inputStream.close();
        outputStream.close();
        map.put("avatar", fileName);
        setMessage(userService.setAvatar(user.getToken(), fileName));
        return SUCCESS;
    }

    /**
     * 更改密码
     *
     * @return
     */
    public String changePassword() {
        setMessage(userService.changePassword(user.getToken(), oldPassword, user.getPassword()));
        return SUCCESS;
    }

    /**
     * 更改个人信息
     *
     * @return
     * @throws ParseException
     */
    public String changeInfo() throws ParseException {
        setMessage(userService.changeInfo(user.getToken(), user.getGender(), user.getNickname(), user.getBirthday(), user.getAddress()));
        return SUCCESS;
    }

    /**
     * 更新签名
     *
     * @return
     */
    public String changeSign() {
        setMessage(userService.changeSign(user.getToken(), user.getSign()));
        return SUCCESS;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public String getInfo() {
        setMessage(0);
        map.put("info", userService.getInfo(user.getToken()));
        return SUCCESS;
    }

    /**
     * 发送验证邮件
     *
     * @return
     * @throws Exception
     */
    public String sendEmail() throws Exception {
        setMessage(userService.sendEmail(user.getToken(), user.getEmail()));
        return SUCCESS;
    }

    /**
     * 绑定邮箱
     *
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String bindEmail() throws IOException, NoSuchAlgorithmException {
        userService.bindEmail(user.getToken(), user.getEmail(), ak);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("绑定成功");
        return NONE;
    }
}
