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

    // nacos server ip
    private static final String remoteAddress = "localhost:8848";
    // nacos group
    private static final String groupId = "SENTINEL_GROUP";
    // nacos dataId
    private static final String dataId = "rims-flow-rules";
    // if change to true, should be config NACOS_NAMESPACE_ID
    private static boolean isDemoNamespace = false;
    // fill your namespace id,if you want to use namespace. for example: 0f5c7314-4983-4022-ad5a-347de1d1057d,you can get it on nacos's console
//    private static final String NACOS_NAMESPACE_ID = "${namespace}";

    private static final String NACOS_NAMESPACE_ID = "a9c67ec0-364d-443d-8dd2-f2959e5e7325";

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, remoteAddress);
        properties.put(PropertyKeyConst.NAMESPACE, NACOS_NAMESPACE_ID);

        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(properties, groupId, dataId,
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
}