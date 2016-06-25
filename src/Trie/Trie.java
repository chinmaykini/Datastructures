package Trie;

import java.util.HashMap;

public class Trie {
	
	public TrieNode root;

	public TrieNode getRoot() {
		return root;
	}

	public void setRoot(TrieNode root) {
		this.root = root;
	}

	// Root node is '0'
	public Trie() {
		this.root = new TrieNode('o');
	}
	
	/*
	 * inserts a string into the Trie structure
	 */
	
	public void insert(String word){
		
		if(word==null) return;
		
		char[] charArr = word.toCharArray();
		
		TrieNode crawler = root;
		
		// for the size of the word
		for(int i = 0; i < charArr.length; i ++){
			
			Character currentChar = charArr[i];
			HashMap<Character, TrieNode> children = crawler.getChildren();
			
			if(children.containsKey(currentChar)){
				// character present
				crawler = children.get(currentChar);
				
			} else {
				// create a new TrieNode
				// add it as a child of current TrieNode
				// increment the crawler
				TrieNode newNode = new TrieNode(currentChar);
				children.put(currentChar, newNode);
				crawler = newNode;
			}
		} // end for
		
		// set that node as end of a word
		crawler.setEnd(true);
	}
	
	/*
	 * Traverses the Trie and returns true is the prefix is present in the Trie
	 */
	
	public boolean isValidPrefix( String prefix){
		
		if(prefix == null ) return false;
		
		TrieNode crawler = root;
		
		char[] prefixChar = prefix.toCharArray();
		
		for(int i = 0; i < prefixChar.length ; i++){
			
			Character currentChar = prefixChar[i];
			HashMap<Character, TrieNode> children = crawler.getChildren();
			
			if(children.containsKey(currentChar)){
				crawler = children.get(currentChar);
			} else{
				// current Word not in tree
				return false;
			}
		}
		// end of the for loop all chars were in the trie hence a valid prefix
		return true;
	}
	
	/*
	 * Traverses the trie and returns true if 
	 * the word is present in the tree
	 * the difference from prefix being ths isEnd flag
	 */
	
	public boolean isValidWord( String word){
		
		if(word == null ) return false;
		
		TrieNode crawler = root;
		
		char[] wordCharArr = word.toCharArray();
		
		for(int i = 0; i < wordCharArr.length ; i++){
			
			Character currentChar = wordCharArr[i];
			HashMap<Character, TrieNode> children = crawler.getChildren();
			
			if(children.containsKey(currentChar)){
				crawler = children.get(currentChar);
			} else{
				// current Word not in tree
				return false;
			}
		}
		// end of the for loop all chars were in the trie hence a valid prefix
		// to check if its a word check if is end
		if(crawler.isEnd())
			return true;
		
		return false;
	}

}
