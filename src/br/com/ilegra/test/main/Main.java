package br.com.ilegra.test.main;

import java.io.*;
import br.com.ilegra.test.exception.IlegraTestException;
import br.com.ilegra.test.util.FileUtil;

public class Main {

	private static boolean DO_LOOP = false;
	private static boolean MOVE_PROCESSED_FILE = false;

	public static void main(String[] args) {

		// Check Environment variable
		String homePathEnv = System.getenv("HOMEPATH");
		if (homePathEnv == null) {
			processError("Undefined environment variable HOMEPATH", true);
		}

		// Check HomePath directory
		String homePath = homePathEnv + "/data/in";
		File homePathDir = new File(homePath);

		if (!homePathDir.exists()) {
			processError("Path does not exists: " + homePath, true);
		} else if (!homePathDir.isDirectory()) {
			processError("Path is not a directory: " + homePath, true);
		} else if (!homePathDir.canRead()) {
			processError("Could not read path: " + homePath, true);
		}

		File outputPathDir = null;
		File processedPathDir = null;
		try {
			// Create output files directory
			String outputPath = homePathEnv + "/data/out";
			outputPathDir = FileUtil.createPath(outputPath);

			// Create processed files directory
			String processedPath = homePathEnv + "/data/processed";
			processedPathDir = FileUtil.createPath(processedPath);

		} catch (IlegraTestException e1) {
			processError(e1.getMessage(), e1.isFatal());
		}

		// Loop to check files and process
		do {

			// Verifiy file list
			File[] fileList = homePathDir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathName) {
					return pathName.isFile() && pathName.getName().endsWith(".dat");
				}
			});

			if (fileList.length > 0) {
				// Process file
				for (File eachFile : fileList) {
					System.out.println("Processing file: " + eachFile.getName());
					ProcessFile processFile = new ProcessFile(eachFile);
					processFile.process();
					processFile.createOutputFile(outputPathDir);
					if (Main.MOVE_PROCESSED_FILE) {
						System.out.println("Moving file to: " + processedPathDir.getName());
						processFile.moveProcessedFile(processedPathDir);
					}
					System.out.println("File processed: " + eachFile.getName());
				}
			}

			// Wait 10 seconds to next verification
			if (Main.DO_LOOP) {
				try {
					System.out.println("Waiting 10s to next verification...");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} while (Main.DO_LOOP);

	}

	public static void processError(String message, boolean exit) {
		System.err.println(message);
		if (exit) {
			System.exit(1);
		}
	}

}
