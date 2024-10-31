package org.example.sentineldemo.init;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

public class DataSourceInitFunc implements InitFunc {

    private static final String KEY = "TestResource";
    // nacos server ip
    private static final String remoteAddress = "localhost:8848";
    // nacos group
    private static final String groupId = "Sentinel_Demo";
    // nacos dataId
    private static final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";
    // if change to true, should be config NACOS_NAMESPACE_ID
    private static boolean isDemoNamespace = false;
    // fill your namespace id,if you want to use namespace. for example: 0f5c7314-4983-4022-ad5a-347de1d1057d,you can get it on nacos's console
//    private static final String NACOS_NAMESPACE_ID = "${namespace}";

    private static final String NACOS_NAMESPACE_ID = "SENTINEL";

    @Override
    public void init() {
        System.out.println("Init DataSourceInitFunc");
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(remoteAddress, groupId, dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
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