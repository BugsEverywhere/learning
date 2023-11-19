package indi.simon.learning.leetcode.gogo20231113;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/11/19.
 */
public class Quiz1570 {

    public static void main(String[] args) {

    }

    public Quiz1570(int[] nums) {
        indexValueMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            indexValueMap.put(i, nums[i]);
        }
    }

    private Map<Integer, Integer> indexValueMap;

    public Map<Integer, Integer> getIndexValueMap() {
        return this.indexValueMap;
    }

    public int dotProduct(Quiz1570 vec) {
        int sum = 0;
        Map<Integer, Integer> thatIndexMap = vec.getIndexValueMap();
        for (Map.Entry<Integer, Integer> entry : thatIndexMap.entrySet()) {
            int index = entry.getKey();
            if (this.indexValueMap.containsKey(index)) {
                sum += entry.getValue() * this.indexValueMap.get(index);
            }
        }
        return sum;
    }

}
