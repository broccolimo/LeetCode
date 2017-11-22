package util;

public class Utils {
	public static int[] Turn(String[] s){
		int l = s.length;
		int[] a = new int[l];
		for(int i = 0; i < l; i++){
			a[i] = Integer.parseInt(s[i]);
		}
		return a;
	}
	
	public static boolean isPalindromic(String s){
		int len = s.length();
		for(int i = 0; i < len; i++){
			if(s.charAt(i) != s.charAt(len - i - 1)){
				return false;
			}
		}
		return true;
	}
}
