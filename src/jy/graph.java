package jy;

public class graph {
	
	public void Floyd(int[][] a){
		int n = a.length;
		for(int k = 0; k < n; k++){
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(a[i][k] + a[k][j] < a[i][j]){
						a[i][j] = a[i][k] + a[k][j];
					}
				}
			}
		}
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(a[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		int[][] a = {{0, 2, 6, 4}, {99999999, 0, 3, 99999999}, {7, 99999999, 0, 1}, {5, 99999999, 12, 0}};
		new graph().Floyd(a);
	}
}
