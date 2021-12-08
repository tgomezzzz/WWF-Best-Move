import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics2D;

public class Tile {

    public enum Mult {
        NONE,
        DOUBLE_LETTER,
        DOUBLE_WORD,
        TRIPLE_LETTER,
        TRIPLE_WORD
    }
    
    public Tile(int r_, int c_, Rectangle2D.Double paint_) {
        this.r = r_;
        this.c = c_;
        this.paint = paint_;
        this.mult = Mult.NONE;
        this.letter = null;

    }

    public Tile(Mult mult_) {
        this.mult = mult_;
        this.letter = null;
    }

    public void paintTile(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fill(paint);
        g.draw(paint);
    }

    /**
     * Consider adjusting return type, ie returning newly available spaces to play on.
     */
    private void setLetter(Letter l) {
        this.letter = l;
    }

    private int r;
    private int c;
    private Rectangle2D.Double paint;
    private Mult mult;
    private Letter letter;
}
