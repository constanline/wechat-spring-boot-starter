package com.magicalyang.wechat.officialaccount.domain;

import com.magicalyang.wechat.officialaccount.message.entity.TemplateInfo;
import com.magicalyang.wechat.officialaccount.message.req.TemplateMessage;
import com.magicalyang.wechat.officialaccount.message.resp.SendTemplateMessageResp;
import com.magicalyang.wechat.officialaccount.message.resp.TemplateListResp;
import com.magicalyang.wechat.common.exception.WechatErrorException;
import com.magicalyang.wechat.common.util.HttpUtil;
import lombok.val;

import java.util.List;

/**
 * @author Constanline
 */
public class TemplateDomain extends ApiDomainDomain {
    private static final String ALL_PRIVATE_TEMPLATE_URL = "https://API_DOMAIN/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";

    private static final String SEND_TEMPLATE_MESSAGE_URL = "https://API_DOMAIN/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    private String allPrivateTemplateUrl;

    private String sendTemplateMessageUrl;

    public TemplateDomain(String apiDomain) {
        super(apiDomain, "allPrivateTemplateUrl", "sendTemplateMessageUrl");
    }

    public List<TemplateInfo> getAllPrivateTemplate(String accessToken) throws WechatErrorException {
        val message = HttpUtil.get(allPrivateTemplateUrl.replace("ACCESS_TOKEN", accessToken),
                TemplateListResp.class);
        return message.getTemplateList();
    }

    public Long sendTemplateMessage(String accessToken, TemplateMessage templateMessage) throws WechatErrorException {
        val message = HttpUtil.post(sendTemplateMessageUrl.replace("ACCESS_TOKEN", accessToken),
                templateMessage, SendTemplateMessageResp.class);
        return message.getMsgId();
    }
}

