package com.company.bfs_bft;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class BFSearch {

    /**
     *      BFS using queue
     *      in WHILE loop twice checked that not visited
     *      for itself and for children
     */


    // Driver program to test methods of graph class
    public static void main(String[] args) {
        // Total 5 vertices in graph
        Graph g = new Graph(5);

        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 4);

        System.out.println("Following is the Breadth First Traversal");
        g.breadthFirstSearch(0);
    }


    static class Graph {


        int verticesNumber; //Number of Vertices

        LinkedList<Integer>[] adj; // adjacency lists

        //Constructor
        Graph(int verticesNumber) {
            this.verticesNumber = verticesNumber;
            adj = new LinkedList[verticesNumber];

            for (int i = 0; i < verticesNumber; i++)
                adj[i] = new LinkedList<Integer>();
        }

        //To add an edge to graph
        void addEdge(int verticesNumber, int w) {
            adj[verticesNumber].add(w); // Add w to verticesNumber’s list.
        }

        // prints all not yet visited vertices reachable from s
        void breadthFirstSearch(int s) {
            // Initially mark all vertices as not visited
            Vector<Boolean> visited = new Vector<Boolean>(verticesNumber);
            for (int i = 0; i < verticesNumber; i++)
                visited.add(false);

            // Create a stack for DFS
            Queue<Integer> queue = new LinkedList<>();

            // Push the current source node
            queue.add(s);

            while (!queue.isEmpty()) {
                // Pop a vertex from stack and print it
                s = queue.poll();

                // Stack may contain same vertex twice. So
                // we need to print the popped item only
                // if it is not visited.
                if (visited.get(s) == false) {
                    System.out.print(s + " ");
                    visited.set(s, true);
                }

                // Get all adjacent vertices of the popped vertex s
                // If a adjacent has not been visited, then push it
                // to the stack.
                Iterator<Integer> itr = adj[s].iterator();

                while (itr.hasNext()) {
                    int verticesNumber = itr.next();
                    if (!visited.get(verticesNumber))
                        queue.add(verticesNumber);
                }

            }
        }


    }


}
