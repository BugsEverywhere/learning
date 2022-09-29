package indi.simon.learning.leetcode.gogo20220919;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1640 {

    public static void main(String[] args) {
        Quiz1640 quiz1640 = new Quiz1640();
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[][] pieces = new int[][]{{1, 2, 3}, {4}, {6, 5}, {7}};
        boolean res = quiz1640.canFormArray(arr, pieces);
        System.out.println(res);
    }

    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> startArrMap = new HashMap<>(pieces.length);
        for (int[] singleArr : pieces) {
            startArrMap.put(singleArr[0], singleArr);
        }

        for (int i = 0; i < arr.length; ) {
            if (startArrMap.containsKey(arr[i])) {
                int[] piece = startArrMap.get(arr[i]);
                if (!isMatch(arr, i, piece)) {
                    return false;
                }
                i = i + piece.length;
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean isMatch(int[] arr, int start, int[] piece) {
        for (int i = 0; i < piece.length; i++) {
            if (start + i >= arr.length) {
                return false;
            }
            if (piece[i] != arr[start + i]) {
                return false;
            }
        }
        return true;
    }

}
