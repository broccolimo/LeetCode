package jy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

import util.ListNode;
import util.RandomListNode;
import util.Utils;

/**
 * @author broccoli
 * @environment JavaSE-1.7
 */
@SuppressWarnings("all")
public class LeetCode {
	/**
	 * 总测试控制
	 * 以LeetCode的方式测试
	 */
    @Test
    public void A(){
    	/**
    	 * 1-6均单独用控制台输出
    	 */
    	
    	//7
    	//System.out.println(new LeetCode().C007_ReverseInteger(-2147483648));
    	//8
    	//System.out.println(new LeetCode().C008_StringToInteger("   0 + 2 4"));
    	//9
    	//System.out.println(new LeetCode().C009_PalindromeNumber(2147483647));
    }
    
    /**
     * 每个代码在测试的时候先用Junit的形式以查看控制台输出为检测手段
     * 代码经LeetCode检验通过后
     * 尽量把代码改成有返回值的方法 且不能以Junit的方式运行
     * 再测试时用别的方法调用
     */

    
	/**
	 * description
	 * Given an array of integers, 
	 * return indices of the two numbers such that they add up to a specific target.
	 * You may assume that each input would have exactly one solution, 
	 * and you may not use the same element twice.
	 * 
	 * example
	 * Given nums = [2, 7, 11, 15], target = 9,
	 * Because nums[0] + nums[1] = 2 + 7 = 9,
	 * return [0, 1].
	 */
	@Test
	public void C001_TwoSum(){
		try{
			Scanner sc = new Scanner(System.in);
			System.out.println("please give nums:");
			String sl = sc.nextLine();
			System.out.println("please give target:");
			int target = sc.nextInt();
			sc.close();
			int[] a = Utils.Turn(sl.split(" "));
			HashMap<Integer, Integer> map = new HashMap<>();
			int[] result = new int[2];
			int l = a.length;
			for(int i = 0; i < l; i++){
				if(map.containsKey(target - a[i])){
					result[0] = map.get(target - a[i]);
					result[1] = i;
					System.out.println("result: [" + result[0] + ", " + result[1] + "]");
					return;
				}
				else{
					map.put(a[i], i);
				}
			}
			System.out.println("no result!");
		}
		catch(Exception e){
			System.out.println("input error!");
		}
	}

	/**
	 * description
	 * You are given two non-empty linked lists 
	 * representing two non-negative integers. 
	 * The digits are stored in reverse order and 
	 * each of their nodes contain a single digit. 
	 * Add the two numbers and return it as a linked list.
	 * You may assume the two numbers do not 
	 * contain any leading zero, 
	 * except the number 0 itself.
	 * 
	 * example
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8
	 */
	@Test
	public void C002_AddTwoNumbers(){
		try{
			Scanner sc = new Scanner(System.in);
			System.out.println("please give two ListNodes:");
			String s1 = sc.nextLine();
			String s2 = sc.nextLine();
			sc.close();
			int[] a1 = Utils.Turn(s1.split("->"));
			int[] a2 = Utils.Turn(s2.split("->"));
			int l1 = a1.length;
			int l2 = a2.length;
			ListNode[] ln1 = new ListNode[l1];
			ListNode[] ln2 = new ListNode[l2];
			if(l1 > 0){
				ln1[0] = new ListNode(a1[0]);
				for(int i = 1; i < l1; i++){
					ln1[i] = new ListNode(a1[i]);
					ln1[i - 1].next = ln1[i];
				}
			}
			if(l2 > 0){
				ln2[0] = new ListNode(a2[0]);
				for(int i = 1; i < l2; i++){
					ln2[i] = new ListNode(a2[i]);
					ln2[i - 1].next = ln2[i];
				}
			}
			ListNode p = ln1[0];
			ListNode q = ln2[0];
			ListNode dummy = new ListNode(0);
			ListNode cursor = dummy;
			int carry = 0;
			while(p != null || q != null){
				int x = p != null ? p.val : 0;
				int y = q != null ? q.val : 0;
				int sum = x + y + carry;
				carry = sum / 10;
				cursor.next = new ListNode(sum % 10);
				cursor = cursor.next;
				if(p != null){
					p = p.next;
				}
				if(q != null){
					q = q.next;
				}
			}
			//最后一位计算的时候可能会产生进位
			if(carry > 0){
				cursor.next = new ListNode(carry);
			}
			StringBuffer sb = new StringBuffer();
			while(dummy.next != null){
				dummy = dummy.next;
				sb.append(dummy.val).append("->");
			}
			System.out.println(sb.substring(0, sb.length() - 2));
		}
		catch(Exception e){
			System.out.println("input error");
			System.out.println("多个数字之间用'->'隔开");
		}
	}

