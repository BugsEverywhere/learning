package indi.simon.learning.leetcode.gogo20220411;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz380 {

    public static void main(String[] args) {

    }

    private List<Integer> array;
    private Map<Integer, Integer> map;

    public Quiz380() {
        array = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        array.add(val);
        map.put(val, array.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        array.set(map.get(val), null);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        Random random = new Random();
        int index = random.nextInt(array.size());
        if (array.get(index) == null) {
            while (array.get(index) == null) {
                index = random.nextInt(array.size());
            }
            return array.get(index);
        } else {
            return array.get(index);
        }
    }
}
