package com.magicalyang.wechat.officialaccount.domain;

import com.magicalyang.wechat.officialaccount.message.resp.AccessTokenResp;
import com.magicalyang.wechat.common.exception.WechatErrorException;
import com.magicalyang.wechat.common.util.FileUtil;
import com.magicalyang.wechat.common.util.HttpUtil;
import lombok.val;

import java.io.File;

/**
 * @author Constanline
 */
public class AccessTokenDomain extends ApiDomainDomain {
    private static final String ACCESS_TOKEN_URL = "https://API_DOMAIN/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private String accessTokenUrl;

    private final File file;

    String accessToken;

    Long expiredTime = 0L;

    public AccessTokenDomain(String apiDomain, String appId, String appSecret) {
        super(apiDomain, "accessTokenUrl");
        accessTokenUrl = accessTokenUrl.replace("APPID", appId).replace("APPSECRET", appSecret);
        file = FileUtil.getFile("access_token", appId);
        String record = FileUtil.readFile(file);
        if (record.contains("|")) {
            String[] arr = record.split("\\|");
            try {
                expiredTime = Long.parseLong(arr[1]);
                accessToken = arr[0];
            } catch (Throwable ignore) { }
        }
    }

    public String getAccessToken() throws WechatErrorException {
        if (System.currentTimeMillis() >= expiredTime) {
            val message = HttpUtil.get(accessTokenUrl, AccessTokenResp.class);
            accessToken = message.getAccessToken();
            expiredTime = System.currentTimeMillis() + message.getExpiresIn();
            FileUtil.writeFile(file, accessToken + "|" + expiredTime);
        }
        return accessToken;
    }
}
