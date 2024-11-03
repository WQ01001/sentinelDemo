package org.example.sentineldemo.handler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;

import java.util.regex.Pattern;

public class WebUrlCleaner implements UrlCleaner {
    /**
     * 正则匹配
     */
    private static final String PATTERN_SENTINEL_TEST_NAME = "^/name/[0-9a-zA-Z]+$";

    private static final Pattern pattern = Pattern.compile(PATTERN_SENTINEL_TEST_NAME);


    @Override
    public String clean(String originUrl) {
        if (pattern.matcher(originUrl).matches()) {
            return "/name/*";
        } else {
            return originUrl;
        }
    }
}
