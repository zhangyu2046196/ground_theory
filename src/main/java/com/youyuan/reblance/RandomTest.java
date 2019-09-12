package com.youyuan.reblance;


import java.util.Random;

/**
 * @author zhangyu
 * @version 1.0
 * @description 负载均衡算法—普通随机算法
 * @date 2019/7/8 9:33
 */
public class RandomTest {

    public static void main(String[] args) {
        //模拟并发请求
        for (int i=1;i<=50;i++){
            System.out.println("ip:"+ServiceIp.getIp(new Random().nextInt(ServiceIp.ipList.size())));
        }
    }

}
