package com.magicalyang.wechat.common.exception;

import com.magicalyang.wechat.common.message.BaseResp;

/**
 * @author Constanline
 */
public class WechatErrorException extends Exception {

    public WechatErrorException(BaseResp message) {
        super(message.getErrMsg());
    }

    public WechatErrorException(int errCode, String errMsg) {
        super(errMsg);
    }
}
