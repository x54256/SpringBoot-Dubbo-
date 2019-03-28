package com.dist.base.utils;

import java.io.UnsupportedEncodingException;

/**
 * oracel数据库相关
 *
 * @author yinxp@dist.com.cn
 * @date 2018/9/30
 */
public abstract class OracleUtil {

    // oracel字符集对应的中文存储所占字节数
    private static final int ZHS16GBK_CHINESE_BYTE_ = 2;
    private static final int AL32UTF8_CHINESE_BYTE = 3;


    /**
     * 字符串所包含字符数小于或等于limit
     *
     * @param s
     * @param limitCharLen
     * @return
     */
    public static boolean equalOrLessThan4Char(String s, int limitCharLen) {
        return s.length() > limitCharLen ? false : true;
    }


    /**
     * 当oracel字符集为ZHS16GBK时，字符串所占字节小于或等于规定的limit
     *
     * @param s
     * @param limitByteLen
     * @return
     */
    public static boolean equalOrLessThan4ZHS16GBK(String s, int limitByteLen) {
        if (!isContianChinese(s)) {
            return s.length() > limitByteLen ? false : true;
        }
        return equalOrLessThan(s, limitByteLen, ZHS16GBK_CHINESE_BYTE_);
    }

    /**
     * 当oracel字符集为AL32UTF8时，字符串所占字节小于或等于规定的limit
     *
     * @param s
     * @param limitByteLen
     * @return
     */
    public static boolean equalOrLessThan4AL32UTF8(String s, int limitByteLen) {
        if (!isContianChinese(s)) {
            return s.length() > limitByteLen ? false : true;
        }
        return equalOrLessThan(s, limitByteLen, AL32UTF8_CHINESE_BYTE);
    }


    /**
     * 字符串所占字节小于或等于规定的limit
     *
     * @param s
     * @param limit
     * @return
     */
    private static boolean equalOrLessThan(String s, int limit, int charactersetIncr) {
        int byteLen = toOracelByteLen(s, charactersetIncr);
        if (byteLen > limit) {
            return false;
        }
        return true;
    }

    /**
     * 字符串s插入指定characterset的oracel中存储所需字节空间
     *
     * @param s
     * @param charactersetIncr
     * @return
     */
    public static int toOracelByteLen(String s, int charactersetIncr) {
        if (s == null) {
            return 0;
        }
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += charactersetIncr;
            }
        }
        return length;
    }

    /**
     * 字符串是否包含中文
     *
     * @param s
     * @return
     */
    public static boolean isContianChinese(String s) {
        return isContianChinese(s, "utf-8");
    }

    /**
     * 字符串是否包含中文
     *
     * @param s
     * @param charset
     * @return
     */
    public static boolean isContianChinese(String s, String charset) {
        try {
            return s.length() == s.getBytes(charset).length ? false : true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

}
