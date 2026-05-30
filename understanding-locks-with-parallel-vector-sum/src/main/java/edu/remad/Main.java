package edu.remad;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) {
		Lock lockObject = new ReentrantLock();

		synchronized (new Object()) {

		}

		lockObject.lock();

		try {

		} catch (Exception e) {

		} finally {
			lockObject.unlock();
		}
	}
}
