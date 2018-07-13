package jy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

/**
 * @target C001
 * @date 2018-04-19
 */
class work implements Comparable<work>{
	int di;
	int pi;
	public int compareTo(work work){
		return (di - work.di);
	}
}

public class programing {

	@Test
	/**
	 * @ref work
	 * @date 2018-04-19
	 * @problem 网易2019年第1道编程题 牛牛找工作
	 */
	public void C001(){
		Scanner sc = new Scanner(System.in);
		int work_num = sc.nextInt();
		int per_num = sc.nextInt();
		//为什么这里要用自定义的一个类
		//因为如果有2组值{di, pi} di一样 pi不一样
		//在hashmap中只能存在一个
		//那必然会破坏di数组和hashmap数量上的一致性以及会增加时间复杂度
		//用自定类的话可以保持di数组和自定义类数组在数量上的一致性
		//同时不存在key值冲突重写value值得情况
		work[] work = new work[work_num];
		for(int i = 0; i < work_num; i++){
			work[i] = new work();
			work[i].di = sc.nextInt();
			work[i].pi = sc.nextInt();
		}
		//人的难度数组
		int[] pdi = new int[per_num];
		for(int i = 0; i < per_num; i++){
			pdi[i] = sc.nextInt();
		}
		//先把值取完 把scanner关掉
		sc.close();
		//如果用Arrays.sort
		//这个类必须实现Comparable接口同时重写compareTo()方法
		//返回值得被减数是自身要比较的值
		Arrays.sort(work);
		//由于work已经从小到大排序
		//可以遍历一遍赋新值
		for(int i = 1; i < work_num; i++){
			if(work[i].pi < work[i - 1].pi){
				work[i].pi = work[i - 1].pi;
			}
		}
		//已有工作的难度数组
		int[] wdi = new int[work_num];
		for(int i = 0; i < work_num; i++){
			wdi[i] = work[i].di;
		}
		//
		for(int i = 0; i < per_num; i++){  
			//这里用Arrays.binarySearch
			//手动实现的话会超时
			//参数1是数组
			//参数2是要插入的值
			//若有相同值 在返回值为对应索引
			//如果没有相同值 分3种情况
			//a:在范围内 从1开始计数 返回负的可插入索引值
			//b:不在范围内 且小于所有 返回-1
			//c:不在范围内 且大于所有 返回-(length+1)
			//a和c可以合并 所以总体上可以划分为3个区间
			//3个区间:x<-1(可以插在某处,最大相当于插在最后边) -1<=x<0(最小) x>=0(有相同值) 
			//注意：由于方法返回值的特性,插指的是在数组中某个数字后边插
			int value = Arrays.binarySearch(wdi, pdi[i]);
			if(value < -1){  
				System.out.println(work[-value - 2].pi);
			}
			else if(value >= 0){  
				System.out.println(work[value].pi);  
			}
			else{  
				System.out.println(0);  
			}
		}  
	}
	
	@Test
	/**
	 * @date 2018-04-19
	 * @problem 网易2019年第2道编程题 被3整除
	 */
	//我去,这什么题
	//不要妄图把数字之和全加起来得到一个数字看能不能被3整除来解决问题
	//时间上都不允许
	//这道题就是找个规律。。。
	public void C002(){
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int r = sc.nextInt();
		sc.close();
		int i = l % 3;
		int j = r % 3;
		int m = 0;
		int n = 0;
		switch (i) {
			case 1:
				m = l + 2;
				break;
			case 2:
				m = l + 1;
				break;
			case 0:
				m = l ;
				break;
			default:
				break;
		}
		switch (j) {
			case 1:
				n = r;
				break;
			case 2:
				n = r - 1;
				break;
			case 0:
				n = r - 2;
				break;
			default:
				break;
		}
		int sub = n - m - 1;
		System.out.println(sub == -3 ? (j == 0 ? 2 : 1) : ((i == 0 ? 1 : 2) + (j == 1 ? 0 : (j == 2 ? 1 : 2)) + ((sub / 3) * 2)));
	}
	