	/**
	 * description
	 * Given a string, 
	 * find the length of the longest substring 
	 * without repeating characters.
	 * 
	 * example
	 * Given "abcabcbb", the answer is "abc", 
	 * which the length is 3.
	 * Given "bbbbb", the answer is "b", 
	 * with the length of 1.
	 * Given "pwwkew", the answer is "wke", 
	 * with the length of 3. 
	 * Note that the answer must be a substring, 
	 * "pwke" is a subsequence and not a substring.
	 */
	@Test
	public void C003_LongestSubstringWithoutRepeatingCharacters(){
		System.out.println("please input:");
		Scanner sc = new Scanner(System.in);
		char[] c = sc.nextLine().toCharArray();
		sc.close();
		int l = c.length;
		int max = 0;
		for(int i = 0; i < l; i++){
			List list = new ArrayList<>();
			for(int j = i; j < l; j++){
				if(list.contains(c[j])){
					if(list.size() > max){
						max = list.size();
					}
					break;
				}
				else{
					list.add(c[j]);
				}
			}
			if(list.size() > max){
				max = list.size();
			}
		}
		System.out.println("the length of the longest substring is " + max);
	}

	@Test
	public void C003_LongestSubstringWithoutRepeatingCharacters_plus(){
		System.out.println("please input:");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		int ans = 0;
		int i = 0;
		int j = 0;
		int l = s.length();
		Set<Character> set = new HashSet<>();
		while(i < l && j < l){
			if(!set.contains(s.charAt(j))){
				set.add(s.charAt(j++));
				ans = Math.max(ans, j - i);
			}
			else{
				set.remove(s.charAt(i++));
			}
			System.out.println("i = " + i + " j = " + j + " ans = " + ans + " s : " + set);
		}
		System.out.println("the length of the longest substring is " + ans);
	}

	/**
	 * description
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * Find the median of the two sorted arrays. 
	 * The overall run time complexity should be O(log (m+n)).
	 * 
	 * example
	 * Example 1:
	 * nums1 = [1, 3] nums2 = [2]
	 * The median is 2.
	 * Example 2:
	 * nums1 = [1, 2] nums2 = [3, 4]
	 * The median is (2 + 3)/2 = 2.5
	 */
	@Test
	public void C004_MedianOfTwoSortedArrays(){
		Scanner sc = new Scanner(System.in);
		System.out.println("please input first sorted array:");
		int[] n1 = Utils.Turn(sc.nextLine().split(" "));
		System.out.println("please input second sorted array:");
		int[] n2 = Utils.Turn(sc.nextLine().split(" "));
		sc.close();
		int i = 0;
		int j = 0;
		int k = 0;
		int l1 = n1.length;
		int l2 = n2.length;
		int[] tar = new int[l1 + l2];
		while(i < l1 && j < l2){
			if(n1[i] <= n2[j]){
				tar[k++] = n1[i++];
			}
			else{
				tar[k++] = n2[j++];
			}
		}
		while(i < l1){
			tar[k++] = n1[i++];
		}
		while(j < l2){
			tar[k++] = n2[j++];
		}
		int m = tar.length;
		if(m % 2 == 1){
			System.out.println("the median is " + tar[(m - 1) / 2]);
		}
		else{
			System.out.println("the median is (" + tar[m / 2 - 1] + " + " + tar[m / 2] + ") = " + (double)(tar[m / 2 - 1] +  tar[m / 2]) / 2);
		}
	}
	
