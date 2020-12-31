package com.example.superjg.utils;

public class ParseUtils {

	private ParseUtils() {
	}

	public static int str2int(String value, int defaultValue) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException | NullPointerException ex) {
			ex.printStackTrace();
			return defaultValue;
		}
	}

	public static int str2int(String value) {
		return str2int(value, 0);
	}

	public static boolean str2bool(String value) {
		value = getstr(value).toLowerCase().trim();
		return value.equals("true") || value.equals("1");
	}

	public static String getstr(String value, String defaultValue) {
		return value != null ? value : defaultValue;
	}

	public static String getstr(String value) {
		return getstr(value, "");
	}

	public static float str2float(String value, float defaultValue) {
		try {
			return Float.parseFloat(value);
		} catch (NumberFormatException | NullPointerException ex) {
			ex.printStackTrace();
			return defaultValue;
		}
	}

	public static float str2float(String value) {
		return str2float(value, 0.0f);
	}

}
