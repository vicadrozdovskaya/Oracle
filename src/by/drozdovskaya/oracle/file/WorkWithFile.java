package by.drozdovskaya.oracle.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {

	private static final String RELATIVE_LOCATION_FILE = "resources\\";

	public List<String> readFromFile(String type) throws IOException {
		System.out.println(RELATIVE_LOCATION_FILE + type + ".txt");
		List<String> lines = Files.readAllLines(Paths.get(RELATIVE_LOCATION_FILE + type + ".txt"),
				StandardCharsets.UTF_8);
		return lines;
	}

}
