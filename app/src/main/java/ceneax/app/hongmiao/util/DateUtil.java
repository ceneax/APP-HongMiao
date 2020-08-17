package ceneax.app.hongmiao.util;

import java.util.Date;

public class DateUtil {

    // //////////////////////////////////////////////////////////////////////////
    private static final long ONE_MINUTE = 60000;
    private static final long ONE_HOUR = 3600000;
    private static final long ONE_DAY = 86400000;
    private static final long ONE_WEEK = 604800000;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    /// 时间转换
    public static String format(Date date) {
        long delta = new Date().getTime() - date.getTime();

        if (delta < 1 * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 60 * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24 * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48 * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30 * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12 * 4 * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60;
    }

    private static long toDays(long date) {
        return toHours(date) / 24;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365;
    }
    // //////////////////////////////////////////////////////////////////////////

    /**
     * 判断当前是否是天黑状态
     * @return 返回bool
     */
    public static boolean isNightNow() {
        int hour = new Date().getHours();
        return (hour >= 18 && hour <= 24) || (hour >= 0 && hour < 6);
    }

}
