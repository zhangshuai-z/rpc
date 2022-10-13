package com.zhangs.framework.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/12 19:08
 * @author: zhangs
 */
public class LocalRegister {

    /**
     * String 接口名
     * Class 具体实现类
     */
    private static Map<String, Class> map = new HashMap<>();

    //向map中添加
    public static void regist(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    //从map中获取
    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }
}
