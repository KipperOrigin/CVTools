package org.cubeville.commons.utils;

public class NumberConverter {
	public static String convertTime(long time) {
		if (time < 0)
			return Colorize.addColor("&cNegative Value!");
		
		String sec = "";
		String min = "";
		String h = "";
		
		long seconds = (long) Math.floor(time / 1000);
		long minutes = (long) Math.floor(seconds / 60);
		long hours = (long) Math.floor(minutes / 60);
		
		if (seconds > 0 || minutes > 0 || hours > 0) {
			sec = seconds % 60 + "&c.&f";
		}
		if (minutes > 0 || hours > 0) {
			min = "&a&f" + minutes % 60 + "&c:&a&f";
		}
		if (hours > 0) {
			h = "&a&f" + hours + "&c:";
		}
				
		return Colorize.addColor(h + min + sec + time % 1000);
	}
	
	public static String convertTimeAsFormatted(long time) {
		if (time < 0)
			return Colorize.addColor("&cNegative Value!");
		
		String ms = "";
		String sec = "";
		String min = "";
		String h = "";
		
		long seconds = (long) Math.floor(time / 1000);
		long minutes = (long) Math.floor(seconds / 60);
		long hours = (long) Math.floor(minutes / 60);

		ms = String.valueOf(time % 1000);
		sec = convertStringToDoubleDigit(String.valueOf(seconds % 60));
		min = convertStringToDoubleDigit(String.valueOf(minutes % 60));
		h = convertStringToDoubleDigit(String.valueOf(hours));	
		
		if (ms.length() < 3) {
			for (int i = ms.length(); i < 3; i ++)
				ms += "0";
		}
		
		return h + "&c:&f" + min + "&c:&f" + sec + "&c.&f" + ms;
	}
	
	public static String convertStringToDoubleDigit(String digit) {
		if (digit.length() < 2) {
			for (int i = digit.length(); i < 2; i ++) {
				digit = ("0" + digit);
			}
		}
		return digit;
	}
}