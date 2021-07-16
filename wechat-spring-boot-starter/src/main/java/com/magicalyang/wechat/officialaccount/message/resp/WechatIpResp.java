package com.magicalyang.wechat.officialaccount.message.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.magicalyang.wechat.common.message.BaseResp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Constanline
 */
@Getter
@Setter
@ToString
public class WechatIpResp extends BaseResp {
    @JsonProperty("ip_list")
    List<String> ipList;
}
