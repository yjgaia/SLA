package sla.util;

public class ShortUrlUtil {

	private final static int LENGTH_OF_URL = 6;

	private final static String[] elements = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
			"w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };

	public static String convert(long id) {
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
		int d = LENGTH_OF_URL - shortUrl.length();
		for (int i = 0 ; i < d ; i++) {
			//shortUrl = "0" + shortUrl;
		}
		return shortUrl;
	}
	public static long revert(String shortUrl){
		long result=0;
		int l=elements.length;
		for(int i=0;i<shortUrl.length();i++){
			char nowChar=shortUrl.charAt(i);
			result+=(long) (getIndexOfElement(nowChar)*Math.pow(l, shortUrl.length()-1-i));
		}
		return result;
	}
	
	public static void main(String[] args){
		System.out.println(convert((long)1251251));
		System.out.println(revert("f6qt"));
	}
	public static long getIndexOfElement(char c){
		String element=String.valueOf(c);
		switch(element){
		case "a": return 0;
		case "b": return 1;
		case "c": return 2;
		case "d": return 3;
		case "e": return 4;
		case "f": return 5;
		case "g": return 6;
		case "h": return 7;
		case "i": return 8;
		case "j": return 9;
		case "k": return 10;
		case "l": return 11;
		case "m": return 12;
		case "n": return 13;
		case "o": return 14;
		case "p": return 15;
		case "q": return 16;
		case "r": return 17;
		case "s": return 18;
		case "t": return 19;
		case "u": return 20;
		case "v": return 21;
		case "w": return 22;
		case "x": return 23;
		case "y": return 24;
		case "z": return 25;
		case "1": return 26;
		case "2": return 27;
		case "3": return 28;
		case "4": return 29;
		case "5": return 30;
		case "6": return 31;
		case "7": return 32;
		case "8": return 33;
		case "9": return 34;
		case "A": return 35;
		case "B": return 36;
		case "C": return 37;
		case "D": return 38;
		case "E": return 39;
		case "F": return 40;
		case "G": return 41;
		case "H": return 42;
		case "I": return 43;
		case "J": return 44;
		case "K": return 45;
		case "L": return 46;
		case "M": return 47;
		case "N": return 48;
		case "O": return 49;
		case "P": return 50;
		case "Q": return 51;
		case "R": return 52;
		case "S": return 53;
		case "T": return 54;
		case "U": return 55;
		case "V": return 56;
		case "W": return 57;
		case "X": return 58;
		case "Y": return 59;
		case "Z": return 60;
		default : return -1;
		}
	}
}
