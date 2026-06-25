package edu.remad;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	private static Lock lock1 = new ReentrantLock();
	private static Lock lock2 = new ReentrantLock();
	
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			lock1.lock();
			System.out.println("Thread 1 acquired lock1");
			
			lock2.lock();
			System.out.println("Thread 1 acquired lock2");
			lock2.unlock();
			
			lock1.unlock();
		});
		Thread t2 = new Thread(() -> {
			lock1.lock();
			System.out.println("Thread 2 acquired lock1");
			
			lock2.lock();
			System.out.println("Thread 2 acquired lock2");
			lock2.unlock();
			
			lock1.unlock();
		});
		
		t1.start();
		t2.start();
	}
}
