package base;

//表达式合法与否
public class T01 {
	public static void main(String[] args) {
		int x = 8;
		//int j = 0;
		int y = 0;
		//合法 但不会改变x的值
		System.out.println(x >> 3);
		System.out.println(x);
		//+++是不合法的
		//System.out.println(+++j);
		System.out.println(x > y ? x : y);
		System.out.println(x %= 4);
		System.out.println(x);
	}
}
