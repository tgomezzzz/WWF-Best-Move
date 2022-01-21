import java.awt.Color;
import javax.swing.JLabel;

public class DLTile extends Tile {

    final static String TEXT = "DL";
    final static Color COLOR = new Color(52, 149, 235);

    public DLTile() {
        super();
        this.color = COLOR;
        this.mult = Mult.DOUBLE_LETTER;
        setBackground(color);
        JLabel t = new JLabel(TEXT);
        t.setForeground(Color.WHITE);
        t.setFont(font);
        add(t);
    }
}