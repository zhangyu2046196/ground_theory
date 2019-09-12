package com.youyuan.reblance;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author zhangyu
 * @version 1.0
 * @description 负载均衡算法—带权重值随机算法
 * @date 2019/7/8 9:51
 */
public class RandomWeightTest {

    public static void main(String[] args) {
        List<String> ipListCopy=new LinkedList<String>();
        for (Map.Entry<String,Integer> entry:ServiceIp.ipMap.entrySet()){
            for (int i=1;i<=entry.getValue();i++){
                ipListCopy=transIpList(ipListCopy,entry.getKey());
            }
        }

        System.out.println("权重值:"+ipListCopy.size());

        for (int i=1;i<=100;i++){
            System.out.println(getIpByWeightCopy(ipListCopy));
        }

    }

    /**
     * 通过权重值是多少就复制多少份ip列表的方式实现随机负载均衡
     * @param ipList 根据权重值组装好的ip列表
     * @return ip
     */
    private static String getIpByWeightCopy(List<String> ipList){
        return ipList.get(new Random().nextInt(ipList.size()));
    }

    /**
     * 根据权重值组装ip列表
     * @param ipList ip列表
     * @param ip 要加入的ip
     * @return 返回组装好的ip 列表
     */
    private static List<String> transIpList(List<String> ipList,String ip){
        if (ipList!=null){
            ipList.add(ip);
        }
        return ipList;
    }

}
