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

import org.example.sentineldemo.dto.TssParamDTO;
import org.example.sentineldemo.service.FooService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author Eric Zhao
 */
@RestController
@RequestMapping("/api")
public class DemoController {

    @Resource
    private FooService fooService;

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/hello")
    public String apiSayHello(@RequestParam String name) {
        return fooService.sayHello(name);
    }

    @GetMapping("/bonjour/{name}")
    public String apiSayHelloLocal(@PathVariable String name) {
        return demoService.bonjour(name);
    }

    @PostMapping("/tss")
    public String tss(@RequestBody TssParamDTO tssParamDTO) {
        return demoService.shamash(tssParamDTO);
    }

    @GetMapping("/t13")
    public String t13() {
        return demoService.T13(new TssParamDTO());
    }

    @GetMapping("/r13")
    public String r13() {
        return demoService.T13(new TssParamDTO());
    }

    @GetMapping("/t9")
    public String t9() {
        return demoService.T9(new TssParamDTO());
    }

    @GetMapping("/t20")
    public String t20() {
        return demoService.T20(new TssParamDTO());
    }

    @PostMapping("/task")
    public String task(@RequestBody TssParamDTO tssParamDTO) {
        return demoService.shamash(tssParamDTO);
    }


    @GetMapping("/time")
    public long apiCurrentTime(@RequestParam(value = "slow", defaultValue = "false") Boolean slow) {
        return fooService.getCurrentTime(slow);
    }
}
