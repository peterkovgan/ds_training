package com.company.forcepoint;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BankAccount implements Serializable {

    static int flowingAccountNumber = 1000;

    private int accountNumber;

    private transient String creditCardNumber;

    private static Set<Integer> vipAccounts = new HashSet<>();;

    public BankAccount(int accountNumber){
        this.accountNumber = accountNumber;
    }

    public static BankAccount createAccount(){
        while(vipAccounts.contains(flowingAccountNumber++)){
        }
        return new BankAccount(flowingAccountNumber);
    }

    public static BankAccount createAccount(int vipAccountNumber){
        if(vipAccountNumber >= flowingAccountNumber && !vipAccounts.contains(vipAccountNumber)){
            vipAccounts.add(vipAccountNumber);
            return new BankAccount(vipAccountNumber);
        }else{
            throw new RuntimeException("Select another number , not that " + vipAccountNumber);
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "acountNumber=" + accountNumber +
                '}';
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            BankAccount account = BankAccount.createAccount();
            System.out.println(account);
        }




    }


}
