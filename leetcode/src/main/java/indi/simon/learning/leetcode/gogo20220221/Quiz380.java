package indi.simon.learning.leetcode.gogo20220221;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz380 {

    public static void main(String[] args) {

    }

    private Map<Integer, Integer> map;
    private List<Integer> arr;

    public Quiz380() {
        map = new HashMap<>();
        arr = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            arr.add(val);
            map.put(val, arr.size() - 1);
            return true;
        }
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            arr.set(map.get(val), null);
            map.remove(val);
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
        Random random = new Random();
        int randomInt = Math.abs(random.nextInt(arr.size()));
        if (arr.get(randomInt) == null) {
            return getRandom();
        } else {
            return arr.get(randomInt);
        }
    }

}
