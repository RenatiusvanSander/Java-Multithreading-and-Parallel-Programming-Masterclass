package edu.remad;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

	private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private static Lock readLock = readWriteLock.readLock();
	private static Lock writeLock = readWriteLock.writeLock();
	private static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) {
	}

	static class WriterThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					writeData();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private void writeData() throws InterruptedException {
			Thread.sleep(10000);
			writeLock.lock();
			
			int value = (int) Math.random();
			System.out.println("Producing data: " + value);
			
			list.add(value);
			
			writeLock.unlock();
		}
	}
	
	static class ReaderThread implements Runnable {

		@Override
		public void run() {
			while(true) {
				try {
					readData();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private void readData() throws InterruptedException {
			Thread.sleep(4000);
			
			readLock.lock();
			
			System.out.println("List is: " + list);
			
			readLock.unlock();
		}
		
	}

}
