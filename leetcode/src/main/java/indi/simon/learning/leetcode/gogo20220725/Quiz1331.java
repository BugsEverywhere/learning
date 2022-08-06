package indi.simon.learning.leetcode.gogo20220725;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1331 {

    public static void main(String[] args) {
        int[] arr = new int[]{37, 12, 28, 9, 100, 56, 80, 5, 12};
        Quiz1331 quiz1331 = new Quiz1331();
        int[] res = quiz1331.arrayRankTransform(arr);
        System.out.println(res);
    }

    public int[] arrayRankTransform(int[] arr) {
        int[] indexArr = new int[arr.length];
        System.arraycopy(arr, 0, indexArr, 0, arr.length);
        Arrays.sort(indexArr);

        Map<Integer, Integer> originIndex = new HashMap<>();
        int index = 1;
        for (int i = 0; i < indexArr.length; i++) {
            if (i > 0 && indexArr[i - 1] == indexArr[i]) {
            } else {
                originIndex.put(indexArr[i], index);
                index++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = originIndex.get(arr[i]);
        }

        return arr;
    }

}
