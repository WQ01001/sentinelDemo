package org.example.sentineldemo.config;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.datasource.redis.RedisDataSource;
import com.alibaba.csp.sentinel.datasource.redis.config.RedisConnectionConfig;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

//@Component
public class SentinelRuleConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    private static final String remoteAddress = "10.105.210.225:8848";
    // nacos group
    private static final String groupId = "Sentinel_Demo";
    // nacos dataId
    private static final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";

    private static final String NACOS_NAMESPACE_ID = "SENTINEL";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        Properties properties = new Properties();
//        properties.put(PropertyKeyConst.SERVER_ADDR, remoteAddress);
////        properties.put(PropertyKeyConst.NAMESPACE, NACOS_NAMESPACE_ID);
//
//        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(properties, groupId,
//                dataId,
//                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
//                }));
//        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

        final String remoteAddress = "127.0.0.1";
        final int port = 6379;
        final String ruleKey = "sentinel.rules.flow.ruleKey";
        final String channel = "sentinel.rules.flow.channel";

        Converter<String, List<FlowRule>> flowConfigParser = buildFlowConfigParser();
        RedisConnectionConfig config = RedisConnectionConfig.builder()
                .withHost(remoteAddress)
                .withPort(port)
                .build();
        ReadableDataSource<String, List<FlowRule>> redisDataSource = new RedisDataSource<>(config,
                ruleKey, channel, flowConfigParser);
        FlowRuleManager.register2Property(redisDataSource.getProperty());
    }

    private Converter<String, List<FlowRule>> buildFlowConfigParser() {
        return source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
        });
    }
}