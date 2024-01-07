package indi.simon.learning.leetcode.gogo20231225;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2023/12/25.
 */
public class Quiz1276 {

    public static void main(String[] args) {
        Quiz1276 quiz1276 = new Quiz1276();
        List<Integer> res = quiz1276.numOfBurgers(4208, 1305);
        System.out.println(res);
    }

    public List<Integer> numOfBurgersV2(int tomatoSlices, int cheeseSlices) {
        if (tomatoSlices % 2 != 0 || tomatoSlices < cheeseSlices * 2 || cheeseSlices * 4 < tomatoSlices) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(tomatoSlices / 2 - cheeseSlices);
        ans.add(cheeseSlices * 2 - tomatoSlices / 2);
        return ans;
    }


    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> res = new ArrayList<>();

        if (tomatoSlices % 2 != 0) {
            return res;
        }

        int burger1Cnt = 0;
        int originTomatoes = tomatoSlices;
        int originCheese = cheeseSlices;
        while (burger1Cnt * 4 <= originTomatoes && burger1Cnt <= originCheese) {
            if (tomatoSlices / 2 == cheeseSlices) {
                res.add(burger1Cnt);
                res.add(cheeseSlices);
                return res;
            }
            burger1Cnt++;
            tomatoSlices -= 4;
            cheeseSlices -= 1;
        }

        return res;
    }

}
