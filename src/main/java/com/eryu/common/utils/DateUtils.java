package com.eryu.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by marco on 2016/12/12.
 */
public class DateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    public static SimpleDateFormat PARTTEN_y_M_d_H_m_s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat PARTTEN_y_M_d = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat PARTTEN_y_M = new SimpleDateFormat("yyyy-MM");

    /**
     * 字符串转为日期 yyyy-MM-dd HH:mm:ss
     */
    public static Date toDateDetail(String s) {
        return toDate(s, PARTTEN_y_M_d_H_m_s);
    }

    /**
     * 字符串转为日期 yyyy-MM-dd
     */
    public static Date toDate(String s) {
        return toDate(s, PARTTEN_y_M_d);
    }

    /**
     * 字符串转为日期 yyyy-MM
     *
     * @param s
     * @return
     */
    public static Date toMonthDate(String s) {
        return toDate(s, PARTTEN_y_M);
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return PARTTEN_y_M_d.format(date);
    }

    /**
     * 字符串转为日期
     *
     * @param s
     * @param format
     * @return
     */
    public static Date toDate(String s, DateFormat format) {
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            LOGGER.error("Parse Date error:", e);
        }
        return date;
    }

    /**
     * 计算二个日期间隔天数
     *
     * @param begin
     * @param end
     * @return
     */
    public static int calcIntervalDay(Date begin, Date end) {
        Long interval = (end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000);
        return interval.intValue();
    }

    //需要注意的是：月份是从0开始的，比如说如果输入5的话，实际上显示的是4月份的最后一天，千万不要搞错了哦
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
        return  new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }

    /**
     * 计算二个日期之间的月份情况
     *
     * @return
     * @data 2017/02/06
     * @author troubleMan
     */
    public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 将周转换成具体的日期
     *
     * @return
     * @date 2017/02/07
     * @author troubleMan
     */
    public static String getWeekFirst(int year, int week) {
        if (week > 53) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(cal.getTime());
        return date;
    }

    /**
     * 判断(当前的1月1号是否跨年)
     *
     * @
     */
    public static boolean testGetWeekNo(int year) throws ParseException {
        boolean flag = false;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setTime(dateFormat.parse(year + "-1-1"));
        int weekNo = calendar.get(Calendar.WEEK_OF_YEAR);
        flag = weekNo == 52 ? true : false;
        return flag;
    }

    /**
     * 查看两个日期相隔的天数详细
     * NumberFormat is the abstract base class for all number formats.
     * This class provides the interface for formatting and parsing numbers.
     *
     * @return null
     * @troubleMan
     */
    public static List<String> getBetweenDays(String stime, String etime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date sdate = null;
        Date eDate = null;
        try {
            sdate = df.parse(stime);
            eDate = df.parse(etime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long betweendays = (long) ((eDate.getTime() - sdate.getTime()) / (1000 * 60 * 60 * 24) + 0.5);//天数间隔
        Calendar c = Calendar.getInstance();
        List<String> list = new ArrayList<String>();
        while (sdate.getTime() <= eDate.getTime()) {
            list.add(df.format(sdate));
            c.setTime(sdate);
            c.add(Calendar.DATE, 1); // 日期加1天
            sdate = c.getTime();
        }
        return list;
    }

    /**
     * 保留小数两为的数据处理方式
     * NumberFormat is the abstract base class for all number formats.
     * This class provides the interface for formatting and parsing numbers.
     *
     * @param d
     * @return
     * @troubleMan
     */
    public static double formatDouble2(double d) {
        // 旧方法，已经不再推荐使用
//        BigDecimal bg = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);


        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);


        return bg.doubleValue();
    }
    /**
     * 获取某日期往前多少天的日期
     * @CreateTime 2016-1-13
     * @Author PSY
     * @param nowDate
     * @param beforeNum
     * @return
     */
    public static String getBeforeDate(String nowDate, Integer beforeNum) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        java.util.Date date=sdf.parse(nowDate);
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -beforeNum);  //设置为前beforeNum天
        return sdf.format(calendar.getTime());   //得到前beforeNum天的时间
    }

    /**
     * 获取某日期往后多少天的日期
     * @CreateTime 2016-1-13
     * @Author PSY
     * @param nowDate
     * @param beforeNum
     * @return
     */
    public static String getAfterDate(String nowDate, Integer beforeNum) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        java.util.Date date=sdf.parse(nowDate);
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, beforeNum);  //设置为前beforeNum天
        return sdf.format(calendar.getTime());   //得到前beforeNum天的时间
    }


    /**
     * 获取某日期当前日期
     * @CreateTime 2016-1-13
     * @Author troubleMan
     * @return
     */
    public static String getToday(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        Calendar calendar = Calendar.getInstance(); //得到日历
        return sdf.format(calendar.getTime());   //得到前beforeNum天的时间
    }


    /**
     * 获取某日期往前多少个月的情况
     * @CreateTime 2016-1-13
     * @Author PSY
     * @param nowDate
     * @param beforeNum
     * @return
     */
    public static String getBeforeMonth(String nowDate, Integer beforeNum) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        java.util.Date date=sdf.parse(nowDate);
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.MONTH, -beforeNum);  //设置为前beforeNum天
        return sdf.format(calendar.getTime());   //得到前beforeNum天的时间
    }


    /**
     * 获取某日期的前一年的那个月
     * @CreateTime 2016-1-13
     * @Author PSY
     * @param nowDate
     * @return
     */
    public static String getBeforeYearDate(String nowDate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        java.util.Date date=sdf.parse(nowDate);
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.YEAR, -1);  //设置为前beforeNum天
        return sdf.format(calendar.getTime());   //得到前beforeNum天的时间
    }

    /**
     * 判断两个时间的前后情况
     * @param DATE1 时间1
     * @param DATE2 时间2
     * @return 数据
     */
    public static int compare_date(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

}
