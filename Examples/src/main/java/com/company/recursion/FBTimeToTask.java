package com.company.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FBTimeToTask {

    /**
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

        Map<Integer, Integer> taskDuration = new HashMap<>();
        taskDuration.put(0,2);
        taskDuration.put(1,3);
        taskDuration.put(2,7);
        taskDuration.put(3,8);
        taskDuration.put(4,5);
        taskDuration.put(5,4);
        taskDuration.put(6,11);
        taskDuration.put(7,10);
        taskDuration.put(8,6);
        taskDuration.put(9,3);
        taskDuration.put(10,22);

        int task1 = 5;
        System.out.println(timeToTask(tasksMap, taskDuration, task1) - taskDuration.get(task1));

        int task2 = 9;
        System.out.println(timeToTask(tasksMap, taskDuration, task2) - taskDuration.get(task2));

        int task3 = 6;
        System.out.println(timeToTask(tasksMap, taskDuration, task3) - taskDuration.get(task3));

    }

    private static int timeToTask(Map<Integer, List<Integer>> tasksMap, Map<Integer, Integer> taskDuration, int task){

         int myTime = taskDuration.get(task);

         List<Integer> children = tasksMap.get(task);

         int childrenTime = 0;

         if(children != null && children.size() > 0) {

             List<Integer> times = new ArrayList<>();

             for (Integer child : children) {

                  times.add(timeToTask(tasksMap, taskDuration, child));

             }

             childrenTime =  times.stream().max(Integer::compare).get();

         }

         return myTime + childrenTime;

    }


}
