public class Trie {

    private static class TrieNode {
        private TrieNode[] child;
        private boolean isEnd;

        public TrieNode() {
            child = new TrieNode[26];
            isEnd = false;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a'; 
            if (node.child[idx] == null) {
                node.child[idx] = new TrieNode();
            }
            node = node.child[idx];
        }
        node.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }

     // Returns if there is any word in the trie that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    // Helper method to search for a prefix in the trie.
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a'; // Calculate the index for the current character
            if (node.child[index] == null) {
                return null;
            }
            node = node.child[index];
        }
        return node;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("riya");
        trie.insert("riy");

        System.out.println(trie.search("riya")); 
        System.out.println(trie.search("riyakumari"));   
        System.out.println(trie.search("riy")); 

        // Check for words starting with a prefix
        System.out.println(trie.startsWith("ri")); 
        System.out.println(trie.startsWith("ra")); 
    }
}
