package jy;

public class R{
	public void method(Object o){
		System.out.println("Object");
	}
	
	public void method(String s){
		System.out.println("String");
	}
	
	public static void main(String[] args) {
		R r = new R();
		r.method(null);
	}
}
