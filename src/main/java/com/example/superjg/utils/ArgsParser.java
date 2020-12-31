package com.example.superjg.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class has helper functions to parse command-line arguments.
 * 
 * @author hugo
 *
 */
public final class ArgsParser {

	private ArgsParser() {
	}

	/**
	 * Creates a map of arguments with key-value.
	 * 
	 * @param args The arguments with the format: {@code [-parameter1=value1,
	 *             -parameter2=value2, -parameter3=value3, ..., -parameterN=valueN]}
	 * @return The map of values
	 */
	public static Map<String, String> getArgsMap(String[] args) {
		var argsMap = ArgsParser.parseArg(String.join(" ", args));
		for (var entry : argsMap.entrySet()) {
			var key = entry.getKey();
			var value = entry.getValue();
			switch (key) {
			case "cfg":
				argsMap.put(key, value);
				break;
			default:
				System.out.printf("warn: nothing to do with %s=%s.%n", key, value);
			}
		}
		return argsMap;
	}

	/**
	 * Parses a string with the format: -parameter1=value1
	 * 
	 * @param input The input string to parse.
	 * @return The map of values.
	 */
	private static Map<String, String> parseArg(String input) {
		Map<String, String> values = new HashMap<>();
		var chunks = input.split("\\s");
		for (var chunk : chunks) {
			Matcher matcher = KEY_VALUE_REGEXER.matcher(chunk);
			if (matcher.matches()) {
				String key = matcher.group("key");
				String value = matcher.group("value");
				values.put(key, value);
			}
		}
		return values;
	}

	public static final String KEY_VALUE_REGEX = "\\-(?<key>.+)\\=(?<value>.+)";

	public static final Pattern KEY_VALUE_REGEXER = Pattern.compile(KEY_VALUE_REGEX);

}