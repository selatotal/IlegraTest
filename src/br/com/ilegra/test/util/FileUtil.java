package br.com.ilegra.test.util;

import java.io.File;
import br.com.ilegra.test.exception.IlegraTestException;

/**
 * Util class to deal with file operations
 * @author Tales Viegas - tales@terra.com.br
 */
public class FileUtil {

	/**
	 * Create a path if it doesn't exists
	 * @param path Path to be created
	 * @return File object that represents the path created
	 * @throws IlegraTestException
	 */
	public static File createPath(String path) throws IlegraTestException{
		File pathDir = new File(path);

		if (!pathDir .exists()) {
			if (!pathDir .mkdirs()) {
				throw new IlegraTestException("Could not create processed path: " + path, true);
			}
		} else if (!pathDir.isDirectory()) {
			throw new IlegraTestException("Processed Path is not a directory: " + path, true);
		}

		return pathDir;

	}
}
