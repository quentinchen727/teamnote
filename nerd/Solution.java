import java.util.*;

public class Solution {
    public static int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k, Collections.reverseOrder());
        for(int[] row : matrix) {
            for(int element : row) {
                minHeap.offer(element);

                if(minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };

        System.out.println("result is " + kthSmallest(matrix, 8));
        String another = "another " + 'a' +'b';
        System.out.println(another);
        System.out.println("String plus letter is " + 'c');
    }
}