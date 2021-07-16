package com.magicalyang.wechat.demo.controller;

import com.magicalyang.wechat.demo.VO.DataVO;
import com.magicalyang.wechat.demo.VO.MessageVO;
import com.magicalyang.wechat.starter.officialaccount.BaseSupport;
import com.magicalyang.wechat.common.exception.WechatErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Constanline
 */
@RestController
@RequestMapping(value = "/base_support")
public class BaseSupportController {

    BaseSupport baseSupport;

    public BaseSupportController(BaseSupport baseSupport) {
        this.baseSupport = baseSupport;
    }

    @GetMapping(value = "/access_token")
    public MessageVO getAccessToken() {
        try {
            return new DataVO<>(baseSupport.getAccessToken());
        } catch (WechatErrorException e) {
            return new MessageVO(e.getCause().getMessage());
        }
    }
}
