package com.youyuan.concurrenthashmap;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zhangy
 * @version 1.0
 * @description 测试CAS的Unsafe类
 * @date 2020/2/4 19:56
 */
public class Person {
    private int i = 0;

    //定义Unsafe类
    private static Unsafe unsafe = null;
    //定义person对象属性的偏移量
    private static long I_OFFSET;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            I_OFFSET = unsafe.objectFieldOffset(Person.class.getDeclaredField("i"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final Person person = new Person();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    //通过Unsafe类的CAS方法保证线程安全
                    boolean result = unsafe.compareAndSwapInt(person, I_OFFSET, person.i, person.i + 1);

                    if (result) {
                        System.out.println(Thread.currentThread().getName() + "------" + unsafe.getIntVolatile(person,I_OFFSET));
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    //通过Unsafe类的CAS方式保证线程安全
                    boolean result = unsafe.compareAndSwapInt(person, I_OFFSET, person.i, person.i + 1);
                    if (result) {
                        System.out.println(Thread.currentThread().getName() + "------" + unsafe.getIntVolatile(person,I_OFFSET));
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
