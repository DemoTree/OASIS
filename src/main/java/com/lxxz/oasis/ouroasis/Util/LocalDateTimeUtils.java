package com.lxxz.oasis.ouroasis.Util;

import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class LocalDateTimeUtils {

    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public final static String SLASH_YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";

    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public final static String YYYYMMDD = "yyyyMMdd";

    public final static String SLASH_YYYY_MM_DD = "yyyy/MM/dd";

    public final static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    public final static String YYYY_MM_DD_DOT = "yyyy.MM.dd";

    public final static String YYYYMMDD_CHINESE = "yyyy年MM月dd日";

    public static LocalDate convertStringToLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(YYYY_MM_DD));
    }

    public static LocalDateTime convertStringToLocalDateTime(String dateStr) {
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }

    public static LocalDate convertStringToLocalDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime convertStringToLocalDateTime(String dateStr, String pattern) {
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        if (null == date) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        if (null == date) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDateTime convertLocalDateToLocalDateTime(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        return LocalDateTime.ofInstant(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                ZoneId.systemDefault());
    }

    public static Date convertLocalDateToDate(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date convertStringToDate(String dateStr, String pattern) {
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(pattern)) {
            return null;
        }
        LocalDate localDate = convertStringToLocalDate(dateStr, pattern);
        return convertLocalDateToDate(localDate);
    }

    public static Date convertStringToDateTime(String dateStr, String pattern) {
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(pattern)) {
            return null;
        }
        LocalDateTime localDateTime = convertStringToLocalDateTime(dateStr, pattern);
        return convertLocalDateTimeToDate(localDateTime);
    }

    public static Date addMonths(LocalDate localDate, int monthsToAdd) {
        if (null == localDate) {
            return null;
        }
        LocalDate plusedLocalDate = localDate.plusMonths(monthsToAdd);
        return convertLocalDateToDate(plusedLocalDate);
    }

    public static LocalDate setMonth(LocalDate localDate, int month) {
        if (null == localDate) {
            return null;
        }
        return LocalDate.of(localDate.getYear(), month, localDate.getDayOfMonth());
    }

    public static LocalDate setDayOfMonth(LocalDate localDate, int dayOfMonth) {
        if (null == localDate) {
            return null;
        }
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), dayOfMonth);
    }

    public static Long between(Date start, Date end) {
        LocalDate startDate = convertDateToLocalDate(start);
        LocalDate endDate = convertDateToLocalDate(end);
        return endDate.toEpochDay() - startDate.toEpochDay();
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        if (Objects.nonNull(localDateTime) && !StringUtils.isEmpty(pattern)) {
            return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
        }
        return null;
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return formatLocalDateTime(localDateTime, YYYY_MM_DD_HH_MM_SS);
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDatetime) {
        if (null == localDatetime) {
            return null;
        }
        return Date.from(localDatetime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String formatLocalDate(LocalDate localDate, String pattern) {
        if (Objects.nonNull(localDate) && !StringUtils.isEmpty(pattern)) {
            return localDate.format(DateTimeFormatter.ofPattern(pattern));
        }
        return null;
    }

    public static String formatLocalDate(LocalDate localDate) {
        return formatLocalDate(localDate, YYYY_MM_DD);
    }

    public static Long converToTimestamp(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public static Long converToTimestamp(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Integer getAgeByBirthday(String birthday) {
        LocalDate birthdayDate = convertStringToLocalDate(birthday);
        return getAgeByBirthday(birthdayDate);
    }

    public static Integer getAgeByBirthday(LocalDate birthday) {
        return null != birthday ? birthday.until(LocalDate.now()).getYears() : 0;
    }

    public static String formatDate(Date date) {
        LocalDate localDate = convertDateToLocalDate(date);
        if (null == localDate) {
            return null;
        }
        return formatLocalDate(localDate);
    }

    public static String formatDatetime(Date date) {
        LocalDateTime localDateTime = convertDateToLocalDateTime(date);
        if (null == localDateTime) {
            return null;
        }
        return formatLocalDateTime(localDateTime);
    }
}
