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
			 * �Ǿ�̬�ڲ�����Է����ⲿ��ľ�̬/�Ǿ�̬����
			 * �����ǲ���˽�е�
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
		 * ��̬�ڲ���ֻ�ܷ�����Χ��ľ�̬����
		 * �����ǲ���˽�е�
		 */
	}
	
	public void T02_M01(){
		final int m = 0;
		//�����ڷ�����ߵ�������ֲ��ڲ���
		//ֻ����final��abstarct���� ��ʲô���η�������
		//���Ծֲ��ڲ�������ǳ����
		class T02_Inner3{
			//�ֲ��ڲ��������й�����
			public T02_Inner3(){
				
			}
			
			void t(){
				System.out.println(m);
			}
		}
	}
}
