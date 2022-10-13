package com.zhangs.provider;

import com.zhangs.framework.URL;
import com.zhangs.framework.protocol.http.HttpServer;
import com.zhangs.framework.register.LocalRegister;
import com.zhangs.framework.register.RemoteMapRegister;
import com.zhangs.provider.api.HelloService;
import com.zhangs.provider.impl.HelloServiceImpl;

/**
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/12 15:36
 * @author: zhangs
 */
public class Provider {
    public static void main(String[] args) {

        //本地注册
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        //服务注册
        URL url = new URL("localhost", 8008);
        RemoteMapRegister.register(HelloService.class.getName(), url);

        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }
}
