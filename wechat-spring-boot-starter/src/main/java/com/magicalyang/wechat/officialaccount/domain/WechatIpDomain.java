package com.magicalyang.wechat.officialaccount.domain;

import com.magicalyang.wechat.officialaccount.message.resp.WechatIpResp;
import com.magicalyang.wechat.common.exception.WechatErrorException;
import com.magicalyang.wechat.common.util.HttpUtil;
import lombok.val;

import java.util.List;

/**
 * @author Constanline
 */
public class WechatIpDomain extends ApiDomainDomain {
    private static final String API_DOMAIN_IP_URL = "https://API_DOMAIN/cgi-bin/get_api_domain_ip?access_token=ACCESS_TOKEN";

    private static final String CALLBACK_IP_URL = "https://API_DOMAIN/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";

    private String apiDomainIpUrl;

    private String callbackIpUrl;

    public WechatIpDomain(String apiDomain) {
        super(apiDomain, "apiDomainIpUrl", "callbackIpUrl");
//        apiDomainIpUrl = API_DOMAIN_IP_URL.replace("API_DOMAIN", apiDomain);
//        callbackIpUrl = CALLBACK_IP_URL.replace("API_DOMAIN", apiDomain);
    }

    public List<String> getApiDomainIp(String accessToken) throws WechatErrorException {
        val message = HttpUtil.get(apiDomainIpUrl.replace("ACCESS_TOKEN", accessToken),
                WechatIpResp.class);
        return message.getIpList();
    }

    public List<String> getCallbackIp(String accessToken) throws WechatErrorException {
        val message = HttpUtil.get(callbackIpUrl.replace("ACCESS_TOKEN", accessToken),
                WechatIpResp.class);
        return message.getIpList();
    }
}
