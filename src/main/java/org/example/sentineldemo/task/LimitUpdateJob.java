package org.example.sentineldemo.task;

import com.alibaba.csp.sentinel.node.ClusterNode;
import com.alibaba.csp.sentinel.slots.clusterbuilder.ClusterBuilderSlot;
import com.alibaba.csp.sentinel.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Slf4j
public class LimitUpdateJob {

    @Scheduled(cron = "0/10 * * * * ?")
    public void updateLimit() {
        // 获取资源的 ClusterNode
        ClusterNode t13Node = getClusterNode("T13");
        ClusterNode t9Node = getClusterNode("T9");
        if (t13Node == null) {
            return;
        }
        System.out.println("Current QPS: " + t13Node.passQps());
    }


    /**
     * 获取资源的 ClusterNode
     *
     * @param resourceName 资源名称
     * @return ClusterNode 对象
     */
    private static ClusterNode getClusterNode(String resourceName) {
        if (StringUtil.isBlank(resourceName)) {
            return null;
        }

        return ClusterBuilderSlot.getClusterNode(resourceName);
    }
}
