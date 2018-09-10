package jy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jdk.nashorn.internal.parser.JSONParser;

import org.junit.Test;

public class Interview {
	//�����Ŀû��˵������
	//�Ӳ��������Ͽ�Ӧ����������Ҫ���ٰ����ܴﵽ���������ճ���
	public int number_of_cards(float length){
		int sum = 1;
		float x = 0.0f;
		while(x < length){
			x += 1.0f / ++sum;
		}
		return sum - 1;
		
	}
	
	public int beauty_of_array(int[] array){
		//�Ȱ��������� ����ͬ����������һ��
		Arrays.sort(array);
		//����ĳ���
		int n = array.length;
		//�����ظ��ó�������ֵ
		int sum = 0;
		//��ǰ�����ظ����� Ĭ�ϴ������һ�����ֿ�ʼ����
		int same = array[0];
		//��ǰ�����ظ������Ѿ����ֵĴ�������ĳ������ֻ��1��ʱ ��ֵΪ1��
		int count = 0;
		//����Ҫ��ȥ��ֵ
		int sub = 0;
		for(int i = 0; i < n; i++){
			//����
			//�ڳ���Ϊn�������� �±�Ϊi��Ԫ�� �ڲ������ظ��������
			//������Ĵ�����(1+2+...+i+1)+(n-2*(i+1))*(i+1)
			//��΢������
			sum += array[i] * (i + 1) * (n - i);
			//��ǰֵ�뵱ǰ�ظ�ֵ��ͬ ���ظ�����+1
			if(array[i] == same){
				count++;
			}
			//��ǰֵ�뵱ǰ�ظ�ֵ��ͬ ��Ҫ����֮ǰ�ظ�ֵ����������ֵ
			//��ĳ������ֻ��һ��ʱ countΪ1 ��ôxΪ0 �������ѭ�����м���
			//�ظ��Ĵ�������ѧ���ɳ�����
			//����֮��ѵ�ǰ�ظ�ֵ��Ϊ��ǰֵ ͬʱ���ظ�������Ϊ1
			if(array[i] != same){
				for(int x = count - 1; x >= 1; x--){
					sub += x * (n - x) * same;
				}
				same = array[i];
				count = 1;
			}
		}
		//�������ĩβ������������ �ðѶ���ļ�ȥ
		if(count > 1){
			for(int x = count - 1; x >= 1; x--){
				sub += x * (n - x) * same;
			}
		}
		return sum - sub;
	}
	
	//��һ���ظ���������������������� ֮��Ķ����������
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
	
	//��������������ܸо�̫�鷳
	//����Ͱ���������������������оٳ��� ��һ��һ�������
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
    
    //����ʱ�䣺256ms ռ���ڴ棺28600k
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
