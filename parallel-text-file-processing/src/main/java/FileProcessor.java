import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileProcessor implements Runnable {

	private File file;
	private final static String OUTPUT_PATH = "./src/main/output/";

	public FileProcessor(File file) {
		this.file = file;
	}

	@Override
	public void run() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH + file.getName()))) {
			Files.lines(Path.of(file.getCanonicalPath()))
			.map(this::hash)
			.map(line -> line + "\n")
			.forEach(el -> {
				try {
					writer.write(el);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " -> processed file: " + file.getName());
	}

	private String hash(String input) {
		if(input.equals("")) {
			throw new RuntimeException("The line is empty, hashing cannot be done");
		}
		
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
			final byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

			return bytesToHex(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	private String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}

		return hexString.toString();
	}

}
