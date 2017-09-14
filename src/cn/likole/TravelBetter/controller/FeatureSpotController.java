package cn.likole.TravelBetter.controller;

import cn.likole.TravelBetter.service.FeatureSpotService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by likole on 8/6/17.
 */
@Controller
@Scope("prototype")
public class FeatureSpotController extends ActionSupport {

    int num;
    int offset;
    @Autowired
    FeatureSpotService featureSpotService;
    Map<String, Object> map = new HashMap<>();

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    private void setMessage(int rsCode) {
        map.put("status", rsCode);
        map.put("message", ErrorController.getMessage(rsCode));
    }

    public String getList() {
        setMessage(0);
        map.put("list", featureSpotService.getList(offset, num));
        return SUCCESS;
    }
}
