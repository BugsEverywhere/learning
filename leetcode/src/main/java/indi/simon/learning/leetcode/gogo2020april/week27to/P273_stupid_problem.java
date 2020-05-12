package indi.simon.learning.leetcode.gogo2020april.week27to;

public class P273_stupid_problem {

    public static void main(String[] args) {

    }

    private static String numberToWords(int num) {

        int billionCount = num / 1000000000;

        int millionCount = (num - billionCount * 1000000000) / 1000000;

        int thousandCount = (num - billionCount * 1000000000 - millionCount * 1000000) / 1000;

        int oneCount = num - billionCount * 1000000000 - millionCount * 1000000 - thousandCount * 1000;






        return null;
    }

    private static String rangeToString(int rangeCount) {

        StringBuilder sb = new StringBuilder();

        int hundredCount = rangeCount / 100;

        //sb.append()

        int tens = (rangeCount - hundredCount * 100) / 10;

        int ones = (rangeCount - hundredCount * 100 - tens * 10);

        switch (tens) {
            case 1:
//                switch (ones){
//                    case
//
//
//                }
//                sb.append();
            case 2:

            case 3:

            case 4:

            case 5:

            case 6:

            case 7:

            case 8:

            case 9:

            default:

        }

        return "";
    }

}