	/**
	 * description
	 * Given a string s, find the longest palindromic substring in s. 
	 * You may assume that the maximum length of s is 1000.
	 * 
	 * example
	 * Example:
	 * Input: "babad"
	 * Output: "bab"
	 * Note: "aba" is also a valid answer.
	 * Example:
	 * Input: "cbbd"
	 * Output: "bb"
	 */
	@Test
	public void C005_LongestPalindromicSubstring(){
		Scanner sc = new Scanner(System.in);
		System.out.println("please input:");
		String s = sc.nextLine();
		sc.close();
		StringBuffer sb = new StringBuffer();
		sb.append("#");
		for(int i = 0; i < s.length(); i++){
			sb.append(s.charAt(i)).append("#");
		}
		String ss = sb.toString();
		int max = 0;
		int bas = 0;
		int len = ss.length();
		for(int i = 0; i < len; i++){
			int k = 0;
			while(i - k >=0 && i + k <= len - 1 && ss.charAt(i - k) == ss.charAt(i + k)){
				if(k > max){
					max = k;
					bas = i;
				}
				k++;
			}
		}
		ss = ss.substring(bas - max, bas + max + 1);
		sb = new StringBuffer();
		len = ss.length();
		for(int i = 0; i < ss.length(); i++){
			if(ss.charAt(i) != '#'){
				sb.append(ss.charAt(i));
			}
		}
		System.out.println("the longest palindromic substring is " + sb);
	}
	
	/**
	 * 未完成
	 */
	@Test
	public void C006(){
//		Scanner sc = new Scanner(System.in);
//		System.out.println("please input:");
//		String s = sc.nextLine();
//		int n = Integer.parseInt(sc.nextLine());
//		sc.close();
		int m = 14;
		int n = 3;
		int x = n;
		//下边的表达式是错的
		int y = (n - 1) * m / (2 * (n - 1)) * + (m % (2 * (n - 1)) > n ? 2 : 1);
		System.out.println(y);
		int i = 0;
		int j = 0;
			
	}
	
	/**
	 * @problem #7 Reverse Integer
	 * @date 2017-11-22
	 * 
	 * Given a 32-bit signed integer, reverse digits of an integer.
	 * 
	 * Example 1:
	 * Input: 123
	 * Output:  321
	 * 
	 * Example 2:
	 * Input: -123
	 * Output: -321
	 * 
	 * Example 3:
	 * Input: 120
	 * Output: 21
	 * 
	 * Note:
	 * Assume we are dealing with an environment 
	 * which could only hold integers within the 32-bit signed integer range. 
	 * For the purpose of this problem, 
	 * assume that your function returns 0 
	 * when the reversed integer overflows.
	 */
    public int C007_ReverseInteger(int x) {
    	int sign = 1;
 		if(x < 0){
 			sign = -1;
 		}
 		/**
 		 * Math.abs是个坑
 		 * 它的参数可以是int 也可以是double 
 		 * 如果参数是int 则结果也是int
 		 * 如果参数是double 则结果也是double
 		 * 在这里必须把int强行转化为long
 		 * 因为int的取值是 -2147483648 ~ 2147483647
 		 * 若输入的参数是-2147483648，它的类型是int
 		 * 按常规绝对值来说 结果应是2147483648
 		 * 这已经超出了int的范围
 		 * 所以这里是有问题的 abs处理不了这个数据
 		 * 结果还是-2147483648
 		 * 所以必须把int强行转化为long
 		 */
 		char[] c = String.valueOf(Math.abs((long)x)).toCharArray();
 		int l = c.length;
 		for(int i = 0; i < l / 2; i++){
 			char temp = c[i];
 			c[i] = c[l - i - 1];
 			c[l - i - 1] = temp;
 		}
 		long y = Long.parseLong(String.valueOf(c));
 		if(y * sign < Integer.MIN_VALUE || y * sign > Integer.MAX_VALUE){
 			return 0;
 		}
 		else{
 			return (int)y * sign;
 		}
    }
    
