package indi.simon.learning.leetcode.gogo20220404;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz780_reviewed {

    public static void main(String[] args) {
        Quiz780_reviewed quiz780 = new Quiz780_reviewed();
        boolean res = quiz780.reachingPoints(44, 43, 921197891, 702724365);
        System.out.println(res);
    }

//    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
//        int dist = tx - sx + ty - sy;
//        return reachingPointsInternal(sx, sy, tx, ty, dist);
//    }
//
//    public boolean reachingPointsInternal(int sx, int sy, int tx, int ty, int lastAbsDistance) {
//        if (sx == tx && sy == ty) {
//            return true;
//        }
//
//        if (sx > tx || sy > ty) {
//            return false;
//        }
//
//        int turnXdist = tx - (sx + sy) + ty - sy;
//        int turnYdist = tx - sx + ty - (sx + sy);
//
//        //x转换
//        boolean turnX = false;
//        if (turnXdist >= 0 && turnXdist <= lastAbsDistance) {
//            //x转换的距离正在缩小，继续迭代
//            turnX = reachingPointsInternal(sx + sy, sy, tx, ty, turnXdist);
//        }
//
//        //y转换
//        boolean turnY = false;
//        if (turnYdist >= 0 && turnYdist <= lastAbsDistance) {
//            //y转换的距离正在缩小，继续迭代
//            turnY = reachingPointsInternal(sx, sx + sy, tx, ty, turnYdist);
//        }
//
//        return turnX || turnY;
//    }

    //todo: 理论上s和t的坐标都是正整数，那么s每次转换，无论x转换还是y转换，其x和y坐标都是单调递增的，
    // 那么s在坐标轴上应该单调逐渐逼近y才对，不存在曲线救国也就是先远离再逼近的情况。但是架不住他测试用例里面超级大的数字，导致算两点距离的时候溢出了。。。

    //================================================================
    // todo: 官方题解，反向操作，从(tx,ty)算(sx,sy)，当tx>ty时，只能执行(tx-ty,ty)，不然反过来的话坐标就小于0了，所以如果tx比ty大很多，tx要一直减ty一直减ty，
    //  减到tx<=ty，所以不如直接把tx缩小到tx % ty（如果取余大于零）或者ty（取余等于零，也就是tx是ty的整数倍），这期间一直比较tx与sx的大小，如果错过了（tx<sx）了就错过了，没办法了

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

    //=======================================================================自己依葫芦画瓢

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        } else if (sx < tx && sy < ty) {
            if (tx < ty) {
                //上一步由(tx,ty)->(tx,tx + ty % tx)而来，这一步减小ty
                return reachingPoints(sx, sy, tx, ty % tx == 0 ? tx : ty % tx);
            } else if (tx == ty) {
                //谁再退就成0了，没戏了
                return false;
            } else {
                //上一步由(tx,ty)->(ty + tx % ty,ty)而来，这一步减小tx
                return reachingPoints(sx, sy, tx % ty == 0 ? ty : tx % ty, ty);
            }
        } else if (sx == tx && sy < ty) {
            //tx已经退到指定位置，因此tx不能再动了
            //todo: 此种情况只需要另一个坐标的坐标差是他的整数倍即可，如果仍然递归下去会擦肩而过，例如44, 43, 44, 87，
            // 因为87-43=44，是前者的整数倍，如果仍然按照 ty= 87 % 43 = 1，结果就错过了
            if (tx < ty) {
                //此时，只要ty与sy的差距是tx的整数倍即可
                return (ty - sy) % tx == 0;
            } else if (tx == ty) {
                //谁再退就成0了，没戏了
                return false;
            } else {
                //ty不能再退了，没戏了
                return false;
            }
        } else if (sx < tx && sy == ty) {
            //ty已经退到指定位置，因此ty不能再动了
            if (tx < ty) {
                //tx也不能退了，没戏了
                return false;
            } else if (tx == ty) {
                //谁再退就成0了，没戏了
                return false;
            } else {
                //此时，只要tx与sx的差距是ty的整数倍即可
                return (tx - sx) % ty == 0;
            }
        } else {
            return false;
        }
    }


}
