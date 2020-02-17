package com.company.topology;

import java.util.*;

public class TaskCompositionDftReqStackComparison {

    /**
     * Calculate the time, required before the particular task.
     *
     * A tree of tasks, each element may depend on other tasks
     * The problem is very simple
     * The time before task calculated as a Max(time of branches)
     *
     * 0 - 2 - []
     * 1 - 3 - []
     * 2 - 7 - [0, 1]
     * 3 - 8 - [2, 10]
     * 4 - 5 - [3]
     * 5 - 4 - [3]
     * 6 - 11 - []
     * 7 - 10 - []
     * 8 - 6 - [5]
     * 9 - 3 - [4]
     * 10 - 22 -[]
     *
     */

    public static void main(String[] args) {

        Map<Integer, List<Integer>> tasksMap = new HashMap<>();
        tasksMap.put(0, new ArrayList<>());
        tasksMap.put(1, new ArrayList<>());

        List<Integer> t2 = new ArrayList<>();
        t2.add(0);
        t2.add(1);
        tasksMap.put(2, t2);

        List<Integer> t3 = new ArrayList<>();
        t3.add(2);
        t3.add(10);
        tasksMap.put(3, t3);

        List<Integer> t4 = new ArrayList<>();
        t4.add(3);
        tasksMap.put(4, t4);

        List<Integer> t5 = new ArrayList<>();
        t5.add(3);
        tasksMap.put(5, t5);

        tasksMap.put(6, new ArrayList<>());
        tasksMap.put(7, new ArrayList<>());

        List<Integer> t8 = new ArrayList<>();
        t8.add(5);
        tasksMap.put(8, t8);

        List<Integer> t9 = new ArrayList<>();
        t9.add(4);
        tasksMap.put(9, t9);

        tasksMap.put(10, new ArrayList<>());


        int task = 5;

        //It is iterative (stack) pre-order traversal
        //thus it needs a final reversal to order in the post order
        //In theory post order with iterative is possible, but it is harder https://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/
        List<Integer> res1 = getTasksInOrderDftStack(tasksMap, task);
        Collections.reverse(res1);//note reverse!
        System.out.println(res1);

        //Recursive traversals are simpler - they allow easier post and  pre order traversals
        //thus we can avoid the final reversal in the case of the post order
        List<Integer> res11 = getTasksInOrderDftReqPostOrder(tasksMap, task);
        System.out.println(res11);//note, no reverse

        List<Integer> res12 = getTasksInOrderDftReqPreOrder(tasksMap, task);
        Collections.reverse(res12);//note reverse!
        System.out.println(res12);

    }

    private static List<Integer> getTasksInOrderDftReqPreOrder(Map<Integer, List<Integer>> tasksMap, int task) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        reqPreOrder(tasksMap,task,result, visited);
        return result;
    }

    private static void reqPreOrder(Map<Integer, List<Integer>> tasksMap, int task, List<Integer> result, Set<Integer> visited) {
        if(!visited.contains(task)) {
            visited.add(task);
            result.add(task);//pre order!
            List<Integer> children = tasksMap.get(task);
            if (children != null && children.size() > 0) {
                for (Integer child : children) {
                    reqPreOrder(tasksMap,child,result, visited);
                }
            }
        }
    }

    private static List<Integer> getTasksInOrderDftReqPostOrder(Map<Integer, List<Integer>> tasksMap, int task) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        reqPostOrder(tasksMap,task,result, visited);
        return result;
    }

    private static void reqPostOrder(Map<Integer, List<Integer>> tasksMap, int task, List<Integer> result, Set<Integer> visited) {
        if(!visited.contains(task)) {
            visited.add(task);
            List<Integer> children = tasksMap.get(task);
            if (children != null && children.size() > 0) {
                for (Integer child : children) {
                    reqPostOrder(tasksMap,child,result, visited);
                }
            }
            result.add(task);//post order!
        }
    }

    private static List<Integer> getTasksInOrderDftStack(Map<Integer, List<Integer>> tasksMap, int task) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> st = new Stack<>();


        st.add(task);
        visited.add(task);

        while(!st.isEmpty()){
            Integer node = st.pop();
            List<Integer> children = tasksMap.get(node);
            result.add(node);
            if(children!=null && children.size() > 0){
                for(Integer child:children){
                    if(!visited.contains(child)){
                        st.add(child);
                        visited.add(child);
                    }
                }
            }
            //If you put it here - it does not matter - it is anyway a pre-order
            //result.add(node);
        }
        return result;
    }



}
