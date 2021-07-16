package com.magicalyang.wechat.starter.officialaccount;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Constanline
 */

@Getter
@Setter
@ConfigurationProperties(prefix = "wechat.official-account")
class WechatOfficialAccountProperties {
    private String appId;

    private String appSecret;

    private String token;

    private String encodingAesKey;

    private String messageEncryptMode;

    private String domainType;

    public String getMessageEncryptMode() {
        return messageEncryptMode == null ? "" : messageEncryptMode;
    }

    public String getDomainType() {
        return domainType == null ? "" : domainType;
    }
}
