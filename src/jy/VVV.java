package jy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;

public class VVV {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
            String[] s1 = sc.nextLine().split(" ");
            String[] s2 = sc.nextLine().split(" ");
            int[] a1 = new int[s1.length];
            int[] a2 = new int[s2.length];
            for(int i = 0; i < s1.length; i++){
                a1[i] = Integer.parseInt(s1[i]);
            }
            for(int i = 0; i < s2.length; i++){
                a2[i] = Integer.parseInt(s2[i]);
            }
            int[] c = new int[s1.length + s2.length];
        }
	}
	
	public static void t(int[] a1, int[] a2, int[] c, int x, int y, int m){
		if(x < a1.length && y < a2.length){
			if(a1[x] <= a2[y]){
				
			}
		}
	}
	
}
