package org.example.sentineldemo.config;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
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

@Component
public class SentinelRuleConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    private static final String remoteAddress = "localhost:8848";
    // nacos group
    private static final String groupId = "Sentinel_Demo";
    // nacos dataId
    private static final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";

    private static final String NACOS_NAMESPACE_ID = "SENTINEL";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, remoteAddress);
//        properties.put(PropertyKeyConst.NAMESPACE, NACOS_NAMESPACE_ID);

        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(properties, groupId,
                dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }
}