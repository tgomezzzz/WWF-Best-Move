public class Trie {
    
    public Trie() {
        this.root = new TrieNode('/');
    }

    public void addWord(String s) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            if (!curr.hasChild(c)) {
                TrieNode next = new TrieNode(c);
                curr.addChild(c, next);
                curr = next;
            } else {
                curr = curr.getChild(c);
            }
        }
        curr.setWord();
    }

    public boolean isWord(String s) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            if (!curr.hasChild(c)) {
                return false;
            }
            curr = curr.getChild(c);
        }
        return curr.isWord();
    }

    private TrieNode root;
}
