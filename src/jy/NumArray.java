package jy;

/**
 * @problem #307 Range Sum Query - Mutable
 * @date 2017-12-15
 * 
 * Given an integer array nums, 
 * find the sum of the elements between indices i and j (i �� j), 
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
	//����������ĺ��ľ��������������ʱ����һ��һ��Ԫ��ȥ���ӷ�
	//���Ǿ��������ڹ���ʱ�������������Ȩֵ���ͣ����ӷ�
	class SegmentTreeNode{
		int sum = 0;
		int start = 0;
		int end = 0;
		SegmentTreeNode left = null;
		SegmentTreeNode right = null;
		//һ���ڵ�����䷶Χ���ڴ���ʱ���е�
		//Ȩֵsumʱͨ���ݹ��������� ���Բ���ʼ��
		//ͬ�� ���Ӻ��Һ���Ҳ��ͨ���ݹ�������� ���ǵ�Ҷ�ӽڵ�Ĭ��Ϊ��
		public SegmentTreeNode(int start, int end){
			this.start = start;
			this.end = end;
		}
	}
	//ȫ�ֱ���root �������������ĸ��ڵ�
	public SegmentTreeNode root = null;
	
	public SegmentTreeNode buildSegmentTree(int[] nums, int start, int end){
		//�����㷽ʽ��˵ ��������������
		//���ܳ��ֵ��������һ��ʼ�Ĳ�������
		if(start > end) return null;
		else{
			SegmentTreeNode root = new SegmentTreeNode(start, end);
			//�ֵľ�ͷ���ǵ�Ҷ�ӽڵ� ���䳤��Ϊ1 ����ֻ��һ����
			if(start == end) root.sum = nums[start];
			else{
				int mid = start + (end - start) / 2;
				root.left = buildSegmentTree(nums, start, mid);
				root.right = buildSegmentTree(nums, mid + 1, end);
				//���������б�������������� ��Ȼ���������������û��������
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
		//��ĳһ����ǴӸ��ڵ㿪ʼ�ҵ�
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