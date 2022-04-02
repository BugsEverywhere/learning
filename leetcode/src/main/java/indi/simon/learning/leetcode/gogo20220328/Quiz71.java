package indi.simon.learning.leetcode.gogo20220328;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz71 {

    public static void main(String[] args) {
        String path = "/../";
        Quiz71 quiz71 = new Quiz71();
        String res = quiz71.simplifyPath(path);
        System.out.println(res);
    }

    public String simplifyPath(String path) {
        String[] strArr = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String singleStr : strArr) {
            if (".".equals(singleStr)) {
                continue;
            } else if ("..".equals(singleStr)) {
                if (stack.size() != 0) {
                    stack.pop();
                }
            } else if (singleStr == null || "".equals(singleStr)) {
                continue;
            } else {
                stack.push(singleStr);
            }
        }

        List<String> pathList = new ArrayList<>(stack);
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : pathList) {
            stringBuilder.append("/");
            stringBuilder.append(str);
        }

        if (stringBuilder.length() == 0) {
            return "/";
        }

        return stringBuilder.toString();
    }
}
