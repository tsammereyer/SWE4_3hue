package helpers;

import java.io.FileWriter;
import java.io.IOException;

public class CSVFileWriter {

	private static final String NEW_LINE_SEPARATOR = "\n";

	String[] fileHeader;
	String fileName;
	String seperator;

	FileWriter fileWriter;

	public CSVFileWriter(String[] fileHeader, String fileName, String seperator) {
		this.fileHeader = fileHeader;
		this.seperator = seperator;
		this.fileName = fileName;
		try {

			fileWriter = new FileWriter(fileName);

			for (int i = 0; i < fileHeader.length; i++) {

				fileWriter.append(fileHeader[i]);

				if (i < fileHeader.length - 1) {
					fileWriter.append(seperator);
				}
			}
			fileWriter.append(NEW_LINE_SEPARATOR);

		} catch (IOException e) {
			System.err.println("IOException Constructor derp \n" + e.toString());
		}
	}

	public void writeLine(String[] values) {
		try {

			for (int i = 0; i < values.length; i++) {
				fileWriter.append(values[i]);
				if (i < fileHeader.length - 1) {
					fileWriter.append(seperator);
				}
			}
			fileWriter.append(NEW_LINE_SEPARATOR);

		} catch (IOException e) {
			System.err.println("IOException writeLine derp \n" + e.toString());
		}
	}

	public void flushAndClose() {
		try {
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			System.err.println("IOException flushAndClose derp \n" + e.toString());
		}

	}
}