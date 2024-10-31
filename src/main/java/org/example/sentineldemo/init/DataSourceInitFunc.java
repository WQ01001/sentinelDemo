package org.example.sentineldemo.init;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;

import java.util.List;
import java.util.Properties;

public class DataSourceInitFunc implements InitFunc {

    @Override
    public void init() {
        System.out.println("Init DataSourceInitFunc");
        final String remoteAddress = "localhost:8848";
//        final int port = 6379;
//        final String ruleKey = "sentinel.rules.flow.ruleKey";
//        final String channel = "sentinel.rules.flow.channel";
        final String groupId = "Sentinel_Demo";
        final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";

        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(remoteAddress, groupId, dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
//        ------------redis----------------
//        Converter<String, List<FlowRule>> flowConfigParser = buildFlowConfigParser();
//        RedisConnectionConfig config = RedisConnectionConfig.builder()
//                .withHost(remoteAddress)
//                .withPort(port)
//                .build();
//        ReadableDataSource<String, List<FlowRule>> redisDataSource = new RedisDataSource<>(config,
//                ruleKey, channel, flowConfigParser);
//        FlowRuleManager.register2Property(redisDataSource.getProperty());
    }

//    private Converter<String, List<FlowRule>> buildFlowConfigParser() {
//        return source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
//        });
//    }
}