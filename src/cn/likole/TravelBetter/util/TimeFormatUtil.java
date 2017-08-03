package cn.likole.TravelBetter.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by likole on 7/25/17.
 * 格式化时间
 */
public class TimeFormatUtil {
    public static String formatTime(Date createTime) {

        String interval = null;

        long second = (new Date().getTime() - createTime.getTime()) / 1000;

        if (second < 30) {
            interval = "刚刚";
        } else if (second >= 30 && second < 60) {
            interval = "半分钟前";
        } else if (second >= 60 && second < 60 * 60) {//大于1分钟 小于1小时
            long minute = second / 60;
            interval = minute + "分钟前";
        } else if (second >= 60 * 60 && second < 60 * 60 * 24*2) {//大于1小时 小于24小时
            long hour = (second / 60) / 60;
            if (hour <= 8) {
                interval = hour + "小时前";
            } else {
                int day=new Date().getDay()-createTime.getDay();
                switch (day)
                {
                    case 0:
                        interval = "今天" + getFormatTime(createTime, "HH:mm");
                        break;
                    case 1:
                        interval = "昨天" + getFormatTime(createTime, "HH:mm");
                        break;
                    case 2:
                        interval = "前天" + getFormatTime(createTime, "HH:mm");
                        break;
                }
            }
        } else if (second >= 60 * 60 * 24 * 2 && second <= 60 * 60 * 24 * 7) {//大于2D小时 小于 7天
            long day = ((second / 60) / 60) / 24;
            interval = day + "天前";
        } else if (second <= 60 * 60 * 24 * 365 && second >= 60 * 60 * 24 * 7) {//大于7天小于365天
            interval = getFormatTime(createTime, "MM-dd");
        } else if (second >= 60 * 60 * 24 * 365) {//大于365天
            interval = getFormatTime(createTime, "yyyy-MM-dd");
        } else {
            interval = "未知";
        }
        return interval;
    }

    private static String getFormatTime(Date date, String Sdf) {
        return (new SimpleDateFormat(Sdf)).format(date);
    }

}
