package Trie;

import java.util.ArrayList;
import java.util.List;

public class TrieRun {

	public static void main(String[] args) {
		
		Trie trie = new Trie();
		List<String> input = new ArrayList<String>();
		input.add("to");
		input.add("do");
		input.add("toll");
		input.add("car");
		input.add("told");
		input.add("cart");
		input.add("dot");
		
		
		System.out.println(trie.prefixPercentage("trcd" , input)); //0
		System.out.println(trie.prefixPercentage("todo" , input)); //4
		System.out.println(trie.prefixPercentage("tod" , input)); //2
		System.out.println(trie.prefixPercentage("dod" , input)); //2
		System.out.println(trie.prefixPercentage("tolld" , input)); //4
		System.out.println(trie.prefixPercentage("cardort" , input));//5
		System.out.println(trie.prefixPercentage("cartendotr" , input));//7
		System.out.println(trie.prefixPercentage("dcartd" , input)); //4
		

	}

}
