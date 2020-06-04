package com.company.topology;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.IntStream;


//Can be done on DAG (without cycles) with Weights on edges
//for not DAG implementation, see Dijkstra

public class ShortestPathInDagBasedOnTopologicalOrder {

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2},
                {4, 3},
                {4, 1}
        };

        int namVertices = 5; //(edges from 0 to 10)

        //make a topOrderFirst by ANY approach
        int[] topOrder = new CourseScheduleDfsReq().findOrder(namVertices, edges); // DFS on adj list here: ( O(V+E) )

        System.out.println("Top order:");

        IntStream.of(topOrder).forEach(System.out::println);

        //adj map
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for(int [] edge : edges){
            List<Integer> list = adjList.get(edge[1]);
            if(list==null){
                list = new ArrayList<>();
                adjList.put(edge[1], list);
            }
            list.add(edge[0]);
        }


        //weights of edges
        Map<Pair,Integer> weights = new HashMap<>();

        weights.put(new Pair(1,0), 1);
        weights.put(new Pair(0,1), 1);

        weights.put(new Pair(2,0), 1);
        weights.put(new Pair(0,2), 1);

        weights.put(new Pair(3,1), 2);
        weights.put(new Pair(1,3), 2);

        weights.put(new Pair(3,2), 1);
        weights.put(new Pair(2,3), 1);

        weights.put(new Pair(4,3), 1);
        weights.put(new Pair(3,4), 1);

        weights.put(new Pair(4,1), 8);
        weights.put(new Pair(1,4), 8);

        int shPath [] = findShortestPath(0, weights, adjList,  topOrder);

        System.out.println("");
        System.out.println("Distances---");
        IntStream.of(shPath).forEach(e->System.out.print(e + " "));



    }

    private static int[] findShortestPath(int node, Map<Pair, Integer> weights, Map<Integer, List<Integer>> adjList, int[] topOrder) {

        int shortPathDistances [] = new int[topOrder.length];
        shortPathDistances[node] = 0;
        for(int i=0; i<shortPathDistances.length;i++){
            if(i!=node)
               shortPathDistances[i]=Integer.MAX_VALUE; //means infinity
        }

        int predecessors [] = new int[topOrder.length]; //keeps predecessors (of U) nodes in the path from S to U
        Arrays.fill(predecessors, -1);

        //find start node
        int indexNode = 0;

        for(int i=0; i<topOrder.length; i++){
            if(topOrder[i] == node){
                indexNode = i;
                break;
            }
        }

        for(int i = indexNode; i<topOrder.length; i++){

            int u = topOrder[i];

            List<Integer> verts = adjList.get(u);

            if(verts!=null && verts.size()>0){

                for(Integer v:verts){//the link from i to v

                     int weightIV = weights.get(new Pair(u,v));

                     if(shortPathDistances[v] > shortPathDistances[u] + weightIV){

                         shortPathDistances[v] = shortPathDistances[u] + weightIV;

                         predecessors[v] = u;

                     }
                }

            }
        }

        System.out.println("Predecessors----");
        /**
         * Predecessors allow, starting from the last node, track the path to the first one, thus construction the entire shortest path
         */
        IntStream.of(predecessors).forEach(e->System.out.print(e + " "));


        return shortPathDistances;
    }

}
