package Misc;

import java.util.PriorityQueue;

public class MaxX {
	
	public static void main(String args[]){
		int[] arr= {1,2,3,4,5};
		int x=maxx(arr);
		System.out.println("Max "+x+" elements greateer than "+x);
	}

	private static int maxx(int[] arr) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		int lastIndex=1;
		int max=0;
		for( int i=0; i < arr.length ; i++){
			if( arr[i] >= lastIndex ){
				int toBeRemovedVal= 0;
				while( ! queue.isEmpty() && queue.peek() < lastIndex){
					queue.poll();
					toBeRemovedVal++;
				}

				queue.add(arr[i]);
				if( max < queue.size()){
					max=queue.size();
				}
				lastIndex=lastIndex-toBeRemovedVal;
				lastIndex++;
			}
		}
		return max;
	}
}

