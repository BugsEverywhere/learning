package indi.simon.learning.复习.单调栈;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Chen Zhuo on 2024/2/7.
 * <p>
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例 1:
 * <p>
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 * <p>
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 * <p>
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * 提示：
 * <p>
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class Quiz739_下一个更高温日 {

    public static void main(String[] args) {
        Quiz739_下一个更高温日 quiz739Hinted = new Quiz739_下一个更高温日();
        int[] res = quiz739Hinted.dailyTemperatures(new int[]{30,40,50,60});
        System.out.println(Arrays.toString(res));
    }

    //todo: 优秀的递减栈
    public int[] dailyTemperatures(int[] temperatures) {
        //记录下标的递减栈
        Stack<Integer> stack = new Stack<>();

        //结果数组
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (stack.size() > 0 && temperatures[stack.peek()] < temperatures[i]) {
                int pos = stack.pop();
                res[pos] = i - pos;
            }
            stack.push(i);
        }

        return res;
    }

}
