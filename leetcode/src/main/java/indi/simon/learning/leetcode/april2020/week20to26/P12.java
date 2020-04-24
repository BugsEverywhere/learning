package indi.simon.learning.leetcode.april2020.week20to26;

public class P12 {

    public static void main(String[] args) {

        System.out.println(intToRoman(1994));
    }

    public static String intToRoman(int num) {

        int thousand = num / 1000;
        String thousandStr = "";
        for (int i = 0; i < thousand; i++) {
            thousandStr = thousandStr + "M";
        }

        num = num - thousand * 1000;
        int hundred = num / 100;
        String hundredStr = "";
        if (hundred != 0) {
            if (hundred < 4) {
                for (int i = 0; i < hundred; i++) {
                    hundredStr = hundredStr + "C";
                }
            } else if (hundred == 4) {
                hundredStr = "CD";
            } else if (hundred < 9) {
                hundredStr = "D";
                for (int i = 5; i < hundred; i++) {
                    hundredStr = hundredStr + "C";
                }
            } else if (hundred == 9) {
                hundredStr = "CM";
            }
        }

        num = num - hundred * 100;
        int ten = num / 10;
        String tenStr = "";
        if (ten != 0) {
            if (ten < 4) {
                for (int i = 0; i < ten; i++) {
                    tenStr = tenStr + "X";
                }
            } else if (ten == 4) {
                tenStr = "XL";
            } else if (ten < 9) {
                tenStr = "L";
                for (int i = 5; i < ten; i++) {
                    tenStr = tenStr + "X";
                }
            } else if (ten == 9) {
                tenStr = "XC";
            }
        }

        num = num - ten * 10;
        String oneStr = "";
        if (num != 0) {
            if (num < 4) {
                for (int i = 0; i < num; i++) {
                    oneStr = oneStr + "I";
                }
            } else if (num == 4) {
                oneStr = "IV";
            } else if (num < 9) {
                oneStr = "V";
                for (int i = 5; i < num; i++) {
                    oneStr = oneStr + "I";
                }
            } else if (num == 9) {
                oneStr = "IX";
            }
        }

        return thousandStr + hundredStr + tenStr + oneStr;
    }


}
