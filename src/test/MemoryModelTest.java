package test;

import java.util.Random;

public class MemoryModelTest {
	public int x, y, x_read, y_read;
	
	public void randowSleep(){
		try {
			Thread.sleep(new Random().nextInt(10));
		} 
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Thread t1(){
		return new Thread(new Runnable() {
			@Override
			public void run() {
				randowSleep();
				x = 1;
				y_read = y;
			}
		});
	}
	
	public Thread t2(){
		return new Thread(new Runnable() {
			@Override
			public void run() {
				randowSleep();
				y = 1;
				x_read = x;
			}
		});
	}
	
	public static void main(String[] args) throws InterruptedException {
		int count = 0;
		while(true){
			count++;
			MemoryModelTest tester = new MemoryModelTest();
			Thread t1 = tester.t1();
			Thread t2 = tester.t2();
			
			t1.start();
			t2.start();
			
			t1.join();
			t2.join();
			
			System.out.println(String.format("(%d, %d)", tester.x_read, tester.y_read));
			
			if(tester.x_read == 0 && tester.y_read == 0){
				throw new RuntimeException("What?count=" + count);
			}
		}
		
	}
}
