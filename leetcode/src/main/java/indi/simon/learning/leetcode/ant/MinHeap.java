package indi.simon.learning.leetcode.ant;

import indi.simon.learning.test.WordCount;

//小顶堆
class MinHeap {
    private WordCount.ComparableWord[] a;
    private int size;
    private int count;

    public MinHeap(int capacity) {
        a = new WordCount.ComparableWord[capacity + 1];
        size = capacity;
        count = 0;
    }

    public boolean isFull() {
        return this.count >= this.size;
    }

    public int getTopCount() {
        if (count == 0) {
            return -1;
        }
        return a[1].count;
    }

    public void insert(WordCount.ComparableWord data) {
        if (count >= size) {
            //堆满了
            return;
        }
        ++count;
        a[count] = data;
        int i = count;
        //自下往上堆化
        while (i / 2 > 0 && a[i].count < a[i / 2].count) {
            swap(a, i, i / 2);
            i = i / 2;
        }
    }

    public void removeTop() {
        if (count == 0) {
            //堆中没有数据
            return;
        }
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }

    public WordCount.ComparableWord[] getHeap() {
        return a;
    }

    //自上往下堆化
    private void heapify(WordCount.ComparableWord[] a, int heapSize, int startIndex) {
        while (true) {
            int minPos = startIndex;
            if (startIndex * 2 <= heapSize && a[startIndex].count > a[startIndex * 2].count) {
                minPos = startIndex * 2;
            }
            if (startIndex * 2 + 1 <= heapSize && a[minPos].count > a[startIndex * 2 + 1].count) {
                minPos = startIndex * 2 + 1;
            }
            if (minPos == startIndex) {
                //小三角里面i仍然是最小的，说明堆化完成，不用动了，跳出循环
                break;
            }
            swap(a, startIndex, minPos);
            startIndex = minPos;
        }
    }

    private void swap(WordCount.ComparableWord[] a, int i, int j) {
        WordCount.ComparableWord tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}