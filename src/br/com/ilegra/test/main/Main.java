package br.com.ilegra.test.main;

import java.io.*;

public class Main {

	
	public static void main(String[] args){
		
		// Check Environment variable
		String homePathEnv = System.getenv("HOMEPATH");
		if (homePathEnv == null){
			processError("Undefined environment variable HOMEPATH", true);
		}
		
		// Check HomePath directory
		String homePath = homePathEnv + "/data/in";
		File homePathDir = new File(homePath);
		
		if (!homePathDir.exists()){
			processError("Path does not exists: " + homePath, true);
		} else if (!homePathDir.isDirectory()) {
			processError("Path is not a directory: " + homePath, true);
		} else if (!homePathDir.canRead()) {
			processError("Could not read path: " + homePath, true);
		}
		
		// Create processed files directory
		String processedPath = homePathEnv + "/data/processed";
		File processedPathDir = new File(processedPath);
		
		if (!processedPathDir.exists()){
			if (!processedPathDir.mkdirs()){
				processError("Could not create processed path: " + processedPath, true);
			}
		} else if (!processedPathDir.isDirectory()){
			processError("Processed Path is not a directory: " + homePath, true);
		}
		
		// Loop to check files and process
		while(true){
			
			File[] fileList = homePathDir.listFiles(new FileFilter(){
				@Override
				public boolean accept(File pathName){
					return pathName.isFile() && pathName.getName().matches("*.dat");
				}
			});
			
			if (fileList.length > 0){
				// Process file
				for (File eachFile : fileList){
					ProcessFile processFile = new ProcessFile(eachFile);
					processFile.process();
					processFile.createOutputFile();
				}
			}
			
			// Wait 10 seconds to next verification
			try {
				System.out.println("Waiting 10s to next verirication...");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public static void processError(String message, boolean exit){
		System.err.println(message);
		if (exit){
			System.exit(1);
		}
	}
	
}
