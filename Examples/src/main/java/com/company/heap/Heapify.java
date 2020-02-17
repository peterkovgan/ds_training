package com.company.heap;

public class Heapify {


    public static void main(String[] args) {
        int[] data = {1, 3, 36, 2, 19, 25, 100, 17, 7};
        heapify(data);
    }

    public static void heapify(int[] data) {
        // last index in the array
        int end = data.length -1;
        // start is assigned the index of the last parent node
        // which in our case is index[3] value(2)
        int start = (end - 1) / 2; // |2|binary heap

        while (start >= 0) {
            // sift down the node at index start to the proper place
            // such that all nodes below the start index are in heap
            // order
            siftDown(data, start, end - 1);
            // decrement to the next lowest parent node
            start--;
        }
        // after sifting down the root all nodes/elements
        //  are in heap order
    }

    public static void siftDown(int[] data, int start, int end) {
        int root = start;
        // while the root has at least one child
        while ((2 * root + 1) <= end) {
            // root*2+1 points to the left child
            int child = 2 * root + 1;
            // take the highest of the left or right child
            if (child + 1 <= end && data[child] < data[child + 1]) {
                // then point to the right child instead
                child = child + 1;
            }

            // out of max-heap order
            // swap the child with root if child is greater
            if (data[root] < data[child]) {
                int tmp = data[root];
                data[root] = data[child];
                data[child] = tmp;

                // return the swapped root to test against
                //  it's new children
                root = child;
            } else {
                return;
            }
        } // End while
    }

}