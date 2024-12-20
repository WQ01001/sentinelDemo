/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.sentineldemo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.example.sentineldemo.dto.TssParamDTO;
import org.springframework.stereotype.Service;

/**
 * @author Eric Zhao
 */
@Service
public class DemoService {

    @SentinelResource(value = "DemoService#bonjour", blockHandler = "exceptionHandler", fallback = "helloFallback", defaultFallback = "bonjourFallback")
    public String bonjour(String name) {
        return "Bonjour, " + name;
    }

//    @SentinelResource(value = "tss", defaultFallback = "bonjourFallback")
//    public String tss(TssParamDTO tssParamDTO) {
//        String shamash = shamash(tssParamDTO);
//        System.out.println("shamash: " + shamash);
//        return "tss: " + tssParamDTO;
//    }
//
//    @SentinelResource(value = "task", defaultFallback = "bonjourFallback")
//    public String task(TssParamDTO tssParamDTO) {
//        String shamash = shamash(tssParamDTO);
//        System.out.println("shamash: " + shamash);
//        return "task: " + tssParamDTO;
//    }

    @SentinelResource(value = "shamash", defaultFallback = "bonjourFallback")
    public String shamash(TssParamDTO tssParamDTO) {
        String paramName = tssParamDTO.getParamName();
        switch (paramName) {
            case "T13":
                return T13(tssParamDTO);
            case "T9":
                return T9(tssParamDTO);
            case "T19":
                return T19(tssParamDTO);
            case "T6":
                return T6(tssParamDTO);
            case "T20":
                return T20(tssParamDTO);
            default:
                return "shamash: " + tssParamDTO;
        }
    }

    @SentinelResource(value = "T13", defaultFallback = "bonjourFallback")
    public String T13(TssParamDTO tssParamDTO) {
        return "request T13: " + tssParamDTO;
    }

    @SentinelResource(value = "R13", defaultFallback = "bonjourFallback")
    public String R13(TssParamDTO tssParamDTO) {
        return "request R13: " + tssParamDTO;
    }

    @SentinelResource(value = "T9", defaultFallback = "bonjourFallback")
    public String T9(TssParamDTO tssParamDTO) {
        return "T9: " + tssParamDTO;
    }

    @SentinelResource(value = "T19", defaultFallback = "bonjourFallback")
    public String T19(TssParamDTO tssParamDTO) {
        return "T19: " + tssParamDTO;
    }

    @SentinelResource(value = "T6", defaultFallback = "bonjourFallback")
    public String T6(TssParamDTO tssParamDTO) {
        return "T6: " + tssParamDTO;
    }

    @SentinelResource(value = "T20", defaultFallback = "bonjourFallback")
    public String T20(TssParamDTO tssParamDTO) {
        return "T20: " + tssParamDTO;
    }

    public String bonjourFallback(Throwable t) {
        if (BlockException.isBlockException(t)) {
            return "Blocked by Sentinel: " + t.getClass().getSimpleName();
        }
        return "Oops, failed: " + t.getClass().getCanonicalName();
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String helloFallback(String name, Throwable t) {
        return "Oops, Blocked by Sentinel: " + name + ":" + t.getClass().getCanonicalName();
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String exceptionHandler(String name, BlockException ex) {
        return "{\"code\": 429, \"message\": \"Request was blocked by Sentinel: " + ex.getClass().getSimpleName() + "\"}";
    }
}
