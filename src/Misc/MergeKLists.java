package Misc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKLists {

	static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x) { val = x; next = null; }
	}

	public ListNode mergeKLists(ArrayList<ListNode> A) {
		Comparator<ListNode> comp = new Comparator<ListNode>() {
			@Override
			public int compare(ListNode n1, ListNode n2) {
				if( n1.val < n2.val){
					return -1;
				}else{
					if( n1.val > n2.val)
						return 1;
					else
						return 0;
				}

			}
		};
		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(comp);

		ListNode newHead = null;
		ListNode currIter=newHead;
		for(ListNode currNode : A){
			if( currNode != null){
				queue.offer( currNode);
			}
		}
		while(!queue.isEmpty()){
			ListNode removedNode = queue.poll();
			if( newHead == null){
				newHead = removedNode;
				currIter = newHead;
			}else{
				currIter.next= removedNode;
				currIter=removedNode;
			}
			if( removedNode.next != null){
				queue.offer( removedNode.next);
			}
		}
		return newHead;
	}
}
