package com.magicalyang.wechat.starter.open;

import com.magicalyang.wechat.common.exception.WechatSettingNotConfiguredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Constanline
 */
@Configuration
@EnableConfigurationProperties(WechatOpenProperties.class)
@ConditionalOnProperty(
        prefix = "wechat.open",
        name = "enable",
        havingValue = "true"
)
class WechatOpenAutoConfiguration {

    private WechatOpenConfig wechatOpenConfig;

    private WechatOpenProperties wechatOpenProperties;

    @Autowired
    public void setWechatProperties(WechatOpenProperties wechatOpenProperties) {
        this.wechatOpenProperties = wechatOpenProperties;
    }

    @Bean
    public WechatOpenConfig wechatOpenConfig() throws WechatSettingNotConfiguredException {
        if (wechatOpenConfig == null) {
            String appId = wechatOpenProperties.getAppId();
            if (appId == null || appId.isEmpty()) {
                throw new WechatSettingNotConfiguredException("Wechat open platform AppId(wechat.open.app-id) not configure");
            }
            wechatOpenConfig = new WechatOpenConfig(wechatOpenProperties.getAppId());
        }
        return wechatOpenConfig;
    }
}
