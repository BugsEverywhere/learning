package indi.simon.learning.复习.排序;

/**
 * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：record = [9, 7, 5, 4, 6]
 * 输出：8
 * 解释：交易中的逆序对为 (9, 7), (9, 5), (9, 4), (9, 6), (7, 5), (7, 4), (7, 6), (5, 4)。
 *
 *
 * 提示：
 *
 * 0 <= record.length <= 50000
 */
public class LCR170_求逆序对数 {

    public static void main(String[] args) {

        LCR170_求逆序对数 obj1 = new LCR170_求逆序对数();
        int[] arr1 = new int[]{7, 5, 6, 4};
        int res1 = obj1.reversePairs(arr1);
        System.out.println(res1);

        LCR170_求逆序对数 obj2 = new LCR170_求逆序对数();
        int[] arr2 = new int[]{5, 5, 5, 5};
        int res2 = obj2.reversePairs(arr2);
        System.out.println(res2);

        LCR170_求逆序对数 obj3 = new LCR170_求逆序对数();
        int[] arr3 = new int[]{};
        int res3 = obj3.reversePairs(arr3);
        System.out.println(res3);

        LCR170_求逆序对数 obj4 = new LCR170_求逆序对数();
        int[] arr4 = new int[]{1, 2, 3, 4};
        int res4 = obj4.reversePairs(arr4);
        System.out.println(res4);

        LCR170_求逆序对数 obj5 = new LCR170_求逆序对数();
        int[] arr5 = new int[]{3, 2, 3, 4};
        int res5 = obj5.reversePairs(arr5);
        System.out.println(res5);

    }

    //todo: 归并排序求逆序对数
    private int cnt = 0;

    public int reversePairs(int[] record) {
        mergeSort(record, 0, record.length - 1, new int[record.length]);
        return this.cnt;
    }

    public void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid, temp);       // 递归排序左半部分
            mergeSort(arr, mid + 1, right, temp);  // 递归排序右半部分
            merge(arr, left, mid, right, temp);    // 合并
        }
    }

    private void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        // 复制到临时数组
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        // 双指针合并
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                temp[k++] = L[i++];
            } else {
                temp[k++] = R[j++];
                //todo: 关键一行，归并时左元素大于右元素，则当前左数组中后续所有元素与右元素都构成逆序对
                // 这里需要注意，需要选择每当右元素右移时自增逆序对数（也就是在else中记录，而不是上面if中）
                // 不能选择左元素右移时记录逆序对数，因为这样会有重复，
                // 例如[7, 9], [4, 5, 6]，如果i=0，j=0，(7,4)是一个逆序对，若干次后，i=1，j=2，此时如果按照左元素来记逆序对，
                // 会将之前已经记录过的重复计算
                cnt += mid - (left + i) + 1;
            }
        }
        // 处理剩余元素
        while (i < n1) temp[k++] = L[i++];
        while (j < n2) temp[k++] = R[j++];

        for(int z = left; z <= right ; z++) {
            arr[z] = temp[z];
        }
    }


}
