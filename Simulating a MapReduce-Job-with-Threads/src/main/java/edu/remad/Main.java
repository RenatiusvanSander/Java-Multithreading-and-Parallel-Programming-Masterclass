package edu.remad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
	
	private static final String input = "a friend in need is a friend indeed";

	private static final List<Map.Entry<String, Integer>> intermediateResult = Collections.synchronizedList(new ArrayList<>());
	
	public static void main(String[] args) {
		List<String> inputList = Arrays.asList(input.split(" "));
		
		new Thread(new Mapper(inputList.subList(0, inputList.size() / 2))).start();
		new Thread(new Mapper(inputList.subList(inputList.size() / 2, inputList.size()))).start();
	}
	
	static class Mapper implements Runnable {

		private final List<String> input;
		
		public Mapper(List<String> input) {
			this.input = input;
		}
		
		@Override
		public void run() {
			for(String word: input) {
				intermediateResult.add(Map.entry(word, 1));
			}
		}
		
	}
	
	static class Partitioner implements Runnable {

		@Override
		public void run() {
		}
		
	}
	
	static class Reducer implements Runnable {

		@Override
		public void run() {
		}
		
	}
}
