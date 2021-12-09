import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics2D;

public class TLTile extends Tile {

	final static String TEXT = "TL";
	
	public TLTile(int r_, int c_, int fSize_, Rectangle2D.Double paint_) {
		super(r_, c_, fSize_, paint_);
		this.color = new Color(86, 207, 72);
		this.mult = Mult.TRIPLE_LETTER;
	}

	@Override
	public void paintTile(Graphics2D g) {
        super.paintTile(g);
        super.drawText(g, TEXT);
	}
}
