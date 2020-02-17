package com.company;

public class TwoDArrayExplanations {

    public static void main(String[] args) {

        String[][] array = new String[3][2];

        System.out.println("array.length="+array.length); //3 - X - WIDTH shown

        // WIDTH - HEIGHT
        // String[][] array = new String[WIDTH][HEIGHT]; !!!
        // String[][] array = new String[X][Y]; !!!
        //init array
        for(int y = 0; y < 2; y++){
            for(int x = 0; x < 3; x++){
                array[x][y] = x+"/"+y;
            }
        }

        for(int y = 0; y < 2; y++){
            for(int x = 0; x < 3; x++){
                System.out.print(array[x][y]);
                System.out.print(" ");
            }
            System.out.println("");
        }

    }
}
