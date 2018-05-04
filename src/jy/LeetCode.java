package jy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import org.hamcrest.DiagnosingMatcher;
import org.junit.Test;

import com.sun.accessibility.internal.resources.accessibility;
import com.sun.javafx.image.impl.IntArgb;
import com.sun.org.apache.bcel.internal.generic.ISTORE;

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
	 * Ҳ��һ��ʼ��һƬ����
	 * ����Ҳ֪�����ȥ�̻�
	 * 
	 * �Ǿ��߰�
	 * ���ż��Ķ���
	 * ��Ȼ�ͻ���
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
	public int[] C001_TwoSum(int[] nums, int target){
		int[] res = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++){
			if(map.containsKey(target - nums[i])){
				res[0] = map.get(target - nums[i]);
				res[1] = i;
				break;
			}
			else{
				map.put(nums[i], i);
			}
		}
		return res;
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
	 * @problem ZigZag Conversion
	 * @date 2018-05-04
	 * 
	 * ����Ҫ��leetcode
	 */
	public String C006_Convert(String s, int numRows) {
    	if(numRows == 1) return s;
    	//һ���м���Ԫ��
    	int m = numRows * 2 - 2;
    	//һ��ռ����
    	int line = numRows - 1;
    	//����
    	int yushu = s.length() % m;
    	//��
    	int shang = s.length() / m;
    	//�м���
    	int n = yushu == 0 ? shang : shang + 1;
    	//����Ҫ�ӵ���
    	int extra = yushu == 0 ? (numRows - 2) : (yushu <= numRows ? 0 : (yushu - numRows));
    	//�ܹ���Ҫ����
    	int count = line * (n - 1) + 1 + extra;
    	char[][] arr = new char[numRows][count];
    	C006_r(0, 0, 0, s, arr, numRows);
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0; i < numRows; i++){
    		for(int j = 0; j < count; j++){
    			if(arr[i][j] == 0) continue;
    			sb.append(arr[i][j]);
    		}
    	}
    	return sb.toString();
    }
	
	public void C006_r(int x, int y, int index, String s, char[][] arr, int row){
		if(index == s.length()) return;
		else if(y % (row - 1) == 0 && x != (row - 1)){
			arr[x++][y] = s.charAt(index++);
			C006_r(x, y, index, s, arr, row);
		}
		else{
			arr[x--][y++] = s.charAt(index++);
			C006_r(x, y, index, s, arr, row);
		}
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
	 * @problem #24 Swap Nodes in Pairs
	 * @date 2017-11-29
	 * 
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 */
	public ListNode C024_SwapNodesInPairs(ListNode head) {
		//������ǿ� ���� ֻ��1���ڵ�
		if(head == null || head.next == null){
			return head;
		}
		ListNode dummy = new ListNode(0);
		ListNode cursor = dummy;
		dummy.next = head;
		while(cursor.next != null && cursor.next.next != null){
			ListNode l1 = cursor.next;
			ListNode l2 = cursor.next.next;
			l1.next = l2.next;
			cursor.next = l2;
			l2.next = l1;
			cursor = l1;
		}
		return dummy.next;
	}

	/**
	 * @problem #26 Remove Duplicates from Sorted Array
	 * @date 2017-11-29
	 * 
	 * Given a sorted array, 
	 * remove the duplicates in-place such that 
	 * each element appear only once and return the new length.
	 * Do not allocate extra space for another array, 
	 * you must do this by modifying the input array in-place with O(1) extra memory.
	 */
	public int C026_RemoveDuplicatesFromSortedArray(int[] nums) {
		int len = nums.length;
		if(len == 0) return 0;
		if(len == 1) return 1;
		int i = 0;
		for(int j = 1; j < len; j++){
			if(nums[j] != nums[i]){
				nums[++i] = nums[j];
			}
		}
		return i + 1;
	}


	/**
	 * @problem #27 Remove Element
	 * @date 2017-11-29
	 * 
	 * Given an array and a value, 
	 * remove all instances of that value in-place and return the new length.
	 * Do not allocate extra space for another array, 
	 * you must do this by modifying the input array in-place with O(1) extra memory.
	 * The order of elements can be changed. 
	 * It doesn't matter what you leave beyond the new length.
	 */
	public int C027_RemoveElement(int[] nums, int val) {
		int len = nums.length;
		if(len == 0) return 0;
		if(len == 1) return nums[0] == val ? 0 : 1;
		int i = 0;
		int j = len;
		while(i < j){
			if(nums[i] == val){
				int temp = nums[i];
				nums[i] = nums[j - 1];
				nums[j - 1] = temp;
				j--;
			}
			else{
				i++;
			}
		}
		if(nums[0] == val){
			return 0;
		}
		return i;
	}


	/**
	 * @problem #28 Implement strStr()
	 * @date 2017-11-29
	 * 
	 * Return the index of the first occurrence of needle in haystack, 
	 * or -1 if needle is not part of haystack.
	 * 
	 * ����ƥ�䷨
	 */
	public int C028_ImplementStrStr(String haystack, String needle) {
		int i = 0;
		int j = 0;
		int hl = haystack.length();
		int nl = needle.length();
		while(i < hl && j < nl){
			if(haystack.charAt(i) == needle.charAt(j)){
				i++;
				j++;
			}
			else{
				i = i - j + 1;
				j = 0;
			}
		}
		if(j == nl){
			return i - j;
		}
		return -1;
	}

	/**
	 * ����KMP��ʵ��
	 */
	public int C028_ImplementStrStr_V2(String haystack, String needle) {
		int hl = haystack.length();
		int nl = needle.length();
		if(nl == 0) return 0;
		//��next����
		int[] next = new int[nl];
		int j = 0;
		int k = -1;
		next[0] = -1;
		while(j < nl - 1){
			if(k == -1 || needle.charAt(j) == needle.charAt(k)){
				next[++j] = ++k;
			}
			else{
				k = next[k];
			}
		}
		//
		int i = 0;
		j = 0;
		while(i < hl && j < nl){
			if(haystack.charAt(i) == needle.charAt(j)){
				i++;
				j++;
			}
			else{
				if(next[j] == -1){
					i++;
					j = 0;
				}
				else{
					j = next[j];
				}
			}
		}
		if(j == nl){
			return i - j;
		}
		return -1;
	}

	/**
	 * @problem #29 Divide Two Integers
	 * @date 2017-11-30
	 * 
	 * Divide two integers without using multiplication, division and mod operator.
	 * If it is overflow, return MAX_INT.
	 * 
	 * �����ı��ʾ��Ƕ�μ���
	 * ÿ�μ��Ķ��ǳ���
	 * �����ǿ��Ե� ��Ч��̫��
	 * ˼·������ÿһ�ξ����ܵ�ȥ���������
	 * ��õ�������ʽ����ָ��������
	 * ������
	 */
	public int C029_DivideTwoIntegers(int dividend, int divisor) {
		//����MAX_INT�����ֻ�г�����0����MIN_VALUE/-1
		if(divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
		boolean flag = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0 ? true : false;
		long m = Math.abs((long)dividend);
		long n = Math.abs((long)divisor);
		int result = 0;
		//m>=n����ʲô ��ʾm���ٰ���һ��n
		while(m >= n){
			//count��ʾ��λ һ��ʼ������ ���Գ�ʼ��Ϊ0
			int count = 0;
			//��m�Ƿ����1��n 2��n 4��n 8��n ...?
			//���� ����m ���n��ָ����
			//������ �ٴαȽ��������m��n �ٴ�1��n�Ƚ�
			while(m >= n << count){
				//��1 2 4 8�ķ�ʽ������
				result += 1 << count;
				m -= n << count;
				count++;
			}
		}
		return flag ? result : -result;
	}


	/**
	 * @problem #30 Substring with Concatenation of All Words
	 * @date 2017-12-01
	 * 
	 * You are given a string, s, and a list of words, words, 
	 * that are all of the same length. 
	 * Find all starting indices of substring(s) in s 
	 * that is a concatenation of each word in words exactly once 
	 * and without any intervening characters.
	 */
	public List<Integer> C030_SubstringWithConcatenationOfAllWords(String s, String[] words) {
		final Map<String, Integer> map = new HashMap<>();
		//��words���ֵ����map��
		//key��Ԫ�ص�ֵ
		//value��Ԫ�س��ֵĴ���
		for(String word : words){
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		List<Integer> result = new ArrayList<>();
		//��������
		int sl = s.length();
		//������Ԫ�صĸ���
		int num = words.length;
		//ÿ��Ԫ�صĳ���
		int len = words[0].length();
		//ģʽ���ĳ���Ϊnum*len
		//����Ӧ��ƥ��sl - num * len + 1��
		for(int i = 0; i < sl - num * len + 1; i++){
			int j = 0;
			Map<String, Integer> temp = new HashMap<>();
			while(j < num){
				//��ȡ��ǰҪƥ��������ϵ�j+1������Ϊlen���ִ�
				String match = s.substring(i + j * len, i + (j + 1) * len);
				//ƥ���ϵ�
				if(map.containsKey(match)){
					temp.put(match, temp.getOrDefault(match, 0) + 1);
					//���ظ� 
					//���� words��ĳԪ��ֻ��һ�� ��ǰ�������ִ��ϳ����˵ڶ�����Ԫ��
					if(temp.get(match) > map.get(match)){
						break;
					}
					else{
						j++;
					}
				}
				else{
					break;
				}
			}
			if(j == num){
				result.add(i);
			}
		}
		return result;
	}

	/**
	 * @problem #31 Next Permutation
	 * @date 2017-12-04
	 * 
	 * Implement next permutation, 
	 * which rearranges numbers into the lexicographically next 
	 * greater permutation of numbers.
	 * If such arrangement is not possible, 
	 * it must rearrange it as the lowest possible order (ie, sorted in ascending order).
	 * The replacement must be in-place, do not allocate extra memory.
	 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
	 * 1,2,3 �� 1,3,2
	 * 3,2,1 �� 1,2,3
	 * 1,1,5 �� 1,5,1
	 */
	public void C031_NextPermutation(int[] nums) {
		int l = nums.length;
		int k = l - 1;
		//�Ӻ���ǰ�Ҹ��±�k ��ֵС����ǰ�ߵ���
		//���ع���: kǰ�ߵ��Ǹ�Ԫ�ؾ��ǵ���
		while(k > 0){
			if(nums[k - 1] < nums[k]) break;
			else k--;
		}
		//��k=0 ��˵��������������Ϊ���� 
		//ֱ�Ӷ�ȫ������reserve��������
		//��k!=0 ���Ƚ�kǰ���Ǹ������k�������һ���������������
		//֮���ٴ�k��ʼ���� reserve����
		if(k != 0){
			for(int i = l - 1; i >= k; i--){
				if(nums[i] > nums[k - 1]){
					int temp = nums[i];
					nums[i] = nums[k - 1];
					nums[k - 1] = temp;
					break;
				}
			}
		}
		C031_r1(nums, k, l - 1);
	}

	//����Ҫ�������һ���ǴӴ�С����� ����ֻ��Ҫreserve
	//C031_r1(nums, 0, nums.length - 1);
	public void C031_r1(int[] nums, int left, int right){
		while(left < right){
			int temp = nums[left];
			nums[left++] = nums[right];
			nums[right--] = temp;
		}
	}

	//��ϰһ�� ��������
	//���ŵ�˼���Ƿ���
	//���� �� ��һ������ ������һ������
	//�� �� ��� �õݹ� 
	//�ֲ��֣�Ҳ����ʲôʱ����ֵ�ͷ�� �������ж�����
	public void C031_r2(int[] nums, int left, int right){
		if(left < right){
			int pos = C031_r3(nums, left, right);
			C031_r2(nums, left, pos - 1);
			C031_r2(nums, pos + 1, right);
		}
	}

	//��
	public int C031_r3(int[] nums, int left, int right){
		int value = nums[right];
		int pos = left;
		for(int i = left; i < right; i++){
			if(nums[i] <= value){
				int temp = nums[i];
				nums[i] = nums[pos];
				nums[pos++] = temp;
			}
		}
		nums[right] = nums[pos];
		nums[pos] = value;
		return pos;
	}

	/**
	 * @problem #32 LongestValidParentheses
	 * @date 2017-12-04
	 * 
	 * Given a string containing just the characters '(' and ')', 
	 * find the length of the longest valid (well-formed) parentheses substring.
	 * For "(()", the longest valid parentheses substring is "()", 
	 * which has length = 2.
	 * Another example is ")()())", 
	 * where the longest valid parentheses substring is "()()", which has length = 4.
	 * 
	 * tmd����ô�������
	 */
	public int C032_LongestValidParentheses(String s) {
		LinkedList<Integer> stack = new LinkedList<>();
		int result = 0;
		stack.push(-1);
		int l = s.length();
		for(int i = 0; i < l; i++){
			if(s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '('){
				stack.pop();
				result = Math.max(result, i - stack.peek());
			}
			else{
				stack.push(i);
			}
		}
		return result;
	}


	/**
	 * @problem #33 Search in Rotated Sorted Array
	 * @date 2017-12-05
	 * 
	 * Suppose an array sorted in ascending order is rotated at 
	 * some pivot unknown to you beforehand.
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 * You are given a target value to search. 
	 * If found in the array return its index, otherwise return -1.
	 * You may assume no duplicate exists in the array.
	 * 
	 * ���ֲ���Ҳ������ѭ�� Ҳ���õݹ�
	 * ������յ���ѭ���Ķ��ֲ���
	 * ͨ���ƶ�2�˵�λ����ȷ��mid��λ��
	 */
	public int C033_SearchInRotatedSortedArray(int[] nums, int target) {
		int min = 0;
		int max = nums.length - 1;
		while(min <= max){
			int mid = (min + max) / 2;
			if(nums[mid] == target) return mid;
			if(nums[min] <= nums[mid]){
				if(nums[min] <= target && target <= nums[mid]){
					max--;
				}
				else{
					min++;
				}
			}
			else{
				if(nums[mid] <= target && target <= nums[max]){
					min++;
				}
				else{
					max--;
				}
			}
		} 
		return -1;
	}



	/**
	 * @problem #34 Search for a Range
	 * @date 2017-12-05
	 * 
	 * Given an array of integers sorted in ascending order, 
	 * find the starting and ending position of a given target value.
	 * Your algorithm's runtime complexity must be in the order of O(log n).
	 * If the target is not found in the array, return [-1, -1].
	 * For example,
	 * Given [5, 7, 7, 8, 8, 10] and target value 8,
	 * return [3, 4].
	 */
	public int[] C034_SearchForARange(int[] nums, int target) {
		int l = nums.length;
		int[] notFound = {-1, -1};
		if(l == 0) return notFound;
		int lo = 0;
		int hi = l - 1;
		while(lo < hi){
			int mid = (lo + hi) / 2;
			if(target <= nums[mid]){
				hi--;
			}
			else{
				lo++;
			}
		}
		if(nums[lo] != target) return notFound;
		int k = lo + 1;
		while(k < l){
			if(nums[k] != target){
				break;
			}
			k++;
		}
		int[] found = new int[2];
		found[0] = lo;
		found[1] = k - 1;
		return found;
	}

	/**
	 * @problem #35 Search Insert Position
	 * @date 2017-12-05
	 * 
	 * Given a sorted array and a target value, 
	 * return the index if the target is found. 
	 * If not, return the index where it would be if it were inserted in order.
	 * You may assume no duplicates in the array.
	 */
	public int C035_SearchInsertPosition(int[] nums, int target) {
		int l = nums.length;
		if(l == 0) return 0;
		int lo = 0;
		int hi = l - 1;
		while(lo < hi){
			int mid = (lo + hi) / 2;
			if(target <= nums[mid]) hi = mid;
			else lo = mid + 1;
		}
		if(target <= nums[lo]) return lo;
		return lo + 1;
	}

	/**
	 * @problem #36 Valid Sudoku
	 * @date 2017-12-05
	 * 
	 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
	 * The Sudoku board could be partially filled, 
	 * where empty cells are filled with the character '.'.
	 */
	public boolean C036_ValidSudoku(char[][] board) {
		for(int i = 0; i < 9; i++){
			HashSet<Character> row = new HashSet<>();
			HashSet<Character> col = new HashSet<>();
			HashSet<Character> cube = new HashSet<>();
			int rowIndex = 3 * (i / 3);
			int colIndex = 3 * (i % 3);
			for(int j = 0; j < 9; j++){
				if(board[i][j] != '.' && !row.add(board[i][j])) return false;
				if(board[j][i] != '.' && !col.add(board[j][i])) return false;
				if(board[rowIndex + j / 3][colIndex + j % 3] != '.' && !cube.add(board[rowIndex + j / 3][colIndex + j % 3]))
					return false;
			}
		}
		return true;
	}


	/**
	 * @problem #37 Sudoku Solver
	 * @date 2017-12-06
	 * 
	 * Write a program to solve a Sudoku puzzle by filling the empty cells.
	 * Empty cells are indicated by the character '.'.
	 * You may assume that there will be only one unique solution.
	 */
	public boolean C037_SudokuSolver(char[][] board) {
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(board[i][j] == '.'){
					for(int k = 1; k <= 9; k++){
						board[i][j] = (char) ('0' + k);
						if(C037_r(board, i, j) && C037_SudokuSolver(board)) return true;
						board[i][j] = '.';
					}
					return false;
				}
			}
		}
		return true;
	}

	public boolean C037_r(char[][] board, int x, int y){
		for(int i = 0; i < 9; i++){
			if(i != y && board[x][y] == board[x][i]) return false;
		}
		for(int i = 0; i < 9; i++){
			if(i != x && board[x][y] == board[i][y]) return false;
		}
		for(int i = 3 * (x / 3) ; i <= 3 * (x / 3) + 2; i++){
			for(int j = 3 * (y / 3); j <= 3 * (y / 3) + 2; j++){
				if(i != x && j != y && board[x][y] == board[i][j]) return false;
			}
		}
		return true;
	}

	/**
	 * @problem #38 Count and Say
	 * @date 2017-12-06
	 * 
	 * 1 is read off as "one 1" or 11.
	 * 11 is read off as "two 1s" or 21.
	 * 21 is read off as "one 2, then one 1" or 1211.
	 * Given an integer n, generate the nth term of the count-and-say sequence.
	 */
	public String C038_CountAndSay(int n) {
		if(n == 1){
			return "1";
		}
		else{
			return C038_r(C038_CountAndSay(n - 1));
		}
	}

	public String C038_r(String s){
		List<Integer> list = new ArrayList<>();
		int l = s.length();
		for(int i = 0; i < l; i++){
			if(i == 0 || s.charAt(i) != s.charAt(i - 1)){
				list.add(1);
				list.add(Integer.parseInt(String.valueOf(s.charAt(i))));
			}
			else{
				list.set(list.size() - 2, list.get(list.size() - 2) + 1);
			}
		}
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < list.size(); i++){
			sb.append(list.get(i));
		}
		return sb.toString();
	}

	/**
	 * @problem #39  Combination Sum
	 * @date 2017-12-11
	 * 
	 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), 
	 * find all unique combinations in C where the candidate numbers sums to T.
	 * The same repeated number may be chosen from C unlimited number of times.
	 * Note:
	 * All numbers (including target) will be positive integers.
	 * The solution set must not contain duplicate combinations.
	 * For example, given candidate set [2, 3, 6, 7] and target 7, 
	 * A solution set is: 
	 * [[7],[2, 2, 3]]
	 * 
	 * ע���
	 * 1.���ڴ��ÿ����֧���ݵ�������Ҫ��List,��Ϊ��ʹ�ö���һ��������������ı����������Ȼ��ı���
	 * 2.���ص����������������Integer,��������л���int���͵�����,����ֶ�ת��,��java native�����������
	 * 3.����Ļ��ݷ�����ߵĲ�����ߵ�lenֵ�Ǳ����е� ��Ϊ����ͨ��list�ĳ�����list������Ԫ��
	 * 4.list�ĳ��ȳ�ʼ��Ϊtarget,������Զ����,�ȽϿ�ѧ
	 * 5.���ö�һ��ʼ����������,�Ͼ�����
	 */
	public List<List<Integer>> C039_CombinationSum(int[] candidates, int target) {
		int[] list = new int[target];
		List<List<Integer>> res = new ArrayList<>();
		C039_r(candidates, res, list, 0, 0, target);
		return res;
	}

	public void C039_r(int[] nums, List<List<Integer>> res, int[] list, int len, int start, int target){
		if(target == 0){
			List<Integer> temp = new ArrayList<>();
			for(int i = 0; i < len; i++){
				temp.add(list[i]);
			}
			res.add(temp);
			return;
		}
		else if(target < 0){
			return;
		}
		else{
			for(int i = start; i < nums.length; i++){
				int[] temp = list;
				temp[len] = nums[i];
				C039_r(nums, res, temp, len + 1, i, target - nums[i]);
			}
		}
	}
	
	/**
	 * @problem #40 Combination Sum II
	 * @date 2017-12-11
	 * 
	 * Given a collection of candidate numbers (C) and a target number (T), 
	 * find all unique combinations in C where the candidate numbers sums to T.
	 * Each number in C may only be used once in the combination.
	 * Note:
	 * All numbers (including target) will be positive integers.
	 * The solution set must not contain duplicate combinations.
	 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
	 * A solution set is: 
	 * [[1, 7],[1, 2, 5],[2, 6],[1, 1, 6]]
	 * 
	 * �����������ϵ���
	 * Ҫ��ÿ����ֻ����һ��
	 * ����ȥ��������
	 * ��ʵ�Ǹ�����
	 * ��Ϊ���������е����ǿ����ظ��� ������ǲ��ܰ����ظ������
	 * ����set�� runtime̫����
	 * һ�������������д��if���� ����Ҳ�Ǻ����ѵ�
	 */
	public List<List<Integer>> C040_CombinationSumII(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		C040_r(result, temp, candidates, target, 0);
		return result;
	}

	public void C040_r(List<List<Integer>> result, List<Integer> temp, int[] candidates, int target, int start){
		if(target < 0) return;
		if(target == 0){
			//Ϊ����new ArrayList���н�
			//��new�Ļ�ָ���temp����ĵ�ַ
			//������Ҫ�Ѵ�ʱ�˿̵�tempֵ�Ž�result
			//��new�Ļ�����result addָ��ǰ�ĵ�ַ
			//�������ַ�ϵ������Ƕ�̬�仯�� �������Ϊ��
			//�������ָ�����ɴ�ͬ���ĵ�ַ����������ַ����ǿյ�
			//����Ҫnewһ���µĵ�ַ���浱ǰ����
			//��ô�Ժ�ԭtemp���ݱ���Ҳ������ν��
			result.add(new ArrayList<>(temp));
			return;
		}
		//target >= candidates[i] ������Ǵ�С�����ŵ� ���ٲ�������
		for(int i = start; i < candidates.length && target >= candidates[i]; i++){
			//{1,2,2,5}
			//��target=10  1->2->2->5û���� iһֱ��start��ͬ
			//��target=8   1->2->5  1->2->5 �ڶ�����i��start��
			if(i > start && candidates[i] == candidates[i - 1]) continue;
			temp.add(candidates[i]);
			//ע��������i+1 ����start+1
			C040_r(result, temp, candidates, target - candidates[i], i + 1);
			temp.remove(temp.size() - 1);
		}
	}
	
	/**
	 * @problem #41 First Missing Positive
	 * @date 2017-12-14
	 */
    public int C041_FirstMissingPositive(int[] nums) {
    	Arrays.sort(nums);
    	int l = nums.length;
    	int count = 1;
    	for(int i = 0; i < l; i++){
    		if(i > 0 && nums[i] == nums[i - 1]) continue;
    		if(nums[i] > 0){
    			if(nums[i] != count) return count;
    			count++;
    		}
    	}
    	return count;
    }
    
    /**
     * @problem #42 Trapping Rain Water
     * @date 2017-12-14
     * 
     * Given n non-negative integers representing an elevation map 
     * where the width of each bar is 1, 
     * compute how much water it is able to trap after raining.
     * For example,
     * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     * 
     * ��Ҫ���ż�����˼��
     * ����˼·�����ż�
     */
    public int C042_TrappingRainWater(int[] height) {
    	int res = 0;
    	int left = 0;
    	int right = height.length - 1;
    	int left_max = 0;
    	int right_max = 0;
    	while(left < right){
    		//�������ȷ���� 
    		//ֻҪ��߷�����������ֻ��ȥ��
    		//�����ұ�����β
    		if(height[left] <= height[right]){
    			if(height[left] >= left_max){
    				left_max = height[left];
    			}
    			else{
    				res += (left_max - height[left]);
    			}
    			left++;
    		}
    		else{
    			if(height[right] >= right_max){
    				right_max = height[right];
    			}
    			else{
    				res += (right_max - height[right]);
    			}
    			right--;
    		}
    	}
    	return res;
    }
    
    /**
     * @problem #43 Multiply Strings
     * @date 2018-02-05
     * 
     * Given two non-negative integers num1 and num2 represented as strings, 
     * return the product of num1 and num2.
     * Note:
     * The length of both num1 and num2 is < 110.
     * Both num1 and num2 contains only digits 0-9.
     * Both num1 and num2 does not contain any leading zero.
     * You must not use any built-in BigInteger library or convert the inputs to integer directly.
     */
    public String C043_MultiplyStrings(String num1, String num2) {
    	int m = num1.length();
    	int n = num2.length();
    	int[] arr = new int[m + n];
    	int sum = 0;
    	for(int i = n - 1; i >= 0; i--){
    		for(int j = m - 1; j >= 0; j--){
    			int pos1 = i + j;
    			int pos2 = i + j + 1;
    			sum = (num1.charAt(j) - '0') * (num2.charAt(i) - '0') + arr[pos2];
    			arr[pos1] += sum / 10;
    			arr[pos2] = sum % 10;
    		}
    	}
    	StringBuffer sb = new StringBuffer();
    	for(int p : arr){
    		if(!(sb.length() == 0 && p == 0)){
    			sb.append(p);
    		}
    	}
    	return sb.length() == 0 ? "0" : sb.toString();
    }
    
    
    /**
     * @problem #44 Wildcard Matching
     * @date 2018-02-24
     * 
     * Implement wildcard pattern matching with support for '?' and '*'.
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * The matching should cover the entire input string (not partial).
     */
    public boolean C044_WildcardMatching(String s, String p) {
    	//s���ַ��� p��ͨ���
    	int i = 0;
    	int j = 0;
    	int sl = s.length();
    	int pl = p.length();
    	int match = 0;
    	int ps = -1;
    	while(i < sl){
    		if(j < pl && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))){
    			i++;
    			j++;
    		}
    		else if(j < pl && p.charAt(j) == '*'){
    			//��¼����*�� ���ߵ�λ��
    			match = i;
    			ps = j;
    			//�ַ������� ƥ��μ���
    			j++;
    		}
    		//���ƥ��������
    		else if(ps != -1){
    			//ʼ�հ�*���һλ��Ϊ��׼
    			j = ps + 1;
    			//i �� ���� match ���
    			match++;
    			i = match;
    		}
    		else{
    			return false;
    		}
    	}
    	//������һ��*ƥ��һ�����ַ��� 666
    	while(j < pl && p.charAt(j) == '*'){
    		j++;
    	}
    	return j == pl ? true : false;
    }
    
    /**
     * @problem #45 Jump Game II
     * @date 2018-02-27
     * 
     * Given an array of non-negative integers, 
     * you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to reach the last index in the minimum number of jumps.
     * For example:
     * Given array A = [2,3,1,1,4]
     * The minimum number of jumps to reach the last index is 2. 
     * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
     */
    public int C045_JumpGameII(int[] nums) {
    	//����˼· ��û������ʵ��
    	//Ī�������AC��
    	//����ʱ�� �϶�Ҫ����count
    	//������������Ҫ����t ������ʱ����max ��Ϊmax����������
    	//��Ҫע����������ĵ� ��ȻҪ����t ����i--
        int max = nums[0];
        int count = nums.length <= 1 ? 0 : 1;
        int t = 0;
        for(int i = 1; i < nums.length; i++){
        	if(i <= max){
        		t = nums[i] + i > t ? nums[i] + i : t;
        	}
        	else{
        		max = t;
        		i--;
        		count++;
        	}
        }
        return count;
    }
    
    /**
     * @problem #46 Permutations
     * @date 2018-02-28
     * 
     * Given a collection of distinct numbers, return all possible permutations.
     */
    public List<List<Integer>> C046_Permutations(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        C046_backtracking(res, temp, nums);
        return res;
    }
    
    public void C046_backtracking(List<List<Integer>> res, List<Integer> temp, int[] nums){
    	if(temp.size() == nums.length){
    		//temp��ֵ�Ƕ�̬�� ���newһ�� ��ʵ�ӵ�ʱ�������� ��󾭹�remove��߶��ǿ�Ԫ��
    		//new�ĺ����Ǵ���һ���¶��������浱ǰ״̬
    		res.add(new ArrayList<>(temp));
    	}
    	else{
    		for(int i = 0; i < nums.length; i++){
    			//ʡȥ��һЩ�鷳
    			//ͳһ�����ַ�ʽ�����
    			if(temp.contains(nums[i])) continue;
    			temp.add(nums[i]);
    			C046_backtracking(res, temp, nums);
    			//ǰ��add���Ƕ��� ����remove�ľ��Ƕ���
    			//��ʹ�м��л��ݲ��� �����ݻ������ ��ì��
    			//���������������е����� ��ŪŪ�ͺ���
    			temp.remove(temp.size() - 1);
    		}
    	}
    }
    
    /**
     * @problem #47 Permutations II
     * @date 2018-02-28
     * 
     * Given a collection of numbers that might contain duplicates, 
     * return all possible unique permutations.
     */
    public List<List<Integer>> C047_PermutationsII(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        C047_backtracking(res, temp, nums, used);
        return res;
    }
    
    public void C047_backtracking(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used){
    	if(temp.size() == nums.length){
    		res.add(new ArrayList<>(temp));
    	}
    	else{
    		for(int i = 0; i < nums.length; i++){
    			if(used[i] == true) continue;
    			if(i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
    			temp.add(nums[i]);
    			used[i] = true;
    			C047_backtracking(res, temp, nums, used);
    			temp.remove(temp.size() - 1);
    			used[i] = false;
    		}
    	}
    }
    
    /**
     * @problem #48 Rotate Image
     * @date 2018-03-01
     * 
     * you are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     */
    public void C048_RotateImage(int[][] matrix) {
        int l = matrix.length;
        for(int i = 0; i < l - 1; i++){
        	for(int j = i + 1; j < l; j++){
        		int temp = matrix[i][j];
        		matrix[i][j] = matrix[j][i];
        		matrix[j][i] = temp;
        	}
        }
        for(int i = 0; i < l; i++){
        	for(int j = 0; j < l / 2; j++){
        		int temp = matrix[i][j];
        		matrix[i][j] = matrix[i][l - j - 1];
        		matrix[i][l - j - 1] = temp;
        	}
        }
    }
    
    /**
     * @problem #49 Group Anagrams
     * @date 2018-03-02
     * 
     * Given an array of strings, group anagrams together.
     * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
     * Return:
     * [
     * 	["ate", "eat","tea"],
     * 	["nat","tan"],
     *  ["bat"]
     * ]
     * Note: All inputs will be in lower-case.
     */
    public List<List<String>> C049_GroupAnagrams(String[] strs) {
        if(strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> res = new HashMap<>();
        for(String s : strs){
        	char[] c = s.toCharArray();
        	Arrays.sort(c);
        	String key = String.valueOf(c);
        	if(!res.containsKey(key)){
        		res.put(key, new ArrayList<String>());
        	}
        	res.get(key).add(s);
        }
        return new ArrayList<>(res.values());
    }
    
    
    /**
     * @problem #50 Pow(x, n)
     * @date 2018-03-09
     * 
     * ��������
     * ֱ��ѭ���ų��ǲ��е� �϶�Ҫ�õݹ��˼��
     * ���⻹Ҫ���۰�
     * �ֳ�2��������Ϊ�˴���n������
     * ��ȻҲ����һ���ݹ����� �Ժ���ʱ�����Ż�
     */
    public double C050_Powxn(double x, int n) {
        if(n < 0) return 1 / C050_pow(x, -n);
        return C050_pow(x, n);
    }
    
    public double C050_pow(double x, int n){
    	if(n == 0) return 1;
    	double half = C050_pow(x, n / 2);
    	if(n % 2 == 0) return half * half;
    	return half * half * x;
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
	
	public List<String> subdomainVisits(String[] cpdomains) {
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < cpdomains.length; i++){
			int num = Integer.parseInt(cpdomains[i].split(" ")[0]);
			String domain = cpdomains[i].split(" ")[1];
			int index = 0;
			do{
				domain = index == 0 ? domain : domain.substring(index + 1, domain.length());
				if(!map.keySet().contains(domain)){
					map.put(domain, num);
				}
				else{
					map.put(domain, map.get(domain) + num);
				}
			}while((index = domain.indexOf(".")) > 0);
		}
		List<String> res = new ArrayList<>();
		for(String key : map.keySet()){
			String s = map.get(key) + " " + key;
			res.add(s);
		}
        return res;
    }
	
	
	@Test
	public void zzzz(){
	}
	//----------------------------------����------------------------------------------------
	//----------------------------------����------------------------------------------------
}
