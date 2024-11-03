package org.example.sentineldemo.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.example.sentineldemo.handler.WebUrlBlockHandler;
import org.example.sentineldemo.handler.WebUrlCleaner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SentinelConfig {
    static {
        //设置自定义的Block异常处理
        WebCallbackManager.setUrlBlockHandler(new WebUrlBlockHandler());
        //设置url清洗
        WebCallbackManager.setUrlCleaner(new WebUrlCleaner());
    }
}
