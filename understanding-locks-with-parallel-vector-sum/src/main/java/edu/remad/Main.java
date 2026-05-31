package edu.remad;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	private static int s = 0;
	private static int[] array = new int[10];
	private static Lock lockObject = new ReentrantLock();

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			array[i] = 10;
		}
		
		int threadSlices = array.length / 2;

		List<Thread> threadlist = new ArrayList<>();
		for(int i = 0; i < 2; i++) {
			Thread t = new Thread(new WorkerThread(i * threadSlices, (i + 1) * threadSlices));
			t.start();
			threadlist.add(t);
		}
		
		threadlist.forEach(t -> {
			try {
				t.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		System.out.println("The sum is: " + s);
	}

	static class WorkerThread implements Runnable {

		private final int left;
		private final int right;

		public WorkerThread(int left, int right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public void run() {
			for (int i = left; i < right; i++) {
				lockObject.lock();
				s = s + array[i];
				lockObject.unlock();
			}
		}

	}
}
