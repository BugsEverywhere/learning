package indi.simon.learning.leetcode.gogo20230911;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Chen Zhuo on 2023/9/17.
 */
public class Quiz251 {

    public static void main(String[] args) {


    }

    private List<Integer> arr;
    private Iterator<Integer> iterator;


    public Quiz251(int[][] vec) {
        arr = new ArrayList<>();
        for (int[] vec1 : vec) {
            for (int num : vec1) {
                arr.add(num);
            }
        }
        iterator = arr.iterator();
    }

    public int next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }


}
