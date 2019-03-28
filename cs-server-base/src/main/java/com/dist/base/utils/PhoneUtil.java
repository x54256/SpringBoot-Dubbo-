package com.dist.base.utils;

import java.util.regex.Pattern;

/**
 * 手机号码处理
 */
public abstract class PhoneUtil {

    //正则表达式：验证手机号
    private static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";


    // 脱敏填充字符
    private static final String FILL_CHAR = "****";


    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }


    /**
     * 电话号码脱敏
     *
     * @param phone
     * @return
     */
    public static String sensitiveFilter(String phone) {
        if (phone == null) {
            return null;
        }
        if (isMobile(phone)) {
            StringBuilder temp = new StringBuilder();
            temp.append(phone.substring(0, 3));
            temp.append(FILL_CHAR);
            temp.append(phone.substring(7));
            return temp.toString();
        }
        return phone;
    }
}
