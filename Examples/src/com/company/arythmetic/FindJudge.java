package com.company.arythmetic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 *
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
 *
 * If the town judge exists, then:
 *
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 *
 * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 2, trust = [[1,2]]
 * Output: 2
 * Example 2:
 *
 * Input: N = 3, trust = [[1,3],[2,3]]
 * Output: 3
 * Example 3:
 *
 * Input: N = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 * Example 4:
 *
 * Input: N = 3, trust = [[1,2],[2,3]]
 * Output: -1
 * Example 5:
 *
 * Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 * Output: 3
 *
 *
 * Note:
 *
 * 1 <= N <= 1000
 * trust.length <= 10000
 * trust[i] are all different
 * trust[i][0] != trust[i][1]
 * 1 <= trust[i][0], trust[i][1] <= N
 *
 *
 */


public class FindJudge {

    private Map<Integer, Set<Integer>> deps = new HashMap<>();

    public int findJudge(int N, int[][] trust) {
        for(int y = 0; y < trust.length; y++){
            int who = trust[y][0];
            //System.out.println("who "+who);
            int trustsWhom=trust[y][1];
            //System.out.println("trustsWhom "+trustsWhom);
            Set<Integer> trustees = deps.get(who);
            if(trustees==null){
                trustees = new HashSet<>();
                deps.put(who, trustees);
            }
            trustees.add(trustsWhom);
        }

        int a = 1;
        int b = N;

        //0  1  2  3  4  5  6
        while( a < b ){
            if(trusts(a, b)){   //this excludes people from left
                a++;
            }else{
                b--;            //this excludes people from right
            }
        }

        //now a - is a potential judge
        //but he is not checked on those from left (they must trust him he must NOT trust them)


        for(int i = 1; i <= N; i++){
            if(i != a && ( trusts(a, i) || !trusts(i, a) )){
                return -1;
            }
        }

        return a;

    }

    private boolean trusts(int a, int b){
        if(deps.keySet().contains(a)){
            if(deps.get(a).contains(b)){
                return true;
            }
        }
        return false;
    }

}
