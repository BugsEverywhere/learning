package indi.simon.learning.leetcode.gogo20230508;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz970 {

    public static void main(String[] args) {
        Quiz970 quiz970 = new Quiz970();
        List<Integer> res = quiz970.powerfulIntegers(1, 3, 10);
        System.out.println(res);
    }

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> res = new HashSet<>();
        int sum = 0;
        int j = 0;
        while (sum <= bound) {
            int i = 0;
            sum = (int) (Math.pow(x, i) + Math.pow(y, j));
            if (sum > bound) {
                break;
            }
            while (true) {
                sum = (int) (Math.pow(x, i) + Math.pow(y, j));
                if (sum > bound) {
                    sum = 0;
                    break;
                }
                res.add(sum);
                //todo: 需要考虑x为1的情况，此时不用使x自增了，无限次自增也相当于一次
                if(x == 1){
                    break;
                }
                i++;
            }
            //todo: 同样，需要考虑y为1的情况，此时不用使y自增了，无限次自增也相当于一次
            if(y == 1){
                break;
            }
            j++;
        }

        return new ArrayList<>(res);
    }

}
