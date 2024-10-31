/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.example.sentineldemo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;

import java.util.Properties;

/**
 * Nacos config sender for demo.
 *
 * @author Eric Zhao
 */
public class NacosConfigSender {

    public static void main(String[] args) throws Exception {
        final String remoteAddress = "127.0.0.1:8848";
        final String groupId = "SENTINEL_GROUP";
        final String dataId = "sentinel-demo-flow-rules";
        final String rule = "[\n"
                + "  {\n"
                + "    \"resource\": \"DemoService#bonjour\",\n"
                + "    \"controlBehavior\": 0,\n"
                + "    \"count\": 1,\n"
                + "    \"grade\": 1,\n"
                + "    \"limitApp\": \"default\",\n"
                + "    \"strategy\": 0\n"
                + "  }\n"
                + "]";

        Properties properties = new Properties();
        properties.put("serverAddr", remoteAddress);

        ConfigService configService = NacosFactory.createConfigService(properties);
        boolean isPublished = configService.publishConfig(dataId, groupId, rule);

        System.out.println("配置发布状态：" + (isPublished ? "成功" : "失败"));
    }
}
