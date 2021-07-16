package com.magicalyang.wechat.starter.officialaccount;

import com.magicalyang.wechat.officialaccount.domain.TemplateDomain;
import com.magicalyang.wechat.officialaccount.message.req.TemplateMessage;
import com.magicalyang.wechat.common.exception.WechatErrorException;

/**
 * @author Constanline
 */
public class SendMessage {

    private static WechatOfficialAccountConfig wechatOfficialAccountConfig;

    private static TemplateDomain templateDomain;

    private final BaseSupport baseSupport;

    public SendMessage(WechatOfficialAccountConfig wechatOfficialAccountConfig, BaseSupport baseSupport) {
        this.baseSupport = baseSupport;
        if (SendMessage.wechatOfficialAccountConfig == null) {
            templateDomain = new TemplateDomain(wechatOfficialAccountConfig.getApiDomain());
            SendMessage.wechatOfficialAccountConfig = wechatOfficialAccountConfig;
        }
    }

    public Long sendTemplateMessage(TemplateMessage templateMessage) throws WechatErrorException {
        return templateDomain.sendTemplateMessage(baseSupport.getAccessToken(), templateMessage);
    }
}
