package com.company.topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * Algorithm  (Topological Order)
 *
 * Initialize a stack S that will contain the topologically sorted order of the courses in our graph.
 * Construct the adjacency list using the edge pairs given in the input.
 * An important thing to note about the input for the problem is that a pair such as [a, b] represents
 * that the course b needs to be taken in order to do the course a. This implies an edge of the form b ➔ a.
 * Please take note of this when implementing the algorithm.
 *
 * For each of the nodes in our graph, we will run a depth first search in case that node was not already visited
 * in some other node's DFS traversal.
 *
 * Suppose we are executing the depth first search for a node N. We will recursively traverse all of the neighbors
 * of node N which have not been processed before.
 * Once the processing of all the neighbors is done, we will add the node N to the stack. We are making use of a
 * stack to simulate the ordering we need. When we add the node N to the stack, all the nodes that require the node
 * N as a prerequisites (among others) will already be in the stack.
 * Once all the nodes have been processed, we will simply return the nodes as
 * they are present in the stack from top to bottom.
 *
 *
 * Start from any node, but be sure that dependency direction points that way:
 *
 * if A depends on B, and B depends on C
 *
 * The direction of dependency is   C -> B -> A
 *
 * So, we start exploration, for example from B and thus we build list:
 *
 * A B
 *
 * Then we start exploration from remaining C and this C added:
 *
 * A B C
 *
 * The we print in reverse order: C (B depends on C), B (A depends on B) , and finally A
 *
 *
 *
 *
 *
 */

public class  CourseScheduleDfsReq {

    static int WHITE = 1;
    static int GRAY = 2;
    static int BLACK = 3;

    boolean isPossible;
    Map<Integer, Integer> color;
    Map<Integer, List<Integer>> adjList;
    List<Integer> topologicalOrder;

    private void init(int numCourses) {
        this.isPossible = true;
        this.color = new HashMap<>();
        this.adjList = new HashMap<Integer, List<Integer>>();
        this.topologicalOrder = new ArrayList<>();

        // By default all vertces are WHITE
        for (int i = 0; i < numCourses; i++) {
            this.color.put(i, WHITE);
        }
    }

    private void dfs(int node) {

        // Don't recurse further if we found a cycle already
        if (!this.isPossible) {
            return;
        }

        // Start the recursion
        this.color.put(node, GRAY);

        // Traverse on neighboring vertices
        for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<Integer>())) {
            if (this.color.get(neighbor) == WHITE) {
                this.dfs(neighbor);
            } else if (this.color.get(neighbor) == GRAY) {
                // An edge to a GRAY vertex represents a cycle
                this.isPossible = false;
            }
        }

        // Recursion ends. We mark it as black
        this.color.put(node, BLACK);
        this.topologicalOrder.add(node);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        this.init(numCourses);

        // Create the adjacency list representation of the graph
        // if A depends on B, and A depends on C then the created adjacency list will have such structure:
        // B -> A and C -> A, meaning: those on whom everyone depends will become ROOTS in the graph
        // So, in the adjList MAP, we will see:
        // B -> List{A}, C -> List{A}
        // in other words - we reverse the adjacency from that we had in the beginning of the task

        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src  = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjList.put(src, lst);
        }

        // If the node is unprocessed, then call dfs on it.
        for (int i = 0; i < numCourses; i++) {
            if (this.color.get(i) == WHITE) {
                this.dfs(i);
            }
        }

        int[] order;
        if (this.isPossible) {
            order = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                order[i] = this.topologicalOrder.get(numCourses - i - 1);
            }
        } else {
            order = new int[0];
        }

        return order;
    }

    public static void main(String[] args) {
        int [][] courses = new int[][]{
                {3,10},
                {2,0},
                {2,1},
                {3,2},
                {5,3},
                {4,3},
                {8,5},
                {9,4},
                {7,2},
                {6,7}
        };

        int array[] = new CourseScheduleDfsReq().findOrder(11, courses);

        IntStream.of(array).forEach(System.out::println);
    }

}
