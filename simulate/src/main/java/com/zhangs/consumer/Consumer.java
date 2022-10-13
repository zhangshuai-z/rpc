package com.zhangs.consumer;

import com.zhangs.framework.Invocation;
import com.zhangs.framework.ProxyFactory;
import com.zhangs.framework.protocol.http.HttpClient;
import com.zhangs.provider.api.HelloService;

/**
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/12 15:32
 * @author: zhangs
 */
public class Consumer {
    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("zhangs123");

        System.out.println(result);
    }
}
