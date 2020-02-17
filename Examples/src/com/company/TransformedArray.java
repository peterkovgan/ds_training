package com.company;

public class TransformedArray {

    public static void main(String[] args) {
        int[] x = new int[]{-4, -2, 2, 4};
        int a = 1;
        int b = 3;
        int c = 5;
        int[] sorted = new TransformedArray().sortTransformedArray(x, a, b, c);
    }

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length == 0) return nums;
        int res[] = new int[nums.length];

        int l = 0;
        int r = nums.length-1;
        int fLeft = funk(nums[l], a, b,c), fRight = funk(nums[r], a, b, c);

        if(a <= 0){
            int idx = 0;
            while( l <= r ) {
                if (fLeft <= fRight) {
                    res[idx++] = fLeft;
                    l++;
                    if (l < nums.length) fLeft = funk(nums[l], a, b, c);
                }else{
                    res[idx++] = fRight;
                    r--;
                    if (r >= 0) fRight = funk(nums[r], a, b, c);
                }
            }
        }else{
            int idx = nums.length-1;
            while( l <= r ) {
                if (fLeft >= fRight) {
                    res[idx--] = fLeft;
                    l++;
                    if (l < nums.length) fLeft = funk(nums[l], a, b, c);
                }else{
                    res[idx--] = fRight;
                    r--;
                    if (r >= 0) fRight = funk(nums[r], a, b, c);
                }
            }
        }

        return res;
    }

    int funk(int x, int a, int b, int c){
        return x*x*a + x*b +c;
    }


}
