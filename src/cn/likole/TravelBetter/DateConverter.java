package cn.likole.TravelBetter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import ognl.DefaultTypeConverter;

public class DateConverter extends DefaultTypeConverter {
    public Object convertValue(Map context, Object value, Class toType) {
        try {
            if (toType == Date.class) { // 如果想转换的是Date类型时将做以下操作
                // 因为在Struts2里会表单传过来的非字符串数据转换为String[],所以这里得取第一个值
                String dataStr = ((String[]) value)[0];
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd"); // 转换为自己想要日期格式
                return f.parse(dataStr);
            } else if (toType == String.class) {
                String dataStr = ((Date) value).toString();
                return dataStr;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
}