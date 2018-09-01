package jy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.TreeNode;

import org.junit.Test;

public class LeetCode_Tree {
	/**
	 * @problem #94 Binary Tree Inorder Traversal
     * @date 2018-08-28
	 */
	//�����������������ӡ �õݹ鼴��
	public List<Integer> C094_BinaryTreeInorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		C094_traversal(res, root);
        return res;
    }
	
	public void C094_traversal(List<Integer> res, TreeNode node){
		if(node == null) return;
		if(node.left != null) C094_traversal(res, node.left);
		res.add(node.val);
		if(node.right != null) C094_traversal(res, node.right);
	}
	
	
	/**
	 * @problem #96 Unique Binary Search Trees
     * @date 2018-08-28
	 */
	/**
	 * ��1, 2, ..., n  n����
	 * ���ж��ٸ���ͬ��BST(binary search tree, ����:��ڵ�ֵС�ڸ��ڵ�ֵ �ҽڵ�ֵ���ڸ��ڵ�ֵ null���Ƚ��Ҳ�Ӱ��)
	 * ������ñ��˵ķ������� ͦ�õ�
	 * G[n]������n����ʱ��Ӧ��bst��Ŀ
	 * ����F(i, n)���������n���� ��iΪ���ڵ��bst��Ŀ
	 * ��ôG[n]=F[1,n]+F[2,n]+...+F[n,n]
	 * F(i, n)����������i-1���� ��ô����G[i-1]�����(���Ľṹһ����ѭ��С�������μ�һ)
	 * ����������n-i���� ��ô����G[n-i]�����
	 * ����F(i, n)=G[i-1]*G[n-i]
	 * ��������
	 * �������·�ʽ���G[n] ��Ҫ������ѧ�ϵĹ�ʽ����
	 * ����G[n]��ֵ������G[n-1] ������Ҫ�������¼n֮ǰÿһ������ֵ��Ӧ��bst��Ŀ
	 */
	public int C096_UniqueBinarySearchTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = G[1] = 1;
        for(int i = 2; i <= n; i++){
        	for(int j = 1; j <= i; j++){
        		G[i] += G[j - 1] * G[i - j];
        	}
        }
        return G[n];
    }
	
	
	
	/**
	 * @problem #98 Validate Binary Search Tree
	 * @date 2018-08-28
	 */
	/**
	 * ��֤�Ƿ�Ϊbst
	 * һ��ʼ��bst�е����
	 * 1 ֻ����ڵ�ֵС�ڸ��ڵ�ֵ �����������������нڵ��ֵ��С�ڸ��ڵ�ֵ
	 * 2 ��������ڵ㻹���ҽڵ� �����ܳ��ֵ���
	 * ��������Ҫ���ÿ��ܵ���Сֵ�����ֵ
	 * ���ڲ�����int���� ���Ҳ��ܳ��ֵ���
	 * ���Բ�����int��min/max ��Ӧ����long��min/max
	 * ���³�������׵��ĵݹ��߼�
	 */
	public boolean C098_ValidateBinarySearchTree(TreeNode root) {
        return C098_traversal(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
	
	public boolean C098_traversal(TreeNode root, long min, long max){
		if(root == null) return true;
		if(root.val >= max || root.val <= min) return false;
		return C098_traversal(root.left, min, root.val) && C098_traversal(root.right, root.val, max);
	}
	
	/**
	 * @problem #100 Same Tree
	 * @date 2018-08-28
	 */
	/**
	 * �ж�2�ö������Ƿ�һ��
	 * ǰ��2��������֮��Ƚ������� ֱ��AC�� ��������
	 */
	public boolean C100_isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null) return true;
		if(p == null && q != null || p != null && q == null || p.val != q.val) return false;
		return C100_isSameTree(p.left, q.left) && C100_isSameTree(p.right, q.right);
    }
	
	
	/**
	 * @problem #101 Symmetric Tree
	 * @date 2018-08-28
	 */
	//�������� ֱ��AC
	public boolean C101_isSymmetric(TreeNode root) {
		if(root == null) return true;
        return C101_traversal(root.left, root.right);
    }
	
	public boolean C101_traversal(TreeNode n1, TreeNode n2){
		if(n1 == null && n2 == null) return true;
		if(n1 == null && n2 != null || n1 != null && n2 == null || n1.val != n2.val) return false;
		return C101_traversal(n1.left, n2.right) && C101_traversal(n1.right, n2.left);
	}
	
	/**
	 * @problem #104 Maximum Depth of Binary Tree
	 * @date 2018-08-28
	 */
	/**
	 * ��һ����������������
	 * �����˵Ĵ� �е����� ������� ��������
	 */
	public int C104_maxDepth(TreeNode root) {
        if(root == null) return 0;
        int left = C104_maxDepth(root.left);
        int right = C104_maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
	
	/**
	 * @problem #106 Construct Binary Tree from Inorder and Postorder Traversal
	 * @date 2018-08-29
	 */
	//������������ͺ������ ���������
	public TreeNode C106_buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length != postorder.length) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
        	map.put(inorder[i], i);
        }
        return C106_traversal(0, inorder.length - 1, 0, postorder.length - 1, inorder, postorder, map);
    }
	
	//������϶�Ҫ�õݹ�
	//�ݹ�����þ��������ǰ��Χ�ڵĸ��ڵ��Լ��������ҽڵ�
	//��Χָ����inorder��ߴ�is��ie postorder��ߴ�ps��pe ����ָ�����ͬһ��(��)��
	public TreeNode C106_traversal(int is, int ie, int ps, int pe, int[] inorder, int[] postorder, Map<Integer, Integer> map){
		if(is > ie || ps > pe) return null;
		//��ǰ��Χ�ڵĸ��ڵ�һ���Ǻ��������������һ��Ԫ��
		int root_val = postorder[pe];
		TreeNode root = new TreeNode(root_val);
		//�ҵ�����������������е�λ��
		//���� ��ĿҪ���� You may assume that duplicates do not exist in the tree
		//����˵i��0��ʼ����  Ҳûɶ�� �������������is
		//��Ϊ�Ǵ�inorder��ǰ��Χ������߿�ʼ�ҵ�
		//����index��ֵ ���� д��0�������
		int index = 0;
		for(int i = is; i <= ie; i++){
			if(inorder[i] == root_val){
				index = i;
				break;
			}
		}
		//inorder��� ���������������Ǳ�index������
		//postorder��� �������������������ŵ� ���Էָ�� ��Ҫ����inorder�������������ĳ��� �����ʵ��Ĺ�ʽ����
		root.left = C106_traversal(is, index - 1, ps, ps + index - is - 1, inorder, postorder, map);
		root.right = C106_traversal(index + 1, ie, ps + index - is, pe - 1, inorder, postorder, map);
		return root;
	}
	
	//�������� �к�д���� ����д���� �Ⱥ�ò�Ʋ���
	public TreeNode C106_Y1(int[] preorder, int[] inorder){
		if(preorder == null || inorder == null || preorder.length != inorder.length) return null;
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < inorder.length; i++){
			map.put(inorder[i], i);
		}
		return C106_Y1_traversal(0, preorder.length - 1, 0, inorder.length - 1, preorder, inorder, map);
	}
	
	public TreeNode C106_Y1_traversal(int ps, int pe, int is, int ie, int[] preorder, int[] inorder, Map<Integer, Integer> map){
		if(ps > pe || is > ie) return null;
		int root_val = preorder[ps];
		TreeNode root = new TreeNode(root_val);
		int index = 0;
		for(int i = is; i <= ie; i++){
			if(inorder[i] == root_val){
				index = i;
				break;
			}
		}
		root.left = C106_Y1_traversal(ps + 1, ps + index - is , is, index - 1, preorder, inorder, map);
		root.right = C106_Y1_traversal(ps + index - is + 1, pe, index + 1, ie, preorder, inorder, map);
		return root;
	}
	
	//�����ӡ3�ֱ���
	public void C106_Y2(){
		int[] inorder = {7, 3, 9, 8, 2};
		int[] postorder = {7, 9, 2, 8, 3};
		TreeNode root = C106_buildTree(inorder, postorder);
		System.out.println("�������: ");
		C106_Y2_traversal_printPreOrder(root);
		System.out.println("�������: ");
		C106_Y2_traversal_printInOrder(root);
		System.out.println("�������: ");
		C106_Y2_traversal_printPostOrder(root);
	}
	
	public void C106_Y2_traversal_printPreOrder(TreeNode node){
		if(node == null) return;
		System.out.println(node.val);
		C106_Y2_traversal_printPreOrder(node.left);
		C106_Y2_traversal_printPreOrder(node.right);
	}
	
	public void C106_Y2_traversal_printInOrder(TreeNode node){
		if(node == null) return;
		C106_Y2_traversal_printInOrder(node.left);
		System.out.println(node.val);
		C106_Y2_traversal_printInOrder(node.right);
	}
	
	public void C106_Y2_traversal_printPostOrder(TreeNode node){
		if(node == null) return;
		C106_Y2_traversal_printPostOrder(node.left);
		C106_Y2_traversal_printPostOrder(node.right);
		System.out.println(node.val);
	}
	
	/**
	 * @problem #109 Convert Sorted List to Binary Search Tree
	 * @date 2018-08-30
	 */
	//���ÿ�����������������
	//��Ϊ�Ѿ��Ǵ�С�������� ��ô��List��Ȼ��bst��inorder
	//�����ҵ��м�ڵ� �ڵ���ߵ���Ȼ�������� �ұ���Ȼ��������
	//�ݹ麯����Ŀ�� ���ص�ǰroot ����root��������������ݹ�
	public TreeNode C109_sortedListToBST(ListNode head) {
		if(head == null) return null;
		if(head.next == null) return new TreeNode(head.val);
		ListNode slow = head;
		ListNode fast = head;
		ListNode prev = null;
		while(fast != null && fast.next != null){
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null;
		TreeNode root = new TreeNode(slow.val);
		root.left = C109_sortedListToBST(head);
		root.right = C109_sortedListToBST(slow.next);
		return root;
    }

	/**
	 * @problem #110 Balanced Binary Tree
	 * @date 2018-09-01
	 */
	public boolean C110_isBalanced(TreeNode root) {
		//��ֹ����
		//������Щ�ǿ����жϳ��� ��Щ��Ҫ�ݹ��֪����
        if(root == null) return true;
        if(Math.abs(C110_getDepth(root.left) - C110_getDepth(root.right)) > 1) return false;
        return C110_isBalanced(root.left) && C110_isBalanced(root.right);
    }
	
	//����ÿ���ڵ�����
	public int C110_getDepth(TreeNode root){
		//�սڵ�����Ϊ0
		if(root == null) return 0;
		int left = C110_getDepth(root.left);
		int right = C110_getDepth(root.right);
		//�ڵ���ȵ�Ȼ�����ҽڵ������ȴ�1
		return Math.max(left, right) + 1;
	}
	
	private TreeNode prev = null;
	//Ϊʲô��flatten right?
	//��Ϊ����ҽڵ����ҡ����� ����Ҫ�����ҽڵ�
	public void flatten(TreeNode root) {
		if(root == null) return;
		flatten(root.right);
		flatten(root.left);
		root.left = null;
		root.right = prev;
		prev = root;
    }
	//-----------------------------�ָ���--------------------------------
	//�ڲ���
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
}
