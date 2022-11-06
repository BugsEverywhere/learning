package indi.simon.learning.leetcode.gogo20221031;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1678 {

    public static void main(String[] args) {

    }

    public String interpret(String command) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] cArr = command.toCharArray();
        for (int i = 0; i < cArr.length; ) {
            if (cArr[i] == 'G') {
                stringBuilder.append(cArr[i]);
                i++;
            } else if (cArr[i] == '(' && cArr[i + 1] == ')') {
                stringBuilder.append('o');
                i += 2;
            } else {
                stringBuilder.append("al");
                i += 4;
            }
        }
        return stringBuilder.toString();
    }

}
