package indi.simon.learning.leetcode.gogo20231023;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/10/29.
 */
public class Quiz353_needReview {

    public static void main(String[] args) {
        Quiz353_needReview quiz353NeedReview = new Quiz353_needReview(3, 3, new int[][]{{2, 0}, {0, 0}, {0, 2}, {2, 2}});
        int res1 = quiz353NeedReview.move("D");
        System.out.println(res1);
        int res2 = quiz353NeedReview.move("D");
        System.out.println(res2);
        int res3 = quiz353NeedReview.move("R");
        System.out.println(res3);
        int res4 = quiz353NeedReview.move("U");
        System.out.println(res4);
        int res5 = quiz353NeedReview.move("U");
        System.out.println(res5);
        int res6 = quiz353NeedReview.move("L");
        System.out.println(res6);
        int res7 = quiz353NeedReview.move("D");
        System.out.println(res7);
        int res8 = quiz353NeedReview.move("R");
        System.out.println(res8);
        int res9 = quiz353NeedReview.move("R");
        System.out.println(res9);
        int res10 = quiz353NeedReview.move("U");
        System.out.println(res10);
        int res11 = quiz353NeedReview.move("L");
        System.out.println(res11);
        int res12 = quiz353NeedReview.move("D");
        System.out.println(res12);
    }

    private int[][] matrix;
    private int[][] food;
    private int foodIndex;
    private int[] head;
    private Deque<int[]> path;
    private int currPoint;

    public Quiz353_needReview(int width, int height, int[][] food) {
        matrix = new int[height][width];
        this.food = food;
        head = new int[]{0, 0};
        path = new ArrayDeque<>();
        path.offer(head);
    }

    public int move(String direction) {
        if (foodIndex < food.length) {
            matrix[food[foodIndex][0]][food[foodIndex][1]] = 1;
        }
        if (direction.equals("R")) {
            int[] tail = path.peekFirst();
            if (head[1] == matrix[0].length - 1 || (matrix[head[0]][head[1] + 1] == -1 && !(tail[0] == head[0] && tail[1] == head[1] + 1))) {
                return -1;
            } else {
                if (path.size() > 1) {
                    matrix[head[0]][head[1]] = -1;
                }
                head[1]++;
                path.offerLast(new int[]{head[0], head[1]});
                if (matrix[head[0]][head[1]] == 1) {
                    //得一分，总分+1，并且尾部不用动
                    currPoint++;
                    foodIndex++;
                } else {
                    //未得分，尾部需要弹出一个
                    tail = path.pollFirst();
                    if (!(tail[0] == head[0] && tail[1] == head[1])) {
                        matrix[tail[0]][tail[1]] = 0;
                    }
                }
            }
        } else if (direction.equals("D")) {
            int[] tail = path.peekFirst();
            if (head[0] == matrix.length - 1 || (matrix[head[0] + 1][head[1]] == -1 && !(tail[0] == head[0] + 1 && tail[1] == head[1]))) {
                return -1;
            } else {
                if (path.size() > 1) {
                    matrix[head[0]][head[1]] = -1;
                }
                head[0]++;
                path.offerLast(new int[]{head[0], head[1]});
                if (matrix[head[0]][head[1]] == 1) {
                    //得一分，总分+1，并且尾部不用动
                    currPoint++;
                    foodIndex++;
                } else {
                    //未得分，尾部需要弹出一个
                    tail = path.pollFirst();
                    if (!(tail[0] == head[0] && tail[1] == head[1])) {
                        matrix[tail[0]][tail[1]] = 0;
                    }
                }
            }
        } else if (direction.equals("U")) {
            int[] tail = path.peekFirst();
            if (head[0] == 0 || (matrix[head[0] - 1][head[1]] == -1 && !(tail[0] == head[0] - 1 && tail[1] == head[1]))) {
                return -1;
            } else {
                if (path.size() > 1) {
                    matrix[head[0]][head[1]] = -1;
                }
                head[0]--;
                path.offerLast(new int[]{head[0], head[1]});
                if (matrix[head[0]][head[1]] == 1) {
                    //得一分，总分+1，并且尾部不用动
                    currPoint++;
                    foodIndex++;
                } else {
                    //未得分，尾部需要弹出一个
                    tail = path.pollFirst();
                    if (!(tail[0] == head[0] && tail[1] == head[1])) {
                        matrix[tail[0]][tail[1]] = 0;
                    }
                }
            }
        } else if (direction.equals("L")) {
            int[] tail = path.peekFirst();
            if (head[1] == 0 || (matrix[head[0]][head[1] - 1] == -1 && !(tail[0] == head[0] && tail[1] == head[1] - 1))) {
                return -1;
            } else {
                if (path.size() > 1) {
                    matrix[head[0]][head[1]] = -1;
                }
                head[1]--;
                path.offerLast(new int[]{head[0], head[1]});
                if (matrix[head[0]][head[1]] == 1) {
                    //得一分，总分+1，并且尾部不用动
                    currPoint++;
                    foodIndex++;
                } else {
                    //未得分，尾部需要弹出一个
                    tail = path.pollFirst();
                    if (!(tail[0] == head[0] && tail[1] == head[1])) {
                        matrix[tail[0]][tail[1]] = 0;
                    }
                }
            }
        }
        return currPoint;
    }
}
