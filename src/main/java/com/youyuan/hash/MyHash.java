package com.youyuan.hash;

/**
 * @author zhangyu
 * @version 1.0
 * @description 自定义HashMap
 * @date 2019/7/4 13:52
 */
public class MyHash<K,V> {

    private MyEntry<K,V>[] tables;

    private static int COUNT=8;

    private static int num;

    public MyHash() {
        this.tables = new MyEntry[COUNT];
    }

    /**

     * 查询hash大小
     * @return
     */
    public int size(){
        return num;
    }

    /**
     * 保存
     * @param k
     * @param v
     * @return
     */
    public V put(K k,V v){
        int hash=k.hashCode();
        int index=hash%COUNT;

        //先循环遍历链表，如果有存在的key覆盖
        for (MyEntry myEntry=tables[index];myEntry!=null;myEntry=myEntry.next){
            if (k.equals(myEntry.getK())){
                V oldV= (V) myEntry.getV();
                myEntry.setV(v);
                return oldV;
            }
        }

        //将新添加的加入到数组或链表中
        addEntry(k, v, index);


        return null;
    }

    private void addEntry(K k, V v, int index) {
        MyEntry<K,V> entry=new MyEntry<K, V>(k,v,tables[index]);
        tables[index]=entry;
        num++;
    }

    /**
     * 查询
     * @param k
     * @return
     */
    public V get(K k){
        int hash=k.hashCode();
        int index=hash%COUNT;

        //先循环遍历链表，如果有存在的key覆盖
        for (MyEntry myEntry=tables[index];myEntry!=null;myEntry=myEntry.next){
            if (k.equals(myEntry.getK())){
                return (V) myEntry.getV();
            }
        }
        return null;
    }

    class MyEntry<K,V>{

        public MyEntry(K k, V v,MyEntry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        private MyEntry<K,V> next;

        public MyEntry<K, V> getNext() {
            return next;
        }

        public void setNext(MyEntry<K, V> next) {
            this.next = next;
        }

        private K k;

        private V v;

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }
    }

    public static void main(String[] args) {
        MyHash<String,String> myHash=new MyHash<String, String>();
        myHash.put("北京","北京");
        myHash.put("上海","上海");
        myHash.put("深圳","深圳");

        System.out.println(myHash.get("上海"));
    }

}
