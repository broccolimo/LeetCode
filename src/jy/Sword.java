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
 * @description 《剑指Offer》面试题 java实现
 */
public class Sword {
	
	/**
	 * @date 2018-07-16
	 * @author broccoli
	 * @description 
	 * 数组中重复的数字输入任意一个
	 * 长度为n的数组里的所有数字都在0~n-1的范围内 
	 */
	//第一种思路 用HashSet 时间复杂度O(n) 空间复杂度O(n)
	//这种方法对输入的数组没有限制
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
	
	//第二种思路 时间复杂度和空间复杂度都是O(n)
	//这种方法必须保证输入数组里边的数字值不超过长度n
	public int C01_2(int arr[]){
		for(int i = 0; i < arr.length; i++){
			//当前数值与索引值相同,不需要变动
			if(i == arr[i]){
				continue;
			}
			int index = arr[i];
			//直接找到了重复的数字
			if(index == arr[index]){
				return index;
			}
			//若不相等,则交换位置
			arr[i] = arr[index];
			arr[index] = index;
			//交换位置后当前数值仍然与索引值不同
			if(i != arr[i]){
				i--;
			}
		}
		return -1;
	}
	
	/**
	 * 在一个二维数组中（每个一维数组的长度相同）
	 * 每一行都按照从左到右递增的顺序排
	 * 每一列都按照从上到下递增的顺序排序
	 * 请完成一个函数
	 * 输入这样的一个二维数组和一个整数
	 * 判断数组中是否含有该整数。
	 */
	//以左下角为基准
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
	//以右上角为基准
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
	 * @description #09 用两个栈实现队列
	 * @mind 
	 * 进队列时 把元素插到第一个栈中 
	 * 出队列时 检出第二个栈的栈尾元素
	 * 如果第二个栈为空 说明之前一直在进队列
	 * 此时把第一个栈中的元素 导入第二个栈中
	 * 就会把后进队列的元素放入第二个栈的栈顶
	 * 把先进队列的元素放入第二个栈的栈尾
	 * 那么就会检出第二个栈的栈尾元素 也就是整个队列第一个进来的元素
	 * 就实现了队列先进先出的功能
	 */
	public class C09{
		private Stack<Integer> stack1 = new Stack<>();
		private Stack<Integer> stack2 = new Stack<>();
		public void push(int i){
			stack1.push(i);
		}
		public int pop(){
			if(stack1.empty() && stack2.empty()){
				throw new RuntimeException("队列中没有元素");
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
	 * 请实现一个函数
	 * 将一个字符串中的每个空格替换成“%20”
	 * 例如
	 * 当字符串为We Are Happy
	 * 则经过替换之后的字符串为We%20Are%20Happy
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
	 * 输入一个链表
	 * 按链表值从尾到头的顺序返回一个ArrayList
	 */
	//借助栈 运行时间：23ms 占用内存：9252k
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
	//递归 运行时间：14ms 占用内存：9424k
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
	 * 输入某二叉树的前序遍历和中序遍历的结果
	 * 请重建出该二叉树
	 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
	 * 则重建二叉树并返回
	 */
	//运行时间：296ms 占用内存：22912k
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
	 * 大家都知道斐波那契数列
	 * 现在要求输入一个整数n
	 * 请你输出斐波那契数列的第n项（从0开始，第0项为0)
	 * n<=39
	 */
	//运行时间：1490ms 占用内存：9272k
	public int Fibonacci(int n) {
		if(n == 0) return 0;
		if(n == 1) return 1;
		return Fibonacci(n - 2) + Fibonacci(n - 1);
    }
	
	
	/**
	 * 用两个栈来实现一个队列
	 * 完成队列的Push和Pop操作
	 *  队列中的元素为int类型
	 */
	//运行时间：19ms 占用内存：9400k
	//注意只有stack2为空时才会从stack1拉数据
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
