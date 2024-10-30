package org.example.sentineldemo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

@SpringBootTest
class SentinelDemoApplicationTests {

    @Test
    void contextLoads() throws NacosException {
        final String groupId = "SENTINEL_GROUP";
        final String dataId = "sentinel-demo-flow-rules";
        // 创建ConfigService实例
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, "10.10.10.12:8848");
        // 指定namespace
        properties.put(PropertyKeyConst.NAMESPACE, "SENTINEL");
        final String rule = "[\n"
                + "  {\n"
                + "    \"resource\": \"GET:/user/getById\",\n"
                + "    \"controlBehavior\": 0,\n"
                + "    \"count\": 1,\n"
                + "    \"grade\": 1,\n"
                + "    \"limitApp\": \"default\",\n"
                + "    \"strategy\": 0\n"
                + "  }\n"
                + "]";
        ConfigService configService = NacosFactory.createConfigService(properties);
        System.out.println(configService.publishConfig(dataId, groupId, rule));
    }

}
