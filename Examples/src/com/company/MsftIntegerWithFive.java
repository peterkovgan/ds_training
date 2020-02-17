package com.company;

public class MsftIntegerWithFive {

    public static void main(String[] args) {

        System.out.println(solution(6656));
    }

    public static int solution(int N) {
        if( N == 0) return 50;

        if(N < 0){

            //-555
            //-5555
            //-20
            //-520 -250 -205

            //-23
            //-523 -253 -235

            //-25
            //-525 -255 -255

            //-26
            //-526 -256 -265

            //-66
            //-566, -656, -665

            //-6656
            //56656 65656 66556 66556 66565
            return processNegative(N);

        }else{

            //25
            //525  255  255

            //26
            //526  256  265

            //66
            //566  656  665

            //6656
            //56656 65656 66556 66556 66565

            return processPositive(N);


        }
    }

    private static int processPositive(int N) {
        String number = N+"";
        char array[] = number.toCharArray();
        String result = "";
        boolean inserted = false;

        for(int i = 0; i<array.length ; i++){
            if(array[i] - 48 < 5 && !inserted){
                result = result+"5"+array[i];
                inserted = true;
            }else{
                result = result+array[i];
            }
        }
        if(!inserted){
            result = result+"5";
        }

        return Integer.valueOf(result);
    }

    private static int processNegative(int N) {
        String number = N+"";
        char array[] = number.toCharArray();
        String result = "";
        boolean inserted = false;

        for(int i = 1; i<array.length ; i++){
            if(array[i] - 48 > 5 && !inserted){
                result = result+"5"+array[i];
                inserted = true;
            }else{
                result = result+array[i];
            }
        }
        if(!inserted){
            result = result+"5";
        }
        return - Integer.valueOf(result);
    }

}
