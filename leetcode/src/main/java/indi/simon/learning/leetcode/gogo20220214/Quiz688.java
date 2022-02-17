package indi.simon.learning.leetcode.gogo20220214;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 第一次审题错误，本题中一旦棋子走出棋盘，则不会再走回来，但是他后续步骤最终走完k步所在位置个数仍然要作为分母。也就是说，本题的分母固定为8的k次幂。
    //todo: 分子则是只计算他一直在棋盘内，最终停下时也仍然在棋盘内的走法数。
public class Quiz688 {

    public static void main(String[] args) {
        Quiz688 quiz688 = new Quiz688();
        double res = quiz688.knightProbability(8, 30, 6, 4);
        System.out.println(res);
    }

    //维度1是行，维度2是列，维度3是到此位置时剩下的步数，维度4只有一个值，即处于此位置剩下此步数情况下的有效步数
    Map<String, Double> mem = new HashMap<>();
    //todo，超时因为没加备忘录

    private double totalPosCount = 0;

    public double knightProbability(int n, int k, int row, int column) {
        double res = move(n, k, row, column);
        return res / totalPosCount;
    }

    private double move(int n, int k, int row, int column) {
        //todo: 第三次是因为备忘录没起作用，命中备忘录了仍然往下递归
        //todo: 第四次比较奇葩，因为move函数返回值是int类型，精度不够导致的，改成double类型才ok
        if (mem.containsKey(row + "_" + column + "_" + k)) {
            //来过，后面不用看了
            totalPosCount = totalPosCount + Math.pow(8, k);
            return mem.get(row + "_" + column + "_" + k);
        }

        if (k == 0) {
            totalPosCount++;
            if (row >= 0 && row < n && column >= 0 && column < n) {
                return 1;
            }
            return 0;
        }

        if (row < 0 || row > n - 1 || column < 0 || column > n - 1) {
            //没戏了
            totalPosCount = totalPosCount + Math.pow(8, k);
            return 0;
        }


        double res = move(n, k - 1, row + 1, column + 2) +
                move(n, k - 1, row + 1, column - 2) +
                move(n, k - 1, row - 1, column + 2) +
                move(n, k - 1, row - 1, column - 2) +

                move(n, k - 1, row + 2, column + 1) +
                move(n, k - 1, row + 2, column - 1) +
                move(n, k - 1, row - 2, column + 1) +
                move(n, k - 1, row - 2, column - 1);

        mem.put(row + "_" + column + "_" + k, res);
        return res;
    }

}
