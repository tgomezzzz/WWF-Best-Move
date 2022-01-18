import java.util.HashMap;

public class TrieNode {

    public TrieNode(char _c) {
        this.c = _c;
        this.children = new HashMap<>();
        this.isWord = false;
    }
    
    public boolean hasChild(char c) {
        return children.containsKey(c);
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public void addChild(char c) {
        TrieNode t = new TrieNode(c);
        addChild(c, t);
    }

    public void addChild(char c, TrieNode suffix) {
        children.put(c, suffix);
    }

    public void setWord() {
        setWord(true);
    }

    public void setWord(boolean b) {
        isWord = b;
    }

    public boolean isWord() {
        return isWord;
    }

    private char c;
    private HashMap<Character, TrieNode> children;
    private boolean isWord; 
    
}
