package com.company.recursion;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//DFS with 3 colors
public class EventuallySafeGraphNode {
    //https://leetcode.com/problems/find-eventual-safe-states
    public static void main(String[] args) {
        int def[][] = new int[][]{{1,2},{2,3},{5},{0},{5},{},{}};
        System.out.println(new EventuallySafeGraphNode().eventualSafeNodes(def));

    }

    /**
     * DFS with white, grey, black
     * Time Complexity: O(N + E)O(N+E), where NN is
     * the number of nodes in the given graph, and EE is the total number of edges.
     * Space Complexity: O(N)O(N) in additional space complexity.
     */

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int N = graph.length;
        int[] color = new int[N];
        List<Integer> ans = new ArrayList();

        for (int i = 0; i < N; ++i)
            if (dfs(i, color, graph))
                ans.add(i);
        return ans;
    }

    // colors: WHITE 0, GRAY 1, BLACK 2;
    public boolean dfs(int node, int[] color, int[][] graph) {
        if (color[node] > 0)
            return color[node] == 2;

        color[node] = 1;
        for (int nei: graph[node]) {
            if (color[node] == 2)
                continue;
            if (color[nei] == 1 || !dfs(nei, color, graph))
                return false;
        }

        color[node] = 2;
        return true;
    }


    public List<Integer> eventualSafeNodes1(int[][] graph) {


        boolean[] visited = new boolean [graph.length];
        boolean cycledGuys[] = new boolean [graph.length];

        for(int i=0; i < graph.length; i++){

            if(!visited[i] && !cycledGuys[i]) {                   //if not visited and not marked cycled
                boolean markArray[] = visited.clone();            //try this , and then disconnect if failed
                boolean cycle = isCycle(i, graph, markArray, cycledGuys);
                if (!cycle) {
                    for (int y = 0; y < markArray.length; y++) {
                        if (markArray[y]) {
                            visited[y] = true;
                        }
                    }
                }else{
                    cycledGuys[i] = true;
                }
            }
        }

        return IntStream.range(0,visited.length).filter(i->visited[i]).boxed().collect(Collectors.toList());
    }

    private boolean isCycle(int startNode, int[][] graph, boolean[] markArray, boolean [] cycledGuys) {
        boolean cycleArray[] = new boolean[markArray.length];
        return isReqCycle(startNode, markArray, cycleArray, graph, cycledGuys);
    }

    private boolean isReqCycle(int startNode, boolean[] markArray, boolean[] cycleArray, int[][] graph, boolean cycledGuys[]) {
        if (cycleArray[startNode]){
            cycledGuys[startNode]=true;
            return true;
        }
        if (markArray[startNode]) {
            return false;
        }
        markArray[startNode] = true;
        cycleArray[startNode] = true;
        int children[] = graph[startNode];
        for(int child: children){
            if (isReqCycle(child, markArray, cycleArray,graph, cycledGuys)) {
                cycledGuys[child] = true;
                return true;
            }else{
                markArray[child] = true;
            }
        }
        cycleArray[startNode] = false;
        return false;

    }

}
