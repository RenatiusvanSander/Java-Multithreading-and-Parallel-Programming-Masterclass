package edu.remad.Javamultithreadingandparallelprogrammingmasterclass.threadstart;

public class ExtendThread {

	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.start();
	}
	
	static class MyThread extends Thread {
		public void run() {
			System.out.println("Current thread: " + Thread.currentThread().getName());
		}
	}
}
