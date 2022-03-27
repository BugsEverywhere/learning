package indi.simon.learning.leetcode.gogo20220321;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz661 {

    public static void main(String[] args) {
        int[][] img = new int[][]{{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
        Quiz661 quiz661 = new Quiz661();
        int[][] res = quiz661.imageSmoother(img);
        System.out.println(res);
    }

    public int[][] imageSmoother(int[][] img) {
        if (img.length == 0) {
            return img;
        }
        int[][] copy = new int[img.length][img[0].length];

        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                copy[i][j] = img[i][j];
                int sum = img[i][j];
                int sumCount = 1;
                //上一行
                if (i - 1 >= 0) {
                    if (j - 1 >= 0) {
                        sum = sum + copy[i - 1][j - 1];
                        sumCount++;
                    }
                    sum = sum + copy[i - 1][j];
                    sumCount++;
                    if (j + 1 < img[0].length) {
                        sum = sum + copy[i - 1][j + 1];
                        sumCount++;
                    }
                }
                //下一行
                if (i + 1 < img.length) {
                    if (j - 1 >= 0) {
                        sum = sum + img[i + 1][j - 1];
                        sumCount++;
                    }
                    sum = sum + img[i + 1][j];
                    sumCount++;
                    if (j + 1 < img[0].length) {
                        sum = sum + img[i + 1][j + 1];
                        sumCount++;
                    }
                }
                //本行
                if (j - 1 >= 0) {
                    sum = sum + copy[i][j - 1];
                    sumCount++;
                }
                if (j + 1 < img[0].length) {
                    sum = sum + img[i][j + 1];
                    sumCount++;
                }
                img[i][j] = sum / sumCount;
            }
        }
        return img;
    }

}
