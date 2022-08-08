package indi.simon.learning.leetcode.gogo20220801;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz636 {

    public static void main(String[] args) {
        List<String> logs = Arrays.asList("0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8");
        Quiz636 quiz636 = new Quiz636();
        int[] res = quiz636.exclusiveTime(2, logs);
        System.out.println(res);
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] execTimeArr = new int[n];
        Stack<String> funcStack = new Stack<>();
        Map<String, Integer> funcExecFlag = new HashMap<>();

        for (String singleFuncCall : logs) {
            String[] callArr = singleFuncCall.split(":");
            if ("start".equals(callArr[1])) {
                //处理开始调用函数
                if (funcStack.size() != 0) {
                    //如果有调用者，更新调用者的耗时
                    String caller = funcStack.peek();
                    int index = Integer.parseInt(caller);
                    execTimeArr[index] = execTimeArr[index] + (Integer.parseInt(callArr[2]) - funcExecFlag.get(caller));
                }
                funcStack.push(callArr[0]);
                funcExecFlag.put(callArr[0], Integer.valueOf(callArr[2]));
            } else {
                //处理函数调用结束出栈，只需计算当前函数的耗时即可，并且将上层调用者的秒表更新
                int index = Integer.parseInt(callArr[0]);
                execTimeArr[index] = execTimeArr[index] + (Integer.parseInt(callArr[2]) - funcExecFlag.get(callArr[0]) + 1);
                funcStack.pop();
                if (funcStack.size() > 0) {
                    //还有调用者
                    funcExecFlag.put(funcStack.peek(), Integer.parseInt(callArr[2]) + 1);
                }
            }
        }

        return execTimeArr;
    }


}
