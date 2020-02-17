package com.company.recursion;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  https://leetcode.com/problems/clone-graph/
 *
 *  Given a reference of a node in a connected undirected graph,
 *  return a deep copy (clone) of the graph. Each node
 *  in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 *
 *
 * The number of nodes will be between 1 and 100.
 * The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 * Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
 * You must return the copy of the given node as a reference to the cloned graph.
 *
 * BFS or DFS
 *
 * O(N) time and space
 *
 * PLUS (in space):
 *
 * DFS
 * The space occupied by the recursion stack would be equal to O(H) where HH is the height of the graph.
 * Overall, the space complexity would be O(N).
 * BFS
 * The space occupied by the queue would be equal to O(W) where WW is the width of the graph.
 * Overall, the space complexity would be O(N).
 *
 *
 */



public class CloneGraph {

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    };


    Map<Node, Node> visitedHash = new HashMap<Node, Node>();

    public Node cloneGraph(Node node) {
        return discovery(node);
    }

    public Node discovery(Node node) {
        if(node == null) return null;
        if( visitedHash.keySet().contains(node) ){
            return visitedHash.get(node);
        }
        Node newNode = new Node(node.val, new ArrayList<Node>());
        visitedHash.put(node, newNode);
        for(Node nd: node.neighbors){
            newNode.neighbors.add(discovery(nd));
        }
        return newNode;
    }

}
