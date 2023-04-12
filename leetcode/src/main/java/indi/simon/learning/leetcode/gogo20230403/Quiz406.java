package indi.simon.learning.leetcode.gogo20230403;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz406 {

    public static void main(String[] args) {
        Quiz406 quiz406 = new Quiz406();
        int[][] res = quiz406.reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
        System.out.println(res);
    }

    public int[][] reconstructQueue(int[][] people) {
        //将原始队列按照身高倒排，相同身高的人按照前面大于等于自己的人数正排
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Integer.compare(o2[0], o1[0]);
            } else {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        //将大家组织成链表
        ArrNode head = null;
        ArrNode tail = null;
        int index = 0;
        for (; index < people.length; index++) {
            if (index == 0) {
                head = new ArrNode(people[index], null);
                tail = head;
            } else {
                if (people[index] == people[index - 1]) {
                    //如果本人与前面一位身高相同，则直接再链表结尾续上
                    tail.next = new ArrNode(people[index], null);
                    tail = tail.next;
                } else {
                    //身高比之前的低，需要在链表中找到他合适的位置
                    int higherCnt = people[index][1];
                    int cnt = 0;
                    ArrNode curr = head;
                    ArrNode last = null;
                    while (cnt < higherCnt) {
                        last = curr;
                        curr = curr.next;
                        cnt++;
                    }
                    //当事人
                    ArrNode me = new ArrNode(people[index], null);
                    if (last == null) {
                        //当事人应该安排在链表头部
                        me.next = head;
                        head = me;
                    } else {
                        //当事人应该安排在链表中间
                        last.next = me;
                        me.next = curr;
                    }
                }
            }
        }

        ArrNode curr = head;
        int i = 0;
        while (curr != null) {
            people[i] = curr.mark;
            curr = curr.next;
            i++;
        }

        return people;
    }

    private class ArrNode {

        public ArrNode(int[] mark, ArrNode next) {
            this.mark = mark;
            this.next = next;
        }

        private int[] mark;
        private ArrNode next;

        public int[] getMark() {
            return mark;
        }

        public void setMark(int[] mark) {
            this.mark = mark;
        }

        public ArrNode getNext() {
            return next;
        }

        public void setNext(ArrNode next) {
            this.next = next;
        }
    }

}
