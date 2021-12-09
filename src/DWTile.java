import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics2D;

public class DWTile extends Tile {

    final static String TEXT = "DW";
    
    public DWTile(int r_, int c_, int fSize_, Rectangle2D.Double paint_) {
        super(r_, c_, fSize_, paint_);
        this.color = new Color(207, 85, 72);
        this.mult = Mult.DOUBLE_WORD;
    }

    @Override
    public void paintTile(Graphics2D g) {
        super.paintTile(g);
        super.drawText(g, TEXT);
    }
}
