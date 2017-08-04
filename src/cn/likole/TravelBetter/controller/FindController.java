package cn.likole.TravelBetter.controller;

import cn.likole.TravelBetter.dto.FindDto;
import cn.likole.TravelBetter.entity.Find;
import cn.likole.TravelBetter.service.FindService;
import cn.likole.TravelBetter.util.MD5Util;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by likole on 8/3/17.
 */
@Controller
public class FindController extends ActionSupport implements ModelDriven<Find> {
    int offset;
    int num;
    String token;
    Find find = new Find();
    File file;
    String fileFileName;
    String fileContentType;
    int pid;
    int cid;
    @Autowired
    FindService findService;
    private Map<String, Object> map = new HashMap<>();

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    @Override
    public Find getModel() {
        return find;
    }

    private void setMessage(int rsCode) {
        map.put("status", rsCode);
        map.put("message", ErrorController.getMessage(rsCode));
    }


    /**
     * 获取发现列表
     *
     * @return
     */
    public String getList() {
        setMessage(0);
        List<FindDto> list = findService.getList(offset, num, token);
        map.put("data", list);
        map.put("next", offset + list.size());
        return SUCCESS;
    }

    /**
     * 根据id获取发现
     *
     * @return
     */
    public String getByFid() {
        setMessage(0);
        map.put("find", findService.getByFid(find.getFid(), token));
        return SUCCESS;
    }

    /**
     * 获取自己的发现
     *
     * @return
     */
    public String getSelf() {
        setMessage(0);
        map.put("data", findService.getByToken(token));
        return SUCCESS;
    }

    /**
     * 喜欢
     *
     * @return
     */
    public String like() {
        setMessage(findService.like(token, find.getFid()));
        return SUCCESS;
    }

    /**
     * 添加发现
     *
     * @return
     */
    public String addFind() {
        setMessage(0);
        map.put("fid", findService.addFind(find.getTitle(), find.getContent(), token));
        return SUCCESS;
    }


    /**
     * 添加图片
     *
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String addPicture() throws IOException, NoSuchAlgorithmException {
        if (file.length() > 1000000) {
            setMessage(108);
            return SUCCESS;
        }
        String path = ServletActionContext.getServletContext().getRealPath("/picture");
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
        setMessage(findService.addPicture(token, find.getFid(), fileName));
        return SUCCESS;
    }

    /**
     * 删除图片
     *
     * @return
     */
    public String delPicture() {
        setMessage(findService.delPicture(token, pid));
        return SUCCESS;
    }

    /**
     * 获取评论
     *
     * @return
     */
    public String getComments() {
        setMessage(0);
        map.put("comments", findService.getComments(find.getFid(), token));
        return SUCCESS;
    }

    /**
     * 添加评论
     *
     * @return
     */
    public String addComment() {
        setMessage(findService.addComment(token, find.getFid(), find.getContent()));
        return SUCCESS;
    }

    /**
     * 删除评论
     *
     * @return
     */
    public String delComment() {
        setMessage(findService.delComment(token, cid));
        return SUCCESS;
    }
}
