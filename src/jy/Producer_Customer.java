package jy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//生产者-消费者
public class Producer_Customer {
	public static void main(String[] args) {
		BlockingQueue<Mantou> queue = new ArrayBlockingQueue<>(5);
		new Thread(new Producer(queue)).start();
		new Thread(new Customer(queue)).start();
		System.out.println("Producer and Consumer has been started");
	}
}

class Mantou{
	private String mantou;
	public Mantou(String mantou){
		this.mantou = mantou;
	}
	public String getMantou(){
		return mantou;
	}
}


class Producer implements Runnable{

	private BlockingQueue<Mantou> queue;
	
	public Producer(BlockingQueue<Mantou> queue){
		this.queue = queue;
	}
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++){
			Mantou mantou = new Mantou("" + i);
			try {
				Thread.sleep(100);
				queue.put(mantou);
				System.out.println("生产馒头" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Mantou mantou = new Mantou("exit");
		try {
			queue.put(mantou);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

class Customer implements Runnable{
	private BlockingQueue<Mantou> queue;
	
	public Customer(BlockingQueue<Mantou> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			Mantou mantou;
			while(!(mantou = queue.take()).getMantou().equals("exit")){
				Thread.sleep(100);
				System.out.println("消费" + mantou.getMantou());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}