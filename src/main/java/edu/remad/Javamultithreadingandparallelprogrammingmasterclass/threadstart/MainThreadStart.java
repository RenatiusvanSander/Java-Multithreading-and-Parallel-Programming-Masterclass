package edu.remad.Javamultithreadingandparallelprogrammingmasterclass.threadstart;

public class MainThreadStart {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = Thread.currentThread();
		System.out.println("Curren thread: " + thread.getName());
		
		Thread.sleep(3000);
	}

}
