package com.company.topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Topological order + DP , that calculates the times below of each node
 */

public class MinTimeForTasksInParallel {

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

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int [] cour : courses){
            List<Integer> list = adjList.get(cour[0]);
            if(list==null){
                list = new ArrayList<>();
                adjList.put(cour[0], list);
            }
            list.add(cour[1]);
        }


        int numCourses = 11; //(courses from 0 to 10)

        //make a topOrderFirst by any approach
        int [] topOrder = new CourseScheduleDfsReq().findOrder(numCourses, courses);

        IntStream.of(topOrder).forEach(System.out::println);

        //Courses duration
        int timesOfCourses [] = new int []{3,4,5,3,6,7,2,1,6,8,5};

        //Now, I peak courses one by one, calculating how to parallelize them
        int minTime = 0;

        int dp[] = new int[numCourses];

        for(int c : topOrder){
            int courseBodyTime = timesOfCourses[c];//the time of the course itself
            List<Integer> iDependOnCourses = adjList.get(c);
            int maxChild = 0;
            if(iDependOnCourses!=null && iDependOnCourses.size()>0){
                for(int childCourse:iDependOnCourses){
                    maxChild = Math.max(maxChild, dp[childCourse]);
                }
            }
            minTime = dp[c] = courseBodyTime + maxChild;
        }

        System.out.println("-----------Times---------");

        IntStream.of(dp).forEach(System.out::println);


        System.out.println("Min time = " + minTime);



    }

}
