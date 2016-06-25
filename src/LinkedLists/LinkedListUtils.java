package LinkedLists;

import BST.*;;

public class LinkedListUtils {
	
	public static String circularLinkedListTraversal(TreeNode head){
		
		if(head== null ) return "";
		
		TreeNode start = head;
		StringBuilder result = new StringBuilder();
		result.append(start.data).append(",");
		start = start.right;
		while(head!=start){
			result.append(start.data).append(",");
			start = start.right;
		}
		return result.toString();
	}

}
