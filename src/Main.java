import javax.swing.*;
import java.awt.*;

public class Main {

    final static int FRAME_SIZE = 700;

    public static void main(String[] wordsWithFriends) {
        JFrame frame = new JFrame();
        frame.setName("Words With Friends Best Move Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        Board b = new Board(FRAME_SIZE);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(b);

        Rack r = new Rack();
        r.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(r);

        frame.pack();
        frame.setVisible(true);
    }
}
