package com.company;

import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Hard
 * https://leetcode.com/problems/trapping-rain-water/
 */

public class TrappingRainWater {

    public static void main(String[] args) {
        int [] columns = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new TrappingRainWater().trap(columns));
    }


    public int trap(int[] height) {
        return stackBased(height);
    }

    /**
     * Brute Force:
     * Intuition
     * Do as directed in question.
     * For each element in the array, we find the maximum level of water it can trap after the rain,
     * which is equal to the minimum of maximum height of bars on both the sides minus its own height.
     *
     * Time complexity: O(n^2)
     * For each element of array, we iterate the left and right parts.
     *
     * Space complexity: O(1) extra space.
     *
    */
    private int brute(int[] height) {
        int answer = 0;
        for(int i = 0; i < height.length; i++ ){
            int maxLeft = 0;
            int maxRight = 0;
            for(int k=i; k>=0; k--){
                maxLeft = Math.max(maxLeft, height[k]);
            }
            for(int k=i; k<height.length; k++){
                maxRight = Math.max(maxRight, height[k]);
            }

            int valueInThisPoint = Math.min(maxLeft, maxRight)-height[i];
            answer+=valueInThisPoint;
        }
        return answer;
    }

    int[] maxLeftA;
    int[] maxRightA;

    /**
     *
     * Time complexity: O(n).
     *
     * We store the maximum heights upto a point using 2 iterations of O(n) each.
     * We finally update {ans}ans using the stored values in O(n).
     * Space complexity: O(n) extra space.
     *
     * Additional O(n) space for arrays.
     *
     */
    private int dynamic (int[] height) {

        maxLeftA = new int[height.length];
        maxRightA = new int[height.length];
        int maxLeftN = 0;
        int maxRightN = 0;
        for(int i=0; i<height.length; i++){
            int a = height[i];
            maxLeftN = Math.max(maxLeftN,a);
            maxLeftA[i] = maxLeftN;
        }
        for(int i=height.length-1; i>=0; i--){
            int a = height[i];
            maxRightN = Math.max(maxRightN,a);
            maxRightA[i] = maxRightN;
        }
        int answer = 0;
        for(int i = 0; i < height.length; i++ ){
            int valueInThisPoint = Math.min(maxLeftA[i], maxRightA[i]) - height[i];
            answer += valueInThisPoint;

        }
        return answer;
    }


    /**
     *
     * Algorithm
     *
     * Use stack to store the indices of the bars.
     * Iterate the array:
     * While stack is not empty and {height}[current]>{height}[st.top()]height[current]>height[st.top()]
     * It means that the stack element can be popped. Pop the top element as {top}top.
     * Find the distance between the current element and the element at top of stack, which is to be filled. {distance} = {current} - {st.top}() - 1distance=current−st.top()−1
     * Find the bounded height {bounded\_height} = \min({height[current]}, {height[st.top()]}) - {height[top]}bounded_height=min(height[current],height[st.top()])−height[top]
     * Add resulting trapped water to answer {ans} \mathrel{+}= {distance} \times {bounded\_height}ans+=distance×bounded_height
     * Push current index to top of the stack
     * Move {current}current to the next position
     *
     * Time complexity: O(n).
     * Single iteration of O(n) in which each bar can be touched at most twice(due to insertion and deletion from stack) and insertion and deletion from stack takes O(1) time.
     * Space complexity: O(n). Stack can take upto O(n) space in case of stairs-like or flat structure.
     *
     *
     */
    private int stackBased (int[] height) {

           int ans = 0, current = 0;

           Stack<Integer> st = new Stack<>();

           while(current < height.length){

               while(!st.isEmpty() && height[current] >= height[st.peek()] ){
                   int top = st.pop();
                   if (st.empty())
                       break;
                   int distance = current - st.peek() - 1;
                   int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
                   ans += distance * bounded_height;
               }

               st.push(current++);

           }

           return ans;
    }


    /**
     *Time complexity: O(n). Single iteration of O(n).
     *Space complexity: O(1) extra space.
     * Only constant space required for left, right, left_max and right_max.
     */
    private int twoPointers (int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if(height[left] >= left_max) {
                    left_max = height[left];
                }else {
                    ans += (left_max - height[left]);
                }
                ++left;
            }
            else {
                if (height[right] >= right_max){
                    right_max = height[right];
                }else{
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;
    }


    private int twoPointersEvenBetter (int[] height) {
        if (height==null) return 0;
        int length = height.length;
        int maxIndex = -1;
        int maxValue = -1;
        for(int i=0; i<length; i++){
            if(maxValue < height[i]){
                maxValue = height[i];
                maxIndex = i;
            }
        }
        int total = 0;
        int maxSoFar = 0;
        for(int i=0; i<=maxIndex; i++){
            if (height[i] < maxSoFar) {
                total += maxSoFar - height[i];
            }
            maxSoFar = Math.max(height[i], maxSoFar);
        }
        maxSoFar = 0;
        for(int i=length-1; i>=maxIndex; i--) {
            if (height[i] < maxSoFar) {
                total += maxSoFar - height[i];
                maxSoFar = Math.max(height[i], maxSoFar);
            }
        }
        return total;
    }



}
