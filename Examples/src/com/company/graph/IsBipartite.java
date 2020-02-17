package com.company.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * https://leetcode.com/problems/is-graph-bipartite/
 *
 *
 *
 */

public class  IsBipartite {

    public static void main(String[] args) {
        //int def[][] = new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}};

        int def[][] = new int[][]{{1,2,3}, {0,2}, {0,1,3}, {0,2}};

        for(int y = 0; y < def.length; y++){
            int[] elems = def[y];
            for(int elem:elems){
                System.out.print(elem);
                System.out.print("  ");
            }
            System.out.println("");
        }

        System.out.println(new IsBipartite().isBipartite(def));
    }
    public boolean isBipartite(int[][] graph) {

        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int start = 0; start < n; ++start) {
            if (color[start] == -1) {
                Stack<Integer> stack = new Stack();
                stack.push(start);
                color[start] = 0;

                while (!stack.empty()) {
                    Integer node = stack.pop();
                    for (int nei: graph[node]) {
                        if (color[nei] == -1) {
                            stack.push(nei);
                            color[nei] = color[node] ^ 1;
                        } else if (color[nei] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }



}
