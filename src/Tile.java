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
        this.text = TEXT;
        this.paint = paint_;
        this.mult = Mult.NONE;
        this.letter = null;
    }

    public void select() {
        isSelected = true;
        if (letter != null) {
            letter.select();
        }
    }

    public void unselect() {
        isSelected = false;
        if (letter != null) {
            letter.unselect();
        }
    }

    public void paintTile(Graphics2D g) {
        if (letter != null) {
            letter.drawLetter(g);
        } else {
            g.setColor(color);
            if (isSelected) {
                g.setColor(color.darker());
            }
            g.fill(paint);
            g.draw(paint);   
            drawText(g, text);         
        }
    }

    protected void drawText(Graphics2D g, String text) {
        g.setFont(new Font("Avenir", Font.PLAIN, fontSize));
        g.setColor(Color.WHITE);
        Rectangle2D strBounds = g.getFontMetrics().getStringBounds(text, g);
        g.drawString(text, (int) (paint.getCenterX() - strBounds.getWidth() / 2),
                           (int) (paint.getCenterY() + strBounds.getHeight() / 4));
    }

    protected void setLetter(Letter l) {
        this.letter = l;
    }

    protected void clearLetter() {
        this.letter = null;
    }

    protected void setLetter(char c) {
        this.letter = new Letter(c, paint);
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
    protected String text;
    protected Rectangle2D.Double paint;
    protected Mult mult;
    protected Letter letter;

    private static final String TEXT = "";
}
