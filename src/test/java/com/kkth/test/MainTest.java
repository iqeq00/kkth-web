package com.kkth.test;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainTest {

    public static void main(String[] args) {

//        System.out.println(cleanContextPath("/etc"));
//        System.out.println("ending");

        System.out.println("18583603233".substring("18583603233".length()-4));

//        Set<String> orderIdList = new HashSet<>(
//                Arrays.asList(StringUtils.split(null, ",")));

    }

    private static String cleanContextPath(String contextPath) {
        if (StringUtils.hasText(contextPath) && contextPath.endsWith("/")) {
            return contextPath.substring(0, contextPath.length() - 1);
        }
        return contextPath;
    }
}
