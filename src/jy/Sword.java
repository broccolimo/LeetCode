package jy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;



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
	/**
	 * һֻ����һ�ο�������1��̨��
	 * Ҳ��������2��
	 * ��Ҳ��������n��
	 * �����������һ��n����̨���ܹ��ж���������
	 */
	//����ʱ�䣺18m ռ���ڴ棺9424k
    public int JumpFloorII(int target) {
        if(target == 1) return 1;
        if(target == 2) return 2;
        int sum = 0;
        while(target > 1){
        	sum += JumpFloorII(--target);
        }
        return sum + 1;
    }
    
    
    /**
     * ����һ������
     * ������������Ʊ�ʾ��1�ĸ���
     * ���и����ò����ʾ
     */
    //����ʱ�䣺11ms ռ���ڴ棺9432k
    //java��ߵĸ���Ĭ�Ͼ����ò�����в�����
    //����û�б�Ҫ���аѸ���ת��, ͷ��Ϊ1����ȡ������1�Ĳ���
    public int NumberOf1(int n) {
    	int count = 0;
    	int flag = 1;
    	while(flag != 0){
    		if((n & flag) != 0){
    			count++;
    		}
    		flag = flag << 1;
    	}
    	return count;
    }
    
    /**
     * ����һ������
     * ����������е�����k�����
     */
    //���������ܹ��м����ڵ� �����ŷ������
    //����ʱ�䣺16ms ռ���ڴ棺9656k
    public ListNode FindKthToTail(ListNode head,int k) {
    	int node_num = 0;
    	ListNode head1 = head;
    	while(head1 != null){
    		node_num++;
    		head1 = head1.next;
    	}
    	if(k > node_num) return null;
    	int _k = node_num - k + 1;
    	ListNode head2 = head;
    	int i = 2;
    	while((i++) <= _k){
    		head2 = head2.next;
    	}
    	return head2;
    }
    
    
    /**
     * ���ǿ�����2*1��С���κ��Ż�������ȥ���Ǹ���ľ���
     * ������n��2*1��С�������ص��ظ���һ��2*n�Ĵ����
     * �ܹ��ж����ַ���
     */
    //����ʱ�䣺591ms ռ���ڴ棺9428k
    public int RectCover(int target) {
    	if(target < 1) return 0;
        if(target == 1 || target == 2) return target;
    	return RectCover(target - 1) + RectCover(target - 2);
    }
    
    /**
     * ����һ����������
     * ʵ��һ�����������������������ֵ�˳��
     * ʹ�����е�����λ�������ǰ�벿��
     * ���е�ż��λ������ĺ�벿��
     * ����֤����������
     * ż����ż��֮������λ�ò��䡣
     */
    //����ʱ�䣺22ms ռ���ڴ棺9372k
    public void reOrderArray(int [] array) {
        if(array.length <= 1) return;
        int index = -1;
        for(int i = 0; i < array.length; i++){
        	if(array[i] % 2 == 1){
        		int val = array[i];
        		int temp = i;
        		while(temp > index + 1){
        			array[temp] = array[temp - 1];
        			temp--;
        		}
        		array[++index] = val;
        	}
        }
    }
    
   
    /**
     * ����һ������
     * ��ת�����
     * ���������ı�ͷ
     */
    //����ʱ�䣺17ms ռ���ڴ棺9564k
    public ListNode ReverseList(ListNode head) {
    	ListNode prev = null;
    	while(head != null){
    		ListNode next = head.next;
    		head.next = prev;
    		prev = head;
    		head = next;
    	}
    	return prev;
    }
    
  
    /**
     * ����������������������
     * �����������ϳɺ������
     * ��Ȼ������Ҫ�ϳɺ���������㵥����������
     */
    //�ǵݹ�
    //����ʱ�䣺20ms ռ���ڴ棺9660k
    public ListNode Merge(ListNode list1,ListNode list2) {
    	if(list1 == null) return list2;
    	if(list2 == null) return list1;
        ListNode root = null;
        if(list1.val <= list2.val){
        	root = list1;
        	list1 = list1.next;
        }
        else{
        	root = list2;
        	list1 = list2.next;
        }
        ListNode temp = root;
        while(list1 != null && list2 != null){
        	if(list1.val <= list2.val){
        		temp.next = list1;
        		temp = list1;
        		list1 = list1.next;
        	}
        	else{
        		temp.next = list2;
        		temp = list2;
        		list2 = list2.next;
        	}
        }
        if(list1 != null){
        	temp.next = list1;
        }
        if(list2 != null){
        	temp.next = list2;
        }
        return root;
    }
    
    //�ݹ�
    //����ʱ�䣺21ms ռ���ڴ棺9432k
    public ListNode Merge2(ListNode list1,ListNode list2) {
    	if(list1 == null) return list2;
    	if(list2 == null) return list1;
    	if(list1.val <= list2.val){
    		list1.next = Merge2(list1.next, list2);
    		return list1;
    	}
    	else{
    		list2.next = Merge2(list1, list2.next);
    		return list2;
    	}
    }
    
    
    
    /**
     * ���������Ķ�����
     * ����任ΪԴ�������ľ���
     * @param root
     */
    //����ʱ�䣺22ms ռ���ڴ棺9560k
	//��
    public void Mirror(TreeNode root) {
        if(root == null) return;
        TreeNode right= root.right;
        root.right = root.left;
        root.left = right;
        Mirror(root.left);
        Mirror(root.right);
    }
    
    
    /**
     * �������ö�����A��B
     * �ж�B�ǲ���A���ӽṹ
     * ����Լ��������������һ�������ӽṹ
     */
    //����ʱ�䣺16ms ռ���ڴ棺9536k
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean flag = false;
        //root2������û��� ֻҪroot2�ǿ� ��ֱ�ӷ���flag�ĳ�ʼֵfalse
        if(root1 != null && root2 != null){
        	//����� �����µĽṹ�Ƿ�Ҳһ�������
        	if(root1.val == root2.val){
        		flag = HasSubtree_reserval(root1, root2);
        	}
        	
        	//root1��ǰ������root2�ĸ���һ�� ������root1��left
        	if(!flag){
        		flag = HasSubtree(root1.left, root2);
        	}
        	
        	//root1��ǰ������left��root2�ĸ���һ�� ������root1��right
        	if(!flag){
        		flag = HasSubtree(root1.right, root2);
        	}
        }
        return flag;
    }
    
    public boolean HasSubtree_reserval(TreeNode root1,TreeNode root2){
    	//root1�� ��root2�� ��root2��root1������
    	if(root2 == null) return true;
    	//����һ��root2�ض���Ϊ�� ��root1���� ��root2����root1������
    	if(root1 == null) return false;
    	if(root1.val != root2.val) return false;
    	return HasSubtree_reserval(root1.left, root2.left) && HasSubtree_reserval(root1.right, root2.right);
    }
    
    
    /**
     * �������´�ӡ����������ÿ���ڵ�
     * ͬ��ڵ�������Ҵ�ӡ
     */
    //�Լ����� �ݹ黹map ��̫��
    //����ʱ�䣺14m ռ���ڴ棺9432k
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        PrintFromTopToBottom_rec(root, map ,0);
        for(int i = 0; i < map.size(); i++){
        	ArrayList<Integer> temp = map.get(i);
        	for(int val : temp){
        		list.add(val);
        	}
        }
        return list;
    }
    
    public void PrintFromTopToBottom_rec(TreeNode node, Map<Integer, ArrayList<Integer>> map, int level){
    	if(node == null) return;
    	if(!map.keySet().contains(level)){
    		ArrayList<Integer> temp = new ArrayList<>();
    		temp.add(node.val);
    		map.put(level, temp);
    	}
    	else{
    		map.get(level).add(node.val);
    	}
    	PrintFromTopToBottom_rec(node.left, map, level + 1);
    	PrintFromTopToBottom_rec(node.right, map, level + 1);
    }
    
    //����˴�ţ��д��
    //����ʱ�䣺14m ռ���ڴ棺9276k
    public ArrayList<Integer> PrintFromTopToBottom_2(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<TreeNode> queue = new ArrayList<>();
        if(root == null) return list;
        queue.add(root);
        while(!queue.isEmpty()){
        	TreeNode temp = queue.remove(0);
        	if(temp.left != null) queue.add(temp.left);
        	if(temp.right != null) queue.add(temp.right);
        	list.add(temp.val);
        }
        return list;
    }
    
    /**
     * ����������������
     * ��һ�����б�ʾջ��ѹ��˳��
     * ���жϵڶ��������Ƿ����Ϊ��ջ�ĵ���˳��
     * ����ѹ��ջ���������־������
     * ��������1,2,3,4,5��ĳջ��ѹ��˳��
     * ����4,5,3,2,1�Ǹ�ѹջ���ж�Ӧ��һ����������
     * ��4,3,5,1,2�Ͳ������Ǹ�ѹջ���еĵ�������
     * ע�⣺���������еĳ�������ȵ�
     */
    //����ʱ�䣺19ms ռ���ڴ棺9436k
    public boolean IsPopOrder(int [] pushA,int [] popA) {
    	if(pushA.length == 0) return false;
    	int len = pushA.length;
    	Stack<Integer> stack = new Stack<Integer>();
    	int i = 0;
    	int j = 0;
    	//ֻ�ܱ�֤push����
    	while(i < len && j < len){
    		if(stack.isEmpty() || stack.peek() != popA[j]){
    			stack.push(pushA[i++]);
    		}
    		else{
    			stack.pop();
    			j++;
    		}
    	}
    	while(!stack.isEmpty()){
    		if(stack.peek() == popA[j]){
    			stack.pop();
    			j++;
    			continue;
    		}
    		return false;
    	}
        return true;
    }
    
    
    /**
     * ����ջ�����ݽṹ
     * ���ڸ�������ʵ��һ���ܹ��õ�ջ��������СԪ�ص�min����
     * ʱ�临�Ӷ�ӦΪO��1��
     *
     */
    //����ʱ�䣺13ms ռ���ڴ棺9308k
    class ImplMinInStack{
    	Stack<Integer> stack = new Stack<>();
    	Stack<Integer> sup = new Stack<>();
    	public void push(int node) {
            if(sup.empty() || sup.peek() > node){
            	sup.push(node);
            }
            stack.push(node);
        }
        
        public void pop() {
        	if(stack.empty()){
        		throw new RuntimeException("ջ�ǿյ�");
        	}
        	if(stack.peek() == sup.peek()){
        		sup.pop();
        	}
            stack.pop();
        }
        
        public int top() {
        	if(stack.empty()){
        		throw new RuntimeException("ջ�ǿյ�");
        	}
            return stack.peek();
        }
        
        public int min() {
        	if(stack.empty()){
        		throw new RuntimeException("ջ�ǿյ�");
        	}
            return sup.peek();
        }
    }
    
    
    /**
     * �����ַ������һ�����ʵĳ���
     * �����Կո����
     */
    //����ʱ�䣺45ms ռ���ڴ棺10572k
    static class CptLenhOfLastWordInString{
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            sc.close();
            System.out.println(str.length() - str.lastIndexOf(" ") - 1);
        }
    }
    
    @Test
    public void zz(){
    	//System.out.println(t("hell3123123o world31231"));
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
