package main.java.indi.simon.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class ZTransformOfString6 {

    public static void main(String[] args) {
        String result = convert("AB", 1);
        System.out.println(result);
    }

    public static String convert(String s, int numRows) {
        int unitEleCount = (numRows + numRows - 2) == 0 ? 1 : (numRows + numRows - 2);
        int columnUnit = (1 + numRows - 2) == 0 ? 1 : (1 + numRows - 2);
        int columnCount;
        if (numRows == 1) {
            columnCount = s.length();
        } else {
            columnCount = (s.length() / unitEleCount + 1) * columnUnit;
        }
        Character[][] twoDimensionArr = new Character[columnCount][numRows];
        char[] charArr = s.toCharArray();
        int n = 0;
        for (; n < charArr.length; n++) {
            int unitIndex = (n / unitEleCount);
            int internalIndex = n % unitEleCount;

            int c = unitIndex * columnUnit + (internalIndex < numRows ? 0 : internalIndex - numRows + 1);
            int r = internalIndex < numRows ? internalIndex : (numRows - 1 - (internalIndex - numRows) - 1);

            twoDimensionArr[c][r] = charArr[n];
        }
        char[] newArr = new char[s.length()];
        int m = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (twoDimensionArr[j][i] != null) {
                    newArr[m] = twoDimensionArr[j][i];
                    m++;
                }
            }
        }
        return new String(newArr);
    }


}
