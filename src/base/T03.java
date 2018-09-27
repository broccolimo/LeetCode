package base;

/**
 * һ���������1��2��ֻ���ֹ�1�ε����� 
 * �������ֶ�������2��
 * ����1��2������
 */
public class T03 {
	public static void main(String[] args) {
		//int[] a = {1, 3, 2, 4, 2, 1, 3, 6, 7, 6};
		//int[] res = new T03().t(a);
		int[] a = {1, 3, 2, 4, 2, 1, 3, 6, 7, 6, 4};
		int res = new T03().t2(a);
		System.out.println(res);
	}
	
	public int[] t(int[] arr){
		int res = 0;
		for(int i = 0; i < arr.length; i++){
			res ^= arr[i];
		}
		int index = 0;
		for(int i = 0; i < 32; i++){
			if(((res >> i) & 1) == 1){
				index = i;
				break;
			}
		}
		int result[] = new int[2];
		for(int i = 0; i < arr.length; i++){
			if(((arr[i] >> index) & 1) == 1){
				result[0] ^= arr[i];
			}
			else{
				result[1] ^= arr[i];
			}
		}
		return result;
	}
	
	public int t2(int[] arr){
		int res = 0;
		for(int i = 0; i < arr.length; i++){
			res ^= arr[i];
		}
		return res;
	}
}
