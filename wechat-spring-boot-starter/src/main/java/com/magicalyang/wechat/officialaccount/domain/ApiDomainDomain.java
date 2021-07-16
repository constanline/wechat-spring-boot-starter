package com.magicalyang.wechat.officialaccount.domain;

import com.magicalyang.wechat.common.util.StringUtil;

import java.lang.reflect.Field;

class ApiDomainDomain {

    public ApiDomainDomain(String apiDomain, String... urls) {
        Class<?> cls = this.getClass();
        for (String url : urls) {
            try {
                String staticUrl = StringUtil.camelToUnderline(url, true);
                Field fieldUrl = cls.getDeclaredField(url);
                Field fieldStaticUrl = cls.getDeclaredField(staticUrl);
                fieldStaticUrl.setAccessible(true);
                String domain = (String)fieldStaticUrl.get(this);
                fieldUrl.setAccessible(true);
                fieldUrl.set(this, domain.replace("API_DOMAIN", apiDomain));
            } catch (NoSuchFieldException | IllegalAccessException ignored) {

            }
        }
    }
}
