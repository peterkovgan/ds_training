package com.company.bfs_bft;

import java.util.*;

/**
 * It is, basically, simple queue-based BFS
 *
 * When we register parent in parents[i] for i-th node
 *
 * Thus, we able rewind that from the i-th node back
 *
 *
 */

public class BFSFindShortestPathInUnweightedG {

    public static void main(String[] args) {
        /*Note , in opposite to other cases
         * here dependency from child to papa:
         * A->B means A must be studied before B*/
        int[][] edges = new int[][]{
                {10, 3},
                {0, 2},
                {1, 2},
                {2, 3},
                {3, 5},
                {3, 4},
                {5, 8},
                {4, 9},
                {2, 7},
                {7, 6}//,
                //{5, 10}// - cycle!
        };

        int nodeFrom = 10;
        int[] path = new BFSFindShortestPathInUnweightedG().findShortestPath(11, edges, nodeFrom);
        for(int i=0; i<path.length;i++){
            System.out.print(path[i]);
            System.out.print("  ");
        }

        int nodeTo = 9;

        printPathToElement(nodeFrom, nodeTo, path);


    }

    private static void printPathToElement(int nodeFrom, int nodeTo, int[] path ) {
        System.out.println("The path is:");
        System.out.print(nodeTo);
        System.out.print(" ");

        int next = nodeTo;

        while(next!=-1){
            if(path[next]!=-1) {
                System.out.print(path[next]);
                System.out.print(" ");
            }
            next = path[next];
        }
    }

    public int[] findShortestPath(int N, int[][] relations, int nodeFrom) {

        int prenode[] = new int[N];

        Arrays.fill(prenode, -1);

        Set<Integer> visited = new HashSet<>();

        Map<Integer, List<Integer>> map = new HashMap<>();
        //First put in into adj Map
        for (int[] relation : relations) {
            List<Integer> list = map.get(relation[0]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(relation[0], list);
            }
            list.add(relation[1]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(nodeFrom);
        visited.add(nodeFrom);

        while(!queue.isEmpty()){

            Integer node  = queue.poll();

            List<Integer> elements = map.get(node);

            if(elements!=null && elements.size()>0){
                for(Integer el: elements){
                    if(!visited.contains(el)){
                        queue.add(el);
                        visited.add(el);
                        prenode[el]=node;
                    }
                }
            }


        }



        return prenode;
    }


}
