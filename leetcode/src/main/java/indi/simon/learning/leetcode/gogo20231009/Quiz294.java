package indi.simon.learning.leetcode.gogo20231009;

/**
 * Created by Chen Zhuo on 2023/10/15.
 */
public class Quiz294 {

    public static void main(String[] args) {
        Quiz294 quiz294 = new Quiz294();
        boolean res = quiz294.canWin("++++");
        System.out.println(res);
    }

    public boolean canWin(String currentState) {
        if (currentState.length() <= 1) {
            return false;
        }
        if (currentState.length() == 2) {
            return currentState.equals("++");
        }
        int i = 0;
        while (i < currentState.length() - 1) {
            if (currentState.charAt(i) == '+' && currentState.charAt(i + 1) == '+') {
                String turned;
                if (i == 0) {
                    turned = "--" + currentState.substring(2);
                } else {
                    turned = currentState.substring(0, i) + "--" + currentState.substring(i + 2);
                }
                boolean turnThisOne = !canWin(turned);
                if (turnThisOne) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

}
