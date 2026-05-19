import java.io.File;
import java.util.Arrays;

public class Watcher implements Runnable {

	@Override
	public void run() {
		File inputDirectory = new File("./src/main/resources");

		while (true) {
			File[] files = inputDirectory.listFiles();
			if (files.length > 0) {
				Arrays.stream(files).forEach(file -> {
					try {
					Thread t = new Thread(new FileProcessor(file));
					t.setUncaughtExceptionHandler(new ExceptionHandler());
					t.start();
					} catch(RuntimeException e) {
						System.out.println();
					}
				});
			}

			sleep();
		}
	}

	public void sleep() {
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
