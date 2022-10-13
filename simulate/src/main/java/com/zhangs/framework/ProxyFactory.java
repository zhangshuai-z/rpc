package com.zhangs.framework;

import com.zhangs.framework.protocol.http.HttpClient;
import com.zhangs.framework.register.RemoteMapRegister;
import com.zhangs.provider.api.HelloService;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 动态代理（dynamic proxy）
 *            利用Java的反射技术(Java Reflection)，在运行时创建一个实现某些给定接口的新类（也称“动态代理类”）及其实例（对象）,代理的是接口(Interfaces)，不是类(Class)，也不是抽象类。在运行时才知道具体的实现，spring aop就是此原理。
 *
 * newProxyInstance，方法有三个参数：
 * loader: 用哪个类加载器去加载代理对象
 * interfaces:动态代理类需要实现的接口
 * h:动态代理方法在执行时，会调用h里面的invoke方法去执行
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/12 20:59
 * @author: zhangs
 */
public class ProxyFactory<T> {
    public static <T> T getProxy(final Class<HelloService> interfaceClass) {
        //读取用户的配置
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                try {

                    HttpClient httpClient = new HttpClient();

                    Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                    //注册中心
                    List<URL> urls = RemoteMapRegister.get(interfaceClass.getName());

                    //实现负载均衡
                    URL url = LoadBalance.random(urls);

                    String result = httpClient.send(url.getHostname(), url.getPort(), new Invocation(HelloService.class.getName(), method.getName(), new Class[]{String.class}, args));

                    return result;
                }catch (Exception e) {
                    //容错信息
                    return "报错了";
                }
            }
        });

    }
}
