package edu.remad.Javamultithreadingandparallelprogrammingmasterclass.threadstart;

public class ThreadGroupExample {

	public static void main(String[] args) throws InterruptedException {
		ThreadGroup group = new ThreadGroup("Group1");

		Thread thread1 = new Thread(group, new MyThread(), "Thread1");
		Thread thread2 = new Thread(group, new MyThread(), "Thread2");
		Thread thread3 = new Thread(group, new MyThread(),"Thread3");

		thread1.start();
		thread2.start();
		thread3.start();
		
		System.out.println("sleeping for 3 seconds...");
		Thread.sleep(3000);
		
		group.interrupt();
	}

	static class MyThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					Thread currentThread = Thread.currentThread();
					System.out.println("Name: " + currentThread.getName());
				}
			}
		}

	}
}
