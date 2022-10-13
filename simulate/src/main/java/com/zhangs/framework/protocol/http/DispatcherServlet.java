package com.zhangs.framework.protocol.http;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/12 18:39
 * @author: zhangs
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 读取配置
        new HttpServerHandler().handler(req, resp);
    }

}
