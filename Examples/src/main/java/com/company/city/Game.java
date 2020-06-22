package com.company.city;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Game {


    public Game(){}

    public Set<Cell> play( Set<Cell> liveCells ){
          Set<Cell> processedSet = liveCells;

          while(true){

              Set<Cell> stepResult = playStep(processedSet);
              print(stepResult);
              if(stepResult.size() == 0 || ((stepResult.size()==processedSet.size() && stepResult.containsAll(processedSet)))){
                   //no change , game ended
                   return stepResult;
              }else{
                   //was some not final change, then need be verified
                   processedSet = stepResult;
              }
          }
    }

    public Set<Cell> playStep( Set<Cell> liveCells ){
         Set<Cell> result = new HashSet<>();
         for(Cell cell : liveCells){

             boolean theCellIsLive = getNewLifeStatus(cell, liveCells);

             if(theCellIsLive){
                 result.add(cell);
             }
         }
         return result;
    }

    private boolean getNewLifeStatus(Cell cell, Set<Cell> liveCells) {
           int x =  cell.getX();
           int y =  cell.getY();
           int liveNeighbors = 0;

           for(int i = x-1; i <= x + 1; i++){
               for(int j = y-1; j <= y+1; j++){
                    if( i == x && j == y ) {
                        //skip myself
                    }else{
                        if (i >= 0 && j >= 0) {
                            Cell potential = new Cell(i, j);
                            if (liveCells.contains(potential)){
                                liveNeighbors++;
                            }
                        }
                    }
               }
           }
           if(liveNeighbors < 2){
               return false;
           }else if(liveNeighbors >= 4){
               return false;
           }else{//2,3
               return true;
           }
    }



    private void print(Set<Cell> toPrint){
        Stream.of(toPrint).forEach(t->System.out.println(t));
    }

    public static void main(String[] args) {

        Set<Cell> liveCells = new HashSet<>();
        // 0 0 1 2 3 4
        // 0 0 0 0 0 0
        // 1 0 1 0 1 0
        // 2 0 1 0 1 0
        // 3 0 0 1 0 0
        // 4 0 0 0 0 0

        Cell c1 = new Cell(1,1);
        Cell c2 = new Cell(3,1);
        Cell c3 = new Cell(1,2);
        Cell c4 = new Cell(3,2);
        Cell c5 = new Cell(2,3);

        liveCells.add(c1);
        liveCells.add(c2);
        liveCells.add(c3);
        liveCells.add(c4);
        liveCells.add(c5);

        Game game = new Game();
        game.play(liveCells);

    }




}
