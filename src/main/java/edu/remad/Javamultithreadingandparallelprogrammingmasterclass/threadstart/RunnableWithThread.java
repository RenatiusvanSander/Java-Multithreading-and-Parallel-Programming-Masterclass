package edu.remad.Javamultithreadingandparallelprogrammingmasterclass.threadstart;

public class RunnableWithThread {

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Current thread: " + Thread.currentThread().getName());
			}
		};
		Thread thread = new Thread(runnable);
		thread.setName("MyThread");
		thread.start();
	}
}
