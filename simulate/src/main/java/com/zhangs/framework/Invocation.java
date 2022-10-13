package com.zhangs.framework;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/12 18:55
 * @author: zhangs
 */
@Data
@AllArgsConstructor
public class Invocation implements Serializable {

    //接口名
    private String interfaceName;
    //方法名
    private String methodName;
    //参数类型列表
    private Class[] paramType;
    //参数
    private Object[] params;

}
