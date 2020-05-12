package indi.simon.learning.leetcode.gogo2020april.week20to26;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P13 {

    public static void main(String[] args) {


        System.out.println(romanToInt("LVIII"));


    }

    public static int romanToInt(String s) {
        Map<Character, Integer> numMap = new HashMap<Character, Integer>(8) {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        Map<String, Integer> bundleMap = new HashMap<String, Integer>(16) {{
            put("IV", 4);
            put("IX", 9);
            put("XL", 40);
            put("XC", 90);
            put("CD", 400);
            put("CM", 900);
            put("I", 1);
            put("V", 5);
            put("X", 10);
            put("L", 50);
            put("C", 100);
            put("D", 500);
            put("M", 1000);
        }};

        List<String> bundleList = new ArrayList<>();

        char[] carArr = s.toCharArray();
        int i = 0;
        while (i < carArr.length) {
            if (i + 1 < carArr.length && numMap.get(carArr[i]) < numMap.get(carArr[i + 1])) {
                bundleList.add(String.copyValueOf(new char[]{carArr[i], carArr[i + 1]}));
                i = i + 2;
            } else {
                bundleList.add(String.copyValueOf(new char[]{carArr[i]}));
                i++;
            }
        }

        int sum = 0;
        for (String singleBundle : bundleList) {
            sum = sum + bundleMap.get(singleBundle);
        }

        return sum;
    }

}
