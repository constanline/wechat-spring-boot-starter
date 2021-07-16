package com.magicalyang.wechat.starter.open;

import lombok.Getter;

/**
 * @author Constanline
 */
@Getter
class WechatOpenConfig {

    private final String appId;

    public WechatOpenConfig(String appId) {
        this.appId = appId;
    }
}
