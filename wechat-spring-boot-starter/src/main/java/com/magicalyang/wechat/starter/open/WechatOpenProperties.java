package com.magicalyang.wechat.starter.open;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Constanline
 */

@Getter
@Setter
@ConfigurationProperties(prefix = "wechat.open")
class WechatOpenProperties {
    private String appId;
}
