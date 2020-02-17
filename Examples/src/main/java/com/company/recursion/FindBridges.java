package com.company.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * FindBridges in a graph
 * <p>
 * This works , but memory limits broken
 * <p>
 * https://www.youtube.com/watch?v=aZXi1unBdJA
 * <p>
 * https://leetcode.com/problems/critical-connections-in-a-network
 */
public class FindBridges {

    List<List<Integer>> bridges = new ArrayList<>();
    boolean[] visited;
    int[] ids;
    int[] low_links;
    int graph[][];

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        visited = new boolean[n];
        ids = new int[n];
        Arrays.fill(ids, -1);
        low_links = new int[n];
        Arrays.fill(low_links, -1);
        graph = new int[n][n];
        int i = 0;
        for (List<Integer> adj : connections) {
            graph[adj.get(0)][adj.get(1)] = 1;
            graph[adj.get(1)][adj.get(0)] = 1;
            i++;
        }
        dfs(0, -1, -1);
        return bridges;
    }

    /**
     *  start from node 0 (we do not need other nodes to start, because we know all nodes connected)
     *
     *  parentIndex = -1 on start
     *
     *  bfsId - we need number all node, using DFS
     *
     *
     */
    void dfs(int nodeIndex, int parentIndex, int bfsId) {

        visited[nodeIndex] = true;

        bfsId = bfsId + 1;

        ids[nodeIndex] = low_links[nodeIndex] = bfsId;

        int[] nbrs = graph[nodeIndex];

        for (int i = 0; i < nbrs.length; i++) {

            if (nbrs[i] == 1) {//has link

                if (i == parentIndex) continue;

                if (!visited[i]) {
                    dfs(i, nodeIndex, bfsId);

                    //it is propagating low_links value up by the recursion path (saving low-Links traversal time)
                    low_links[nodeIndex] = Math.min(low_links[nodeIndex], low_links[i]);

                    if (ids[nodeIndex] < low_links[i]) { //It is a bridge!!!
                        List<Integer> arr = new ArrayList<>();
                        arr.add(nodeIndex);
                        arr.add(i);
                        bridges.add(arr);
                    }

                } else {
                    //if neighbor node was visited, if its ID is small, change my low_link value
                    low_links[nodeIndex] = Math.min(low_links[nodeIndex], ids[i]);
                }
            }

        }

    }

}
