package indi.simon.learning.leetcode.gogo20230619;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1401 {

    public static void main(String[] args) {

    }

    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {

        if (xCenter >= x1 && xCenter <= x2) {
            if (yCenter >= y1 - radius && yCenter <= y2 + radius) {
                return true;
            }
        }

        if ((xCenter <= x1 && xCenter >= x1 - radius) || (xCenter >= x2 && xCenter <= x2 + radius)) {
            if (yCenter >= y1 && yCenter <= y2) {
                return true;
            }
        }

        if (Math.sqrt(Math.pow(xCenter - x1, 2) + Math.pow(yCenter - y2, 2)) <= radius
                || Math.sqrt(Math.pow(xCenter - x1, 2) + Math.pow(yCenter - y1, 2)) <= radius
                || Math.sqrt(Math.pow(xCenter - x2, 2) + Math.pow(yCenter - y1, 2)) <= radius
                || Math.sqrt(Math.pow(xCenter - x2, 2) + Math.pow(yCenter - y2, 2)) <= radius) {
            return true;
        }

        return false;
    }

}
