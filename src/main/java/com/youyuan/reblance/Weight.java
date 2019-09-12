package com.youyuan.reblance;

import java.io.Serializable;

/**
 * @author zhangyu
 * @version 1.0
 * @description 权重值类对象
 * @date 2019/7/8 20:42
 */
public class Weight implements Serializable {
    private static final long serialVersionUID = -7215386223527553284L;

    //ip信息
    private String ip;
    //固定权重值
    private Integer weight;
    //动态权重值
    private Integer currentWeight;

    public Weight(String ip, Integer weight, Integer currentWeight) {
        this.ip = ip;
        this.weight = weight;
        this.currentWeight = currentWeight;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }
}
