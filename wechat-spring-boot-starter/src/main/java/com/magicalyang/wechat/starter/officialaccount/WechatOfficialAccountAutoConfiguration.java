package com.magicalyang.wechat.starter.officialaccount;

import com.magicalyang.wechat.common.exception.WechatSettingNotConfiguredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Constanline
 */
@Configuration
@EnableConfigurationProperties(WechatOfficialAccountProperties.class)
class WechatOfficialAccountAutoConfiguration {

    private WechatOfficialAccountConfig wechatOfficialAccountConfig;

    private WechatOfficialAccountProperties wechatOfficialAccountProperties;

    @Autowired
    public void setWechatOfficialAccountProperties(WechatOfficialAccountProperties wechatOfficialAccountProperties) {
        this.wechatOfficialAccountProperties = wechatOfficialAccountProperties;
    }

    @ConditionalOnMissingBean
    @Bean
    public WechatOfficialAccountConfig wechatOfficialAccountConfig() throws WechatSettingNotConfiguredException {
        if (wechatOfficialAccountConfig == null) {
            String appId = wechatOfficialAccountProperties.getAppId();
            if (appId == null || appId.isEmpty()) {
                throw new WechatSettingNotConfiguredException("Wechat official account AppId(wechat.official-account.app-id) not configured");
            }
            String appSecret = wechatOfficialAccountProperties.getAppSecret();
            if (appSecret == null || appSecret.isEmpty()) {
                throw new WechatSettingNotConfiguredException("Wechat official account AppSecret(wechat.official-account.app-secret) not configured");
            }
            String token = wechatOfficialAccountProperties.getToken();
            if (token == null || token.isEmpty()) {
                throw new WechatSettingNotConfiguredException("Wechat official account Token(wechat.official-account.token) not configured");
            }
            String encodingAesKey = wechatOfficialAccountProperties.getEncodingAesKey();
            String tmpMessageEncryptMode = wechatOfficialAccountProperties.getMessageEncryptMode();
            WechatOfficialAccountConfig.MessageEncryptMode messageEncryptMode = null;
            switch (tmpMessageEncryptMode) {
                case "plain":
                    messageEncryptMode = WechatOfficialAccountConfig.MessageEncryptMode.PLAIN_TEXT;
                    break;
                case "cipher":
                    messageEncryptMode = WechatOfficialAccountConfig.MessageEncryptMode.CIPHER_TEXT;
                    if (encodingAesKey == null || encodingAesKey.isEmpty()) {
                        throw new WechatSettingNotConfiguredException("Wechat official account EncodingAESSecret(wechat.official-account.encoding-aes-secret) not configured");
                    }
                    break;
                case "both":
                    messageEncryptMode = WechatOfficialAccountConfig.MessageEncryptMode.BOTH;
                    break;
                default:
                    break;
            }
            if (messageEncryptMode == null) {
                messageEncryptMode = (encodingAesKey == null || encodingAesKey.isEmpty()) ?
                        WechatOfficialAccountConfig.MessageEncryptMode.PLAIN_TEXT :
                        WechatOfficialAccountConfig.MessageEncryptMode.CIPHER_TEXT;
            }
            String tmpDomainType = wechatOfficialAccountProperties.getDomainType();
            WechatOfficialAccountConfig.DomainType domainType;
            switch (tmpDomainType) {
                case "commonDisaster":
                    domainType = WechatOfficialAccountConfig.DomainType.COMMON_DISASTER;
                    break;
                case "shanghai":
                    domainType = WechatOfficialAccountConfig.DomainType.SHANGHAI;
                    break;
                case "shenzhen":
                    domainType = WechatOfficialAccountConfig.DomainType.SHENZHEN;
                    break;
                case "hongkong":
                    domainType = WechatOfficialAccountConfig.DomainType.HONG_KONG;
                    break;
                default:
                    domainType = WechatOfficialAccountConfig.DomainType.COMMON;
                    break;
            }
            wechatOfficialAccountConfig = new WechatOfficialAccountConfig(appId, appSecret, token, encodingAesKey,
                    messageEncryptMode, domainType);
        }
        return wechatOfficialAccountConfig;
    }

    @ConditionalOnMissingBean
    @Bean
    public BaseSupport baseSupport() throws WechatSettingNotConfiguredException {
        return new BaseSupport(wechatOfficialAccountConfig());
    }
}
