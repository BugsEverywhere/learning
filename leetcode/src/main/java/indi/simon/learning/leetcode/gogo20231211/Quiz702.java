package indi.simon.learning.leetcode.gogo20231211;

import indi.simon.learning.leetcode.commonmodel.ArrayReader;

/**
 * Created by Chen Zhuo on 2023/12/17.
 */
public class Quiz702 {

    public static void main(String[] args) {
        Quiz702 quiz702 = new Quiz702();


    }

    public int search(ArrayReader reader, int target) {

        int r = 10000;
        int l = 0;
        int mid;

        while (l <= r) {
            mid = (l + r) / 2;
            int val = reader.get(mid);
            if (val == target) {
                return mid;
            } else if (val < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }


}
