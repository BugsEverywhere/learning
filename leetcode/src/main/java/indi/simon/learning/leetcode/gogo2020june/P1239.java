package indi.simon.learning.leetcode.gogo2020june;

import java.util.*;

public class P1239 {

    public static void main(String[] args) {

    }

    private static int maxLength(List<String> arr) {

        arr.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        List<boolean[]> bitmapList = new ArrayList<>();

        for (String singleStr : arr) {
            boolean[] bitmap = new boolean[26];
            char[] charArr = singleStr.toCharArray();
            for (char singleChar : charArr) {
                bitmap[singleChar - 'a'] = true;
            }
            bitmapList.add(bitmap);
        }

        int maxLength = Integer.MIN_VALUE;

        for (int i = 0; i < arr.size(); i++) {
            String str = arr.get(i);
            boolean[] bitMap = bitmapList.get(i);
            for (int j = i + 1; j < arr.size(); j++) {
                String str1 = arr.get(j);
                boolean[] bitMap1 = bitmapList.get(j);
//                if(){
//
//                }

            }
        }

        return -1;
    }


}
