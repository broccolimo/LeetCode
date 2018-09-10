package jy;

import org.junit.Test;

public class Sort {
	public void printArray(int[] a){
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	public void QuickSort(int[] a){
		if(a.length == 0) return;
		QuickSort_r(a, 0, a.length - 1);
		printArray(a);
	}
	
	public void QuickSort_r(int[] a, int left, int right){
		if(left < right){
			int pos = QuickSort_c(a, left, right);
			QuickSort_r(a, left, pos - 1);
			QuickSort_r(a, pos + 1, right);
		}
	}
	
	public int QuickSort_c(int[] a, int left, int right){
		int pos = left;
		int value = a[right];
		for(int i = left; i < right; i++){
			if(a[i] <= value){
				int temp = a[i];
				a[i] = a[pos];
				a[pos++] = temp;
			}
		}
		a[right] = a[pos];
		a[pos] = value;
		return pos;
	}
	
	public void InsertSort(int[] a){
		for(int i = 1; i < a.length; i++){
			InsertSort_c(a, i, a[i]);
		}
		printArray(a);
	}
	
	public void InsertSort_c(int[] a, int pos, int value){
		int x = pos - 1;
		while(x >= 0 && a[x] > value){
			a[x + 1] = a[x];
			x--;
		}
		a[++x] = value;
	}
	
	public int lcs(String a, String b){
		int[][] arr = new int[a.length() + 1][b.length() + 1];
		for(int i = 0; i < a.length() + 1; i++){
			for(int j = 0; j < b.length() + 1; j++){
				if(i == 0 || j == 0){
					arr[i][j] = 0;
				}
				else if(a.charAt(i - 1) == b.charAt(j - 1)){
					arr[i][j] = arr[i - 1][j - 1] + 1;
				}
				else{
					arr[i][j] = Math.max(arr[i][j - 1], arr[i - 1][j]);
				}
			}
		}
		return arr[a.length()][b.length()];
	}
	
	
	@Test
	public void z(){
		//int[] a = {4, 5, 6, 2, 1, 3};
		//QuickSort(a);
		//InsertSort(a);
		String a = "ABCBDAB";
		String b = "BDCABA";
		System.out.println(lcs(a, b));
	}
}
