import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] wordsWithFriends) {
        Trie words = buildTrie();
        if (words == null) {
            System.out.println("Unable to build dictionary. Exiting.");
            return;
        }
    }

    private static Trie buildTrie() {
        Trie t = new Trie();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("../dict.txt"));
        } catch (FileNotFoundException e) {
            return null;
        }
        try {
            String line = reader.readLine();
            while (line != null) {
                t.addWord(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            return null;
        }
        return t;
    }
    
}
