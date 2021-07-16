package com.magicalyang.wechat.common.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Constanline
 */
@Getter
@Setter
@ToString
public class BaseResp {

    @JsonProperty("errcode")
    private int errCode;

    @JsonProperty("errmsg")
    private String errMsg;
}