	/**
	 * @date 2018-04-20
	 * @problem 网易2019年第3道编程题 安置路灯
	 * @reference C_003_R
	 */
	//这种题不用想，绝对要用递归，找个规律就行
	//试出来纯属运气
	//主体是递归函数
	//其实就算有注释 也可能看不懂
	@Test
	public void C003(){
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		String[] arr = new String[num];
		for(int i = 0; i < num; i++){
			sc.nextLine();
			arr[i] = sc.nextLine();
		}
		sc.close();
		for(int i = 0; i < num; i++){
			C003_R(arr[i], 1, 1, true);
		}
	}
	
	public void C003_R(String str, int flag, int count, boolean init){
		//递归的终结
		//分情况
		//如果一开始就终结 表明没有 输出0
		//其余情况输出count
		if(str.indexOf(".") < 0){
			System.out.println(init? 0 : count);
			return;
		}
		int index = str.indexOf(".");
		//递归一开始count就是1 所以默认开始不是X
		if(init){
			str = str.substring(index, str.length());
			index = 0;
		}
		//小于等于3 说明之前的灯还能照亮
		if(index + flag <= 3){
			C003_R(str.substring(index + 1, str.length()), flag + index + 1, count, false);
		}
		//大于3说明需要有新的灯 所以count要加1
		//为什么flag是2 
		//因为就应该是2
		//只是刚开始时flag必须为1
		else{
			C003_R(str.substring(index + 1, str.length()), 2, count + 1, false);
		}
	}
	
	@Test
	/**
	 * @date 2018-04-20
	 * @problem 网易2019年第4道编程题 
	 */
	//太简单了 不注释了
	public void C004(){
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		String str = sc.nextLine();
		sc.close();
		int n = 0;
		for(char c : str.toCharArray()){
			if(c == 'L'){
				n--;
			}
			else{
				n++;
			}
		}
		char[] des1 = {'N', 'E', 'S', 'W'};
		char[] des2 = {'N', 'W', 'S', 'E'};
		if(n > 0){
			System.out.println(des1[n % 4]);
		}
		else{
			System.out.println(des2[Math.abs(n) % 4]);
		}
	}
	@Test
	public void C005(){
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		int length = s.length();
		boolean flag = true;
		for(int i = 0; i < length / 2; i++){
			if(s.charAt(i) != s.charAt(length - i - 1)){
				flag = false;
				System.out.println("N");
				break;
			}
		}
		if(flag){
			System.out.println("Y");
		}
	}
	
	
	@Test
	public void C006(){
		Scanner sc = new Scanner(System.in);
		List<Integer> list = new ArrayList<Integer>();
		int zero = -1;
		while((zero = sc.nextInt()) != 0){
			list.add(zero);
		}
		sc.close();
		for(int i = 0; i < list.size(); i++){
			int n = list.get(i);
			if(n <= 3){
				System.out.println(0);
				continue;
			}
			else{
				int count = 0;
				for(int j = 2; j <= n / 2; j++){
					if(zhi(j) && zhi(n - j)){
						count++;
					};
				}
				System.out.println(count);
			}
		}
		System.out.println("end");
	}
	
	public boolean zhi(int n){
		if(n == 2) return true;
		if(n < 2 || n % 2 == 0) return false;
		for(int i = 3; i <= Math.sqrt(n); i += 2){
			if(n % i == 0){
				return false;
			}
		}
		return true;
	}
	
	@Test
	public void zzz(){
		int[] aaa = {15, -1, 2, -100, -4, 5, 6, -8, 66};
		sort(aaa);
	}
	
	
	
	public void sort(int[] arr){
		int flag = 0;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] < 0){
				continue;
			}
			int temp = arr[flag];
			arr[flag++] = arr[i];
			arr[i] = temp;
		}
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
	}
}


