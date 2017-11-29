package jy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import javax.annotation.Resource;

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
	 * @problem #14 Longest Common Prefix
	 * @date 2017-11-24
	 * 
	 * Write a function to find the longest common prefix string 
	 * amongst an array of strings.
	 * 
	 * 用V2
	 */
	public String C014_LongestCommonPrefix(String[] strs) {
		int l = strs.length;
		if(l == 0 || strs == null){
			return "";
		}
		if(l == 1){
			return strs[0];
		}
		int i = 0;
		int il = strs[0].length();
		String result = "";
		DL:
			for(int k = 0; k < il; k++){
				for(int j = 1; j < l; j++){
					if(strs[j].length() < k + 1){
						break DL;
					}
					if(strs[0].charAt(k) != strs[j].charAt(k)){
						break DL;
					}
				}
				result += strs[0].charAt(k);
			}
		return result;
	}

	/**
	 * 2种思想的差异
	 * 前者(自己想的)
	 * 从第一个元素的第一个字符开始
	 * 把每一个字符跟后续所有元素的相应字符比较
	 * 只要有一个对不上
	 * 就把之前的返回
	 * 
	 * 后者(别人的)
	 * 直接从第2个元素比较第1个元素
	 * 中间经过若干次对第一个元素的削减
	 * 知道第一个元素削减成第2个元素的前缀
	 * 再拿削减后的前缀去跟后续元素比较
	 * 如果前缀不幸被削为空
	 * 则证明没有LCS 返回空字符串
	 * 
	 * 如果把字符在元素上移动的方向说是横向
	 * 把遍历元素的过程说是纵向
	 * 则前者是先横再纵
	 * 后者是先纵后横
	 */

	/**
	 * indexOf值分3类
	 * a) -1
	 * 压根不包含
	 * b) 0
	 * 是前缀
	 * c) 1,2,...
	 * 包含但不是前缀
	 */
	public String C014_LongestCommonPrefix_V2(String[] strs) {
		int l = strs.length;
		if(l == 0){
			return "";
		}
		String prefix = strs[0];
		//让i从1开始有2个作用
		//若数组中仅有一个元素 则直接返回 不进入for循环
		//若数组中不止一个元素 进入循环 正常逻辑
		for(int i = 1; i < l; i++){
			//只要prefix不是前缀 就从后往前削
			//直到是当前元素的前缀
			//再把当前值给后边的数组元素食用
			//不行就再削
			//如果不幸被削完了 
			//那就只能GG了 返回""
			while(strs[i].indexOf(prefix) != 0){
				prefix = prefix.substring(0, prefix.length() - 1);
				if(prefix.isEmpty()){
					return "";
				}
			}
		}
		return prefix;
	}

	/**
	 * @problem #15 3Sum
	 * @date 2017-11-24
	 * 
	 * Given an array S of n integers, 
	 * are there elements a, b, c in S such that a + b + c = 0? 
	 * Find all unique triplets in the array which gives the sum of zero.
	 */
	public List<List<Integer>> C015_3Sum(int[] nums) {
		int l = nums.length;
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		for(int i = 0; i < l - 2; i++){
			if(i == 0 || (i > 0 && nums[i] != nums[i - 1])){
				int j = i + 1;
				int k = l - 1;
				while(j < k){
					if(nums[i] + nums[j] + nums[k] == 0){
						list.add(Arrays.asList(nums[i], nums[j], nums[k]));
						while(j < k && nums[j] == nums[j + 1]){
							j++;
						}
						while(j < k && nums[k] == nums[k - 1]){
							k--;
						}
						j++;
						k--;
					}
					else if(nums[i] + nums[j] + nums[k] < 0){
						j++;
					}
					else{
						k--;
					}
				}
			}
		}
		return list;
	}

	/**
	 * @problem #16 3Sum Closest
	 * @date 2017-11-27
	 * 
	 * Given an array S of n integers, 
	 * find three integers in S such that the sum is closest to a given number, target. 
	 * Return the sum of the three integers. 
	 * You may assume that each input would have exactly one solution.
	 * 
	 * 输入数组的长度不小于3
	 * 跟#15的思想保持一致
	 */
	public int C016_3SumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int result = nums[0] + nums[1] + nums[2];
		int m = Math.abs(result - target);
		int l =nums.length;
		DL:
			for(int i = 0; i < l - 2; i++){
				//过滤重复数字 i==0是例外
				if(i == 0 || (i > 0 && i != i - 1)){
					int j = i + 1;
					int k = l - 1;
					while(j < k){
						if(nums[i] + nums[j] + nums[k] == target){
							result = target;
							break DL;
						}
						//和比目标小 j要加
						else if(nums[i] + nums[j] + nums[k] < target){
							if(Math.abs(nums[i] + nums[j] + nums[k] - target) < m){
								m = Math.abs(nums[i] + nums[j] + nums[k] - target);
								result = nums[i] + nums[j] + nums[k];
							}
							j++;
							//过滤重复数字
							while(j < k && nums[j] == nums[j - 1]){
								j++;
							}
						}
						//和比目标大 k要减
						else{
							if(Math.abs(nums[i] + nums[j] + nums[k] - target) < m){
								m = Math.abs(nums[i] + nums[j] + nums[k] - target);
								result = nums[i] + nums[j] + nums[k];
							}
							k--;
							//过滤重复数字
							while(j < k && nums[k] == nums[k + 1]){
								k--;
							}
						}
					}
				}
			}
		return result;
	}





	/**
	 * @problem #17 Letter Combinations of a Phone Number
	 * @date 2017-11-27
	 * 
	 * Given a digit string, 
	 * return all possible letter combinations that the number could represent.
	 */
	public List<String> C017_LetterCombinationsOfAPhoneNumber(String digits) {
		LinkedList<String> res = new LinkedList<String>();
		String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		int l = digits.length();
		//测试时会用空字符串
		if(l == 0){
			return res;
		}
		/**
		 * 这里加个空字符串
		 * 1是为了一开始peek()可用 体现在加了一个对象
		 * 2是为了与i对应 体现在这个对象的长度是0
		 */
		res.add("");
		for(int i = 0; i < l; i++){
			//char类型的数字变成int
			int x = Character.getNumericValue(digits.charAt(i));
			//LinkedList相当于一个队列 先进先出 peek相当于头
			//i的值 自己体会
			while(res.peek().length() == i){
				String remove = res.remove();
				for(char c : mapping[x].toCharArray()){
					res.add(remove + c);
				}
			}
		}
		return res;
	}
	
	/**
	 * @problem #18 4Sum
	 * @date 2017-11-27
	 * 
	 * Given an array S of n integers, 
	 * are there elements a, b, c, and d in S such that a + b + c + d = target? 
	 * Find all unique quadruplets in the array which gives the sum of target.
	 */
	public List<List<Integer>> C018_4Sum(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		int l = nums.length;
		for(int i = 0; i < l - 3; i++){
			//i为头 总循环为i的移动 if过滤重复数字
			if(i == 0 || nums[i] != nums[i - 1]){
				for(int m = l - 1; i < m; m--){
					//m是尾 且对于每个i都是尾 每次都由m向i逼近 总宏观控制为i和m
					//if过滤重复数字
					if(m == l - 1 || nums[m] != nums[m + 1]){
						int j = i + 1;
						int k = m - 1;
						//j k为每次宏观控制下的活动元素 遍历宏观规定的内容
						while(j < k){
							if(nums[i] + nums[j] + nums[k] + nums[m] == target){
								res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[m]));
								j++;
								k--;
								//过滤重复数字
								while(j < k && nums[j] == nums[j - 1]){
									j++;
								}
								//过滤重复数字
								while(j < k && nums[k] == nums[k + 1]){
									k--;
								}
							}
							else if(nums[i] + nums[j] + nums[k] + nums[m] < target){
								j++;
							}
							else{
								k--;
							}
						}
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * @problem #19 Remove Nth Node From End of List
	 * @date 2017-11-27
	 * @reference ListNode.java
	 * 
	 * Given a linked list, 
	 * remove the nth node from the end of list and return its head.
	 */
    public ListNode C019_RemoveNthNodeFromEndOfList(ListNode head, int n) {
        int l = 1;
        ListNode cursor = head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        while(cursor.next != null){
        	l++;
        	cursor = cursor.next;
        }
        cursor = head;
        if(n == l){
        	pre.next = head.next;
        	head.next = null;
        }
        else{
        	for(int i = 1; i < l - n; i++){
        		cursor = cursor.next;
        	}
        	cursor.next = cursor.next.next;
        }
        return pre.next;
    }
    
    
    /**
     * @problem #20 Valid Parentheses
     * @date 2017-11-27
     * 
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
     * determine if the input string is valid.
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid 
     * but "(]" and "([)]" are not.
     * 
     * 用V3
     */
    public boolean C020_ValidParentheses(String s) {
    	LinkedList<Character> list = new LinkedList<>();
    	int l = s.length();
    	int count = 0;
    	for(int i = 0; i < l; i++){
    		switch (s.charAt(i)) {
			case '(':
				list.add('(');
				count++;
				break;
			case '{':
				list.add('{');
				count++;
				break;
			case '[':
				list.add('[');
				count++;
				break;
			case ')':
				if(list.indexOf('(') >= 0){
					while(list.peekLast() != '('){
						list.removeLast();
					}
					list.removeLast();
					count--;
				}
				else{
					return false;
				}
				break;
			case '}':
				if(list.indexOf('{') >= 0){
					while(list.peekLast() != '{'){
						list.removeLast();
					}
					list.removeLast();
					count--;
				}
				else{
					return false;
				}
				break;
			case ']':
				if(list.indexOf('[') >= 0){
					while(list.peekLast() != '['){
						list.removeLast();
					}
					list.removeLast();
					count--;
				}
				else{
					return false;
				}
				break;
			default:
				break;
			}
    	}
    	if(count == 0){
    		return true;
    	}
    	return false;
    }
    
    /**
     * 按V3的思路修改
     */
    public boolean C020_ValidParentheses_V2(String s) {
    	LinkedList<Character> list = new LinkedList<>();
    	int l = s.length();
    	for(int i = 0; i < l; i++){
    		switch (s.charAt(i)) {
			    case '(':
				    list.add(')');
				    break;
			    case '{':
				    list.add('}');
				    break;
			    case '[':
				    list.add(']');
				    break;
			    case ')':
                    if(list.size() == 0 || list.peekLast() != ')'){
                        return false;
                    }
                    else{
                        list.removeLast();
                    }
                    break;
			    case '}':
                    if(list.size() == 0 || list.peekLast() != '}'){
                        return false;
                    }
                    else{
                        list.removeLast();
                    }
                    break;
			    case ']':
                    if(list.size() == 0 || list.peekLast() != ']'){
                        return false;
                    }
                    else{
                        list.removeLast();
                    }
                    break;
			    default:
				    break;
			}
    	}
    	if(list.size() != 0){
    		return false;
    	}
    	return true;
    }
    /**
     * 用Stack
     */
    public boolean C020_ValidParentheses_V3(String s) {
    	Stack<Character> stack = new Stack<>();
    	for(char c : s.toCharArray()){
    		if(c == '('){
    			stack.push(')');
    		}
    		else if(c == '{'){
    			stack.push('}');
    		}
    		else if(c == '['){
    			stack.push(']');
    		}
    		/** 
    		 * pop前一定要考虑栈是否为空
    		 * 若为空 借助||的短路 直接就不用pop了
    		 * 直接判错 例如 ']' 左是进不到这个判断条件的
    		 * 
    		 * pop的作用
    		 * 如果输入了一个右 想要判断为正确
    		 * 那么栈顶一定为相应的左
    		 * [{] 
    		 * [{}]
    		 */
    		else if(stack.isEmpty() || stack.pop() != c){
    			return false;
    		}
    	}
    	return stack.isEmpty();
    }
    
    /**
     * @problem #21 Merge Two Sorted Lists
     * @date 2017-11-28
     * 
     * Merge two sorted linked lists and return it as a new list. 
     * The new list should be made by splicing together the nodes of 
     * the first two lists.
     * 
     * 默认排序是从小到大
     * 不排除空链表的情况
     * 时间超时要转变方法 用递归(recursion)
     */
    public ListNode C021_MergeTwoSortedLists(ListNode l1, ListNode l2) {
    	if(l1 == null){
    		return l2;
    	}
    	if(l2 == null){
    		return l1;
    	}
    	if(l1.val <= l2.val){
    		l1.next = C021_MergeTwoSortedLists(l1.next, l2);
    		return l1;
    	}
    	else{
    		l2.next = C021_MergeTwoSortedLists(l1, l2.next);
    		return l2;
    	}
    }
    
    /**
     * @problem #22 Generate Parentheses
     * @date 2017-11-28
     * 
     * Given n pairs of parentheses, 
     * write a function to generate all combinations of well-formed parentheses.
     */
	public List<String> C022_GenerateParentheses(int n){
		List<String> res = new ArrayList<>();
		C022_r(res, "", n, n);
		return res;
	}
	
	public void C022_r(List<String> list, String s, int left, int right){
		if(left < 0 || right < 0 || left > right){
			return;
		}
		if(left == 0 && right == 0){
			list.add(s);
		}
		//加一个左括号
		C022_r(list, s + "(", left - 1, right);
		//加一个右括号
		C022_r(list, s + ")", left, right - 1);
	}
	
	
	/**
	 * @problem #23 Merge k Sorted Lists
	 * @date 2017-11-29
	 * 
	 * Merge k sorted linked lists and return it as one sorted list. 
	 * Analyze and describe its complexity.
	 * 
	 * 总的思想是归并(分治)
	 * 
	 * 时间复杂度(不考虑系数)
	 * 假设一共有k个链表 共n个节点
	 * while执行的次数为O(logk)
	 * 对于每一次归并
	 * for执行的次数为O(k)
	 * 每次都是O(n)个节点在比较大小 所以每次比较的时间复杂度为O(n)
	 * 总的时间复杂度=O(logk)*O(k)*O(n)=O(nklogk)
	 * 
	 * 错误思想：
	 * 不应该从头开始 由小及大一次串成一个链表
	 * 而应该每2个链表合并 最后合并成一个单一链表  即为所求
	 */
	public ListNode C023_MergeKSortedLists(ListNode[] lists) {
		if(lists == null){
			return null;
		}
		int len = lists.length;
		if(len == 0){
			return null;
		}
		if(len == 1){
			return lists[0];
		}
		//数组的长度是几就代表剩几个链表没有合并
		while(len > 1){
			//mid代表跨度 也代表合并后剩余的链表个数
			//0 1 => 01 => 1 1
			//0 1 2 => 02 1 => 2 2
			//0 1 2 3 => 02 13 =>差为2 最后也剩2个
			//0 1 2 3 4 => 03 14 2 => 3 3
			//0 1 2 3 4 5 => 03 14 25 => 3 3
			int mid = (len + 1) / 2;
			for(int i = 0 ; i < len / 2; i++){
				lists[i] = C023_r1(lists[i], lists[i + mid]);
				//lists[i] = C023_r2(lists[i], lists[i + mid]);
			}
			len = mid;
		}
		return lists[0];
    }
	
	//合并2个链表 不难吧。。
	//递归也好 循环也好
	//r1 用递归 r2 用循环
	public ListNode C023_r1(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val <= l2.val){
			l1.next = C023_r1(l1.next, l2);
			return l1;
		}
		else{
			l2.next = C023_r1(l1, l2.next);
			return l2;
		}
	}
	
	public ListNode C023_r2(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		ListNode source = new ListNode(0);
		ListNode cursor = source;
		while(l1 != null && l2 != null){
			if(l1.val <= l2.val){
				cursor.next = l1;
				cursor = cursor.next;
				l1 = l1.next;
			}
			else{
				cursor.next = l2;
				cursor = cursor.next;
				l2 = l2.next;
			}
		}
		if(l1 != null){
			cursor.next = l1;
		}
		if(l2 != null){
			cursor.next = l2;
		}
		return source.next;
	}
	
	/**
	 * @problem #138 Copy List with Random Pointer
	 * @date 2017-11-23
	 * @reference RandomListNode.java
	 * 
	 * A linked list is given such that 
	 * each node contains an additional random pointer 
	 * which could point to any node in the list or null.
	 * Return a deep copy of the list.
	 * 
	 */
	public RandomListNode C138_CopyListWithRandomPointer(RandomListNode head){
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


	/**
	 * @problem #237 Delete Node in a Linked List
	 * @date 2017-11-24
	 * @reference ListNode.java
	 * 
	 * Write a function to delete a node (except the tail) in a singly linked list, 
	 * given only access to that node.
	 * Supposed the linked list is 1 -> 2 -> 3 -> 4 
	 * and you are given the third node with value 3, 
	 * the linked list should become 1 -> 2 -> 4 after calling your function.
	 */
	public void C237_DeleteNodeInALinkedList(ListNode node) {
		ListNode next = node.next;
		node.next = next.next;
		node.val = next.val;
		next.next = null;
	}
}
