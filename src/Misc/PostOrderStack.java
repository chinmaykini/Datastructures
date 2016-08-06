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
		TreeNode pre=root;
		TreeNode lastPopped = null;

		if( pre != null){
			st.push(pre);
		}
		if( pre.right != null){
			st.push(pre.right);
		}
		if( pre.left != null){
			st.push(pre.left);
		}

		while(  ! st.isEmpty() ){
			TreeNode curr = st.pop();
			if( lastPopped != null
					&& ( lastPopped == curr.right ||
					lastPopped == curr.left)
					|| (curr.left == null && curr.right == null)
					){
				res.add(curr.val);
			}else{
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
			lastPopped=curr;
		}
		return res;
	}
}
