package indi.simon.learning.leetcode.gogo20220221;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz537 {

    public static void main(String[] args) {
        Quiz537 quiz537 = new Quiz537();
        String res = quiz537.complexNumberMultiply("1+-1i", "1+-1i");
        System.out.println(res);
    }

    public String complexNumberMultiply(String num1, String num2) {
        String[] num1Arr = num1.split("\\+");
        String num1RealStr;
        String num1VirtualStr;
        if (num1Arr[0].contains("i")) {
            num1VirtualStr = num1Arr[0].replace("i","");
            num1RealStr = num1Arr[1];
        } else {
            num1VirtualStr = num1Arr[1].replace("i","");
            num1RealStr = num1Arr[0];
        }

        String[] num2Arr = num2.split("\\+");
        String num2RealStr;
        String num2VirtualStr;
        if (num2Arr[0].contains("i")) {
            num2VirtualStr = num2Arr[0].replace("i","");
            num2RealStr = num2Arr[1];
        } else {
            num2VirtualStr = num2Arr[1].replace("i","");
            num2RealStr = num2Arr[0];
        }

        Integer num1Real = Integer.valueOf(num1RealStr);
        Integer num1Virtual = Integer.valueOf(num1VirtualStr);
        Integer num2Real = Integer.valueOf(num2RealStr);
        Integer num2Virtual = Integer.valueOf(num2VirtualStr);

        int realSum = num1Real * num2Real - num1Virtual * num2Virtual;
        int virtualSum = num1Real * num2Virtual + num1Virtual * num2Real;

        return realSum + "+" + virtualSum + "i";
    }

}
