package com.youyuan.reblance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 * @version 1.0
 * @description 负载均衡—加权平滑轮询算法
 * @date 2019/7/8 20:36
 *
 * 思路：
 * 例如Ip列表为  AAAAABC,需要转换成AABAACA来轮询
 * weight:固定权重值   currentWeight:动态变化权重值  totalWeight:总权重值 sum(weight)
 * 实现方案，循环出Ip列表中最大的weight的Ip,返回出去,然后将最大Ip的weight-totalWeight,然后Ip列表中的每个Ip的
 * currentWeight=currentWeight+weight
 */
public class RoundRobinTestV2 {

    private static Map<String,Weight> weightMap=new HashMap<String,Weight>();

    public static void main(String[] args) {

        //计算总的权重值和初始化weightMap
        int totalWeight=0;
        for (Map.Entry<String,Integer> entry:ServiceIp.ipMap.entrySet()){
            totalWeight+=entry.getValue();
            weightMap.put(entry.getKey(),new Weight(entry.getKey(),entry.getValue(),entry.getValue()));
        }

        for (int i=1;i<50;i++){
            System.out.println(getIpByCurrentWeight(totalWeight));
        }

    }

    /**
     * 获取加权平滑轮询负载均衡服务地址
     * @param totalWeight 总权重值
     * @return 返回服务地址
     */
    private static String getIpByCurrentWeight(int totalWeight) {
        //计算当前weightMap列表中currentWeight最大的值
        Weight maxCurrentWeight=null;
        for (Map.Entry<String,Weight> entry:weightMap.entrySet()){
            if (maxCurrentWeight==null || maxCurrentWeight.getCurrentWeight()<entry.getValue().getCurrentWeight()){
                maxCurrentWeight=entry.getValue();
            }
        }

        //将最大的权重值减去总权重值
        weightMap.get(maxCurrentWeight.getIp()).setCurrentWeight(maxCurrentWeight.getCurrentWeight()-totalWeight);

        //循环遍历Ip 列表 ，将动态权重值加上固定权重值

        for (Map.Entry<String,Weight> entry:weightMap.entrySet()){
            weightMap.get(entry.getKey()).setCurrentWeight(entry.getValue().getCurrentWeight()+entry.getValue().getWeight());
        }

        return maxCurrentWeight.getIp();
    }


}
