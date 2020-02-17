package com.company.topology;

import java.util.*;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 * It is "simplified" in a way, that instead of colors I use 2 boolean sets
 * one for visited nodes - this is to avoid to process the node twice
 * one for inprocessNodes - this is to see cycles.
 */

public class CourseScheduleSimplified {
    Set<Integer> visited;
    Set<Integer> inProcessNodes;
    Map<Integer, List<Integer>> adjList;
    List<Integer> topologicalOrder;

    private void init(int numCourses) {
        this.visited = new HashSet<>();
        this.inProcessNodes = new HashSet<>();
        this.adjList = new HashMap<Integer, List<Integer>>();
        this.topologicalOrder = new ArrayList<>();
    }

    private void dfs(int node) {
        // Start the recursion
        this.visited.add(node);
        if(!this.inProcessNodes.contains(node)) {
            this.inProcessNodes.add(node);
        }else{
            throw new RuntimeException("cycle!");
        }
        // Traverse on neighboring vertices
        for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<Integer>())) {
            if(this.inProcessNodes.contains(neighbor)){
                throw new RuntimeException("cycle!");
            }
            if (!this.visited.contains(neighbor)) {
                this.dfs(neighbor);
            }
        }
        this.inProcessNodes.remove(node);

        this.topologicalOrder.add(node);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        this.init(numCourses);

        // Create the adjacency list representation of the graph
        // if A depends on B, and A depends on C then the created adjacency list will have such structure:
        // B -> A and C -> A, meaning: those on whom everyone depends will become ROOTS in the graph
        // So, in the adjList MAP, we will see:
        // B -> List{A}, C -> List{A}
        //in other words - we reverse the adjacency from that we had in the beginning of the task
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjList.put(src, lst);
        }

        // If the node is unprocessed, then call dfs on it.
        for (int i = 0; i < numCourses; i++) {
            if (!this.visited.contains(i)) {
                this.dfs(i);
            }
        }

        int[] order = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            order[i] = this.topologicalOrder.get(numCourses - i - 1);
        }


        return order;
    }

    public static void main(String[] args) {
        int[][] courses = new int[][]{
                {3, 10},
                {2, 0},
                {2, 1},
                {3, 2},
                {5, 3},
                {4, 3},
                {8, 5},
                {9, 4},
                {7, 2},
                {6, 7}//,
                //{10, 5} - cycle!
        };

        int array[] = new CourseScheduleSimplified().findOrder(11, courses);

        IntStream.of(array).forEach(System.out::println);
    }

}
