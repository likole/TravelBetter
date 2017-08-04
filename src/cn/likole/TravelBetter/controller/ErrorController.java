package cn.likole.TravelBetter.controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by likole on 8/2/17.
 */
@Controller
public class ErrorController extends ActionSupport {
    public static Map<Integer, String> errorInfo;

    static {
        errorInfo = new HashMap<>();
        errorInfo.put(101, "用户名或邮箱不能为空");
        errorInfo.put(102, "密码不符合要求");
        errorInfo.put(103, "用户名或邮箱已存在");
        errorInfo.put(104, "用户不存在");
        errorInfo.put(105, "用户名或密码错误");
        errorInfo.put(106, "用户名不符合要求");
        errorInfo.put(107, "原密码错误");
        errorInfo.put(108, "文件过大");
        errorInfo.put(109,"邮箱地址不能为空");
        errorInfo.put(110,"该邮箱已被绑定");
        errorInfo.put(111,"绑定失败");
        errorInfo.put(112,"您已经喜欢过了");
        errorInfo.put(113,"该发现不存在");
        errorInfo.put(114,"无权操作");
        errorInfo.put(115, "该图片不存在");
        errorInfo.put(116, "该评论不存在");
    }

    public static String getMessage(int rsCode) {
        return errorInfo.getOrDefault(rsCode, null);
    }

    public String notFound() throws IOException {
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print("{\"status\":404,\"message\":\"Not Found\"}");
        return NONE;
    }
}
