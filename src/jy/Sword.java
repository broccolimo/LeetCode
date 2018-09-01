package jy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import com.sun.corba.se.impl.orbutil.graph.Node;


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
	 * ��һ����ά�����У�ÿ��һά����ĳ�����ͬ��
	 * ÿһ�ж����մ����ҵ�����˳����
	 * ÿһ�ж����մ��ϵ��µ�����˳������
	 * �����һ������
	 * ����������һ����ά�����һ������
	 * �ж��������Ƿ��и�������
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
	
	
	/**
	 * ��ʵ��һ������
	 * ��һ���ַ����е�ÿ���ո��滻�ɡ�%20��
	 * ����
	 * ���ַ���ΪWe Are Happy
	 * �򾭹��滻֮����ַ���ΪWe%20Are%20Happy
	 */
	public String replaceSpace(StringBuffer str) {
		int space_num = 0;
		for(char c : str.toString().toCharArray()){
			if(c == ' ') space_num++;
		}
		int old_index = str.length() - 1;
		int new_length = str.length() + 2 * space_num;
		str.setLength(new_length);
		int new_index = str.length() - 1;
		while(old_index >= 0){
			if(str.charAt(old_index) == ' '){
				str.setCharAt(new_index--, '0');
				str.setCharAt(new_index--, '2');
				str.setCharAt(new_index--, '%');
			}
			else{
				str.setCharAt(new_index--, str.charAt(old_index));
			}
			old_index--;
		}
		return str.toString();
    }
	
	
	/**
	 * ����һ������
	 * ������ֵ��β��ͷ��˳�򷵻�һ��ArrayList
	 */
	//����ջ ����ʱ�䣺23ms ռ���ڴ棺9252k
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while(listNode != null){
        	stack.push(listNode.val);
        	listNode = listNode.next;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while(stack.size() != 0){
        	list.add(stack.pop());
        }
        return list;
    }
	//�ݹ� ����ʱ�䣺14ms ռ���ڴ棺9424k
	public ArrayList<Integer> printListFromTailToHead_2(ListNode listNode){
		ArrayList<Integer> list = new ArrayList<>();
		printListFromTailToHead_2_traversal(listNode, list);
		return list;
	}
	
	public void printListFromTailToHead_2_traversal(ListNode listNode, ArrayList<Integer> list){
		if(listNode != null){
			if(listNode.next != null){
				printListFromTailToHead_2_traversal(listNode.next, list);
			}
			list.add(listNode.val);
		}
	}
	
	
	/**
	 * ����ĳ��������ǰ���������������Ľ��
	 * ���ؽ����ö�����
	 * ���������ǰ���������������Ľ���ж������ظ�������
	 * ��������ǰ���������{1,2,4,7,3,5,6,8}�������������{4,7,2,1,5,3,8,6}
	 * ���ؽ�������������
	 */
	//����ʱ�䣺296ms ռ���ڴ棺22912k
	public TreeNode reConstructBinaryTree(int[] pre,int[] in) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < in.length; i++){
			map.put(in[i], i);
		}
        return reConstructBinaryTree_traversal(pre, in, 0, pre.length - 1, 0, in.length - 1, map);
    }
	
	public TreeNode reConstructBinaryTree_traversal(int[] pre, int[] in, int ps, int pe, int is, int ie, Map<Integer, Integer> map){
		if(ps > pe || is > ie) return null;
		TreeNode node = new TreeNode(pre[ps]);
		int mid = map.get(pre[ps]);
		node.left = reConstructBinaryTree_traversal(pre, in, ps + 1, ps + mid - is, is, mid - 1, map);
		node.right = reConstructBinaryTree_traversal(pre, in, ps + mid - is + 1, pe, mid + 1, ie, map);
		return node;
	}
	@Test
	public void zz(){
		int[] pre = {1,2,3,4,5,6,7};
		int[] in = {3,2,4,1,6,5,7};
		//TreeNode node = reConstructBinaryTree(pre, in);
	}
	
	
	/**
	 * ��Ҷ�֪��쳲���������
	 * ����Ҫ������һ������n
	 * �������쳲��������еĵ�n���0��ʼ����0��Ϊ0)
	 * n<=39
	 */
	//����ʱ�䣺1490ms ռ���ڴ棺9272k
	public int Fibonacci(int n) {
		if(n == 0) return 0;
		if(n == 1) return 1;
		return Fibonacci(n - 2) + Fibonacci(n - 1);
    }
	
	
	/**
	 * ������ջ��ʵ��һ������
	 * ��ɶ��е�Push��Pop����
	 *  �����е�Ԫ��Ϊint����
	 */
	//����ʱ�䣺19ms ռ���ڴ棺9400k
	//ע��ֻ��stack2Ϊ��ʱ�Ż��stack1������
	public class Solution01 {
	    Stack<Integer> stack1 = new Stack<Integer>();
	    Stack<Integer> stack2 = new Stack<Integer>();
	    
	    public void push(int node) {
	        stack1.push(node);
	    }
	    
	    public int pop() {
	        if(stack2.empty()){
	            while(!stack1.empty()){
	                stack2.push(stack1.pop());
	            }
	        }
	        return stack2.pop();
	    }
	}
	
	
	//----------------------------------------------------------
	class ListNode {
		int val;
		ListNode next = null;
		ListNode(int val) {
			this.val = val;
		}
	}
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	
}
