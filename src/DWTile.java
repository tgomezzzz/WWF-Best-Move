import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics2D;

public class DWTile extends Tile {
    
    public DWTile(int r_, int c_, Rectangle2D.Double paint_) {
        super(r_, c_, paint_);
        this.mult = Mult.DOUBLE_WORD;
    }

    @Override
    public void paintTile(Graphics2D g) {
        g.setColor(new Color(207, 85, 72));
        g.fill(paint);
        g.draw(paint);
    }
}
