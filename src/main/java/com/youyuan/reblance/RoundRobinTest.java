package com.youyuan.reblance;

/**
 * @author zhangyu
 * @version 1.0
 * @description 负载均衡算法—普通轮询
 * @date 2019/7/8 13:36
 */
public class RoundRobinTest {

    private static Integer posisent=0;

    public static void main(String[] args) {
        for (int i=1;i<=50;i++){
            System.out.println(getIpByRoundRobin());
        }
    }

    /**
     * 获取ip列表索引值
     * @return
     */
    private static String getIpByRoundRobin(){
        if (posisent>=ServiceIp.ipList.size()){
            posisent=0;
        }
        return ServiceIp.ipList.get(posisent++);
    }

}
