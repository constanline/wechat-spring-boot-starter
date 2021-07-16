package com.magicalyang.wechat.common.exception;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * @author Constanline
 */
public class WechatFailureAnalyzer extends AbstractFailureAnalyzer<WechatSettingNotConfiguredException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, WechatSettingNotConfiguredException cause) {
        return new FailureAnalysis(cause.getLocalizedMessage(), "Missing configuration", cause);
    }
}
