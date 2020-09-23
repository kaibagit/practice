package me.luliru.parctice.algorithm.sorting;

/**
 * “构建堆”，《算法导论》6.3章节 Building a heap
 * 利用之前实现的<code>MaxHeapify</code>算法，构建max-heap。
 * 伪代码：
 * BUILD-MAX-HEAP(A)
 * 1 heap-size[A] ← length[A]
 * 2 for i ← ⌊length[A]/2⌋ downto 1
 * 3 do MAX-HEAPIFY(A, i)
 */
public class BuildMaxHeap {

    private static int[] input = new int[] { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
    private static int heapSize = input.length;

    public static void main(String[] args) {
        buildMaxHeap();
        //打印数组
        printArray();
    }

    /**
     * 构造max-heap
     * 复杂度：《算法导论》原文分析如下：
     * Each call to MAX-HEAPIFY costs O(lg n) time, and there are O(n) such calls.
     * Thus,the running time is O(n lg n).
     */
    private static void buildMaxHeap() {
        //从树的深层逆序，构造max-heap,正好每次均可满足
        //MaxHeapify算法的前提，即所有子二叉树已经是max-heap
        for (int i = heapSize/2; i > 0; i--) {
            maxHeapify(input, i);
        }
    }

    /**
     * MaxHeap，调整算法，前提是假设所有的子二叉树已经是max-heap。
     * 复杂度：
     * 因为：T (n) ≤ T(2n/3) + Θ(1)
     * 所以有：T (n) = O(lgn)
     * @param array
     * @param index
     */
    private static void maxHeapify(int[] array, int index) {
        int l = index * 2;
        int r = l + 1;
        int largest;
        //如果左叶子节点索引小于堆大小，比较当前值和左叶子节点的值，取值大的索引值
        if (l <= heapSize && array[l-1] > array[index-1]) {
            largest = l;
        } else {
            largest = index;
        }
        //如果右叶子节点索引小于堆大小，比较右叶子节点和之前比较得出的较大值，取大的索引值
        if (r <= heapSize && array[r-1] > array[largest-1]) {
            largest = r;
        }
        //交换位置，并继续递归调用该方法调整位置。
        if (largest != index) {
            int temp = array[index-1];
            array[index-1] = array[largest-1];
            array[largest-1] = temp;
            maxHeapify(array,largest);
        }
    }

    private static void printArray() {
        for (int i : input) {
            System.out.print(i + " ");
        }
    }
}
