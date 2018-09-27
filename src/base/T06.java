package base;

import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import sun.security.jca.GetInstance;

public class T06 {
	public static void main(String[] args) {
		singleton instance = singleton.GetInstance();
	}
}

class singleton{
	private singleton(){};
	private static singleton instance;
	public static synchronized singleton GetInstance(){
		if(instance == null) instance = new singleton();
		return instance;
	}
}
