package Trie;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TrieTest {
	public static Trie trie; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		trie = new Trie();
	}
	
	@Test
	public void testbatchInsert(){
		
		//batch insert
		List<String> input = new ArrayList<String>();
		input.add("to");
		input.add("do");
		input.add("toll");
		input.add("car");
		input.add("told");
		input.add("cart");
		input.add("dot");
		input.add("apple");
		input.add("length");
		
		trie.batchInsert(input);
		
		assertTrue(trie.isValidWord("toll"));
		assertTrue(trie.isValidWord("do"));
		assertTrue(trie.isValidWord("dot"));
		assertTrue(trie.isValidWord("cart"));
		assertTrue(trie.isValidWord("car"));
		assertTrue(trie.isValidPrefix("car"));
		assertTrue(trie.isValidPrefix("tol"));
		assertFalse(trie.isValidPrefix("dor"));
		assertFalse(trie.isValidWord("tol"));
	}

	@Test
	public void testisValidPrefix() {
		
		// insert
		trie.insert("car");
		trie.insert("cat");
		trie.insert("cart");
		trie.insert("a");
		trie.insert("apple");
				
				
		assertTrue(trie.isValidPrefix(""));
		assertTrue(trie.isValidPrefix("a"));
		assertTrue(trie.isValidPrefix("ca"));
		assertTrue(trie.isValidPrefix("car"));
		assertTrue(trie.isValidPrefix("cart"));
		assertFalse(trie.isValidPrefix("0"));
		assertTrue(trie.isValidPrefix("t"));
	}
	
	@Test
	public void testisValidWord() {
		
		// insert
		trie.insert("car");
		trie.insert("cat");
		trie.insert("cart");
		trie.insert("a");
		trie.insert("apple");
		
		assertFalse(trie.isValidWord(""));
		assertFalse(trie.isValidWord("0"));
		assertFalse(trie.isValidWord("c"));
		assertFalse(trie.isValidWord("appl"));
		assertTrue(trie.isValidWord("car"));
		assertTrue(trie.isValidWord("cart"));
		assertTrue(trie.isValidWord("apple"));
		assertTrue(trie.isValidWord("a"));
	}
	
	@Test
	public void testprefixPercentage(){
		List<String> glossary = new ArrayList<String>();
		glossary.add("to");
		glossary.add("do");
		glossary.add("toll");
		glossary.add("car");
		glossary.add("told");
		glossary.add("cart");
		glossary.add("dot");
		
		assertEquals(0, trie.prefixPercentage("trcd" , glossary));
		assertEquals(100, trie.prefixPercentage("todo" , glossary));
		assertEquals(66, trie.prefixPercentage("tod" , glossary));
		assertEquals(66, trie.prefixPercentage("dod" , glossary));
		assertEquals(80, trie.prefixPercentage("tolld" , glossary));
		assertEquals(71, trie.prefixPercentage("cardort" , glossary));
		assertEquals(70, trie.prefixPercentage("cartendotr" , glossary));
		assertEquals(66, trie.prefixPercentage("dcartd" , glossary));
		
		
	}

}
