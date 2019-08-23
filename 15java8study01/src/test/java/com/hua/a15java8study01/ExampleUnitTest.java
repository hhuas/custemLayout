package com.hua.a15java8study01;

import com.hua.a15java8study01.javastudy.builder.MutrifitFacts;
import com.hua.a15java8study01.javastudy.enumtest.ResultDataEnums;

import org.junit.Test;

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
        System.out.print("hello");
    }

    @Test
    public void testBuilder() {
        MutrifitFacts build = new MutrifitFacts.Builder(22, 33).build();
        int servings = build.servings;
        System.out.println("hello：" + servings);

    }

    @Test
    public void testEnum() {
        int code = ResultDataEnums.SUCCESS.getCode();
        int i = ResultDataEnums.SUCCESS.hashCode();
        String message = ResultDataEnums.SUCCESS.getMessage();
        System.out.println(code + "----" + message + "---hasCode" + i);
    }

    @Test
    public void testDoubleCompare() {
        double d1 = 18800.00;
        double d2 = 10000;
        int compare = Double.compare(d1, d2);
        int compare2 = Double.compare(d2, d1);

        System.out.println("compare:" + compare + "---compare2:" + compare2);


    }

}