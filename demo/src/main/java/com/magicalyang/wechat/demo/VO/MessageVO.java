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
public class MessageVO {
    Integer errCode;

    String errMsg;

    public MessageVO(String errMsg) {
        this.errCode = -1;
        this.errMsg = errMsg;
    }

    public MessageVO(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public MessageVO() {
        this.errCode = 0;
    }
}
