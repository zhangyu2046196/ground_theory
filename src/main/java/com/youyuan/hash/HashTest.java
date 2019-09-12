package com.youyuan.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyu
 * @version 1.0
 * @description HashMap底层原理解析
 * @date 2019/7/4 13:43
 */
public class HashTest {

    public static void main(String[] args) {
        Map<String,String> hashMap=new HashMap<String, String>();

        hashMap.put("北京","北京");
        hashMap.put("上海","上海");
        hashMap.put("广州","广州");
        hashMap.put("深圳","深圳");
        hashMap.put("天津","天津");
        hashMap.put("香港","香港");

        for (Map.Entry<String,String> entry:hashMap.entrySet()){
            System.out.println(String.format("key是%s,hashCode是%s,indexOrder是%s",entry.getKey(),entry.getKey().hashCode(),entry.getKey().hashCode()%hashMap.size()));
        }

    }

}
