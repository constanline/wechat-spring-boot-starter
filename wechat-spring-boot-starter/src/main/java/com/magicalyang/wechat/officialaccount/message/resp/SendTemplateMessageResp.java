package com.magicalyang.wechat.officialaccount.message.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.magicalyang.wechat.common.message.BaseResp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Constanline
 */
@Getter
@Setter
@ToString
public class SendTemplateMessageResp extends BaseResp {
    @JsonProperty("msgid")
    long msgId;
}
