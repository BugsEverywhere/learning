package indi.simon.learning.复习.优先级队列;

/**
 * @author chenzhuo(zhiyue)
 * <p>
 * 手写大顶堆（max-heap）的入堆操作，堆使用数组来表示。
 * <p>
 * 数组表示（0 下标开始）：
 * - 节点 i 的父节点下标：(i - 1) / 2
 * - 节点 i 的左孩子下标：2 * i + 1
 * - 节点 i 的右孩子下标：2 * i + 2
 * <p>
 * 大顶堆性质：任意节点 i 都满足 heap[i] >= heap[左孩子] 且 heap[i] >= heap[右孩子]，
 * 即每个节点的值都不小于它的孩子，堆顶 heap[0] 即为最大值。
 * <p>
 * 入堆（插入）步骤：
 * 1. 将新元素放到数组末尾（下标为 size 的位置），size++；
 * 2. 从该位置开始「上浮」sift-up：只要当前节点比父节点大，就与父节点交换，直到满足堆性质或到达根节点；
 * 3. 单次入堆时间复杂度 O(log n)，n 为堆中元素个数。
 */
public class 整数入堆 {

    // 底层数组，存放堆元素
    private final int[] heap;
    // 堆中当前元素个数（也指向下一个可插入的位置）
    private int size;
    // 堆的最大容量
    private final int capacity;

    public 整数入堆(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    public static void main(String[] args) {
        整数入堆 maxHeap = new 整数入堆(20);
        int[] input = new int[]{3, 1, 6, 5, 2, 4, 9, 7, 8};
        for (int num : input) {
            maxHeap.offer(num);
            System.out.println("插入 " + num + " 后，当前堆顶为: " + maxHeap.peek());
        }

        System.out.print("最终堆数组: ");
        for (int i = 0; i < maxHeap.size; i++) {
            System.out.print(maxHeap.heap[i] + " ");
        }
        System.out.println();
    }

    /**
     * 整数入堆：将元素 val 插入大顶堆
     *
     * @return true 插入成功；false 堆已满
     */
    public boolean offer(int val) {
        if (size == capacity) {
            // 堆已满，不再插入
            return false;
        }
        // 1. 新元素放到数组末尾
        heap[size] = val;
        int cur = size;
        size++;
        // 2. 上浮：只要当前节点比父节点大就交换
        while (cur > 0) {
            int parent = (cur - 1) / 2;
            if (heap[cur] > heap[parent]) {
                swap(cur, parent);
                cur = parent;
            } else {
                // 已经满足大顶堆性质，提前结束
                break;
            }
        }
        return true;
    }

    /**
     * 查看堆顶元素（不弹出）
     */
    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("堆为空");
        }
        return heap[0];
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

}
