package org.example.sentineldemo.service.impl;

import org.example.sentineldemo.service.FooService;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FooServiceImpl implements FooService {
    @Override
    public String sayHello(String name) {
//        initFlowRules();
        try {
            Entry entry = SphU.entry("helloSentinel");
            return "helloSentinel success.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "helloSentinel error";
        }
    }

    @Override
    public long getCurrentTime(boolean slow) {
        return System.currentTimeMillis();
    }

    public void initFlowRules() {
        //1.创建存放限流规则的集合
        List<FlowRule> rules = new ArrayList<>();
        //2.创建限流规则
        FlowRule rule = new FlowRule();
        //定义资源，表示sentinel会对这个资源生效
        rule.setResource("helloSentinel");
        //定义限流规则类型
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //定义QPS每秒能通过的请求个数
        rule.setCount(1);
        //3.将限流规则放入集合中
        rules.add(rule);
        //4.加载限流规则
        FlowRuleManager.loadRules(rules);
    }
}
