package jni.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {

	public static File getStreamAsTempFile(InputStream in,
			final String fileNamePrefix, final String extension)
			throws IOException, FileNotFoundException {
		File libraryFile = File.createTempFile(fileNamePrefix, extension);
		libraryFile.deleteOnExit();
		writeStreamToFile(in, libraryFile);
		
		return libraryFile;
	}

	public static File getStreamAsTempFileOrCry(InputStream in, String fileNamePrefix) {
		try {
			return getStreamAsTempFile(in, fileNamePrefix, null);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void writeStreamToFile(InputStream aStream,
			File targetFile) throws FileNotFoundException,
			IOException {
		FileOutputStream out = new FileOutputStream(targetFile);
		int count;
		byte[] buffer = new byte[1024];
		while(0 < (count = aStream.read(buffer))) {
		    out.write(buffer, 0, count);
		}
	}
}
