package Misc;
import java.util.PriorityQueue;

public class KthSmallest {
	
	public static void main(String args[]){
		int[] arr={15,11,10,9,8,7,6};
		System.out.println(kthLargest(arr,7));
		System.out.println(kthSmallest(arr,0,arr.length-1,1));
		quickSort( arr,0,arr.length-1);
		printArray(arr);
		
	}

	private static void printArray(int arr[]){
		for( int i=0; i <arr.length;i++)
			System.out.print(arr[i]+" ");
	}
	private static int kthLargest(int[] arr, int k) {
		if( k<=0 || k > arr.length){
			return  -1;
		}
		PriorityQueue<Integer> smallest = new PriorityQueue<Integer>();
		for( int i=0;i< arr.length;i++){
			if( smallest.size() <k){
				smallest.offer(arr[i]);
			}else{
				if( smallest.peek() < arr[i]){
					smallest.poll();
					smallest.offer(arr[i]);
				}
			}
		}
		return smallest.peek();
	}
	
	private static int kthSmallest(int arr[],int low,int high,int k){
		if( k<=0 || k > arr.length){
			return  -1;
		}
		int pivotIndex=partition(arr,low,high);
		if( pivotIndex == k-1)
			return arr[k-1];
		if( pivotIndex < k-1 ){
			return kthSmallest( arr,pivotIndex+1,high,k);
		}else{
			return kthSmallest( arr,low,pivotIndex-1,k);
		}
	}
	private static void quickSort( int arr[] ,int low,int high){
		if( low < high){
			int pivot=partition(arr,low,high);
			quickSort(arr,low,pivot-1 );
			quickSort(arr,pivot+1,high );
		}
	}
	
	private static int partition( int arr[] , int low,int high){
		int pivot=arr[low];
		int lessPivot=low+1;
		for( int i=low+1;i <=high ;i++){
			if (lessPivot<=high && arr[i] <=pivot){
				int temp=arr[lessPivot];
				arr[lessPivot]=arr[i];
				arr[i]=temp;
				
				lessPivot++;
			}else{
				
			}
		}
		arr[low]=arr[lessPivot-1];
		arr[lessPivot-1]=pivot;
		return lessPivot-1;
	}

}
