package indi.simon.learning.leetcode.gogo20230206;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1138 {

    public static void main(String[] args) {
        Quiz1138 quiz1138 = new Quiz1138();
        String res = quiz1138.alphabetBoardPath("zyz");
        System.out.println(res);
    }

    public String alphabetBoardPath(String target) {

        List<Character> res = new ArrayList<>();

        char[] cArr = target.toCharArray();
        char lastPoint = 'a';
        int currRow = 0;
        int currColumn = 0;
        for (char c : cArr) {
            int rowInc = (c - 'a') / 5 - (lastPoint - 'a') / 5;
            int columnInc = (c - 'a') % 5 - (lastPoint - 'a') % 5;
            while (rowInc != 0 || columnInc != 0) {

                if (rowInc < 0) {
                    res.add('U');
                    rowInc++;
                    currRow--;

                }
                //todo: 唯一要要注意的地方
                else if (rowInc > 0 && !(currRow >= 4 && currColumn > 0)) {
                    res.add('D');
                    rowInc--;
                    currRow++;
                }

                if (columnInc < 0) {
                    res.add('L');
                    columnInc++;
                    currColumn--;
                } else if (columnInc > 0) {
                    res.add('R');
                    columnInc--;
                    currColumn++;
                }
            }

            res.add('!');
            lastPoint = c;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : res) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

}
