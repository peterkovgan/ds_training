package com.company.arythmetic;

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
 */


public class FindJudgeBetter {

    public int findJudge(int N, int[][] trust) {

        int arr[]=new int[N];

        for(int i=0;i<trust.length;i++){
            //trust[i][0] - mister A that trusts in somebody
            arr[trust[i][0]-1]=-1; //reduce chances of Mr A to become judge
            //trust[i][1] - mister B, that somebody trusts him
            arr[trust[i][1]-1]+=1; //increase chances of Mr B to become a judge
        }

        for(int i=0;i<N;i++){
            if(arr[i] == N-1) return (i+1); //if somebody given the trust of everyone else and not trusted anyone, he is a judge
        }

        return -1;

    }



}
