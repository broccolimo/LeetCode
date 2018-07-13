package jy;

import java.util.Stack;
import org.junit.Test;

/**
 * @date 2018-07-13
 * @author broccoli
 * @description 《剑指Offer》面试题 java实现
 */
public class Sword {
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
	
	@Test
	public void z(){
		Sword.C09 obj = new Sword.C09();
		obj.push(7);
		obj.push(5);
		obj.push(8);
		System.out.println(obj.pop());
		System.out.println(obj.pop());
		System.out.println(obj.pop());
		System.out.println(obj.pop());
	}
}
