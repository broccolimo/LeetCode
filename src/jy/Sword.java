package jy;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;





import org.junit.Test;

import sun.print.resources.serviceui;




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
	/**
	 * 一只青蛙一次可以跳上1级台阶
	 * 也可以跳上2级
	 * 它也可以跳上n级
	 * 求该青蛙跳上一个n级的台阶总共有多少种跳法
	 */
	//运行时间：18m 占用内存：9424k
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
     * 输入一个整数
     * 输出该数二进制表示中1的个数
     * 其中负数用补码表示
     */
    //运行时间：11ms 占用内存：9432k
    //java里边的负数默认就是用补码进行操作的
    //所以没有必要进行把负数转正, 头部为1其余取反最后加1的操作
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
     * 输入一个链表
     * 输出该链表中倒数第k个结点
     */
    //就是先求总共有几个节点 别想着反向输出
    //运行时间：16ms 占用内存：9656k
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
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形
     * 总共有多少种方法
     */
    //运行时间：591ms 占用内存：9428k
    public int RectCover(int target) {
    	if(target < 1) return 0;
        if(target == 1 || target == 2) return target;
    	return RectCover(target - 1) + RectCover(target - 2);
    }
    
    /**
     * 输入一个整数数组
     * 实现一个函数来调整该数组中数字的顺序
     * 使得所有的奇数位于数组的前半部分
     * 所有的偶数位于数组的后半部分
     * 并保证奇数和奇数
     * 偶数和偶数之间的相对位置不变。
     */
    //运行时间：22ms 占用内存：9372k
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
     * 输入一个链表
     * 反转链表后
     * 输出新链表的表头
     */
    //运行时间：17ms 占用内存：9564k
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
     * 输入两个单调递增的链表
     * 输出两个链表合成后的链表
     * 当然我们需要合成后的链表满足单调不减规则
     */
    //非递归
    //运行时间：20ms 占用内存：9660k
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
    
    //递归
    //运行时间：21ms 占用内存：9432k
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
     * 操作给定的二叉树
     * 将其变换为源二叉树的镜像。
     * @param root
     */
    //运行时间：22ms 占用内存：9560k
	//蠢
    public void Mirror(TreeNode root) {
        if(root == null) return;
        TreeNode right= root.right;
        root.right = root.left;
        root.left = right;
        Mirror(root.left);
        Mirror(root.right);
    }
    
    
    /**
     * 输入两棵二叉树A，B
     * 判断B是不是A的子结构
     * 我们约定空树不是任意一个树的子结构
     */
    //运行时间：16ms 占用内存：9536k
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean flag = false;
        //root2从来就没变过 只要root2是空 则直接返回flag的初始值false
        if(root1 != null && root2 != null){
        	//若相等 则看余下的结构是否也一样或包含
        	if(root1.val == root2.val){
        		flag = HasSubtree_reserval(root1, root2);
        	}
        	
        	//root1当前这个点和root2的跟不一样 则试试root1的left
        	if(!flag){
        		flag = HasSubtree(root1.left, root2);
        	}
        	
        	//root1当前这个点和left和root2的跟不一样 则试试root1的right
        	if(!flag){
        		flag = HasSubtree(root1.right, root2);
        	}
        }
        return flag;
    }
    
    public boolean HasSubtree_reserval(TreeNode root1,TreeNode root2){
    	//root1有 而root2空 则root2是root1的子树
    	if(root2 == null) return true;
    	//到这一步root2必定不为空 若root1空了 则root2不是root1的子树
    	if(root1 == null) return false;
    	if(root1.val != root2.val) return false;
    	return HasSubtree_reserval(root1.left, root2.left) && HasSubtree_reserval(root1.right, root2.right);
    }
    
    
    /**
     * 从上往下打印出二叉树的每个节点
     * 同层节点从左至右打印
     */
    //自己做的 递归还map 不太妥
    //运行时间：14m 占用内存：9432k
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
    
    //借鉴了大牛的写法
    //运行时间：14m 占用内存：9276k
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
     * 输入两个整数序列
     * 第一个序列表示栈的压入顺序
     * 请判断第二个序列是否可能为该栈的弹出顺序
     * 假设压入栈的所有数字均不相等
     * 例如序列1,2,3,4,5是某栈的压入顺序
     * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列
     * 但4,3,5,1,2就不可能是该压栈序列的弹出序列
     * 注意：这两个序列的长度是相等的
     */
    //运行时间：19ms 占用内存：9436k
    public boolean IsPopOrder(int [] pushA,int [] popA) {
    	if(pushA.length == 0) return false;
    	int len = pushA.length;
    	Stack<Integer> stack = new Stack<Integer>();
    	int i = 0;
    	int j = 0;
    	//只能保证push完了
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
     * 定义栈的数据结构
     * 请在该类型中实现一个能够得到栈中所含最小元素的min函数
     * 时间复杂度应为O（1）
     *
     */
    //运行时间：13ms 占用内存：9308k
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
        		throw new RuntimeException("栈是空的");
        	}
        	if(stack.peek() == sup.peek()){
        		sup.pop();
        	}
            stack.pop();
        }
        
        public int top() {
        	if(stack.empty()){
        		throw new RuntimeException("栈是空的");
        	}
            return stack.peek();
        }
        
        public int min() {
        	if(stack.empty()){
        		throw new RuntimeException("栈是空的");
        	}
            return sup.peek();
        }
    }
    
    
    /**
     * 计算字符串最后一个单词的长度
     * 单词以空格隔开
     */
    //运行时间：45ms 占用内存：10572k
    static class CptLenhOfLastWordInString{
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            sc.close();
            System.out.println(str.length() - str.lastIndexOf(" ") - 1);
        }
    }
    
    
    /**
     * 输入一个矩阵
     * 按照从外向里以顺时针的顺序依次打印出每一个数字
     * 例如
     * 如果输入如下4 X 4矩阵
     * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
     * 则依次打印出数字
     * 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
     */
    //运行时间：19ms 占用内存：9544k
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int xmax = rows - 1;
        int ymax = cols - 1;
        int xmin = 1;
        int ymin = 0;
        int i = 0;
        int j = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while(true){
        	//step1
        	while(j <= ymax){
        		list.add(matrix[i][j++]);
        	}
        	j--;
        	ymax--;
        	i++;
        	if(i > xmax) break;
        	//step2
        	while(i <= xmax){
        		list.add(matrix[i++][j]);
        	}
        	i--;
        	xmax--;
        	j--;
        	if(j < ymin) break;
        	//step3
        	while(j >= ymin){
        		list.add(matrix[i][j--]);
        	}
        	j++;
        	ymin++;
        	i--;
        	if(i < xmin) break;;
        	//step4
        	while(i >= xmin){
        		list.add(matrix[i--][j]);
        	}
        	i++;
        	xmin++;
        	j++;
        	if(j > ymax) break;
        }
        return list;
    }
    
    
    
    //运行时间：17ms 占用内存：9380k
    public boolean VerifySquenceOfBST(int [] sequence) {
    	if(sequence.length == 0) return false;
        return VerifySquenceOfBST_R(sequence, 0, sequence.length - 1);
    }
    
    public boolean VerifySquenceOfBST_R(int[] a, int start, int end){
    	if(start >= end) return true;
    	//mid指右子树的起始index 
    	//由于不排除没有左子树的可能
    	//所以mid初始化为start
    	int mid = start;
    	//存在左子树
    	if(a[start] < a[end]){
    		mid++;
    		//mid == end时说明没有右子树
    		while(mid < end && a[mid] < a[end]){
    			mid++;
    		}
    	}
    	//不排除 所谓右子树里边有节点值小于根节点值的情况
    	int temp = mid;
    	while(temp < end){
    		if(a[temp++] < a[end]) return false;
    	}
    	return VerifySquenceOfBST_R(a, start, mid - 1) && VerifySquenceOfBST_R(a, mid, end - 1);
    }
    
    
    //运行时间：18ms 占用内存：9296k
    public int MoreThanHalfNum_Solution(int [] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < array.length; i++){
        	if(!map.keySet().contains(array[i])){
        		map.put(array[i], 1);
        		if(map.get(array[i]) > array.length / 2){
        			return array[i];
        		}
        	}
        	else{
        		map.put(array[i], map.get(array[i]) + 1);
        		if(map.get(array[i]) > array.length / 2){
        			return array[i];
        		}
        	}
        }
        return 0;
    }
    
    
    /**
     * 输入n个整数
     * 找出其中最小的K个数
     * 例如输入4,5,1,6,2,7,3,8这8个数字
     * 则最小的4个数字是1,2,3,4
     */
    //运行时间：20ms 占用内存：9584k
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
    	ArrayList<Integer> list = new ArrayList<>();
    	int length = input.length;
    	if(k > length || length == 0 || k == 0) return list;
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				if(arg0 > arg1) return -1;
				if(arg0 == arg1) return 0;
				return 1;
			}
    		
    	});
    	for(int i = 0; i < length; i++){
    		if(i < k){
    			maxHeap.add(input[i]);
    		}
    		else{
    			if(maxHeap.peek() > input[i]){
    				maxHeap.poll();
    				maxHeap.add(input[i]);
    			}
    		}
    	}
    	for(int i : maxHeap){
    		list.add(i);
    	}
    	return list;
    }
    
    /**
     * 找连续子数组的最大和
     */
    //运行时间：17ms 占用内存：9368k
    public int FindGreatestSumOfSubArray(int[] array) {
    	if(array.length == 0) return 0;
        int max = array[0];
        int cur = array[0];
        for(int i = 1; i < array.length; i++){
        	cur = Math.max(array[i], cur + array[i]);
        	max = Math.max(cur, max);
        }
        return max;
    }
    
    //运行时间：15ms 占用内存：9664k
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        r(res, root, target, new ArrayList<Integer>());
        return res;
    }
    
    public void r(ArrayList<ArrayList<Integer>> res, TreeNode root, int target, ArrayList<Integer> temp){
    	if(root == null) return;
    	if(root.val == target){
    		if(root.left == null && root.right == null){
    			temp.add(root.val);
        		res.add(temp);
    		}
    		return;
    	}
    	if(root.val > target) return;
    	temp.add(root.val);
    	ArrayList<Integer> r1 = new ArrayList<>(temp);
    	ArrayList<Integer> r2 = new ArrayList<>(temp);
    	r(res, root.left, target - root.val, r1);
    	r(res, root.right, target - root.val, r2);
    }
    
    
    /**
     * 输入一棵二叉树
     * 求该树的深度
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径
     * 最长路径的长度为树的深度
     */
    //运行时间：17ms 占用内存：9152k
    public int TreeDepth(TreeNode root) {
        if(root == null) return 0;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return Math.max(left, right) + 1;
    }
    

    /**
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中
     * 找到第一个只出现一次的字符
     * 并返回它的位置
     * 如果没有则返回 -1(需要区分大小写)
     */
    //运行时间：45ms 占用内存：9952k
    public int FirstNotRepeatingChar(String str) {
    	Map<Character, Integer> map = new HashMap<>();
    	for(int i = 0; i < str.length(); i++){
    		if(map.get(str.charAt(i)) == null){
    			map.put(str.charAt(i), 1);
    		}
    		else{
    			map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
    		}
    	}
    	for(int i = 0; i < str.length(); i++){
    		if(map.get(str.charAt(i)) == 1){
    			return i;
    		}
    	}
        return -1;
    }
    
    //旋转数组找最大值
    public int CC01(int[] a){
    	int left = 0;
    	int right = a.length - 1;
    	if(a[left] < a[right]) return a[right];
    	while(left < right){
    		int mid = (left + right) / 2;
    		if(a[mid] > a[mid + 1]) return a[mid];
    		if(a[mid] < a[left]) right--;
    		else left++;
    	}
    	return a[left];
    }
    
    //旋转求最大值
    public int CC02(int[] a){
    	int left = 0;
    	int right = a.length - 1;
    	if(a[left] < a[right]) return a[left];
    	while(left < right){
    		int mid = (left + right) / 2;
    		if(a[mid] < a[mid - 1]) return a[mid];
    		if(a[mid] < a[left]) right--;
    		else left++;
    	}
    	return a[left];
    }
    
    
    /**
     * 输入一个字符串
     * 按字典序打印出该字符串中字符的所有排列
     * 例如输入字符串abc
     * 则打印出由字符a,b,c所能排列出来的所有字符串
     * abc,acb,bac,bca,cab和cba。
     */
    //使用回溯法 注意循环里回溯方法的第一个参数是i+1不是j+1
    //最后要用Collections.sort()进行自然排序
    //运行时间：165ms 占用内存：12224k
    public ArrayList<String> Permutation(String str) {
    	ArrayList<String> res = new ArrayList<>();
    	if(str == null || str.length() == 0) return res;
    	Permutation_r(0, str.toCharArray(), res);
    	Collections.sort(res);
    	return res;
    }
    
    public void Permutation_r(int index, char[] c, ArrayList<String> res){
    	if(index == c.length - 1){
    		String s = String.valueOf(c);
    		if(!res.contains(s)){
    			res.add(s);
    		} 
            return;
    	}
    	for(int i = index; i < c.length; i++){
    		for(int j = i; j < c.length; j++){
    			char t1 = c[i];
    			c[i] = c[j];
    			c[j] = t1;
    			Permutation_r(i + 1, c, res);
    			t1 = c[i];
    			c[i] = c[j];
    			c[j] = t1;
    		}
    	}
    }
    
    
    /**
     * 求1+2+3+...+n
     * 要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
     */
    //既然不能用循环 那就只能用递归
    //递归得有if终止条件 
    //但if不让用
    //就用短路操作代替if
    //无奈java还必须给这个操作的结果赋个值
    //运行时间：19ms 占用内存：9180k
    public int Sum_Solution(int n) {
        int res = n;
        boolean flag = (res != 0) && ((res += Sum_Solution(n - 1)) > 0);
        return res;
    }
    
    
    
    /**
     * 写出一个程序
     * 接受一个十六进制的数值字符串
     * 输出该数值的十进制字符串（多组同时输入 ）
     * 输入描述:
     * 输入一个十六进制的数值字符串。
     * 输出描述:
     * 输出该数值的十进制字符串。
     */
    //如果自测没问题 但就是AC不了
    //可以换一下scanner的写法
    //运行时间：51ms 占用内存：10536k
    @Test
    public void C_十六进制转十进制(){
    	Scanner scanner = new Scanner(System.in);
    	while(scanner.hasNext()){
    		String s = scanner.nextLine();
        	StringBuffer sb = new StringBuffer();
        	System.out.println(s.substring(2).toLowerCase());
        	sb.append(s.substring(2).toLowerCase());
        	char[] c = sb.reverse().toString().toCharArray();
        	int sum = 0;
        	for(int i = 0; i < c.length; i++){
        		int val = c[i] - '0';
        		if(val >= 49){
        			sum += (val - 39) * Math.pow(16, i);
        		}
        		else{
        			sum += val * Math.pow(16, i);
        		}
        	}
        	System.out.println(sum);
    	}
    	scanner.close();
    }
    
    
    /**
     * 输入两个链表，找出它们的第一个公共结点。
     */
    //首先要理解这个题目的含义
    //这其实是个Y型链表
    //就是说两个链表的尾巴是一样的
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
    	if(pHead1 == null || pHead2 == null) return null;
    	int len1 = 0;
    	int len2 = 0;
    	ListNode temp1 = pHead1;
    	ListNode temp2 = pHead2;
    	while(temp1 != null){
    		len1++;
    		temp1 = temp1.next;
    	}
    	while(temp2 != null){
    		len2++;
    		temp2 = temp2.next;
    	}
    	temp1 = pHead1;
    	temp2 = pHead2;
    	int diff = 0;
    	if(len1 < len2){
    		diff = len2 - len1;
    		while(diff != 0){
    			temp2 = temp2.next;
    			diff--;
    		}
    	}
    	else{
    		diff = len1 - len2;
    		while(diff != 0){
    			temp1 = temp1.next;
    			diff--;
    		}
    	}
    	while(temp1.val != temp2.val){
    		temp1 = temp1.next;
    		temp2 = temp2.next;
    	}
    	return temp1 == null ? null : temp1;
    }
    
    
    /**
     * 复制一个复杂链表(leetcode138)
     * 2018-09-26
     */
    /**
     * 这应该是较唯一的写法了
     * 因为如果想独立于链表再造一个平行链表时
     * 会发现random无法处理(造是肯定要造的 但不知道在何处造)
     * 先在原链表每个节点后复制自身
     * 就能很好解决这个问题了
     */
    public RandomListNode Clone(RandomListNode pHead){
    	RandomListNode cur = pHead;
    	RandomListNode next;
    	while(cur != null){
    		next = cur.next;
    		RandomListNode temp = new RandomListNode(cur.label);
    		cur.next = temp;
    		temp.next = next;
    		cur = next;
    	}
    	cur = pHead;
    	while(cur != null){
    		if(cur.random != null){
    			cur.next.random = cur.random.next;
    		}
    		cur = cur.next.next;
    	}
    	cur = pHead;
    	RandomListNode res = new RandomListNode(0);
    	RandomListNode r1;
    	RandomListNode r2 = res;
    	while(cur != null){
    		next = cur.next.next;
    		r1 = cur.next;
    		r2.next = r1;
    		r2 = r1;
    		cur.next = next;
    		cur = next;
    	}
    	return res.next;
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

	class RandomListNode {
		int label;
		RandomListNode next = null;
		RandomListNode random = null;

		RandomListNode(int label) {
			this.label = label;
		}
	}
	
}
