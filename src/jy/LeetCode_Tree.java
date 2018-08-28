package jy;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_Tree {
	/**
	 * @problem #94 Binary Tree Inorder Traversal
     * @date 2018-08-28
	 */
	//二叉树的中序遍历打印 用递归即可
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
	 * 有1, 2, ..., n  n个数
	 * 求有多少个不同的BST(binary search tree, 特征:左节点值小于根节点值 右节点值大于根节点值 null不比较且不影响)
	 * 这个题用别人的方法即可 挺好的
	 * G[n]代表有n个数时对应的bst数目
	 * 假设F(i, n)代表的是有n个数 以i为根节点的bst数目
	 * 那么G[n]=F[1,n]+F[2,n]+...+F[n,n]
	 * F(i, n)左子树上有i-1个数 那么就有G[i-1]种情况(数的结构一律遵循从小到大依次加一)
	 * 右子树上有n-i个数 那么既有G[n-i]种情况
	 * 所以F(i, n)=G[i-1]*G[n-i]
	 * 综上所述
	 * 可用以下方式求出G[n] 不要考虑数学上的公式化简
	 * 由于G[n]的值依赖于G[n-1] 所以需要用数组记录n之前每一个数量值对应的bst数目
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
	 * 验证是否为bst
	 * 一开始对bst有点误解
	 * 1 只是左节点值小于根节点值 而不是左子树上所有节点的值都小于根节点值
	 * 2 不管是左节点还是右节点 都不能出现等于
	 * 另外我们要设置可能的最小值和最大值
	 * 由于参数是int类型 而且不能出现等于
	 * 所以不能用int的min/max 而应该用long的min/max
	 * 以下程序就是妥当的递归逻辑
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
	 * 判断2棵二叉树是否一样
	 * 前边2道题做过之后比较熟练了 直接AC了 不解释了
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
	//不解释了 直接AC
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
	 * 求一个二叉树的最大深度
	 * 看别人的答案 有点厉害 多加理解吧 不解释了
	 */
	public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
	
	
	
	
	
	//-----------------------------分割线--------------------------------
	//内部类
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
