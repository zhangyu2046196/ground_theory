package com.youyuan.reblance;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.util.concurrent.Futures;

/**
 * @author zhangyu
 * @version 1.0
 * @description 布隆过滤器测试代码
 * <!-- 包含布隆过滤器算法 -->
 *   <dependency>
 *       <groupId>com.google.guava</groupId>
 *       <artifactId>guava</artifactId>
 *       <version>28.0-jre</version>
 *   </dependency>
 * @date 2019/7/10 16:27
 */
public class BloomFilterTest {

    public static void main(String[] args) {
        //创建布隆过滤器   第一个参数是字符集编码  第二个参数是默认BloomFilter的长度  第三个参数是一个系数，设置后BloomFilter会重新计算二进制槽的数量和随机函数的数量
        //在不设置fpp的参数误报数量是322，槽数是72984，随机函数是5   设置fpp参数后的误报数量是0,槽数是287599，随机函数是20
        BloomFilter<String> bloomFilter=BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),10000);
//        BloomFilter<String> bloomFilter=BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),10000,0.000001);
        //向BloomFilter里面添加一万条记录
        for (int i=1;i<=10000;i++){
            bloomFilter.put("友缘网"+i);
        }

        //模拟从BloomFilter查询一万条不存在的记录，测试误报率
        int count=0;//误报率
        for (int i=1;i<=10000;i++){
            //mightContain代表可能存在，因为查询的内容是在BloomFilter不存在的，存在就是误报
            if (bloomFilter.mightContain("北京"+i)) {
                count++;
            }
        }

        System.out.println("误报="+count);

    }

}
