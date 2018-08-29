package jy;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


/**
 * @date 2018-07-13
 * @author broccoli
 * @description ����ָOffer�������� javaʵ��
 */
public class Sword {
	
	/**
	 * @date 2018-07-16
	 * @author broccoli
	 * @description 
	 * �������ظ���������������һ��
	 * ����Ϊn����������������ֶ���0~n-1�ķ�Χ�� 
	 */
	//��һ��˼· ��HashSet ʱ�临�Ӷ�O(n) �ռ临�Ӷ�O(n)
	//���ַ��������������û������
	public int C01_1(int arr[]){
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < arr.length; i++){
			if(set.contains(arr[i])){
				return arr[i];
			}
			set.add(arr[i]);
		}
		return -1;
	}
	
	//�ڶ���˼· ʱ�临�ӶȺͿռ临�Ӷȶ���O(n)
	//���ַ������뱣֤����������ߵ�����ֵ����������n
	public int C01_2(int arr[]){
		for(int i = 0; i < arr.length; i++){
			//��ǰ��ֵ������ֵ��ͬ,����Ҫ�䶯
			if(i == arr[i]){
				continue;
			}
			int index = arr[i];
			//ֱ���ҵ����ظ�������
			if(index == arr[index]){
				return index;
			}
			//�������,�򽻻�λ��
			arr[i] = arr[index];
			arr[index] = index;
			//����λ�ú�ǰ��ֵ��Ȼ������ֵ��ͬ
			if(i != arr[i]){
				i--;
			}
		}
		return -1;
	}
	
	/**
	 * @date 2018-07-18
	 * @author broccoli
	 * @description 
	 * ����һ���������ֶ���С�������е����� �� һ������
	 * �ж���������Ƿ������������
	 */
	/**
	 * ����: �ӽ�����
	 * ���Ͻ���С ���½���� ûɶ����
	 * ���½���һ����� һ����С ���Ͻ���һ����� һ����С
	 * ���������ǵ���ֵȥ�͸����������Ƚ�
	 * Ҫô��� ֱ�ӵó��� Ҫô����� �����ų�һ�л���һ�� ��С��Χ�ظ��˲��� ����
	*/
	//�����½�Ϊ��׼
	public boolean C_04_1(int[][] arr, int val){
		int rows = arr.length;
		int cols = arr[0].length;
		int i = rows - 1;
		int j = 0;
		while(i >= 0 && j <= cols - 1){
			if(arr[i][j] == val){
				return true;
			}
			else if(arr[i][j] < val){
				j++;
			}
			else{
				i--;
			}
		}
		return false;
	}
	//�����Ͻ�Ϊ��׼
	public boolean C_04_2(int[][] arr, int val){
		int rows = arr.length;
		int cols = arr[0].length;
		int i = 0;
		int j = cols - 1;
		while(i <= rows - 1 && j >= 0){
			if(arr[i][j] == val){
				return true;
			}
			else if(arr[i][j] < val){
				i++;
			}
			else{
				j--;
			}
		}
		return false;
	}
	
	
	/** 
	 * @date 2018-07-13
	 * @author broccoli
	 * @description #09 ������ջʵ�ֶ���
	 * @mind 
	 * ������ʱ ��Ԫ�ز嵽��һ��ջ�� 
	 * ������ʱ ����ڶ���ջ��ջβԪ��
	 * ����ڶ���ջΪ�� ˵��֮ǰһֱ�ڽ�����
	 * ��ʱ�ѵ�һ��ջ�е�Ԫ�� ����ڶ���ջ��
	 * �ͻ�Ѻ�����е�Ԫ�ط���ڶ���ջ��ջ��
	 * ���Ƚ����е�Ԫ�ط���ڶ���ջ��ջβ
	 * ��ô�ͻ����ڶ���ջ��ջβԪ�� Ҳ�����������е�һ��������Ԫ��
	 * ��ʵ���˶����Ƚ��ȳ��Ĺ���
	 */
	public class C09{
		private Stack<Integer> stack1 = new Stack<>();
		private Stack<Integer> stack2 = new Stack<>();
		public void push(int i){
			stack1.push(i);
		}
		public int pop(){
			if(stack1.empty() && stack2.empty()){
				throw new RuntimeException("������û��Ԫ��");
			}
			if(stack2.empty()){
				while(!stack1.empty()){
					stack2.push(stack1.pop());
				}
			}
			return stack2.pop();
		}
	}
	
	
	
	
}
