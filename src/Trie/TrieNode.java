package Trie;

import java.util.HashMap;

public class TrieNode {
	
	public Character value;
	public HashMap<Character, TrieNode> children;
	public boolean isEnd;
	
	
	public Character getValue() {
		return value;
	}
	public void setValue(Character value) {
		this.value = value;
	}
	public HashMap<Character, TrieNode> getChildren() {
		return children;
	}
	public void setChildren(HashMap<Character, TrieNode> children) {
		this.children = children;
	}

	public boolean isEnd() {
		return isEnd;
	}
	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
	public TrieNode(Character value) {
		this.value = value;
		this.children = new HashMap<Character, TrieNode>();
		this.isEnd = false;
	}
	public TrieNode() {

	}
	
	

}
