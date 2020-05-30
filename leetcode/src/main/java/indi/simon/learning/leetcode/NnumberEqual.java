package indi.simon.learning.leetcode;

import java.util.Arrays;

public class NnumberEqual {

    public static void main(String[] args) {

    }

    private static int equalNumberV2(int[] nArr){



        return -1;
    }

    private static int equalNnumber(int[] nArr) {
        boolean equal = false;
        int count = 0;
        while (!equal) {
            Arrays.sort(nArr);
            if (nArr[0] == nArr[nArr.length - 1]) {
                equal = true;
            } else {
                addArr(nArr);
                count++;
            }
        }
        return count;
    }

    private static void addArr(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i] + 1;
        }
    }


}
