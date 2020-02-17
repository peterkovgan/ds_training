package com.company.graph;

import javafx.util.Pair;

import java.util.*;

public class Dijkstra {

    /**
     * Good for Directed graphs even with CYCLEs  (DCG)
     * Complexity O ( V * log V )
     *
     * If there were NO cycles(DAG), then another approach would fit better: topological ordering ,
     * followed by repeated relaxation of the "final distance to the node"
     * Described in the: ShortestPathInDagBasedOnTopologicalOrder
     *
     *
     * But here we use Dijkstra
     *
     * Dijkstra is similar to BFS in some sense
     * But instead of queue , we use mean heap. (we call it "unsettled nodes")
     * And in this heap we add nodes, based on the node distance
     * Thus nodes with smaller distance will be fetched first from the queue(heap)
     * And thus, we always go by the minimum path.
     *
     * We also relax distances, on the way
     * And we populate "settled" Set by nodes, once we processed those nodes:
     * "poll from unsettled - add to settled."
     *
     * Note, that distances must be set infinity to all nodes , except the one ,
     * where the process starts (that one must be set 0)
     *
     * Note, it is not obvious...
     * But Dijkstra is super-clever:
     * It is not that it is not afraid of cycles.
     * It is quite afraid of them.
     * But they(cycles) are neutralized by the fact, that such node (one with 2 or more arrows pointing into it)
     * will be first approached from the smaller distance.
     * Thus it will be settled first by the shortest path to it.
     *
     *
     *
     */

    public static void main(String[] args) {

        int[][] edges = new int[][]{
                {0, 1},
                {0, 2},
                {1, 3},
                {2, 3},
                {3, 4},
                {1, 4},
                {4, 1}//cycle! which  Dijkstra must not respect

        };

        int numVertices = 5; //(edges from 0 to 10)

        int findPathFrom = 0;

        Map<Pair, Integer> weights = new HashMap<>();
        weights.put(new Pair(0, 1), 8);
        weights.put(new Pair(0, 2), 1);
        weights.put(new Pair(1, 3), 2);
        weights.put(new Pair(2, 3), 1);
        weights.put(new Pair(3, 4), 1);
        weights.put(new Pair(1, 4), 8);
        weights.put(new Pair(4, 1), 1);

        int[] distance = new Dijkstra().getDistance(numVertices, edges, findPathFrom, weights);


        for (int i = 0; i < distance.length; i++) {
            System.out.println("shortest  to " + i + " = " + distance[i]);
        }


    }

    class Node {
        int value;
        int distance = Integer.MAX_VALUE;

        Node(int value) {
            this.value = value;
        }
    }

    class MinHeap {

        List<Node> sorted = new ArrayList<>();

        void addNode(Node node) {
            sorted.add(node);
        }

        boolean isEmpty() {
            return sorted.isEmpty();
        }

        Node getMinAndRemove() {
            Collections.sort(sorted, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.distance - o2.distance;
                }
            });

            System.out.println("Sorted======:");

            for(Node n:sorted){
                System.out.print(n.distance);
                System.out.print("  ");
            }

            Node nde = sorted.remove(0);

            System.out.println("Removed "+nde.distance);

            return nde;
        }
    }

    private int[] getDistance(int namVertices, int[][] edges, int findPathFrom, Map<Pair, Integer> weights) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] distances = new int[namVertices];
        //First put in into adj Map
        for (int[] relation : edges) {
            List<Integer> list = map.get(relation[0]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(relation[0], list);
            }
            list.add(relation[1]);
        }
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[findPathFrom] = 0;

        Set<Integer> settled = new HashSet<>();
        MinHeap unsettled = new MinHeap();

        Node source = new Node(findPathFrom);
        source.distance = 0;
        unsettled.addNode(source);

        while (!unsettled.isEmpty()) {

            Node node = unsettled.getMinAndRemove();
            List<Integer> children = map.get(node.value);
            settled.add(node.value);

            if (children != null && children.size() > 0) {
                for (Integer child : children) {
                    if (!settled.contains(child)) {
                        int weight = weights.get(new Pair(node.value, child));
                        if (distances[child] > distances[node.value] + weight) {
                            distances[child] = distances[node.value] + weight;
                        }
                        Node childN = new Node(child);
                        childN.distance = distances[child];
                        unsettled.addNode(childN);
                    }
                }
            }

        }


        return distances;


    }
}
