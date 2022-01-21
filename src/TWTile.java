import java.awt.Color;

public class TWTile extends Tile {

	final static String TEXT = "TW";
	final static Color COLOR = new Color(235, 160, 49);
	
	public TWTile() {
		super();
		this.color = COLOR;
		this.text = TEXT;
		this.mult = Mult.TRIPLE_WORD;
		setBackground(color);
	}

}
