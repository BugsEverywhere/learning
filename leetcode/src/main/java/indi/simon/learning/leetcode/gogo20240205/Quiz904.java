package indi.simon.learning.leetcode.gogo20240205;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2024/2/7.
 */
public class Quiz904 {

    public static void main(String[] args) {
        Quiz904 quiz904 = new Quiz904();
        int res = quiz904.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4});
        System.out.println(res);
    }

    public int totalFruit(int[] fruits) {

        int i = 0;
        int j = 0;
        Map<Integer, Integer> window = new HashMap<>();
        window.put(fruits[0], 1);

        int maxWindow = 1;
        while (j < fruits.length) {
            maxWindow = Math.max(maxWindow, j - i + 1);
            j++;
            if (j < fruits.length) {
                if (window.containsKey(fruits[j])) {
                    window.put(fruits[j], window.get(fruits[j]) + 1);
                } else {
                    if (window.size() >= 2) {
                        while (i <= j) {
                            int eleCnt = window.get(fruits[i]);
                            eleCnt--;
                            if (eleCnt == 0) {
                                window.remove(fruits[i]);
                            } else {
                                window.put(fruits[i], eleCnt);
                            }
                            i++;
                            if (window.size() < 2) {
                                break;
                            }
                        }
                    }
                    window.put(fruits[j], 1);
                }
            }
        }
        if(j == fruits.length){
            maxWindow = Math.max(maxWindow, j - i);
        }
        return maxWindow;
    }
}
