package com.magicalyang.wechat.officialaccount.message.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Constanline
 */
@Getter
@Setter
@ToString
public class TemplateMessage {
    @Getter
    @Setter
    @ToString
    public static class MiniProgramPage {
        @JsonProperty("appid")
        String appid;

        @JsonProperty("pagepath")
        String pagePath;

        public MiniProgramPage(String appId, String pagePath) {
            this.appid = appId;
            this.pagePath = pagePath;
        }
    }

    @JsonProperty("touser")
    String toUser;

    @JsonProperty("template_id")
    String templateId;

    String url;

    @JsonProperty("miniprogram")
    MiniProgramPage miniProgramPage;

    JsonNode data;

    public TemplateMessage(String toUser, String templateId) {
        this.toUser = toUser;
        this.templateId = templateId;
    }
}
