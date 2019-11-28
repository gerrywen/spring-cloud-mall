package com.mall.common.base.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间格式工具类
 *
 * @author wenguoli
 * @date 2019/9/27 10:26
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String DATE_PATTERN = "yyyy-MM-dd";
    public static String TIME_PATTREN = "yyyy-MM-dd HH:mm:ss";
    public static String MSEC_PATTREN = "yyyyMMddHHmmssSSS";
    // 同步附件的时间戳
    public static String MSEC_PATTREN_MS = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 格式
     */
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    //:工作日默认设置
    public static Map<Integer, Integer> weekDayMap = new HashMap<Integer, Integer>();
    public static Logger logger = LogManager.getLogger(DateUtils.class);

    public static String getDateStr(Date date, String pattern) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            df.setLenient(false);
            return df.format(date);
        } catch (Exception e) {
            logger.error("【date:" + date + ",pattern:" + pattern + ";转化格式异常】");
        }
        return "";
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getDate() {
        return new Date();
    }

    /**
     * 获取时间 格式 为 yyyy-MM-dd HH:mm:ss的 时间
     *
     * @param date
     * @return
     */
    public static String getTimeStr(Date date) {
        return getDateStr(date, TIME_PATTREN);
    }


    /**
     * 获取时间 格式 为 yyyy-MM-dd 的 时间
     *
     * @param date
     * @return
     */
    public static String getDateStr(Date date) {
        return getDateStr(date, DATE_PATTERN);
    }


    /**
     * 获取时间 格式 为 yyyy-MM-dd HH:mm:ss的 时间
     *
     * @return
     */
    public static String getCurrentTimeStr() {
        return getDateStr(new Date(), TIME_PATTREN);
    }


    /**
     * 获取时间 格式 为 yyyy-MM-dd 的 时间
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return getDateStr(new Date(), DATE_PATTERN);
    }

    /**
     * 根据字符串转换为date时间
     * 格式{
     * "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"}
     * }
     *
     * @param str
     * @return
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 通过 yyyy-MM-dd HH:mm:ss  获取date dateStr必须是  yyyy-MM-dd HH:mm:ss这个格式
     *
     * @param dateStr
     * @return
     */
    public static Date getTimeFromStr(String dateStr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(TIME_PATTREN);
            df.setLenient(false);
            return df.parse(dateStr);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    /**
     * 通过 yyyy-MM-dd  获取date dateStr必须是  yyyy-MM-dd这个格式
     *
     * @param dateStr
     * @return
     */
    public static Date getDateFromStr(String dateStr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
            df.setLenient(false);
            return df.parse(dateStr);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }


    public static Date getDate(String dateStr, String pattern) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            df.setLenient(false);
            return df.parse(dateStr);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, days);
        return c.getTime();
    }

    /**
     * 获取一个日期某一部分的值
     *
     * @param date
     * @param fiedNum
     * @return
     */
    public static int getValueFromDate(Date date, int fiedNum) {

        Calendar c = Calendar.getInstance();

        c.setTime(date);

        return c.get(fiedNum);
    }

    /**
     * 把一个标准日期转化成formart格式的日期
     *
     * @param date
     * @param formart
     * @return
     */
    public static Date getFormatDate(Date date, String formart) {

        String desc = DateUtils.getDateStr(date, formart);

        return DateUtils.getDate(desc, formart);

    }

    /**
     * 对一个日期的某一部分加减操作
     *
     * @param date       日期
     * @param partOfDate 年，月，日对应的整数
     * @param increment  增量
     * @return
     */
    public static Date operationDate(Date date, int partOfDate, int increment) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(partOfDate, increment);

        Date currentDate = calendar.getTime();

        return currentDate;
    }

    public static List<Date> getExactlyDates(Date startTime, Date endTime) {

        if (startTime == null || endTime == null) {

            return new ArrayList<Date>();

        } else if (endTime.before(startTime)) {

            return new ArrayList<Date>();

        } else {

            List<Date> dates = new ArrayList<Date>();

            do {

                dates.add(startTime);

                startTime = DateUtils.addDays(startTime, 1);

            } while (!startTime.after(endTime));

            return dates;
        }

    }


    /**
     * 获取日期相对于 当前周的编号 e.g. "2014-09-22" : 1
     *
     * @param date
     */
    public static int getNumOfWeekByDate(String date) {

        Date begin = DateUtils.getDate(date, DateUtils.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(begin);

        return weekDayMap.get(calendar.get(Calendar.DAY_OF_WEEK));

    }

    /**
     * 获取一个月的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getTotalDayOfMonth(int year, int month) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);

        calendar.set(Calendar.MONTH, month - 1);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    }

    /**
     * @param range e.g.2014-4
     * @return
     */
    public static int getTotalDayOfMonth(String range) {

        String[] rangeArray = range.split("-");

        int year = Integer.parseInt(rangeArray[0]);

        int month = Integer.parseInt(rangeArray[1]);

        return getTotalDayOfMonth(year, month);
    }

    /**
     * 推断出指定日期是当前周的第几天--
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1) {

            dayOfWeek = 7;

        } else {

            dayOfWeek = dayOfWeek - 1;
        }

        return dayOfWeek;
    }

    /**
     * 获取指定日期所在周的星期一
     *
     * @param date
     * @return
     */
    public static Date getFrisrtDateOfWeek(Date date) {

        // :获取指定日期是当周的第几天
        int dayOfWeek = getDayOfWeek(date);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.add(Calendar.DATE, -(dayOfWeek - 1));

        return calendar.getTime();

    }

    /**
     * 获取指定日期所在周的星期天
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfWeek(Date date) {

        int dayOfWeek = getDayOfWeek(date);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.add(Calendar.DATE, -(dayOfWeek - 7));

        return calendar.getTime();

    }

    /**
     * 判断指定日期所在周的星期一是否在本月
     *
     * @param date
     * @return
     */
    public static boolean isFirstDayOfWeekInCurrentMonth(Date date) {

        Date firstDayOfWeek = getFrisrtDateOfWeek(date);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(firstDayOfWeek);

        Calendar calendar2 = Calendar.getInstance();

        int month = calendar2.get(Calendar.MONTH);

        if (month == calendar.get(Calendar.MONTH)) {

            return true;
        }

        return false;

    }

    /**
     * 判断指定日期所在周的星期七是否在本月
     *
     * @param date
     * @return
     */
    public static boolean isLastDayOfWeekInCurrentMonth(Date date) {

        Date lastDayOfWeek = getLastDateOfWeek(date);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(lastDayOfWeek);

        Calendar calendar2 = Calendar.getInstance();

        int month = calendar2.get(Calendar.MONTH);

        if (month == calendar.get(Calendar.MONTH)) {

            return true;
        }

        return false;

    }

    /**
     * :获取指定日期所在月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {

        Calendar cDay1 = Calendar.getInstance();

        cDay1.setTime(date);

        final int lastDay = cDay1.getActualMinimum(Calendar.DAY_OF_MONTH);

        Date lastDate = cDay1.getTime();

        lastDate.setDate(lastDay);

        return lastDate;
    }

    /**
     * 获取指定日期所在月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {

        Calendar cDay1 = Calendar.getInstance();

        cDay1.setTime(date);

        final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);

        Date lastDate = cDay1.getTime();

        lastDate.setDate(lastDay);

        return lastDate;
    }


    public static int getDistanceBetweenDate(Date startDate, Date endDate) {

        startDate = DateUtils.getFormatDate(startDate, DateUtils.DATE_PATTERN);

        endDate = DateUtils.getFormatDate(endDate, DateUtils.DATE_PATTERN);

        Calendar cal = Calendar.getInstance();

        cal.setTime(startDate);

        long time1 = cal.getTimeInMillis();

        cal.setTime(endDate);

        long time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));

    }

    public static int getPartDistanceBetweenDate(Date startDate, Date endDate, int fileIndex) {

        if (startDate == null || endDate == null) {

            return 0;
        }

        int min = getValueFromDate(startDate, fileIndex);

        int max = getValueFromDate(endDate, fileIndex);

        return max - min;

    }

    public static List<String> getYearBetweenDate(Date startDate, Date endDate) {
        List<String> list = new ArrayList<String>();
        if (startDate == null || endDate == null) {
            return list;
        }

        Calendar dd = Calendar.getInstance();// 定义日期实例

        dd.setTime(startDate);// 设置日期起始时间

        while (dd.getTime().before(endDate)) {// 判断是否到结束日期

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

            String str = sdf.format(dd.getTime());

            list.add(str);

            dd.add(Calendar.YEAR, 1);// 进行当前日期月份加1
        }

        list.add(new SimpleDateFormat("yyyy").format(endDate));

        return list;
    }

    public static List<String> getMonthBetweenDate(Date startDate, Date endDate) {
        List<String> list = new ArrayList<String>();
        if (startDate == null || endDate == null) {
            return list;
        }

        Calendar dd = Calendar.getInstance();// 定义日期实例

        dd.setTime(startDate);// 设置日期起始时间

        while (dd.getTime().before(endDate)) {// 判断是否到结束日期

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

            String str = sdf.format(dd.getTime());

            list.add(str);

            dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1
        }

        list.add(new SimpleDateFormat("yyyy-MM").format(endDate));

        return list;
    }

    public static List<String> getDayBetweenDate(Date startDate, Date endDate) {
        List<String> list = new ArrayList<String>();
        if (startDate == null || endDate == null) {
            return list;
        }

        Calendar dd = Calendar.getInstance();// 定义日期实例

        dd.setTime(startDate);// 设置日期起始时间

        while (dd.getTime().before(endDate)) {// 判断是否到结束日期

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String str = sdf.format(dd.getTime());

            list.add(str);

            dd.add(Calendar.DATE, 1);// 进行当前日期月份加1
        }

        list.add(new SimpleDateFormat("yyyy-MM-dd").format(endDate));

        return list;
    }

    /**
     * 判断一个时间点是否属于一个时间区间
     */
    public static boolean isBelongDateArea(Date beginDate, Date enDate, Date confirmDate) {
        boolean isBelong = false;

        if (beginDate != null && enDate != null && confirmDate != null) {

            if (confirmDate.getTime() >= beginDate.getTime() && confirmDate.getTime() <= enDate.getTime()) {
                isBelong = true;
            } else {
                isBelong = false;
            }

        }
        return isBelong;

    }

    /*
     *计算俩个日期时间差
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (smdate == null || smdate.equals("") || bdate == null || bdate.equals("")) {
            return 0;
        }
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * Description:获取两个日期之间较小的那一个
     * By Song Tianyang
     *
     * @param date1
     * @param date2
     * @return
     * @下午3:14:47
     * @2017年10月25日
     */
    public static Date getSmallDate(Date date1, Date date2) {
        if (date1 == null) {
            return date2;
        } else if (date2 == null) {
            return date1;
        } else if (date1 == null && date2 == null) {
            return null;
        }

        long betweenTime = (date1.getTime() - date2.getTime());

        if (betweenTime < 0)
            return date2;

        return date1;
    }

    /**
     * Description:获取两个日期之间较大的那一个
     * By Song Tianyang
     *
     * @param data1
     * @param date2
     * @return
     * @下午3:14:47
     * @2017年10月25日
     */
    public static Date getLargeDate(Date data1, Date date2) {
        long betweenTime = (data1.getTime() - date2.getTime());

        if (betweenTime > 0)
            return date2;

        return data1;
    }

    /*
     *计算当前日期占目标日期区间的百分比
     */
    public static double getDateBetweenPercent(Date startDate, Date endDate) {
        Date nowDate = new Date();
        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            return 0;
        }
        if (nowDate.getTime() < startDate.getTime()) {
            return 0;
        }
        if (nowDate.getTime() >= endDate.getTime()) {
            return 1;
        }
        double daysBetween1 = DateUtils.daysBetween(startDate, nowDate);
        double daysBetween2 = DateUtils.daysBetween(startDate, endDate);
        double result = daysBetween1 / daysBetween2;
        return result;
    }

    /**
     * 通过两个时间区间获取到时间区间内的所有月份
     *
     * @param beginTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthsBetweenDates(String beginTime, String endTime)
            throws ParseException {

        List<String> result = new ArrayList<String>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();

        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(beginTime));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(endTime));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 算两个时间差
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 分
     */
    public static long diffMin(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 1000 / 60;
    }

    /**
     * 算两个时间差到秒
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 秒
     */
    public static long diffTimeMin(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 1000;
    }

    /**
     * 获取当月天数
     *
     * @return 天数
     */
    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }


    /**
     * 计算时间差
     *
     * @param afterDate  后面时间
     * @param beforeDate 前面时间
     * @return 日期
     */
    public static String getDateDiff(Date afterDate, Date beforeDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long diff = afterDate.getTime() - beforeDate.getTime();
        long day = diff / nd;
        long hour = diff % nd / nh;
        long min = diff % nd % nh / nm;
        return day + "天" + hour + "小时" + min + "分钟";
    }


    /**
     * 计算时间区间是否在当前时间内
     *
     * @param start_time 开始时间
     * @param end_time   结束时间
     * @return bool
     */
    public static Boolean isTimeRange(String start_time, String end_time) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date now = null;
        boolean shopStatus = false;
        try {
            now = df.parse(df.format(new Date()));
            Date begin = df.parse(start_time);
            Date end = df.parse(end_time);
            Calendar nowTime = Calendar.getInstance();
            nowTime.setTime(now);
            Calendar beginTime = Calendar.getInstance();
            beginTime.setTime(begin);
            Calendar endTime = Calendar.getInstance();
            endTime.setTime(end);
            shopStatus = nowTime.before(endTime) && nowTime.after(beginTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return shopStatus;
    }

    /**
     * 计算时间区间是否未过完
     *
     * @param start_time 开始时间
     * @param end_time   结束时间
     * @return bool
     */
    public static Boolean isNoTimeRange(String start_time, String end_time) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date now = null;
        boolean shopStatus = false;
        try {
            now = df.parse(df.format(new Date()));
            Calendar nowTime = Calendar.getInstance();
            nowTime.setTime(now);

            Date begin = df.parse(start_time);
            Calendar beginTime = Calendar.getInstance();
            beginTime.setTime(begin);

            Date end = df.parse(end_time);
            Calendar endTime = Calendar.getInstance();
            endTime.setTime(end);
            shopStatus = beginTime.after(nowTime) && endTime.after(nowTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return shopStatus;
    }


    /**
     * 获取当天凌晨的时间戳
     *
     * @return 时间戳
     */
    public static long getEarlyMorningTime() {
        long current = System.currentTimeMillis();
        return current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
    }

    /**
     * 分钟数转换为毫秒数
     *
     * @param minute 分钟
     * @return 毫秒数
     */
    public static long getFzChangeHm(long minute) {

        long nm = 1000 * 60;

        return nm * minute;
    }

}
