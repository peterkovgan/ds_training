package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ShortestPathinBinaryMatrix {

    /**
     * https://leetcode.com/problems/shortest-path-in-binary-matrix/
     * <p>
     * I did it BFS
     */

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null) return -1;
        int width = grid.length;
        //System.out.println("width="+width);
        if (width == 0) return -1;
        int height = grid[0].length;
        //System.out.println("height="+height);
        //if(true)
        //   return -1;
        if (height == 0) return -1;
        if (width == 1 && height == 1 && grid[0][0] == 0) return 1;
        if (width == 1 && height == 1 && grid[0][0] == 1) return -1;
        if (grid[0][0] != 0 || grid[width - 1][height - 1] != 0) return -1;

        Map<Integer, Integer> visited1 = new HashMap<>();
        Map<Integer, Integer> visited2 = new HashMap<>();

        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();

        queue1.add(0 * height + 0);
        visited1.put(0 * height + 0, 1);
        queue2.add((width - 1) * height + (height - 1));
        visited2.put((width - 1) * height + (height - 1), 1);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            if (!queue1.isEmpty()) {
                Integer num = queue1.poll();
                if (visited2.keySet().contains(num)) {
                    return visitCell(visited1, visited2, num);
                }
                addNeighborNodes(num, visited1, queue1, height, width, grid);
            }
            if (!queue2.isEmpty()) {
                Integer num = queue2.poll();
                if (visited1.keySet().contains(num)) {
                    return visitCell(visited2, visited1, num);
                }
                addNeighborNodes(num, visited2, queue2, height, width, grid);
            }
        }

        return -1;
    }

    private int visitCell(Map<Integer, Integer> visited1, Map<Integer, Integer> visited2, Integer num) {
        int level2 = visited2.get(num);
        int level1 = visited1.get(num);
        int path = level1 + level2 - 1;
        return path;
    }

    void addNeighborNodes(int num, Map<Integer, Integer> visited, Queue<Integer> queue, int height, int width, int[][] grid) {
        int level = visited.get(num);
        int x = num / height;
        int y = num % height;

        //1
        add(x - 1, y - 1, width, height, visited, queue, grid, level);
        //2
        add(x, y - 1, width, height, visited, queue, grid, level);
        //3
        add(x + 1, y - 1, width, height, visited, queue, grid, level);
        //4
        add(x + 1, y, width, height, visited, queue, grid, level);
        //5
        add(x + 1, y + 1, width, height, visited, queue, grid, level);
        //6
        add(x, y + 1, width, height, visited, queue, grid, level);
        //7
        add(x - 1, y + 1, width, height, visited, queue, grid, level);
        //8
        add(x - 1, y, width, height, visited, queue, grid, level);
    }

    void add(int x, int y, int width, int height, Map<Integer, Integer> visited, Queue<Integer> queue, int[][] grid, int level) {
        if (x >= 0 && x < width && y >= 0 && y < height && grid[x][y] == 0) {
            int thisNum = x * height + y;
            if (!visited.keySet().contains(thisNum)) {
                visited.put(thisNum, level + 1);
                queue.add(thisNum);
            }
        }
    }


}
