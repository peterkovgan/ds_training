package com.company.cato;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Example {


    Thread t = new Thread();


    public void m() {
        Lock lock = new ReentrantLock();
        lock.lock();
    }



}
