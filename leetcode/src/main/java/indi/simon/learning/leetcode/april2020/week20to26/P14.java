package indi.simon.learning.leetcode.april2020.week20to26;

public class P14 {

    public static void main(String[] args) {

    }

    public static String longestCommonPrefix(String[] strs) {

        char[] pubArr;
        int pubIndex;

        int i = 0;
        while (i < strs.length) {
            if (i == 0) {
                pubArr = strs[i].toCharArray();
                pubIndex = strs[i].length() - 1;
            }
            char[] thisStrCharArr = strs[i].toCharArray();
           // for (int j = 0; j < Math.min(thisStrCharArr.length, pubIndex + 1); j++) {
//                if () {
//
//                }


        //    }


            i++;
        }


        return null;
    }


}
