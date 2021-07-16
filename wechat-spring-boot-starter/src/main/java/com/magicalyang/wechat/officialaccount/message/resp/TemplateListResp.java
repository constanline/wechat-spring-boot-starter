package com.magicalyang.wechat.officialaccount.message.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.magicalyang.wechat.common.message.BaseResp;
import com.magicalyang.wechat.officialaccount.message.entity.TemplateInfo;
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
public class TemplateListResp extends BaseResp {

    @JsonProperty("template_list")
    List<TemplateInfo> templateList;
}
