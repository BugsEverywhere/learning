package indi.simon.learning.leetcode.gogo20220711;

import java.util.Stack;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz735 {

    public static void main(String[] args) {
        int[] asteroids = new int[]{10, 2, -15};
        Quiz735 quiz735 = new Quiz735();
        int[] res = quiz735.asteroidCollision(asteroids);
        System.out.println(res);
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int singlePlanet : asteroids) {
            leftPlanet(singlePlanet, stack);
        }
        int[] res = new int[stack.size()];
        for (int j = stack.size() - 1; j >= 0; j--) {
            res[j] = stack.pop();
        }
        return res;
    }

    private void leftPlanet(int planet, Stack<Integer> stack) {
        if (stack.size() > 0 && stack.peek() > 0 && planet < 0) {
            if (stack.peek() + planet < 0) {
                stack.pop();
                leftPlanet(planet, stack);
            } else if (stack.peek() + planet == 0) {
                stack.pop();
            }
        } else {
            stack.push(planet);
        }
    }
}
