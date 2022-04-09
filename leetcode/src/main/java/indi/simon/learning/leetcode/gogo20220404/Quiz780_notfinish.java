package indi.simon.learning.leetcode.gogo20220404;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz780_notfinish {

    public static void main(String[] args) {
        Quiz780_notfinish quiz780 = new Quiz780_notfinish();
        boolean res = quiz780.reachingPoints(44, 43, 921197891, 702724365);
        System.out.println(res);
    }

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        int dist = tx - sx + ty - sy;
        return reachingPointsInternal(sx, sy, tx, ty, dist);
    }

    public boolean reachingPointsInternal(int sx, int sy, int tx, int ty, int lastAbsDistance) {
        if (sx == tx && sy == ty) {
            return true;
        }

        if (sx > tx || sy > ty) {
            return false;
        }

        int turnXdist = tx - (sx + sy) + ty - sy;
        int turnYdist = tx - sx + ty - (sx + sy);

        //x转换
        boolean turnX = false;
        if (turnXdist >= 0 && turnXdist <= lastAbsDistance) {
            //x转换的距离正在缩小，继续迭代
            turnX = reachingPointsInternal(sx + sy, sy, tx, ty, turnXdist);
        }

        //y转换
        boolean turnY = false;
        if (turnYdist >= 0 && turnYdist <= lastAbsDistance) {
            //y转换的距离正在缩小，继续迭代
            turnY = reachingPointsInternal(sx, sx + sy, tx, ty, turnYdist);
        }

        return turnX || turnY;
    }

    //todo: 理论上s和t的坐标都是正整数，那么s每次转换，无论x转换还是y转换，其x和y坐标都是单调递增的，
    // 那么s在坐标轴上应该单调逐渐逼近y才对，不存在曲线救国也就是先远离再逼近的情况。但是架不住他测试用例里面超级大的数字，导致算两点距离的时候溢出了。。。

    //================================================================官方题解

    public boolean reachingPointsOfficial(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }
        if (tx == sx && ty == sy) {
            return true;
        } else if (tx == sx) {
            return ty > sy && (ty - sy) % tx == 0;
        } else if (ty == sy) {
            return tx > sx && (tx - sx) % ty == 0;
        } else {
            return false;
        }
    }


}
