import java.awt.Color;

public class TLTile extends Tile {

	final static String TEXT = "TL";
	final static Color COLOR = new Color(86, 207, 72);
	
	public TLTile() {
		super();
		this.color = COLOR;
		this.text = TEXT;
		this.mult = Mult.TRIPLE_LETTER;
		setBackground(color);
	}

}
