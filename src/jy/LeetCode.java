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
			//���һλ�����ʱ����ܻ������λ
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
			System.out.println("�������֮����'->'����");
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
	 * δ���
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
		//�±ߵı��ʽ�Ǵ��
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
		 * Math.abs�Ǹ���
		 * ���Ĳ���������int Ҳ������double 
		 * ���������int ����Ҳ��int
		 * ���������double ����Ҳ��double
		 * ����������intǿ��ת��Ϊlong
		 * ��Ϊint��ȡֵ�� -2147483648 ~ 2147483647
		 * ������Ĳ�����-2147483648������������int
		 * ���������ֵ��˵ ���Ӧ��2147483648
		 * ���Ѿ�������int�ķ�Χ
		 * ����������������� abs�������������
		 * �������-2147483648
		 * ���Ա����intǿ��ת��Ϊlong
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
		//��������
		int sign = 1;
		//����ֵ
		int total = 0;
		//�α�
		int index = 0;
		if(str == null || l == 0){
			return 0;
		}
		//�ҵ���һ����Ϊ�ո���ַ�
		while(str.charAt(index) == ' ' && index < l){
			index++;
		}
		//���һ��ʼ�������ţ�����һ��
		//������ֻ�ܳ����ڿ�ͷ
		//�����Ǽ�Ϊ���� ��ʹǰ��ȫ��0
		if(str.charAt(index) == '+' || str.charAt(index) == '-'){
			sign = str.charAt(index) == '+' ? 1 : -1;
			index++;
		}
		while(index < l){
			int digit = str.charAt(index) - '0';
			//�ж��Ƿ�Ϊ���� ��������ֱ�Ӱѽ������еĽ�����
			if(digit < 0 || digit > 9){
				break;
			}
			//���ܵȵ������ʱ���ٴ��� Ҫ��ǰ���� ���¾��Ǵ����ȷ���Է���
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
	 * �ж�һ��int���͵������Ƿ�Ϊ������
	 * ûɶ��˵��
	 * �κθ��������ǻ�����
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
	 * ��ס�� ������д
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
	 * ��򵥵������������ܽ��
	 * ���ǵ����������ʱ�� 
	 * ����ʱ��̫������ͨ��
	 * ԭ���Ǵ��ںܶ಻��Ҫ�ļ���
	 * ����������Ǵ��������м�ƽ�
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
	 * ��V2
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
	 * 2��˼��Ĳ���
	 * ǰ��(�Լ����)
	 * �ӵ�һ��Ԫ�صĵ�һ���ַ���ʼ
	 * ��ÿһ���ַ�����������Ԫ�ص���Ӧ�ַ��Ƚ�
	 * ֻҪ��һ���Բ���
	 * �Ͱ�֮ǰ�ķ���
	 * 
	 * ����(���˵�)
	 * ֱ�Ӵӵ�2��Ԫ�رȽϵ�1��Ԫ��
	 * �м侭�����ɴζԵ�һ��Ԫ�ص�����
	 * ֪����һ��Ԫ�������ɵ�2��Ԫ�ص�ǰ׺
	 * �����������ǰ׺ȥ������Ԫ�رȽ�
	 * ���ǰ׺���ұ���Ϊ��
	 * ��֤��û��LCS ���ؿ��ַ���
	 * 
	 * ������ַ���Ԫ�����ƶ��ķ���˵�Ǻ���
	 * �ѱ���Ԫ�صĹ���˵������
	 * ��ǰ�����Ⱥ�����
	 * ���������ݺ��
	 */

	/**
	 * indexOfֵ��3��
	 * a) -1
	 * ѹ��������
	 * b) 0
	 * ��ǰ׺
	 * c) 1,2,...
	 * ����������ǰ׺
	 */
	public String C014_LongestCommonPrefix_V2(String[] strs) {
		int l = strs.length;
		if(l == 0){
			return "";
		}
		String prefix = strs[0];
		//��i��1��ʼ��2������
		//�������н���һ��Ԫ�� ��ֱ�ӷ��� ������forѭ��
		//�������в�ֹһ��Ԫ�� ����ѭ�� �����߼�
		for(int i = 1; i < l; i++){
			//ֻҪprefix����ǰ׺ �ʹӺ���ǰ��
			//ֱ���ǵ�ǰԪ�ص�ǰ׺
			//�ٰѵ�ǰֵ����ߵ�����Ԫ��ʳ��
			//���о�����
			//������ұ������� 
			//�Ǿ�ֻ��GG�� ����""
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
	 * ��������ĳ��Ȳ�С��3
	 * ��#15��˼�뱣��һ��
	 */
	public int C016_3SumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int result = nums[0] + nums[1] + nums[2];
		int m = Math.abs(result - target);
		int l =nums.length;
		DL:
			for(int i = 0; i < l - 2; i++){
				//�����ظ����� i==0������
				if(i == 0 || (i > 0 && i != i - 1)){
					int j = i + 1;
					int k = l - 1;
					while(j < k){
						if(nums[i] + nums[j] + nums[k] == target){
							result = target;
							break DL;
						}
						//�ͱ�Ŀ��С jҪ��
						else if(nums[i] + nums[j] + nums[k] < target){
							if(Math.abs(nums[i] + nums[j] + nums[k] - target) < m){
								m = Math.abs(nums[i] + nums[j] + nums[k] - target);
								result = nums[i] + nums[j] + nums[k];
							}
							j++;
							//�����ظ�����
							while(j < k && nums[j] == nums[j - 1]){
								j++;
							}
						}
						//�ͱ�Ŀ��� kҪ��
						else{
							if(Math.abs(nums[i] + nums[j] + nums[k] - target) < m){
								m = Math.abs(nums[i] + nums[j] + nums[k] - target);
								result = nums[i] + nums[j] + nums[k];
							}
							k--;
							//�����ظ�����
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
		//����ʱ���ÿ��ַ���
		if(l == 0){
			return res;
		}
		/**
		 * ����Ӹ����ַ���
		 * 1��Ϊ��һ��ʼpeek()���� �����ڼ���һ������
		 * 2��Ϊ����i��Ӧ �������������ĳ�����0
		 */
		res.add("");
		for(int i = 0; i < l; i++){
			//char���͵����ֱ��int
			int x = Character.getNumericValue(digits.charAt(i));
			//LinkedList�൱��һ������ �Ƚ��ȳ� peek�൱��ͷ
			//i��ֵ �Լ����
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
			//iΪͷ ��ѭ��Ϊi���ƶ� if�����ظ�����
			if(i == 0 || nums[i] != nums[i - 1]){
				for(int m = l - 1; i < m; m--){
					//m��β �Ҷ���ÿ��i����β ÿ�ζ���m��i�ƽ� �ܺ�ۿ���Ϊi��m
					//if�����ظ�����
					if(m == l - 1 || nums[m] != nums[m + 1]){
						int j = i + 1;
						int k = m - 1;
						//j kΪÿ�κ�ۿ����µĻԪ�� ������۹涨������
						while(j < k){
							if(nums[i] + nums[j] + nums[k] + nums[m] == target){
								res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[m]));
								j++;
								k--;
								//�����ظ�����
								while(j < k && nums[j] == nums[j - 1]){
									j++;
								}
								//�����ظ�����
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
     * ��V3
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
     * ��V3��˼·�޸�
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
     * ��Stack
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
    		 * popǰһ��Ҫ����ջ�Ƿ�Ϊ��
    		 * ��Ϊ�� ����||�Ķ�· ֱ�ӾͲ���pop��
    		 * ֱ���д� ���� ']' ���ǽ���������ж�������
    		 * 
    		 * pop������
    		 * ���������һ���� ��Ҫ�ж�Ϊ��ȷ
    		 * ��ôջ��һ��Ϊ��Ӧ����
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
     * Ĭ�������Ǵ�С����
     * ���ų�����������
     * ʱ�䳬ʱҪת�䷽�� �õݹ�(recursion)
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
		//��һ��������
		C022_r(list, s + "(", left - 1, right);
		//��һ��������
		C022_r(list, s + ")", left, right - 1);
	}
	
	
	/**
	 * @problem #23 Merge k Sorted Lists
	 * @date 2017-11-29
	 * 
	 * Merge k sorted linked lists and return it as one sorted list. 
	 * Analyze and describe its complexity.
	 * 
	 * �ܵ�˼���ǹ鲢(����)
	 * 
	 * ʱ�临�Ӷ�(������ϵ��)
	 * ����һ����k������ ��n���ڵ�
	 * whileִ�еĴ���ΪO(logk)
	 * ����ÿһ�ι鲢
	 * forִ�еĴ���ΪO(k)
	 * ÿ�ζ���O(n)���ڵ��ڱȽϴ�С ����ÿ�αȽϵ�ʱ�临�Ӷ�ΪO(n)
	 * �ܵ�ʱ�临�Ӷ�=O(logk)*O(k)*O(n)=O(nklogk)
	 * 
	 * ����˼�룺
	 * ��Ӧ�ô�ͷ��ʼ ��С����һ�δ���һ������
	 * ��Ӧ��ÿ2������ϲ� ���ϲ���һ����һ����  ��Ϊ����
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
		//����ĳ����Ǽ��ʹ���ʣ��������û�кϲ�
		while(len > 1){
			//mid������ Ҳ����ϲ���ʣ����������
			//0 1 => 01 => 1 1
			//0 1 2 => 02 1 => 2 2
			//0 1 2 3 => 02 13 =>��Ϊ2 ���Ҳʣ2��
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
	
	//�ϲ�2������ ���Ѱɡ���
	//�ݹ�Ҳ�� ѭ��Ҳ��
	//r1 �õݹ� r2 ��ѭ��
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
		//��ÿ���ڵ���(next)����������ĸ���
		//��ָ���� �ø���ָ��ԭ���Լ���ָ��
		//Round 1
		while(cursor != null){
			//next��ʾԭ�����е�ǰ�ڵ���ʵ��nextָ��
			next = cursor.next;
			//�������ƽڵ� ע���������cursor������
			RandomListNode copy = new RandomListNode(cursor.label);
			//�ı�ԭ����ǰ�ڵ��nextָ�� ָ������ĸ���
			cursor.next = copy;
			//��������nextָ��ԭ�����������nextָ��
			copy.next = next;
			//������һ��Ҫ����Ľڵ�
			cursor = next;
		}
		//������ ��δ���random
		cursor = head;
		//Round 2 
		while(cursor != null){
			//Ҫ�Ը��Ƶ�random��ֵ
			//����ֵ��Դ�ڵ�random�ĸ�ֵ Ҳ����������next
			//��ԭ�����������null�Ļ� �Ͳ�����random��
			if(cursor.random != null){
				cursor.next.random = cursor.random.next;
			}
			//������һ��Ҫ����Ľڵ�
			cursor = cursor.next.next;
		}
		//next��randomָ�������� 
		//�ѵ�ǰ������ԭ����͸��ƺõ�����
		//��Ȼ���ǰ�next��� 
		//Ϊɶ������random
		//��Ϊû�ж���random��ԭ�����е�ָ��
		//���Բ��ù�
		cursor = head;
		//cursor�Ͼ�ֻ���൱������ Ҫ���ر�
		//������Ҫһ���½ڵ����������Ƶ��õ�����
		//����ڵ��ֵ����㸳��
		RandomListNode fr = new RandomListNode(0);
		//Ӧ��Ҳ��һ���൱��fr��cursor
		//������Ҫ2��
		//cr1 �൱�� �ȷ��α� ���ҽڵ�� ��������
		RandomListNode cr1;
		//cr2�൱�� �󲿶� �������ָ�Ӳ�(fr)���� ����cr1�� 
		//cr1���� cr2����ָ��cr1 �ٸ�cr1���
		RandomListNode cr2 = fr;
		//��ʼ̽��
		//Round 3
		while(cursor != null){
			//next��Ȼ��ԭ�����е�ǰ�������ʵ��nextָ��
			next = cursor.next.next;
			//��һ������������
			cr1 = cursor.next;
			//�ڶ��� Ҳ���� ����� nextָ��cr1
			//������Ӳ�����fr���� ����cr2�����fr������ͬ
			cr2.next = cr1;
			//�ڶ���Ҳ������ǰ������
			//ָ����fr����
			cr2 = cr1;
			//��ԭԭ����ָ��
			cursor.next = next;
			//������һ���ڵ�
			cursor = next;
		}
		//ָ�Ӳ�ָ��ĵ�һ���ڵ� ����Ҫ���ص�
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
