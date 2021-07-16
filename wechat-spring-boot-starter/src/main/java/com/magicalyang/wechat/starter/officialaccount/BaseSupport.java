package com.magicalyang.wechat.starter.officialaccount;

import com.magicalyang.wechat.officialaccount.domain.AccessTokenDomain;
import com.magicalyang.wechat.officialaccount.domain.WechatIpDomain;
import com.magicalyang.wechat.common.exception.WechatErrorException;
import com.magicalyang.wechat.common.util.EncryptUtil;
import lombok.val;

import java.util.Arrays;
import java.util.List;

/**
 * @author Constanline
 */
public class BaseSupport {

    private static WechatOfficialAccountConfig wechatOfficialAccountConfig;

    private static AccessTokenDomain accessTokenDomain;

    private static WechatIpDomain wechatIpDomain;

    public BaseSupport(WechatOfficialAccountConfig wechatOfficialAccountConfig) {
        if (BaseSupport.wechatOfficialAccountConfig == null) {
            accessTokenDomain = new AccessTokenDomain(wechatOfficialAccountConfig.getApiDomain(),
                    wechatOfficialAccountConfig.getAppId(), wechatOfficialAccountConfig.getAppSecret());
            wechatIpDomain = new WechatIpDomain(wechatOfficialAccountConfig.getApiDomain());
            BaseSupport.wechatOfficialAccountConfig = wechatOfficialAccountConfig;
        }
    }

    public String getAccessToken() throws WechatErrorException {
        return accessTokenDomain.getAccessToken();
    }

    public List<String> getApiDomainIp() throws WechatErrorException {
        return wechatIpDomain.getApiDomainIp(accessTokenDomain.getAccessToken());
    }

    public List<String> getCallbackIp() throws WechatErrorException {
        return wechatIpDomain.getCallbackIp(accessTokenDomain.getAccessToken());
    }

    public Boolean checkSignature(String signature, String timestamp, String nonce) {
        val token = wechatOfficialAccountConfig.getToken();
        val tmpArr = new String[] { token, timestamp, nonce };
        Arrays.sort(tmpArr);
        String sign;
        try {
            sign = EncryptUtil.sha1(String.join("", tmpArr));
        } catch (Exception e) {
            sign = "";
        }

        return sign.equals(signature);
    }
}
