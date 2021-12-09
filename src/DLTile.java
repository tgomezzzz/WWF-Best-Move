import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics2D;

public class DLTile extends Tile {

    final static String TEXT = "DL";

    public DLTile(int r_, int c_, int fSize_, Rectangle2D.Double paint_) {
        super(r_, c_, fSize_, paint_);
        this.color = new Color(52, 149, 235);
        this.mult = Mult.DOUBLE_LETTER;
    }

    @Override
    public void paintTile(Graphics2D g) {
        super.paintTile(g);
        super.drawText(g, TEXT);
    }
}