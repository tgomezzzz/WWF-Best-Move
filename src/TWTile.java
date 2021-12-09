import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics2D;

public class TWTile extends Tile {

	final static String TEXT = "TW";
	
	public TWTile(int r_, int c_, int fSize_, Rectangle2D.Double paint_) {
		super(r_, c_, fSize_, paint_);
		this.color = new Color(235, 160, 49);
		this.mult = Mult.TRIPLE_WORD;
	}

	@Override
	public void paintTile(Graphics2D g) {
        super.paintTile(g);
		super.drawText(g, TEXT);
	}
}
