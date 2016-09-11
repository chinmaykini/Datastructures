package BST;

public class TreeRunner {
	public static void main(String args[]){

		TreeNode n6= new TreeNode ( 6,null,null);
		TreeNode n7= new TreeNode ( 7,null,null);
		TreeNode n4= new TreeNode ( 4,n6,n7);
		TreeNode n5= new TreeNode ( 5,null,null);
		TreeNode n2= new TreeNode ( 2,n4,n5);
		TreeNode n3= new TreeNode ( 3,null,null);
		TreeNode n1= new TreeNode ( 1,n2,n3);
		
		TreeFunctions fn = new TreeFunctionsImpl();
		//System.out.println(fn.levelOrderTraversal(n1));
		
		//System.out.println(fn.inorder(n1));
		
		TreeNode newRoot =fn.rotateUpsideDownV2(n1);
	
		
		
		System.out.println(fn.levelOrderTraversal(newRoot));
		
	}

}
