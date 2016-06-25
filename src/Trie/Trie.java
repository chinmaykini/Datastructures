package Trie;

import java.util.HashMap;
import java.util.List;

public class Trie {
	
	public TrieNode root;

	public TrieNode getRoot() {
		return root;
	}

	public void setRoot(TrieNode root) {
		this.root = root;
	}

	// Root node is '/'
	public Trie() {
		this.root = new TrieNode('/');
	}
	
	/*
	 * batch insert words
	 */
	public void batchInsert(List<String> inputList){
		if(inputList == null){
			return;
		}
		
		for(String element : inputList){
			insert(element);
		}
		
		return;
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
	
	
	/*
	 *  return percentage match workd from a five string
	 *   ("todo", ["to","cat","do"]) => 100%
	 *   ("dto", ["to","cat","do"]) => 66%
	 *   ("rtoldedo", ["to","told","dot"]) => 4/8*100
	 */
			
	public int prefixPercentage(String word, List<String> glossary){
		
		int matchSize = 0;
		
		if(word == null || glossary == null){
			return matchSize;
		}
		
		//add glossary to trie
		batchInsert(glossary);
		
		// process one letter at a time, skip ahead if there is a word match 
		char[] wordCharArray = word.toCharArray();
		for(int i = 0; i < wordCharArray.length; i++){
			
			int maxSizeMatch = getMaxSizeMatch(i,wordCharArray);
			
			// if its not equal to current index there was some match
			if(maxSizeMatch > i){
				matchSize += maxSizeMatch - i + 1;
				i = maxSizeMatch;
			}
		}
		
		// getting percentage from 2 ints, need to add .0f to 100
		return (int)(matchSize * 100.0f)/word.length();
	}
	
	/*
	 * return the next index to which the search can begin in the trie
	 */

	private int getMaxSizeMatch(int startIndex, char[] wordCharArray) {
		
		// 5 cases
		// 1) given index doesnt match the trie - return startIndex
		// 2) there is prefix match but not a word - return startIndex
		// 3) there is a word match - return wordMatchIndex
		// 4) a word and more some prefix match - return wordMatchIndex, last matched
		// 5) double word match - return wordMatchIndex, updated multiple times
		
		if(startIndex > wordCharArray.length-1){
			return startIndex;
		}
		
		int wordMatchIndex = startIndex;
		int currentIndex = startIndex;
		TrieNode crawler = this.root;
		while(currentIndex < wordCharArray.length){
			
			HashMap<Character, TrieNode> children = crawler.getChildren();
			if(children.containsKey(wordCharArray[currentIndex])){
				
				crawler = children.get(wordCharArray[currentIndex]);
				
				// if its a wordmatch update wordMatchIndex
				if(crawler.isEnd()){
					wordMatchIndex = currentIndex;
				}
				
				// proceed further for max match
				currentIndex++;
			
			}else {
				// there is no match 
				break;
			}		
		}
		
		// - there was a no match OR we have reached end of string
		if(currentIndex == wordCharArray.length){
			
			// check last node for match
			if(crawler.isEnd()){
				
				// return the index of the last element (size-1)
				wordMatchIndex = currentIndex-1;
			}
		} else{
			// the current index didnt exists and return last known match
		}
		
		return wordMatchIndex;
	}

}
