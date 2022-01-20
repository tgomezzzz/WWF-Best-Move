import java.awt.Color;

public class DWTile extends Tile {

    final static String TEXT = "DW";
    final static Color COLOR = new Color(207, 85, 72);
    
    public DWTile(int size) {
        super(size);
        this.color = COLOR;
        this.text = TEXT;
        this.mult = Mult.DOUBLE_WORD;
        setBackground(color);
    }

}
