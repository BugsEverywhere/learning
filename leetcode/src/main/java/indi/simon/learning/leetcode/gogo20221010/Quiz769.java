package indi.simon.learning.leetcode.gogo20221010;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz769 {

    public static void main(String[] args) {
        Quiz769 quiz769 = new Quiz769();
        int res = quiz769.maxChunksToSorted(new int[]{1, 0, 2, 3, 4});
        System.out.println(res);
    }

    public int maxChunksToSorted(int[] arr) {
        //动态天花板
        int ceiling;
        int res = 0;

        for (int i = 0; i < arr.length; ) {
            //首先将动态天花板赋一个初始值，这个初始值有可能很大（比当前下标大很多），也有可能就是当前下标
            ceiling = arr[i];
            //然后锚定该天花板，遍历当前下标到该天花板的所有数，如果这期间所有数都小于等于天花板，那么说明在数组的下标[0,ceiling]内，0 ~ ceiling的所有数都齐活了，
            // 这里有一个规律，假设满足下标[i,j]内的所有数，大小都在[i,j]内，那么这个区间我们称之为齐活区间，那么如果[0,i]是一个齐活区间，且ceiling > i，
            // 并且[0,ceiling]也是一个齐活区间，那么(i,ceiling]必然是一个齐活区间。因此当下面这个while循环结束时，代表我们找到了这样一个齐活区间，res加一
            while (i <= ceiling) {
                if (arr[i] > ceiling) {
                    //中间有可能出现某个数比当前天花板还大，那么就抬高天花板，意味着所考察的区间也将变大
                    ceiling = arr[i];
                }
                i++;
            }
            res++;
        }

        return res;
    }

}
