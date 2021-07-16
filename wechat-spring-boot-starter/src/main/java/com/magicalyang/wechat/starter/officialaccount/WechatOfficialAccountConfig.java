package com.magicalyang.wechat.starter.officialaccount;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author Constanline
 */
@Getter
class WechatOfficialAccountConfig {
    public enum MessageEncryptMode {
        /**
         * 明文模式
         */
        PLAIN_TEXT,
        /**
         * 兼容模式
         */
        BOTH,
        /**
         * 秘闻模式
         */
        CIPHER_TEXT
    }

    public enum DomainType {
        /**
         * 通用域名(api.weixin.qq.com)
         */
        COMMON,
        /**
         * 通用异地容灾域名(api2.weixin.qq.com)
         */
        COMMON_DISASTER,
        /**
         * 上海域名(sh.api.weixin.qq.com)
         */
        SHANGHAI,
        /**
         * 深圳域名(sz.api.weixin.qq.com)
         */
        SHENZHEN,
        /**
         * 香港域名(hk.api.weixin.qq.com)
         */
        HONG_KONG
    }

    private final MessageEncryptMode messageEncryptMode;

    private final String appId;

    private final String appSecret;

    private final String token;

    private final String encodingAesKey;

    private final String apiDomain;

    public WechatOfficialAccountConfig(@NonNull String appId, @NonNull String appSecret, @NonNull String token,
                                       String encodingAesKey, @NonNull MessageEncryptMode messageEncryptMode,
                                       DomainType domainType) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.token = token;
        this.encodingAesKey = encodingAesKey;
        this.messageEncryptMode = messageEncryptMode;
        switch (domainType) {
            case COMMON_DISASTER:
                apiDomain = "api2.weixin.qq.com";
                break;
            case SHANGHAI:
                apiDomain = "sh.api.weixin.qq.com";
                break;
            case SHENZHEN:
                apiDomain = "sz.api.weixin.qq.com";
                break;
            case HONG_KONG:
                apiDomain = "hk.api.weixin.qq.com";
                break;
            default:
                apiDomain = "api.weixin.qq.com";
                break;
        }
    }
}
