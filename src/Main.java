import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

public class Main {

    final static int FRAME_SIZE = 800;

    public static void main(String[] wordsWithFriends) {
        Trie words = buildTrie();
        if (words == null) {
            System.out.println("Unable to build dictionary. Exiting.");
            return;
        }

        JFrame frame = new JFrame();
        Board b = new Board(FRAME_SIZE);
        frame.getContentPane().add(b, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(FRAME_SIZE, FRAME_SIZE + 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
