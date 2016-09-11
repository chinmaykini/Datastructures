package BST;

import java.util.ArrayList;


public interface TreeFunctions {
	
	public String BFS(TreeNode root);
	
	public String DFS(TreeNode root);

	String inorder(TreeNode node);
	
	ArrayList<ArrayList<Integer>> levelOrderTraversal(TreeNode node);
	
	public TreeNode rotateUpsideDown(TreeNode root);
	
	public TreeNode trimBST(TreeNode root, int min, int max);
	
	public TreeNode BstToSingleLinkedList(TreeNode root);
	
	public TreeNode BstToDoubleLinkedList(TreeNode root);
	
	public TreeNode BstToDoubleCircularLinkedList(TreeNode root);

	TreeNode deleteLeaves(TreeNode root);

	int getTreeHeight(TreeNode root);

	void traverseSpiralRecursive(TreeNode root);

	TreeNode lowestCommmonAncestor(TreeNode root, int p, int q);

	TreeNode treeRightRotate(TreeNode root);

	int sizeTree(TreeNode root);

	void traverseSpiralNonRecursive(TreeNode root);

	void printNodesInRange(TreeNode root, int min, int max);
	
	TreeNode rotateUpsideDownV2(TreeNode root);
	
	TreeNode rotateUpsideDownV3(TreeNode root);
	
}
