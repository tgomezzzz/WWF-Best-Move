import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics2D;

public class DLTile extends Tile {

    public DLTile(int r_, int c_, Rectangle2D.Double paint_) {
        super(r_, c_, paint_);
        this.mult = Mult.DOUBLE_LETTER;
    }

    @Override
    public void paintTile(Graphics2D g) {
        g.setColor(new Color(52, 149, 235));
        g.fill(paint);
        g.draw(paint);
    }
}