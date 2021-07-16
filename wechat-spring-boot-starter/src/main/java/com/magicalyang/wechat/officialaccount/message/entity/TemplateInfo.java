package com.magicalyang.wechat.officialaccount.message.entity;

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
public class TemplateInfo {
    @JsonProperty("template_id")
    String templateId;

    String title;

    @JsonProperty("primary_industry")
    String primaryIndustry;

    @JsonProperty("deputy_industry")
    String deputyIndustry;

    String content;

    String example;
}
