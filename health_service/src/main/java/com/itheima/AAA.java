package com.itheima;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Authour: chenming
 * @Date： 2020/9/18 20:39
 */
public class AAA {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("classpath:spring-dubbo.xml");
        System.in.read();
    }
}
