package com.youyuan.reblance;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyu
 * @version 1.0
 * @description 模拟服务器列表
 * @date 2019/7/8 9:34
 */
public class ServiceIp {

    public static List<String> ipList=new LinkedList<String>();

    public static Map<String,Integer> ipMap=new HashMap<String,Integer>(); //有权重值的hashmap

    static {
        ipList.add("192.168.0.1");
        ipList.add("192.168.0.2");
        ipList.add("192.168.0.3");
        ipList.add("192.168.0.4");
        ipList.add("192.168.0.5");
        ipList.add("192.168.0.6");
        ipList.add("192.168.0.7");
        ipList.add("192.168.0.8");
        ipList.add("192.168.0.9");
        ipList.add("192.168.0.10");

        ipMap.put("192.168.0.1",2);
        ipMap.put("192.168.0.2",7);
        ipMap.put("192.168.0.3",8);
        ipMap.put("192.168.0.4",9);
        ipMap.put("192.168.0.5",1);
        ipMap.put("192.168.0.6",3);
        ipMap.put("192.168.0.7",8);
        ipMap.put("192.168.0.8",6);
        ipMap.put("192.168.0.9",5);
        ipMap.put("192.168.0.10",6);
    }

    /**
     * 根据索引值查询ip
     * @param index
     * @return
     */
    public static String getIp(Integer index){
        return ipList.get(index);
    }

}
