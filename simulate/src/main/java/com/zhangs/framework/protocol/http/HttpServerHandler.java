package com.zhangs.framework.protocol.http;

import com.zhangs.framework.Invocation;
import com.zhangs.framework.register.LocalRegister;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;

/**
 * 请求处理器
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/12 18:45
 * @author: zhangs
 */
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {

        //实现处理请求的逻辑
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();

            String interfaceName = invocation.getInterfaceName();

            //根据接口名字拿的到具体的实现类
            Class classImpl = LocalRegister.get(interfaceName);

            //根据反射拿到实现类的方法
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParamType());

            //根据反射执行实现类的方法
            Object result = method.invoke(classImpl.newInstance(), invocation.getParams());

            //将结果返回
            IOUtils.write((String) result, resp.getOutputStream());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
