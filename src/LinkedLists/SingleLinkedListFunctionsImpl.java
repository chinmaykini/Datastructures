package LinkedLists;

import java.util.Collections;
import java.util.PriorityQueue;


public class SingleLinkedListFunctionsImpl implements SingleLinkedListFunctions {

	@Override
	public String traversal(SingleListNode head) {

		StringBuilder result = new StringBuilder();
		
		if( head == null) return "";
		SingleListNode tmp = head;

		while(tmp != null){
			result.append(tmp.data.toString()).append(",");
			tmp = tmp.next;
		}
		return result.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ck.list.single.service.SingleLinkedListFunctions#topK(com.ck.list.single.model.SingleListNode, int)
	 *  Max k elements using min heap
	 */
	@Override
	public String topK(SingleListNode head, int k) {
		
		StringBuilder result = new StringBuilder();
		
		PriorityQueue<SingleListNode> minHeap = new PriorityQueue<SingleListNode>(k);
		if(head == null) return "";
		
		SingleListNode runner = head;
		int count = k;
		
		while(runner!=null && count > 0){
			minHeap.add(runner);
			runner = runner.next;
			count--;
		}
		
		if(count == 0){
			while(runner!=null){
				SingleListNode min = minHeap.peek();
				if(runner.data > min.data){
					minHeap.poll();
					minHeap.add(runner);
					
				}
				runner = runner.next;
			}
			
		}
		int len = minHeap.size();
		for(int i = 0; i<len; i ++){
			result.append(minHeap.poll().data.toString()).append(",");
		}
		
		return( result.toString());
	}

	/*
	 * (non-Javadoc)
	 * @see com.ck.list.single.service.SingleLinkedListFunctions#minK(com.ck.list.single.model.SingleListNode, int)
	 * Min K elements using maxHeap
	 */
	@Override
	public String minK(SingleListNode head, int k) {
		StringBuilder result = new StringBuilder();
		
		PriorityQueue<SingleListNode> maxHeap = new PriorityQueue<SingleListNode>(k, Collections.reverseOrder());
		if(head == null) return "";
		
		SingleListNode runner = head;
		int count = k;
		
		while(runner!=null && count > 0){
			maxHeap.add(runner);
			runner = runner.next;
			count--;
		}
		
		if(count == 0){
			while(runner!=null){
				SingleListNode max = maxHeap.peek();
				if(runner.data < max.data){
					maxHeap.poll();
					maxHeap.add(runner);
					
				}
				runner = runner.next;
			}
			
		}
		int len = maxHeap.size();
		for(int i = 0; i<len; i ++){
			result.append(maxHeap.poll().data.toString()).append(",");
		}
		
		return( result.toString());
	}

	/*
	 * (non-Javadoc)
	 * @see com.ck.list.single.service.SingleLinkedListFunctions#reverseTraversal(com.ck.list.single.model.SingleListNode)
	 * Reverse traversal without modifying Linked List, rescurssive
	 */
	@Override
	public String reverseTraversal(SingleListNode head) {

		if(head == null) return "";
		return  reverseTraversalhelper(head, "");

	}

	private String reverseTraversalhelper(SingleListNode p, String result) {
		if(p == null) return "";

		if(p!=null){
			 result += reverseTraversalhelper(p.next, result) + "," + p.data.toString();
		}
		return result;
	}

}
