package sla.util;

public class ShortUrlUtil {

	private final int LENGTH_OF_URL = 6;

	public static String convert(long id) {
		String[] elements = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8",
				"9", "0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		String shortUrl = "";
		int l = elements.length;
		if (id < l + 1 && id > 0) {
			shortUrl = elements[(int) (id - 1)];
		} else if (id > l) {
			long mod = 0;
			long multiplier = 0;
			boolean determinedTheLength = false;
			for (int i = LENGTH_OF_URL; i >= 0; i--) {
				multiplier = (long) (id / Math.pow(l, i));
				if (multiplier > 0 && id >= l) {
					shortUrl += elements[(int) multiplier];
					determinedTheLength = true;
				} else if (determinedTheLength && multiplier == 0) {
					shortUrl += elements[0];
				} else if (id < l) {
					shortUrl += elements[(int) mod];
				}
				mod = (long) (id % Math.pow(l, i));
				id = mod;
			}

		}
		return shortUrl;
	}

}
