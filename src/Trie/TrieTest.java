package Trie;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TrieTest {
	public static Trie trie = new Trie();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		trie.insert("car");
		trie.insert("cat");
		trie.insert("cart");
		trie.insert("a");
		trie.insert("apple");
	}

	@Test
	public void testisValidPrefix() {
		assertTrue(trie.isValidPrefix(""));
		assertFalse(trie.isValidPrefix("0"));
		assertTrue(trie.isValidPrefix("a"));
		assertTrue(trie.isValidPrefix("ca"));
		assertTrue(trie.isValidPrefix("car"));
		assertTrue(trie.isValidPrefix("cart"));
		assertFalse(trie.isValidPrefix("t"));
	}
	
	@Test
	public void testisValidWord() {
		
		assertFalse(trie.isValidWord(""));
		assertFalse(trie.isValidWord("0"));
		assertFalse(trie.isValidWord("c"));
		assertTrue(trie.isValidWord("car"));
		assertTrue(trie.isValidWord("cart"));
		assertFalse(trie.isValidWord("appl"));
		assertTrue(trie.isValidWord("apple"));
		assertTrue(trie.isValidWord("a"));
	}

}
