package IST;

import java.util.ArrayList;
import java.util.List;

public class IST {

	public Node root;

	public static class Interval implements Comparable<Interval> {
		public int low;
		public int high;

		@Override
		public int compareTo(Interval that) {

			if (this.low < that.low)
				return 1;
			else if (this.low > that.low)
				return -1;
			else if (this.high < that.high)
				return 1;
			else if (this.high > that.high)
				return -1;
			else
				return 0;
		}

		public Interval(int low, int high) {
			this.low = low;
			this.high = high;
		}

		public Interval() {
			this.low = 0;
			this.high = 0;
		}

		public String toString() {
			return "interval={low=" + this.low + ", high=" + this.high + "}";
		}
		
		public boolean isOverLapInterval(Interval interval){
			
			if(!(this.high < interval.low || interval.high < this.low))
				return true;
			
			return false;
		}

	}

	public static class Node {
		public Interval interval;
		public int max;
		Node right;
		Node left;

		public Node(Interval interval, int max, Node right, Node left) {
			this.interval = interval;
			this.max = max;
			this.right = right;
			this.left = left;
		}

		public Node(Interval interval) {
			this.interval = interval;
			this.max = interval.high;
			this.right = null;
			this.left = null;
		}

		public String toString() {
			return "node={" + this.interval.toString() + ", max=" + this.max + "}\n";
		}

	}

	public static String printInorder(Node x){
		if (x == null)
			return "";
		
		return  printInorder(x.left) + x.toString() + printInorder(x.right);
		
	}

	public void insertInterval(Interval interval) {
		root = insertLeafNode(root, interval);
	}

	public Node insertLeafNode(Node x, Interval interval) {

		if (x == null)
			return new Node(interval);

		int cmp = x.interval.compareTo(interval);

		if (cmp < 0)
			x.left = insertLeafNode(x.left, interval);

		else if (cmp > 0)
			x.right = insertLeafNode(x.right, interval);

		else
			return x; // skip duplicate intervals

		// update the max of node
		if (interval.high > x.max)
			x.max = interval.high;

		return x;

	}
	
	public boolean search(Node x, Interval interval, List<Interval> result){
	
		boolean foundcurr = false;
		boolean foundleft = false;
		boolean foundright = false;
		
		if(x == null)
			return foundcurr;
		
		// if the current root is overlap add itself to result
		if(x.interval.isOverLapInterval(interval)){
			result.add(x.interval);
			foundcurr = true;
		}
		
		//decide to recurse left if the max of left child fall outside rage
		if(x.left != null && x.left.max >= interval.low){
			foundleft = search(x.left, interval, result);
		}
		
		// recurse right if one of the following
		// we did NOT recurse left
		// we recursed left AND did find something, if we did  NOT find anything, NO NEED TO RECURSE RIGHT (ONLY IF LEFT WAS RECURSED)
		if(foundleft || x.left == null || x.left.max < interval.low){
			System.out.print("I am recurssing right Interval now " +  x);
			foundright = search(x.right, interval, result);
		}
		
		return foundcurr || foundleft || foundright;
	}
	
	public List<Interval> searchAllOverlap(Interval interval){
		
		 List<Interval> result = new ArrayList<Interval>();
		
		 if(root == null)
			return result;
		
		 search(root,interval,result);
		
		 return result;
	}

	public static List<Interval> createIntervalList() {

		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(50, 100));
		intervals.add(new Interval(40, 60));
		intervals.add(new Interval(60, 70));
		intervals.add(new Interval(30, 80));
		intervals.add(new Interval(10, 20));
		intervals.add(new Interval(90, 110));
		intervals.add(new Interval(45, 90));
		intervals.add(new Interval(55, 80));

		return intervals;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Interval> intervals = createIntervalList();

		IST ist = new IST();
		for (Interval interval : intervals) {
			ist.insertInterval(interval);
		}

		System.out.println(printInorder(ist.root));
		
		List<Interval> result = ist.searchAllOverlap(new Interval(5,9));
		for(Interval interval : result){
			System.out.println(interval);
		}
	}

}
