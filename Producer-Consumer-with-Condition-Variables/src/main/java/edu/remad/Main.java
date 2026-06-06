package edu.remad;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		
		Condition condition = lock.newCondition();
		
		// Thread1
		lock.lock();
		
		condition.await();
		
		lock.unlock();
		
		// Thread 2
		lock.lock();
		
		condition.signal();
		
		lock.unlock();
	}
}
