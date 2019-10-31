package com.lianxin.a22optionalstudy;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testOptional1() {
        Optional<String> name = Optional.of("tomi");
        System.out.println(name.get());
        //传入参数为null，抛出NullPointerException
//        Optional<Object> empty = Optional.of(null);
        //ofNullable与of方法相似，唯一的区别是可以接受参数为null的情况
        Optional<Object> empty = Optional.ofNullable(null);

        //isPresent方法用来检查Optional实例中是否包含值
        if (name.isPresent()) {
            //在Optional实例内调用get()返回已存在的值
            System.out.println(name.get());
        }

        try {
            //在空的Optional实例上调用get()，抛出NoSuchElementException
            System.out.println(empty.get());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        //ifPresent方法接受lambda表达式作为参数。
        //lambda表达式对Optional的值调用consumer进行处理。
        name.ifPresent((value) -> {
            System.out.println("The length of the value is: " + value.length());
        });
        name.ifPresent(value -> {
            System.out.println("The length of the value is: " + value.length());
        });
        name.ifPresent(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("The length of the value is: " + s.length());
            }
        });

        //如果Optional实例有值则将其返回，否则返回orElse方法传入的参数
        System.out.println(empty.orElse("There is no value present!"));
        System.out.println(name.orElse("There is some value!"));

        System.out.println(name.orElseGet(() -> {
            return "Default Value";
        }));
        System.out.println(name.orElseGet(() ->
                "Default Value"
        ));
        System.out.println(name.orElseGet(new Supplier<String>() {
            @Override
            public String get() {
                return "Default Value";
            }
        }));

    }


    @Test
    public void testStrCut() {
        String str = "http://localwap.st365.vip/booking?id=435&companyId=1&regionId=121&bunkId=234";
//        String[] split = str.split("\\?");
//        System.out.println("url1:" + split[0]);
//        System.out.println("url2:" + split[1]);
//        String regin = split[1];
//        String[] split1 = regin.split("&");
//        for (int i = 0; i < split1.length; i++) {
//            String s2 = split1[i];
//            String[] split2 = s2.split("=");
//            if (split2[0].equals("regionId")) {
//                System.out.println("reginId:" + split2[1]);
//            } else if (split2[0].equals("bunkId")) {
//                System.out.println("bunkId:" + split2[1]);
//            }
//        }
        String[] split = str.split("&");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            String[] split2 = s.split("=");
            if (split2[0].equals("regionId")) {
                System.out.println("reginId:" + split2[1]);
            } else if (split2[0].equals("bunkId")) {
                System.out.println("bunkId:" + split2[1]);
            }
        }
    }
}