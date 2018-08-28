package jy;

import java.util.ArrayList;
import java.util.List;

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
	public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
	
	
	
	
	
	//-----------------------------�ָ���--------------------------------
	//�ڲ���
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
