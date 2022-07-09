package indi.simon.learning.leetcode.gogo20220704;


import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 一定要记住使用TreeSet来实现动态排序，自己做动态排序太矬了。
// TreeSet的各个方法也要记清楚，floor()小于等于，ceiling()大于等于，lower()小于，higher()大于，last()从小到大最后一个，first()从小到大第一个
public class Quiz729_notfinish {

    public static void main(String[] args) {
        Quiz729_notfinish calendar = new Quiz729_notfinish();
        boolean res1 = calendar.book(47, 50);
        System.out.println(res1);
        boolean res2 = calendar.book(33, 41);
        System.out.println(res2);
        boolean res3 = calendar.book(39, 45);
        System.out.println(res3);
        boolean res4 = calendar.book(33, 42);
        System.out.println(res4);
        boolean res5 = calendar.book(25, 32);
        System.out.println(res5);
        boolean res6 = calendar.book(26, 35);
        System.out.println(res6);
        boolean res7 = calendar.book(19, 25);
        System.out.println(res7);
        boolean res8 = calendar.book(3, 8);
        System.out.println(res8);
        boolean res9 = calendar.book(8, 13);
        System.out.println(res9);
        boolean res10 = calendar.book(18, 27);
        System.out.println(res10);

    }


    TreeSet<int[]> booked;

    public Quiz729_notfinish() {
        booked = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
    }

    public boolean book(int start, int end) {
        if (booked.isEmpty()) {
            booked.add(new int[]{start, end});
            return true;
        }
        int[] tmp = {end, 0};
        int[] arr = booked.ceiling(tmp);
//        int[] prev = arr == null ? booked.last() : booked.lower(arr);
        //todo: 比end稍大的区间是最小的区间（那么本次插入的start和end就是新的第一个），或者仍然存在比end小的区间，且该区间右端点比start更小
        // 两种情况都应该将arr加入到排序中
        if (arr == booked.first() || booked.lower(tmp)[1] <= start) {
            booked.add(new int[]{start, end});
            return true;
        }
        return false;
    }


    //=============================================================================线段树解法：

    class MyCalendar {
        Set<Integer> tree;
        Set<Integer> lazy;

        public MyCalendar() {
            tree = new HashSet<>();
            lazy = new HashSet<>();
        }

        public boolean book(int start, int end) {
            if (query(start, end - 1, 0, 1000000000, 1)) {
                return false;
            }
            update(start, end - 1, 0, 1000000000, 1);
            return true;
        }

        public boolean query(int start, int end, int l, int r, int idx) {
            if (start > r || end < l) {
                return false;
            } /* 如果该区间已被预订，则直接返回 */
            if (lazy.contains(idx)) {
                return true;
            }
            if (start <= l && r <= end) {
                return tree.contains(idx);
            } else {
                int mid = (l + r) >> 1;
                if (end <= mid) {
                    return query(start, end, l, mid, 2 * idx);
                } else if (start > mid) {
                    return query(start, end, mid + 1, r, 2 * idx + 1);
                } else {
                    return query(start, end, l, mid, 2 * idx) | query(start, end, mid + 1, r, 2 * idx + 1);
                }
            }
        }

        public void update(int start, int end, int l, int r, int idx) {
            if (r < start || end < l) {
                return;
            }
            if (start <= l && r <= end) {
                tree.add(idx);
                lazy.add(idx);
            } else {
                int mid = (l + r) >> 1;
                update(start, end, l, mid, 2 * idx);
                update(start, end, mid + 1, r, 2 * idx + 1);
                tree.add(idx);
            }
        }
    }


}
