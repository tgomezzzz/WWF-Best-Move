import javax.swing.*;
import java.awt.*;

public class Main {

    final static int FRAME_SIZE = 700;

    public static void main(String[] wordsWithFriends) {
        // Trie words = buildTrie();
        // if (words == null) {
        //     System.out.println("Unable to build dictionary. Exiting.");
        //     return;
        // }

        JFrame frame = new JFrame();
        Board b = new Board(FRAME_SIZE);
        frame.getContentPane().add(b, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(FRAME_SIZE, FRAME_SIZE + 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
