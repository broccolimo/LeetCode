package jy;

import java.util.Stack;
import org.junit.Test;

/**
 * @date 2018-07-13
 * @author broccoli
 * @description ����ָOffer�������� javaʵ��
 */
public class Sword {
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
