package com.company.trie;

public class TrieStructure {

    /**
     * Initialize your data structure here.
     */
    Element root;

    class Element {
        Element[] children = new Element[26];
        int eof = 0;
    }

    public TrieStructure() {
        root = new Element();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        requInsert(root.children, word, word);
    }

    public void requInsert(Element[] children, String word, String whole) {
        if (word.isEmpty()) {
            return;
        } else {
            Character insertChar = word.charAt(0);
            Element currentElement = children[insertChar - 97];
            if (currentElement == null) {
                currentElement = new Element();
                children[insertChar - 97] = currentElement;
            }
            String rest = word.substring(1);
            if (rest.isEmpty()) {
                currentElement.eof = 1;
            }
            requInsert(currentElement.children, rest, whole);
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return requSearch(root.children, word, word);
    }

    public boolean requSearch(Element[] children, String word, String whole) {
        if (word.isEmpty()) {
            return true;
        } else {
            Character searchChar = word.charAt(0);

            Element currentElement = children[searchChar - 97];

            boolean found = (currentElement != null);

            if (!found) return false;

            String rest = word.substring(1);

            if (whole != null && rest.isEmpty() && currentElement.eof == 1) {
                return true;
            } else if (whole != null && rest.isEmpty() && currentElement.eof == 0) {
                return false;
            }
            return requSearch(currentElement.children, rest, whole);
        }

    }


    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return requSearch(root.children, prefix, null);
    }

}
