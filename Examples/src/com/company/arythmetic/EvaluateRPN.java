package com.company.arythmetic;

import java.util.*;

public class EvaluateRPN {
    // Reversed polish notation, looks like: a b +
    // Take a list
    // Find the first symbol after 2 numbers, replace it by the result

    public static void main(String[] args) {
        //String str[] =  {"2", "1", "+", "3", "*"};
        //String str[] =  {"4", "13", "5", "/", "+"};
        String str[] =  {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(new EvaluateRPN().evalRPN(str));

    }

    public int evalRPN(String[] tokens) {
        if(tokens==null || tokens.length==0) return 0;
        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");

        LinkedList<String> ll = new LinkedList<>();
        for(int i=0; i < tokens.length; i++){
            ll.add(tokens[i]);
        }

        String param = "";
        int result = 0;

        while(ll.size() > 0){
            String symb = "";
            Iterator<String> it =  ll.iterator();
            int countNumbers = 0;
            int symbIndex = -1;
            while(it.hasNext()){
                symb = it.next();
                symbIndex++;
                if(operators.contains(symb) && countNumbers>=2){
                    //calculate and remove, continue:
                    result = reduceArray(symbIndex,ll);
                    break;
                }else if(operators.contains(symb) && countNumbers < 2){
                    countNumbers = 0;
                }else{
                    countNumbers++;
                }
            }
        }

        return result;


    }

    private int reduceArray(int symbIndex, LinkedList<String> ll) {

        int result = 0;

        String operator = ll.get(symbIndex);

        String operand1 = ll.get(symbIndex-2);
        String operand2 = ll.get(symbIndex-1);

        int op1 = Integer.valueOf(operand1);
        int op2 = Integer.valueOf(operand2);

        switch(operator){
            case "+":
                result = op1+op2;
                break;
            case "-":
                result = op1-op2;
                break;
            case "*":
                result = op1*op2;
                break;
            case "/":
                result = op1/op2;
                break;
        }

        int indRem = symbIndex-2;

        ll.remove(indRem);
        ll.remove(indRem);
        ll.remove(indRem);

        if(ll.size()>0){
            ll.add(indRem, result+"");
        }

        return result;
    }

}
