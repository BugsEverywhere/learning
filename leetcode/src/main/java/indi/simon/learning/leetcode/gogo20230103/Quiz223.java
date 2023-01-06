package indi.simon.learning.leetcode.gogo20230103;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz223 {

    public static void main(String[] args) {
        Quiz223 quiz223 = new Quiz223();
        int res = quiz223.computeArea(-5, -2, 5, 1, -3, -3, 3, 3);
        System.out.println(res);
    }

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {

        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);

        int dupWidth = (Math.min(ax2, bx2) - Math.max(ax1, bx1));
        int dupHeight = (Math.min(ay2, by2) - Math.max(ay1, by1));

        int duplicateArea = 0;
        if (dupWidth > 0 && dupHeight > 0) {
            duplicateArea = dupWidth * dupHeight;
        }
        return area1 + area2 - duplicateArea;
    }

}
