package indi.simon.learning.leetcode.gogo2020june;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P93 {

    public static void main(String[] args) {

        List<String> res = restoreIpAddresses("25525511135");
        Map<String,Integer> map = new HashMap<>();

        System.out.println(res);


    }

    public static List<String> restoreIpAddresses(String s) {

        List<String> res = new ArrayList<String>();
        restoreInternal(res, s, 1, "");
        return res;

    }

    private static void restoreInternal(List<String> res, String remain, int level, String thread) {
        if (remain.length() <= 0) {
            return;
        }
        if (level == 4) {
            //todo 终止条件，同样要判断最后一层0开头的情况
            if (remain.length() > 3 || Integer.parseInt(remain) > 255 || (remain.startsWith("0") && remain.length() > 1)) {
                return;
            } else {
                thread = thread + "." + remain;
                res.add(thread);
                return;
            }
        }

        //todo ip地址的合法性判断，第一次在这里栽了跟头，每一位不能以0开头
        if (remain.startsWith("0") && remain.length() > 1) {
            //todo 这里也要留意判断是不是第一层
            if (level > 1) {
                restoreInternal(res, remain.substring(1), level + 1, thread + ".0");
            } else {
                restoreInternal(res, remain.substring(1), level + 1, thread + "0");
            }
        } else {
            for (int i = 1; i <= Math.min(remain.length(), 3); i++) {
                //todo 每一次循环往下递归的时候，使用自己的thread变量，不能动公家的
                String thisThread = thread;
                String sub;
                if (i == remain.length()) {
                    //还未到level 4就已经将remain瓜分完了，此种方法直接返回，因为不合法
                    return;
                } else {
                    //递归瓜分remain不同位数
                    sub = remain.substring(0, i);
                }

                if (Integer.parseInt(sub) <= 255) {
                    if (level == 1) {
                        thisThread = sub;
                    } else {
                        thisThread = thisThread + "." + sub;
                    }
                    restoreInternal(res, remain.substring(i), level + 1, thisThread);
                }
            }
        }
    }


}
