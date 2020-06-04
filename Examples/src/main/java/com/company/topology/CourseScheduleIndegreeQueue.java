package com.company.topology;

import java.util.*;
import java.util.stream.IntStream;

public class CourseScheduleIndegreeQueue {

    /**
     *
     * Must be DAG, no cycles!!!!
     *
     * https://en.wikipedia.org/wiki/Topological_sorting
     * It is Kahn algorithm version
     *
     * https://leetcode.com/problems/course-schedule-ii/solution/
     * <p>
     * Initialize a queue, Q to keep a track of all the nodes in the graph with 0 in-degree.
     * Iterate over all the edges in the input and create an adjacency list and also a map of node v/s in-degree.
     * Add all the nodes with 0 in-degree to Q.
     * The following steps are to be done until the Q becomes empty.
     * Pop a node from the Q. Let's call this node, N.
     * For all the neighbors of this node, N, reduce their in-degree by 1. If any of the nodes' in-degree reaches 0, add it to the Q.
     * Add the node N to the list maintaining topologically sorted order.
     * Continue from step 4.1.
     *
     *
     * Suppose A depends on B and C
     *
     * D depends on A
     *
     * D <--- A <-----B
     *        ^
     *        |
     *        C
     *
     * Note, C and B have only exiting arrows, no entering arrows - so they are appropriate to start from them (everybody depends on them)
     *
     * They (B and C) depend on nobody, so their indegree = 0
     *
     * A depends on 2, so its indegree = 2
     *
     * Indegree calculated together with the adjacency list creation:
     *
     * B -> A (indegree [A]+=1
     * C -> A (indegree [A]+=1
     *
     * One that nobody points on it has indegree  = 0
     *
     * Add to queue all nodes with indegree == 0
     *
     * start while (queue is not empty) loop
     *
     * Now: how we feel topological order array
     * get from queue = > add this node to topological order array
     *
     * Take adjacency list of this node
     * And reduce indegree of every node in the adjacency list by 1
     * in reduced indegree == 0, add this node to the list
     *
     * end while loop
     *
     * O(V+E) - but just because we use adj list, in matrix it will be V^2
     *
     *
     */

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
        int array[] =  new CourseScheduleIndegreeQueue().findOrder(11, courses);
        IntStream.of(array).forEach(System.out::println);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        // Create the adjacency list representation of the graph
        // if A depends on B, and A depends on C then the created adjacency list will have such structure:
        // B -> A and C -> A, meaning: those on whom everyone depends will become ROOTS in the graph
        // So, in the adjList MAP, we will see:
        // B -> List{A}, C -> List{A}
        //in other words - we reverse the adjacency from that we had in the beginning of the task
        for (int i = 0; i < prerequisites.length; i++) {

            int dest = prerequisites[i][0];
            int src  = prerequisites[i][1];

            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());

            lst.add(dest);

            adjList.put(src, lst);

            // Record in-degree of each vertex
            // Note, that B and C (from the description above, they are "Sources", not belonging to "destinations")
            // will have 0 indegree, as roots have (nobody points on them).
            // but A will be "mentioned" (or "pointed on") twice, by link from B and by the link from C, so we have a node A, pointed by 2 roots
            // so A will have an indegree 2
            indegree[dest] += 1;
        }

        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);              //It means, only "roots" added into the queue , guess why...
            }
        }

        int i = 0;
        // Process until the Q becomes empty
        while (!q.isEmpty()) {
            int node = q.remove();            //get the root
            topologicalOrder[i++] = node;     //add it to the topology (print it)

            // Reduce the in-degree of each neighbor by 1 - meaning: we processed papa, we removed it, so child left without 1 papa
            if (adjList.containsKey(node)) {
                for (Integer neighbor : adjList.get(node)) {
                    indegree[neighbor]--;

                    // If in-degree of a neighbor becomes 0, add it to the Q, meaning: the child has 0 papas, it can be analyzed as papa now
                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }
        }

        // Check to see if topological sort is possible or not.
        if (i == numCourses) {           //all nodes from the task input reached and got through the queue, no cycles, no unreacheable nodes.
            return topologicalOrder;
        }

        return new int[0];
    }


}
