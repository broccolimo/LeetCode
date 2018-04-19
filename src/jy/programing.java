package jy;

import java.util.Arrays;
import java.util.Scanner;
import org.junit.Test;

/**
 * @target C_01
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
	 * @problem 网易2019年第一道编程题 牛牛找工作
	 */
	public void C_01(){
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
	 * @problem 网易2019年第二道编程题 被3整除
	 */
	//我去,这什么题
	//不要妄图把数字之和全加起来得到一个数字看能不能被3整除来解决问题
	//时间上都不允许
	//这道题就是找个规律。。。
	public void C_02(){
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

}


