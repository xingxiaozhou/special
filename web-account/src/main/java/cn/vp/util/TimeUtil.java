package cn.vp.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class TimeUtil {

    //获取时间  返回毫秒级时间
    public static String getTime() {
        System.out.println("getTime...util...");
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Long date = calendar.getTime().getTime();            //获取毫秒时间

        //String dateStringPaString = sdf.format(date);
        //System.out.println(dateStringPaString);

        return date.toString();
    }


    public static boolean cmpTime(String time) {
        System.out.println("cmpTime...util...");
        long tempTime = Long.parseLong(time);
        System.out.println("tempTime"+tempTime);

        //在获取现在的时间
        Calendar calendar = Calendar.getInstance();
        Long date = calendar.getTime().getTime();            //获取毫秒时间
        System.out.println("date"+date);

        if(date - tempTime > 600000 ) {   //10分钟
            return false;
        } else {
            return true;
        }

    }


    /**
     * 判断两个日期是否同一天
     * @param date1
     * @param date2
     * @return boolean
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }


}
