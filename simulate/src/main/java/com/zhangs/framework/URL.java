package com.zhangs.framework;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/13 16:54
 * @author: zhangs
 */
@Data
@AllArgsConstructor
public class URL implements Serializable {
    //服务地址
    private String hostname;
    //端口号
    private Integer port;
}
