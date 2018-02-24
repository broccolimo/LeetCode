package jy;

/**
 * @problem #307 Range Sum Query - Mutable
 * @date 2017-12-15
 * 
 * Given an integer array nums, 
 * find the sum of the elements between indices i and j (i ≤ j), 
 * inclusive.
 * The update(i, val) function modifies nums by updating 
 * the element at index i to val.
 * Example:
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange 
 * function is distributed evenly.
 */
public class NumArray {
	//区间二叉树的核心就是在做区间求和时不用一个一个元素去做加法
	//而是尽可能用在构建时就算出来的区间权值（和）做加法
	class SegmentTreeNode{
		int sum = 0;
		int start = 0;
		int end = 0;
		SegmentTreeNode left = null;
		SegmentTreeNode right = null;
		//一个节点的区间范围是在创建时就有的
		//权值sum时通过递归求解得来的 所以不初始化
		//同样 左孩子和右孩子也是通过递归求出来的 考虑到叶子节点默认为空
		public SegmentTreeNode(int start, int end){
			this.start = start;
			this.end = end;
		}
	}
	//全局变量root 仅仅是整个树的根节点
	public SegmentTreeNode root = null;
	
	public SegmentTreeNode buildSegmentTree(int[] nums, int start, int end){
		//按计算方式来说 不会出现这种情况
		//可能出现的情况就是一开始的参数配置
		if(start > end) return null;
		else{
			SegmentTreeNode root = new SegmentTreeNode(start, end);
			//分的尽头就是到叶子节点 区间长度为1 就是只有一个数
			if(start == end) root.sum = nums[start];
			else{
				int mid = start + (end - start) / 2;
				root.left = buildSegmentTree(nums, start, mid);
				root.right = buildSegmentTree(nums, mid + 1, end);
				//构建过程中必须把区间和求出来 不然构建区间二叉树就没有意义了
				root.sum = root.left.sum + root.right.sum;
			}
			return root;
		}
	}
	public NumArray(int[] nums) {
		root = buildSegmentTree(nums, 0, nums.length - 1);
	}

	public void update(int i, int val) {
		update(root, i, val);
	}
	
	public void update(SegmentTreeNode root, int i, int val){
		//找某一结点是从根节点开始找的
		if(root.start == root.end){
			root.sum = val;
		}
		else{
			int mid = root.start + (root.end - root.start) / 2;
			if(i <= mid){
				update(root.left, i, val);
			}
			else{
				update(root.right, i, val);
			}
			root.sum = root.left.sum + root.right.sum;
		}
	}

	public int sumRange(int i, int j) {
		return sumRange(root, i, j);
	}
	
	public int sumRange(SegmentTreeNode root, int i, int j){
		if(root.start == i && root.end == j) return root.sum;
		else{
			int mid = root.start + (root.end - root.start) / 2;
			if(j <= mid){
				return sumRange(root.left, i, j);
			}
			else if(i > mid){
				return sumRange(root.right, i, j);
			}
			else{
				return sumRange(root.left, i, mid) + sumRange(root.right, mid + 1, j);
			}
		}
	}
}