package com.youyuan.reblance;

import java.util.Map;
import java.util.Random;

/**
 * @author zhangyu
 * @version 1.0
 * @description  负载均衡算法—加权随机
 * 比如权重值是[3,5,2]
 *0---3-----8--10  权重值组成一个水平线
 *思路：在总的权重值(上面是10)里面随机，随机一个数然后遍历数组[3,5,2]，对比如果随机数小于数组中的权重值，就用数组中
 * 的权重对应的IP，否则就减去权重值，然后在和数组中的下个权重值对比，直到符合小于数组权重值的IP
 * @date 2019/7/8 10:14
 */
public class RandomWeightPlusTest {

    public static void main(String[] args) {
        Integer weightCount=0;
        for (Map.Entry<String,Integer> entry:ServiceIp.ipMap.entrySet()){
            weightCount+=entry.getValue();
        }

        System.out.println("权重值："+weightCount);

        for (int i=1;i<=50;i++){
            System.out.println(getIpByWeightPlus(weightCount));
        }

    }

    /**
     * 加权随机算法
     * @param weightCount 总的权重值
     * @return 返回ip
     */
    private static String getIpByWeightPlus(Integer weightCount){
        int random= new Random().nextInt(weightCount);
        for (Map.Entry<String,Integer> entry:ServiceIp.ipMap.entrySet()){
            if (random<entry.getValue()){
                return entry.getKey();
            }
            random=random-entry.getValue();
        }
        return null;
    }

}
