package jy;

import org.junit.Test;

public class A {
	public boolean C01_判断一个数是否为素数(int n){
		if(n <= 1) return false;
		for(int i = 2; i <= Math.sqrt(n); i++){
			if(n % i == 0){
				return false;
			}
		}
		return true;
	}
	
	
	public String C02_求最长子回文串(String str){
		if(str.length() <= 1) return str;
		//arr 0代表回文子串起始坐标 1代表长度
		int[] arr = new int[2];
		//str.length()-1是考虑回文长度为偶数的情况
		//然而不影响奇数情况
		for(int i = 0; i < str.length() - 1; i++){
			//奇数情况
			C02_h(str, i, i, arr);
			//偶数情况
			C02_h(str, i, i + 1, arr);
		}
		return str.substring(arr[0], arr[0] + arr[1]);
	}
	
	public void C02_h(String str, int start, int end, int[] arr){
		while(start >= 0 && end < str.length() && str.charAt(start) == str.charAt(end)){
			start--;
			end++;
		}
		if(arr[1] < end - start - 1){
			arr[1] = end - start - 1;
			arr[0] = start + 1;
		} 
	}
	@Test
	public void zz(){
		
	}
}
