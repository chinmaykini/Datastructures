import java.util.ArrayList;
import java.util.Stack;

public class PostOrderStack {
	static  class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode lastPopped = null;
		pushTriplet(root,st);
		while(  ! st.isEmpty() ){
			TreeNode curr = st.pop();
			if( lastPopped != null 
				&& isChild( curr,lastPopped)
				|| isLeaf(curr)){
				res.add(curr.val);
			}else{
				pushTriplet(curr,st);
			}
			lastPopped=curr;
		}
		return res;
	}
	void pushTriplet(TreeNode curr,Stack<TreeNode> st){
		if( curr != null){
			st.push(curr);
		}
		if( curr.right != null){
			st.push(curr.right);
		}
		if( curr.left != null){
			st.push(curr.left);
		}
	}
	boolean isChild(TreeNode curr,TreeNode child){
		return (curr.left == child ||  curr.right == child);
	}
	
	boolean isLeaf(TreeNode curr){
		return (curr.left == null && curr.right == null);
	}
}