    /**
     * @problem #8 String to Integer (atoi)
     * @date 2017-11-22
     * 
     * Requirements for atoi:
     * The function first discards as many whitespace characters as necessary 
     * until the first non-whitespace character is found. 
     * Then, starting from this character, 
     * takes an optional initial plus or minus sign followed 
     * by as many numerical digits as possible, 
     * and interprets them as a numerical value.
     * The string can contain additional characters 
     * after those that form the integral number, 
     * which are ignored and have no effect on the behavior of this function.
     * If the first sequence of non-whitespace characters in str 
     * is not a valid integral number, or if no such sequence exists 
     * because either str is empty or it contains only whitespace characters, 
     * no conversion is performed.
     * If no valid conversion could be performed, 
     * a zero value is returned. 
     * If the correct value is out of the range of representable values, 
     * INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
     */
    public int C008_StringToInteger(String str) {
    	int l = str.length();
    	//区别正负
    	int sign = 1;
    	//最终值
    	int total = 0;
    	//游标
    	int index = 0;
    	if(str == null || l == 0){
    		return 0;
    	}
    	//找到第一个不为空格的字符
    	while(str.charAt(index) == ' ' && index < l){
    		index++;
    	}
    	//如果一开始是正负号，处理一下
    	//正负号只能出现在开头
    	//若不是即为错误 即使前边全是0
    	if(str.charAt(index) == '+' || str.charAt(index) == '-'){
    		sign = str.charAt(index) == '+' ? 1 : -1;
    		index++;
    	}
    	while(index < l){
    		int digit = str.charAt(index) - '0';
    		//判断是否为数字 若不是则直接把截至已有的结果输出
    		if(digit < 0 || digit > 9){
    			break;
    		}
    		//不能等到溢出的时候再处理 要提前处理 以下就是处理的确定性方法
    		if(total > Integer.MAX_VALUE / 10 || total == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10){
    			return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
    		}
    		total = total * 10 + digit;
    		index++;
    	}
    	return sign * total;
    }
    
    /**
     * @problem #9 Palindrome Number
     * @date 2017-11-22
     * 
     * 判断一个int类型的数字是否为回文数
     * 没啥好说的
     * 任何负数都不是回文数
     */
    public boolean C009_PalindromeNumber(int x) {
    	if(x < 0){
            return false;
    	}
    	String s = String.valueOf(x);
    	int l = s.length();
    	for(int i = 0; i < l / 2; i++){
    		if(s.charAt(i) != s.charAt(l - i - 1)){
                return false;
    		}
    	}
    	return true;
    }
    
