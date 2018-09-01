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
	//根据中序遍历和后序遍历 构造二叉树
	public TreeNode C106_buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length != postorder.length) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
        	map.put(inorder[i], i);
        }
        return C106_traversal(0, inorder.length - 1, 0, postorder.length - 1, inorder, postorder, map);
    }
	
	//这种题肯定要用递归
	//递归的作用就是求出当前范围内的根节点以及它的左右节点
	//范围指的是inorder里边从is到ie postorder里边从ps到pe 二者指向的是同一颗(子)树
	public TreeNode C106_traversal(int is, int ie, int ps, int pe, int[] inorder, int[] postorder, Map<Integer, Integer> map){
		if(is > ie || ps > pe) return null;
		//当前范围内的跟节点一定是后序遍历数组的最后一个元素
		int root_val = postorder[pe];
		TreeNode root = new TreeNode(root_val);
		//找到它在中序遍历数组中的位置
		//这里 题目要求有 You may assume that duplicates do not exist in the tree
		//所以说i从0开始遍历  也没啥事 但理论上最好是is
		//因为是从inorder当前范围的最左边开始找的
		//至于index的值 随意 写成0看着舒服
		int index = 0;
		for(int i = is; i <= ie; i++){
			if(inorder[i] == root_val){
				index = i;
				break;
			}
		}
		//inorder里边 左子树和右子树是被index隔开的
		//postorder里边 左子树和右子树是连着的 所以分割点 需要根据inorder里边算出左子树的长度 进行适当的公式计算
		root.left = C106_traversal(is, index - 1, ps, ps + index - is - 1, inorder, postorder, map);
		root.right = C106_traversal(index + 1, ie, ps + index - is, pe - 1, inorder, postorder, map);
		return root;
	}
	
	//延申以下 中后写过了 下来写中先 先后貌似不行
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
	
	//延申打印3种遍历
	public void C106_Y2(){
		int[] inorder = {7, 3, 9, 8, 2};
		int[] postorder = {7, 9, 2, 8, 3};
		TreeNode root = C106_buildTree(inorder, postorder);
		System.out.println("先序遍历: ");
		C106_Y2_traversal_printPreOrder(root);
		System.out.println("中序遍历: ");
		C106_Y2_traversal_printInOrder(root);
		System.out.println("后序遍历: ");
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
	//不用考虑左旋右旋的问题
	//因为已经是从小到大排序 那么该List必然是bst的inorder
	//可以找到中间节点 节点左边的自然是左子树 右边自然是右子树
	//递归函数的目的 返回当前root 并让root的左右子树赋予递归
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
		//终止条件
		//想想哪些是可以判断出的 哪些是要递归才知道的
        if(root == null) return true;
        if(Math.abs(C110_getDepth(root.left) - C110_getDepth(root.right)) > 1) return false;
        return C110_isBalanced(root.left) && C110_isBalanced(root.right);
    }
	
	//计算每个节点的深度
	public int C110_getDepth(TreeNode root){
		//空节点的深度为0
		if(root == null) return 0;
		int left = C110_getDepth(root.left);
		int right = C110_getDepth(root.right);
		//节点深度当然比左右节点最大深度大1
		return Math.max(left, right) + 1;
	}
	
	private TreeNode prev = null;
	//为什么先flatten right?
	//因为左的右节点是右。。。 所以要先求右节点
	public void flatten(TreeNode root) {
		if(root == null) return;
		flatten(root.right);
		flatten(root.left);
		root.left = null;
		root.right = prev;
		prev = root;
    }
	//-----------------------------分割线--------------------------------
	//内部类
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
