package util;

/**
 * @target C138_CopyListWithRandomPointer
 * @author broccoli
 *
 */
public class RandomListNode {
	public int label;
	public RandomListNode next, random;
	public RandomListNode(int x) { 
		this.label = x; 
	}
}
