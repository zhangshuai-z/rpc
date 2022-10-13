package com.zhangs.framework.register;

import com.zhangs.framework.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 远程map注册中心等价于zookeeper
 *
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/13 16:55
 * @author: zhangs
 */
public class RemoteMapRegister {
    public static Map<String, List<URL>> REGISTER = new HashMap<>();

    public static void register(String interfaceName, URL url) {

        List<URL> list = REGISTER.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(url);

        REGISTER.put(interfaceName, list);

        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        REGISTER = getFile();
        List<URL> list = REGISTER.get(interfaceName);
        return list;
    }


    private static void saveFile() {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\zhangs\\IdeaProjects\\rpc\\simulate\\src\\main\\java\\com\\zhangs\\temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\zhangs\\IdeaProjects\\rpc\\simulate\\src\\main\\java\\com\\zhangs\\temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
