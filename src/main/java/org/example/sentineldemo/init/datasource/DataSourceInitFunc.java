package org.example.sentineldemo.init.datasource;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;

import java.util.List;

public class DataSourceInitFunc implements InitFunc {

    @Override
    public void init() throws Exception {
        final String remoteAddress = "localhost";
        final String groupId = "Sentinel:Demo";
        final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";
    }
}