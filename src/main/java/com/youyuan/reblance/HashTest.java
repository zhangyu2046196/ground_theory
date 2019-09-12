package com.youyuan.reblance;

import java.util.*;

/**
 * @author zhangyu
 * @version 1.0
 * @description 负载均衡—hash环加虚拟节点算法
 * @date 2019/7/9 22:31
 */
public class HashTest {

    /**
     * 真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好
     */
    private static List<String> realNodes = new LinkedList<String>();
    /**
     * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
     */
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();

    /**
     * 虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点
     */
    private static final int VIRTUAL_NODES = 1000;

    static
    {
        // 先把原始的服务器添加到真实结点列表中
        for (int i = 0; i < ServiceIp.ipList.size(); i++)
            realNodes.add(ServiceIp.ipList.get(i));

        // 再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高
        for (String str : realNodes)
        {
            for (int i = 0; i < VIRTUAL_NODES; i++)
            {
                String virtualNodeName = str + "&&VN" + String.valueOf(i);
                int hash = getHash(virtualNodeName);
                System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
        System.out.println();
    }

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     */
    private static int getHash(String str)
    {
        final int p = 16777619;
        int hash = (int)2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    /**
     * 得到应当路由到的结点
     */
    private static String getServer(String node)
    {
        // 得到带路由的结点的Hash值
        int hash = getHash(node);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap =
                virtualNodes.tailMap(hash);
        Integer i=null;
        String virtualNode = null;
        if(subMap==null||subMap.size()==0){
            i=virtualNodes.firstKey();
            virtualNode=virtualNodes.get(i);
        }else{
            i = subMap.firstKey();
            virtualNode= subMap.get(i);
        }
        // 第一个Key就是顺时针过去离node最近的那个结点

        // 返回对应的虚拟节点名称，这里字符串稍微截取一下
        return virtualNode.substring(0, virtualNode.indexOf("&&"));
    }

    public static void main(String[] args)
    {
        for(int i=0;i<1000;i++){
            System.out.println(getServer(UUID.randomUUID().toString().replace("-", "")));
        }
    }
}

