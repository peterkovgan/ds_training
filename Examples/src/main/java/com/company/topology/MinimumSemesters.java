package com.company.topology;

import java.util.*;
import java.util.stream.IntStream;

public class MinimumSemesters {

    /**
     *
     * DFS , levels, count all levels
     *
     * Levels recounted if must be increased
     *
     *
     */

    public static void main(String[] args) {
        /*Note , in opposite to other cases
        * here dependency from child to papa:
        * A->B means A must be studied before B*/
        int [][] courses = new int[][]{
                {10,3},
                {0,2},
                {1,2},
                {2,3},
                {3,5},
                {3,4},
                {5,8},
                {4,9},
                {2,7},
                {7,6}//,
                //{5, 10}// - cycle!
        };

        System.out.println(new MinimumSemesters().minimumSemesters(11, courses ));
    }

    public int minimumSemesters(int N, int[][] relations) {

        Map<Integer, List<Integer>> map = new HashMap<>();
         //First put in into adj Map
        for(int [] relation : relations){
            List<Integer> list = map.get(relation[0]);
            if(list==null){
                list = new ArrayList<>();
                map.put(relation[0], list);
            }
            list.add(relation[1]);
       }

       //set all levels to 0
       Map<Integer, Integer> nodeLevels = new HashMap<>();
       IntStream.range(0, N).forEach(e->nodeLevels.put(e, 1));

       for(Integer startNode: map.keySet()){
           //to detect cycles
           Set<Integer> inProcess = new HashSet<>();
           if(!req(startNode, nodeLevels, map, inProcess, 1)){
               //cycle detected
               return -1;
           }
       }

       return nodeLevels.values().stream().max(Integer::compare).get();

    }

    private boolean req(Integer node, Map<Integer, Integer> nodeLevels, Map<Integer, List<Integer>> map, Set<Integer> inProcess, int loopNumber) {

            int currentRegisteredLevel = nodeLevels.get(node);

            int currentLevelToContinue = 0;

            if(currentRegisteredLevel == loopNumber){
                currentLevelToContinue = loopNumber;
                if(currentLevelToContinue > 1){
                    //revisit earlier visited
                    return true;
                }
            }else if(currentRegisteredLevel < loopNumber){
                currentLevelToContinue = loopNumber;
            }else if(currentRegisteredLevel > loopNumber){
                currentLevelToContinue = currentRegisteredLevel;
                if(currentLevelToContinue > 1){
                    //revisit earlier visited
                    return true;
                }
            }

            nodeLevels.put(node, currentLevelToContinue);
            int nextLoopNumber = currentLevelToContinue + 1;

            if(inProcess.contains(node)){
                return false;//cycle
            }else {
                inProcess.add(node);
            }
            List<Integer> children = map.get(node);
            if(children!=null && children.size()>0){
                   for(int child: children) {
                       if(inProcess.contains(child)) return false;
                       boolean res = req(child, nodeLevels, map, inProcess, nextLoopNumber);
                       if(!res) return false;
                   }
            }
            inProcess.remove(node);
            return true;
    }


}
