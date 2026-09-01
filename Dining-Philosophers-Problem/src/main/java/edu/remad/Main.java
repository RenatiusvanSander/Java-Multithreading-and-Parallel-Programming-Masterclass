package edu.remad;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	
	private static List<Lock> forks = new ArrayList<>();
	
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++) {
			forks.add(new ReentrantLock());
		}
		
		Semaphore semaphore = new Semaphore(4);
		
		for(int i = 0; i < 5; i++) {
			new Thread(new Philosopher(i, semaphore)).start();
		}
	}
	
	static class Philosopher implements Runnable {
		
		private final int id;
		private Semaphore semaphore;
		
		public Philosopher(int id, Semaphore semaphore) {
			this.id = id;
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			while(true) {
				think();
				pick_forks();
				eat();
				put_forks();
			}
		}
		
		void pick_forks() {
			
			try {
				semaphore.acquire();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			forks.get(id).lock();
			System.out.println("Philosopher " + id + " picked the right fork");
			forks.get((id + 1) % 5).lock();
			System.out.println("Philosopher " + id + " picked the left fork");
		}
		
		void put_forks() {
			forks.get(id).lock();
			forks.get((id + 1) % 5).lock();
			
			semaphore.release();
		}
		
		void think() {
			System.out.println("Philosopher " + id + " thinks");
		}
		
		void eat() {
			System.out.println("Philosopher " + id + " eats");
		}
		
		void sat() {
			System.out.println("Philosopher " + id + " sats");
		}		
	}
}
