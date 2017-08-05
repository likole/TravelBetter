package cn.likole.TravelBetter.controller;

import cn.likole.TravelBetter.service.HotelService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by likole on 8/5/17.
 */
@Controller
@Scope("prototype")
public class HotelController extends ActionSupport {

    int offset;
    int num;
    String title;
    @Autowired
    HotelService hotelService;
    private Map<String, Object> map = new HashMap<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    private void setMessage(int rsCode) {
        map.put("status", rsCode);
        map.put("message", ErrorController.getMessage(rsCode));
    }

    /**
     * 获取列表
     *
     * @return
     */
    public String getList() {
        setMessage(0);
        map.put("list", hotelService.getList(offset, num));
        return SUCCESS;
    }

    /**
     * 获取详情
     *
     * @return
     */
    public String getDetail() throws IOException {
        String rs = "";
        String temp = null;
        String path = ServletActionContext.getServletContext().getRealPath("/res/hotel/");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path + title + ".json"), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((temp = bufferedReader.readLine()) != null) {
            rs += temp;
        }
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(rs);
        return NONE;
    }
}
