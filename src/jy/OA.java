package jy;

import org.junit.Test;

public class OA {
	/**
	 * @problem ººÅµËþ
	 * @date 2017-11-28
	 */
	public void move(int n, String A, String B, String C){
		if(n == 1){
			System.out.println("Move " + A + " to " + C);
		}
		else{
			move(n - 1, A, C, B);
			System.out.println("Move " + A + " to " + C);
			move(n - 1, B, A, C);
		}
	}
	
	@Test
	public void z(){
		String s = "(()(";
		System.out.println(s.lastIndexOf(")("));
	}
}
