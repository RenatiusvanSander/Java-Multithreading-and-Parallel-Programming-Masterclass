package edu.remad;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;

public class Main {

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		Phaser phaser = new Phaser();
		CyclicBarrier barrier = new CyclicBarrier(4);
		
		barrier.await();
		
		phaser.register();
		phaser.arriveAndAwaitAdvance();
	}
	
}
