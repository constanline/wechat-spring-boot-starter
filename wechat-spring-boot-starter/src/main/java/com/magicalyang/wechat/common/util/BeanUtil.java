package com.magicalyang.wechat.common.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Constanline
 */
public class BeanUtil {

    @FunctionalInterface
    public interface BeanCopyUtilCallBack <S, T> {

        /**
         * 定义默认回调方法
         * @param t 源
         * @param s 目标
         */
        void callBack(S t, T s);
    }

    /**
     * 数据的拷贝
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @return 拷贝结果
     */
    public static <S, T> T copyProperties(S sources, Supplier<T> target) {
        T t = target.get();
        BeanUtils.copyProperties(sources, t);
        return t;
    }

    /**
     * 集合数据的拷贝
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @return 拷贝结果
     */
    public static <S, T> List<T> copyProperties(List<S> sources, Supplier<T> target) {
        return copyProperties(sources, target, null);
    }


    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     * @return 拷贝结果
     */
    public static <S, T> List<T> copyProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            BeanUtils.copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }
}
