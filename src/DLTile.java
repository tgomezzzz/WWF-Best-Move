import java.awt.Color;

public class DLTile extends Tile {

    final static String TEXT = "DL";
    final static Color COLOR = new Color(52, 149, 235);

    public DLTile(int size) {
        super(size);
        this.color = COLOR;
        this.text = TEXT;
        this.mult = Mult.DOUBLE_LETTER;
        setBackground(color);
    }
}