package com.zhangs.provider.impl;

import com.zhangs.provider.api.HelloService;

/**
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/12 15:37
 * @author: zhangs
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello: " + name;
    }
}