    /**
     * @problem #10 Regular Expression Matching
     * @date 2017-11-23
     * 
     * Implement regular expression matching with support for '.' and '*'.
     * 
     * 记住吧 就这样写
     */
    public boolean C010_RegularExpressionMatching(String s, String p){
    	boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
    	dp[0][0] = true;
    	for(int i = 0; i < p.length(); i++){
    		if(p.charAt(i) == '*' && dp[0][i - 1] == true){
    			dp[0][i + 1] = true;
    		}
    	}
    	for(int i = 0; i < s.length(); i++){
    		for(int j = 0; j < p.length(); j++){
    			if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
    				dp[i + 1][j + 1] = dp[i][j];
    			}
    			if(p.charAt(j) == '*'){
    				if(p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.'){
    					dp[i + 1][j + 1] = dp[i + 1][j - 1];
    				}
    				else{
    					dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1];
    				}
    			}
    		}
    	}
    	return dp[s.length()][p.length()];
    }
    
    /**
     * @problem #11 Container With Most Water
     * @date 2017-11-23
     * 
     * Given n non-negative integers a1, a2, ..., an, 
     * where each represents a point at coordinate (i, ai). 
     * n vertical lines are drawn 
     * such that the two endpoints of line i is at (i, ai) and (i, 0). 
     * Find two lines, which together with x-axis forms a container, 
     * such that the container contains the most water.
     * Note: You may not slant the container and n is at least 2.
     * 
     * 最简单的用蛮力法就能解决
     * 可是当数据量大的时候 
     * 会因时间太长而不通过
     * 原因是存在很多不必要的计算
     * 解决方法就是从两边向中间逼近
     */
    public int C011_ContainerWithMostWater(int[] height) {
    	int l = height.length;
    	int max = 0;
    	int i = 0;
    	int j = l - 1;
    	while(i < j){
    		int temp = Math.min(height[i], height[j]) * (j - i);
    		max = temp > max ? temp : max;
    		if(height[i] <= height[j]){
    			i++;
    		}
    		else{
    			j--;
    		}
    	}
        return max;
    }
    
    /**
     * @problem #12 Integer to Roman
     * @date 2017-11-23
     */
    public String C012_IntegerToRoman(int num){
    	String[] M = {"", "M", "MM", "MMM"};
    	String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    	String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    	String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    	return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }
    
    /**
     * @problem #13 Roman to Integer
     * @date 2017-11-23
     */
    public int C013_RomanToInteger(String s) {
    	s = s + ' ';
    	int num = 0;
    	for(int i = 0; i < s.length() - 1; i++){
    		switch (s.charAt(i)) {
			case 'M':
				num += 1000;
				break;
			case 'D':
				num += 500;
				break;
			case 'C':
				num = s.charAt(i + 1) == 'M' || s.charAt(i + 1) == 'D' ? num - 100 : num + 100;
				break;
			case 'L':
				num += 50;
				break;
			case 'X':
				num = s.charAt(i + 1) == 'C' || s.charAt(i + 1) == 'L'? num - 10 : num + 10;
				break;
			case 'V':
				num += 5;
				break;
			case 'I':
				num = s.charAt(i + 1) == 'X' || s.charAt(i + 1) == 'V' ? num - 1 : num + 1;
				break;
			default:
				break;
			}
    	}
    	return num;
    }
    
    /**
     * @problem #138 Copy List with Random Pointer
     * @date 2017-11-23
     * @reference util/RandomListNode.java
     * 
     * A linked list is given such that 
     * each node contains an additional random pointer 
     * which could point to any node in the list or null.
     * Return a deep copy of the list.
     * 
     */
    public RandomListNode C138_CopyListWithRandomPointer(){
    	RandomListNode head = new RandomListNode(3);
    	RandomListNode cursor = head;
    	RandomListNode next;
    	//在每个节点后边(next)都加上自身的复制
    	//并指向复制 让复制指向原来自己的指向
    	//Round 1
    	while(cursor != null){
    		//next表示原链表中当前节点真实的next指向
    		next = cursor.next;
    		//创建复制节点 注意参数是由cursor得来的
    		RandomListNode copy = new RandomListNode(cursor.label);
    		//改变原链表当前节点的next指向 指向自身的复制
    		cursor.next = copy;
    		//让自身复制next指向原链表中自身的next指向
    		copy.next = next;
    		//跳到下一个要处理的节点
    		cursor = next;
    	}
    	//重新来 这次处理random
    	cursor = head;
    	//Round 2 
    	while(cursor != null){
    		//要对复制的random赋值
    		//赋的值是源节点random的赋值 也就是其如今的next
    		//但原来的如果就是null的话 就不处理random了
    		if(cursor.random != null){
    			cursor.next.random = cursor.random.next;
    		}
    		//跳到下一个要处理的节点
    		cursor = cursor.next.next;
    	}
    	//next和random指向都做好了 
    	//把当前链表拆成原链表和复制好的链表
    	//当然拆是按next拆的 
    	//为啥不考虑random
    	//因为没有动过random在原链表中的指向
    	//所以不用管
    	cursor = head;
    	//cursor毕竟只是相当于索引 要来回变
    	//所以需要一个新节点来引导复制到好的链表
    	//这个节点的值是随便赋的
    	RandomListNode fr = new RandomListNode(0);
    	//应该也有一个相当于fr的cursor
    	//这里需要2个
    	//cr1 相当于 先锋游标 是找节点的 出身随意
    	RandomListNode cr1;
    	//cr2相当于 大部队 ，必须从指挥部(fr)出发 跟着cr1走 
    	//cr1到哪 cr2就先指向cr1 再跟cr1会合
    	RandomListNode cr2 = fr;
    	//开始探索
    	//Round 3
    	while(cursor != null){
    		//next依然是原链表中当前链表的真实的next指向
    		next = cursor.next.next;
     		//第一个爬到复制上
    		cr1 = cursor.next;
    		//第二个 也就是 新起点 next指向cr1
    		//这个连接不能由fr建立 所以cr2必须和fr出身相同
    		cr2.next = cr1;
    		//第二个也爬到当前复制上
    		//指向由fr保留
    		cr2 = cr1;
    		//还原原链表指向
    		cursor.next = next;
    		//跳到下一个节点
    		cursor = next;
    	}
    	//指挥部指向的第一个节点 就是要返回的
    	return fr.next;
    }
}
