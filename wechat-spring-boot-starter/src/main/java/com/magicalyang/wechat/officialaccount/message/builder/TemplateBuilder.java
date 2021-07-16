package com.magicalyang.wechat.officialaccount.message.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.magicalyang.wechat.officialaccount.message.req.TemplateMessage;

/**
 * @author Constanline
 */
public class TemplateBuilder {
    TemplateMessage templateMessage;

    public TemplateBuilder(String toUser, String templateId) {
        templateMessage = new TemplateMessage(toUser, templateId);
    }

    public TemplateMessage build() {
        return templateMessage;
    }

    public TemplateBuilder url(String url) {
        templateMessage.setUrl(url);
        return this;
    }

    public TemplateBuilder miniProgramPath(String appId, String pagePath) {
        templateMessage.setMiniProgramPage(new TemplateMessage.MiniProgramPage(appId, pagePath));
        return this;
    }

    public TemplateBuilder data(JsonNode data) {
        templateMessage.setData(data);
        return this;
    }
}
