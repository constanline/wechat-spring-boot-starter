package com.magicalyang.wechat.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Constanline
 */
public class StringUtil {
    public static final char UNDERLINE = '_';

    /**
     * 将驼峰转换成"_"(userId:user_id)
     * @param param 要转化的参数
     * @return 转换结果
     */
    public static String camelToUnderline(String param, boolean toUpperCase){
        if (param == null || "".equals(param.trim())){
            return "";
        }

        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
            }
            sb.append(toUpperCase ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 将驼峰转换成"_"(userId:user_id)
     * @param param 要转化的参数
     * @return 转换结果
     */
    public static String camelToUnderline(String param){
        return camelToUnderline(param, false);
    }

    /**
     * 将"_"转成驼峰(user_id:userId)
     * @param param 要转化的参数
     * @return 转换结果
     */
    public static String underlineToCamel(String param){
        if (param == null || "".equals(param.trim())){
            return "";
        }

        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile(UNDERLINE + "").matcher(param);
        int i = 0;
        while (mc.find()){
            int position = mc.end() - ++i;
            sb.replace(position - 1,position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();

    }
}
