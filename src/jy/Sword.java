package jy;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


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
	 * @date 2018-07-18
	 * @author broccoli
	 * @description 
	 * 给定一个列行数字都从小到大排列的数组 和 一个数字
	 * 判断这个数字是否在这个数组中
	 */
	/**
	 * 技巧: 从角入手
	 * 左上角最小 右下角最大 没啥卵用
	 * 左下角是一列最大 一行最小 右上角是一行最大 一列最小
	 * 用这两个角的数值去和给的数字作比较
	 * 要么相等 直接得出答案 要么不相等 可以排除一列或者一行 缩小范围重复此步骤 足矣
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
	
	
	
	
}
