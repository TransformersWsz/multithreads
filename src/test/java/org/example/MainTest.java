package org.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MainTest {
    @Test
    public void test1() {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println("gg");
    }
}
