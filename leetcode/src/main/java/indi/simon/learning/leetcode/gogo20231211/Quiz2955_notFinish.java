package indi.simon.learning.leetcode.gogo20231211;

/**
 * Created by Chen Zhuo on 2023/12/17.
 */
public class Quiz2955_notFinish {

    public static void main(String[] args) {

    }

    public int[] sameEndSubstringCount(String s, int[][] queries) {
        //前缀和
        int[][] cnt = new int[26][s.length()];
        for (int i = 0; i < s.length(); i++) {
            int[] thisCharCnt = cnt[s.charAt(i) - 'a'];


        }

        int[] res = new int[queries.length];

        for (int k = 0; k < queries.length; k++) {


        }

        return res;
    }

}
