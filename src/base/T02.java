package base;

public class T02 {
	int a = 1;
	private int b = 2;
	static int c = 3;
	private static int d = 4;
	
	class T02_Inner1{
		void t(){
			System.out.println(a);
			System.out.println(b);
			System.out.println(c);
			System.out.println(d);
			/**
			 * 非静态内部类可以访问外部类的静态/非静态数据
			 * 无论是不是私有的
			 */
		}
	}
	
	static class T02_Inner2{
		void t(){
			//System.out.println(a);
			//System.out.println(b);
			System.out.println(c);
			System.out.println(d);
		}
		/**
		 * 静态内部类只能访问外围类的静态数据
		 * 无论是不是私有的
		 */
	}
	
	public void T02_M01(){
		final int m = 0;
		//定义在方法里边的类叫做局部内部类
		//只能用final或abstarct修饰 或什么修饰符都不加
		//所以局部内部类可以是抽象的
		class T02_Inner3{
			//局部内部类允许有构造器
			public T02_Inner3(){
				
			}
			
			void t(){
				System.out.println(m);
			}
		}
	}
}
