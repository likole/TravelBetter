package cn.likole.TravelBetter.controller;

import cn.likole.TravelBetter.service.OtherService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by likole on 9/18/17.
 */
@Controller
@Scope("prototype")
public class FoodController extends ActionSupport {

    int category;
    Map<String, Object> map = new HashMap<>();
    @Autowired
    OtherService otherService;

    public Map<String, Object> getMap() {
        return map;
    }

    public int getCategory() {

        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getByCategory() throws Exception {
        map.put("status", 0);
        map.put("data", otherService.getByCategory(category));
        return SUCCESS;
    }
}
