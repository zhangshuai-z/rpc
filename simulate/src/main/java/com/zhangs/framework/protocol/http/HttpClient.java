package com.zhangs.framework.protocol.http;

import com.zhangs.framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/12 21:00
 * @author: zhangs
 */
public class HttpClient {

    public String send(String hostname, Integer port, Invocation invocation) {
        // 用户的配置
        try {
            /**
             * public URL(String protocol, String host, int port, String file) throws MalformedURLException.
             * 通过给定的参数(协议、主机名、端口号、文件名)创建URL。
             */
            URL url = new URL("http", hostname, port, "/");
            /**
             * 	public URLConnection openConnection() throws IOException
             * 打开一个URL连接，并运行客户端访问资源。
             */
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //设置请求方式
            httpURLConnection.setRequestMethod("POST");
            /**
             * public void setDoOutput(boolean output)
             * URL 连接可用于输入和/或输出。如果打算使用 URL 连接进行输出，则将 DoOutput 标志设置为 true；如果不打算使用，则设置为 false。默认值为 false。
             */
            httpURLConnection.setDoOutput(true);

            /**
             * public OutputStream getOutputStream() throws IOException
             * 返回URL的输出流, 用于写入资源。
             */
            OutputStream outputStream = httpURLConnection.getOutputStream();
            /**
             * ObjectOutputStream 类用来序列化一个对象
             */
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            /**
             * 写入对象
             */
            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            /**
             * 	public InputStream getInputStream() throws IOException
             * 返回URL的输入流，用于读取资源
             */
            InputStream inputStream = httpURLConnection.getInputStream();

            String result = IOUtils.toString(inputStream);
            return result;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
