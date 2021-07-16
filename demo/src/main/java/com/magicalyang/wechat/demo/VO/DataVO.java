package com.magicalyang.wechat.demo.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Constanline
 */
@Getter
@Setter
@ToString
public class DataVO<T> extends MessageVO {
    T data;

    public DataVO(T data) {
        super();
        this.data = data;
    }
}
