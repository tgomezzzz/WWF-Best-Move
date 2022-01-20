import java.awt.Color;

public class TWTile extends Tile {

	final static String TEXT = "TW";
	final static Color COLOR = new Color(235, 160, 49);
	
	public TWTile(int size) {
		super(size);
		this.color = COLOR;
		this.text = TEXT;
		this.mult = Mult.TRIPLE_WORD;
		setBackground(color);
	}

}
