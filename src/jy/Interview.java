package jy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jdk.nashorn.internal.parser.JSONParser;

import org.junit.Test;

public class Interview {
	//这个题目没有说清问题
	//从测试用例上看应该是至少需要多少板子能达到给定的悬空长度
	public int number_of_cards(float length){
		int sum = 1;
		float x = 0.0f;
		while(x < length){
			x += 1.0f / ++sum;
		}
		return sum - 1;
		
	}
	
	public int beauty_of_array(int[] array){
		//先把数组排序 是相同的数字排在一起
		Arrays.sort(array);
		//数组的长度
		int n = array.length;
		//不计重复得出来的总值
		int sum = 0;
		//当前可能重复数字 默认从数组第一个数字开始遍历
		int same = array[0];
		//当前可能重复数字已经出现的次数（当某个数字只有1个时 该值为1）
		int count = 0;
		//最终要减去的值
		int sub = 0;
		for(int i = 0; i < n; i++){
			//规律
			//在长度为n的数组里 下标为i的元素 在不考虑重复的情况下
			//被计算的次数是(1+2+...+i+1)+(n-2*(i+1))*(i+1)
			//稍微化简下
			sum += array[i] * (i + 1) * (n - i);
			//当前值与当前重复值相同 把重复次数+1
			if(array[i] == same){
				count++;
			}
			//当前值与当前重复值不同 就要计算之前重复值被多计算的数值
			//当某个数字只有一个时 count为1 那么x为0 不会进入循环进行计算
			//重复的次数是数学归纳出来的
			//计算之后把当前重复值设为当前值 同时把重复个数设为1
			if(array[i] != same){
				for(int x = count - 1; x >= 1; x--){
					sub += x * (n - x) * same;
				}
				same = array[i];
				count = 1;
			}
		}
		//如果数组末尾存在连续数字 得把多余的减去
		if(count > 1){
			for(int x = count - 1; x >= 1; x--){
				sub += x * (n - x) * same;
			}
		}
		return sum - sub;
	}
	
	//第一个重复数字是真正被计算的数字 之后的都不参与计算
	public int getSum(int[] array,int start,int end){
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        for(int i = start; i <= end; i++){
        	if(!set.contains(array[i])){
        		set.add(array[i]);
        		sum += array[i];
        	}
        }
        return sum;
    }
	
	//上述方法做完后总感觉太麻烦
	//不如就把数组的所有连续子数组列举出来 再一个一个计算和
    public int beauty_of_array2(int[] array){
    	Arrays.sort(array);
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                sum += getSum(array,i,j);
            }
        }
        return sum;
    }
    
    //运行时间：256ms 占用内存：28600k
    public int minNumberInRotateArray(int [] array) {
        int length = array.length;
        if(length == 0) return 0;
        if(array[0] < array[length - 1]) return array[0];
        int i = 0;
        int j = length - 1;
        while(i < j){
        	int mid = (i + j) / 2;
        	if(array[mid] > array[mid + 1]) return array[mid + 1];
        	if(array[mid] > array[i]){
        		i++;
        		continue;
        	}
        	j--;
        }
        return 0;
    }

}
