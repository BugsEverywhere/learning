package indi.simon.learning.leetcode.gogo20231002;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2023/10/7.
 */
public class Quiz901 {

    public static void main(String[] args) {
        Quiz901 quiz901 = new Quiz901();
        quiz901.next(31);
        quiz901.next(41);
        quiz901.next(48);
        quiz901.next(59);
        quiz901.next(79);
    }

    private List<Integer> rangeList;
    private List<Integer> priceList;

    public Quiz901() {
        priceList = new ArrayList<>();
        rangeList = new ArrayList<>();
    }

    public int next(int price) {
        if (priceList.size() > 0) {
            if (price >= priceList.get(priceList.size() - 1)) {
                int thisRange = 1;
                int i = priceList.size() - 1;
                while (i >= 0 && priceList.get(i) <= price) {
                    thisRange += rangeList.get(i);
                    i -= rangeList.get(i);
                }
                priceList.add(price);
                rangeList.add(thisRange);
                return thisRange;
            } else {
                priceList.add(price);
                rangeList.add(1);
                return 1;
            }
        } else {
            priceList.add(price);
            rangeList.add(1);
            return 1;
        }
    }

}
