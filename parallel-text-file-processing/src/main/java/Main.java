/**
 * === Requirements ===
 * 1.Periodically scans the the ./src/main/resources directory and watches for new files
 * 2. For each file found into this directory, anew thread should take care of its processing
 * 3. Processing = each line of the file will be hashed a hashing algorithm.
 * 4. The program will create new files, marked _output suffix, and they will placed into ./src/main/resources/output
 * 5. Throw an exception if a line is empty
 */
public class Main {

	public static void main(String[] args) {
		new Thread(new Watcher()).start();
	}
}
