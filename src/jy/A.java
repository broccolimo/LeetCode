package jy;

import org.junit.Test;

public class A {
	public boolean C01_�ж�һ�����Ƿ�Ϊ����(int n){
		if(n <= 1) return false;
		for(int i = 2; i <= Math.sqrt(n); i++){
			if(n % i == 0){
				return false;
			}
		}
		return true;
	}
	
	
	public String C02_����ӻ��Ĵ�(String str){
		if(str.length() <= 1) return str;
		//arr 0��������Ӵ���ʼ���� 1������
		int[] arr = new int[2];
		//str.length()-1�ǿ��ǻ��ĳ���Ϊż�������
		//Ȼ����Ӱ���������
		for(int i = 0; i < str.length() - 1; i++){
			//�������
			C02_h(str, i, i, arr);
			//ż�����
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
