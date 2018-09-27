package jy;

import java.util.Scanner;

public class T{


	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		String[] s = str.split(" ");
		String source = s[0];
		String target = s[1];
		int index = judgeIndex(source, target);
		System.out.println(index);
	}

	private static int judgeIndex(String source,String target){
		int index = -1;
		if(!source.contains(target)) return index;
		String tmp = null;
		for (int i = 0; i < source.length()-target.length(); i++) {
			tmp = source.substring(i,i+target.length());
			if(tmp.equals(target)){
				index =  i;
				break;
			}else{
				continue;
			}
		}
		return index;
	}



}



