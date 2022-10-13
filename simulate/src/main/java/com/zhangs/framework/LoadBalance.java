package com.zhangs.framework;

import java.util.List;
import java.util.Random;

/**
 * @PROJECT_NAME: rpc
 * @DATE: 2022/10/13 19:20
 * @author: zhangs
 */
public class LoadBalance {
    public static URL random(List<URL> list) {
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }
}
