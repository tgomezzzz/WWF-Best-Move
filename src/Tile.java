import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Tile {

    public enum Mult {
        NONE,
        DOUBLE_LETTER,
        DOUBLE_WORD,
        TRIPLE_LETTER,
        TRIPLE_WORD
    }
    
    public Tile(int r_, int c_, int fontSize_, Rectangle2D.Double paint_) {
        this.r = r_;
        this.c = c_;
        this.color = new Color(209, 209, 209);
        this.fontSize = fontSize_;
        this.paint = paint_;
        this.mult = Mult.NONE;
        this.letter = null;
    }

    public Tile(Mult mult_) {
        this.mult = mult_;
        this.letter = null;
    }

    public void select() {
        this.isSelected = true;
    }

    public void unselect() {
        this.isSelected = false;
    }

    public void paintTile(Graphics2D g) {
        g.setColor(color);
        if (isSelected) {
            g.setColor(color.darker());
        }
        g.fill(paint);
        g.draw(paint);
    }

    protected void drawText(Graphics2D g, String text) {
        g.setFont(new Font("Avenir", Font.PLAIN, fontSize));
        g.setColor(Color.WHITE);
        g.drawString(text, getX() - (g.getFontMetrics().stringWidth(text) / 2), getY() + 8);
    }

    /**
     * Consider adjusting return type, ie returning newly available spaces to play on.
     */
    protected void setLetter(Letter l) {
        this.letter = l;
    }

    protected int getX() {
        return (int) paint.getCenterX();
    }

    protected int getY() {
        return (int) paint.getCenterY();
    }

    protected int r;
    protected int c;
    protected boolean isSelected;
    protected Color color;
    protected int fontSize;
    protected Rectangle2D.Double paint;
    protected Mult mult;
    protected Letter letter;
}
