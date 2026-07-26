package edu.remad;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {

	public static void main(String[] args) {
		Executors.newFixedThreadPool(5);
		Executors.newCachedThreadPool(new myThreadFactory());
	}
	
	static class myThreadFactory implements ThreadFactory {
		
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread();
			
			t.setPriority(4);
			t.setName("my-thread");
			
			return t;
		}
	}
}
