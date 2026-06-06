package edu.remad;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		
		lock.newCondition();
	}
}
