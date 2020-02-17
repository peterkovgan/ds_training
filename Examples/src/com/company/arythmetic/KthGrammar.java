package com.company.arythmetic;

public class KthGrammar {

    public static void main(String[] args) {
        System.out.println(new KthGrammar().kthGrammar(4, 5));
    }

    public int kthGrammar(int N, int K) {

        int A[] = new int[]{0,1,1,0};
        int B[] = new int[]{1,0,0,1};

        if(N<=3) {

            if (N == 1) {
                if (K != 1) throw new RuntimeException();
                return 0;
            }

            if (N == 2) {
                if (K != 1 && K != 2) throw new RuntimeException();
                if (K == 1) return 0;
                if (K == 2) return 1;
            }

            if (N == 3) {
                if (K < 1 || K > 4) throw new RuntimeException();
                return A[K - 1];
            }
        }else {

            int floor = N - 2 - 1; //in 0 index

            int highestFloor = floor;

            int room = (K - 1) / 4;

            int bed = (K - 1) % 4;

            int roomsOnFloor[] = new int[30];


            //remember current room
            roomsOnFloor[floor] =  room;

            //go up to the root
            while (floor > 0) {
                floor--;
                room = room / 2;
                roomsOnFloor[floor] =  room;
            }

            int currentWord[] = A;

            //go down
            for (int f = 1; f <= highestFloor; f++) {
                int roomNow = roomsOnFloor[f];
                if (roomNow % 2 == 0) {
                    //go left
                    if (currentWord[0]==A[0]) {
                        currentWord = A;
                    } else {
                        currentWord = B;
                    }
                } else if (roomNow % 2 == 1) {
                    //go right
                    if (currentWord[0] == A[0]) {
                        currentWord = B;
                    } else  {
                        currentWord = A;
                    }
                }
            }

            return currentWord[bed];
        }

        throw new RuntimeException();
    }

}
