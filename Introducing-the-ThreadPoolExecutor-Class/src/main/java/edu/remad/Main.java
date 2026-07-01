package edu.remad;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				3, // worker threads
				5, // maximal worker threads
				1, // keep alive time
				TimeUnit.MINUTES,
				new ArrayBlockingQueue<>(3));
		
		threadPoolExecutor.prestartAllCoreThreads();
		
		threadPoolExecutor.execute(() -> System.out.println("Task 1"));
		threadPoolExecutor.execute(() -> System.out.println("Task 2"));
//		threadPoolExecutor.execute(() -> System.out.println("Task 3"));
//		threadPoolExecutor.execute(() -> System.out.println("Task 4"));
		
		System.out.println("Pool size" + threadPoolExecutor.getPoolSize());
		
		threadPoolExecutor.shutdown();
		threadPoolExecutor.awaitTermination(3, TimeUnit.SECONDS);
		
//		Future<Integer> future = threadPoolExecutor.submit(new CallableTask());
//		
//		// Do other stuff
//		
//		Integer result = future.get();
	}
	
	static class CallableTask implements Callable<Integer> {
		
		@Override
		public Integer call() throws Exception {
			// Do dome work
			return 4;
		}
	}
}
