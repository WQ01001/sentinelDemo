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
package org.example.sentineldemo.service;

/**
 * 该接口为 Dubbo 的服务端、消费端公用的接口定义。
 * 当前案例中，通过复制代码的方式实现接口发布，这不是最优雅的使用方法。更好的建议是通过maven坐标的方式独立维护api。
 */
public interface FooService {

    String sayHello(String name);

    long getCurrentTime(boolean slow);
}
