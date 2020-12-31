package com.example.superjg.utils;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemUtils {

	private SystemUtils() {
	}

	public static String getCurrentPath() {
		var currentRelativePath = Paths.get("");
		return currentRelativePath.toAbsolutePath().toString() + System.getProperty("file.separator");
	}

	public static String getCurrentDateTime() {
		return dateFormat.format(new Date());
	}

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

}
