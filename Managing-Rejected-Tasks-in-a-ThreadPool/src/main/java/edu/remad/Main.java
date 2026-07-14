package edu.remad;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, // worker threads
				3, // maximal worker threads
				1, // keep alive time
				TimeUnit.MINUTES,
				new ArrayBlockingQueue<>(2),
				(Runnable r, ThreadPoolExecutor executor) -> {
					System.out.println("Task rejected.");
				});

		threadPoolExecutor.submit(new SleepingTask(1));
		threadPoolExecutor.submit(new SleepingTask(2));
		
		System.out.println("[1] Pool size: " + threadPoolExecutor.getPoolSize());
		
		threadPoolExecutor.submit(new SleepingTask(3));
		threadPoolExecutor.submit(new SleepingTask(4));
		
		threadPoolExecutor.submit(new SleepingTask(5));
		System.out.println("[2] Pool size: " + threadPoolExecutor.getPoolSize());
		
		threadPoolExecutor.submit(new SleepingTask(6));

//		try {
//			future.get();
//		} catch(InterruptedException e) {
//			e.printStackTrace();
//		} catch(ExecutionException e) {
//			e.printStackTrace();
//		}
	}

	static class CustomThreadPoolExecutor extends ThreadPoolExecutor {

		public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		}

		public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
		}

		public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
		}

		public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
		}

		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			super.afterExecute(r, t);

			if (t != null) {
				System.out.println(t);
			}
		}

	}
	
	static class SleepingTask implements Runnable {
		
		private final int id;
		
		public SleepingTask(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(99999);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}

}

