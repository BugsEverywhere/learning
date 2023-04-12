package indi.simon.learning.leetcode.gogo20230410;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1041 {

    public static void main(String[] args) {
        Quiz1041 quiz1041 = new Quiz1041();
        boolean res = quiz1041.isRobotBounded("LGR");
        System.out.println(res);
    }

    public boolean isRobotBounded(String instructions) {

        int x = 0;
        int y = 0;
        int dir = 0;

        for (char c : instructions.toCharArray()) {
            if (c == 'G') {
                if (dir == 0) {
                    y++;
                } else if (dir == 1) {
                    x++;
                } else if (dir == 2) {
                    y--;
                } else {
                    x--;
                }
            } else if (c == 'L') {
                if (dir == 0) {
                    dir = 3;
                } else if (dir == 1) {
                    dir = 0;
                } else if (dir == 2) {
                    dir = 1;
                } else {
                    dir = 2;
                }
            } else if (c == 'R') {
                if (dir == 0) {
                    dir = 1;
                } else if (dir == 1) {
                    dir = 2;
                } else if (dir == 2) {
                    dir = 3;
                } else {
                    dir = 0;
                }
            }
        }

        //todo: 运行完一轮之后，只要满足两种情况皆可：
        // 1. 机器人仍然在原点，那肯定没问题，因为以后无论如何，每走一轮都会回到原点
        // 2. 机器人不在原点，那么只要他的朝向不朝北，他都会回到原点，只要朝北，就会越走越远。自己想吧
        return (x == 0 && y == 0) || (dir != 0);
    }


}
