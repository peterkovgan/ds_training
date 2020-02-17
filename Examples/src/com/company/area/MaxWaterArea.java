package com.company.area;

public class MaxWaterArea {

    public int maxArea(int[] height) {

        int length = height.length;
        if(height.length<=1) return 0;
        if(height.length==2) return Math.min(height[0], height[1]) ;
        int max  = 0;
        int left = 0;
        int right = length-1;

        while(left < right){
            max = Math.max(max, Math.min(height[right], height[left])*(right-left));
            if(height[left] < height[right]) left++;
            else right--;
        }
        return max;
    }


}
