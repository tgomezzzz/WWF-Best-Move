import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
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
        this.multIsUsed = false;
        this.horzWord = null;
        this.vertWord = null;
        this.color = new Color(209, 209, 209);
        this.fontSize = fontSize_;
        this.text = TEXT;
        this.paint = paint_;
        this.mult = Mult.NONE;
        this.letter = null;
    }

    public void select() {
        isSelected = true;
        if (hasLetter()) {
            letter.select();
        }
    }

    public void unselect() {
        isSelected = false;
        if (hasLetter()) {
            letter.unselect();
        }
    }

    public char getLetter() {
        if (hasLetter()) {
            return letter.getLetter();
        }
        return 0;
    }

    public int getVal() {
        if (hasLetter()) {
            return letter.getVal();
        }
        return -1;
    }

    public boolean hasHorzWord() {
        return horzWord != null;
    }

    public boolean hasVertWord() {
        return vertWord != null;
    }

    public void setHorzWord(Word w) {
        horzWord = w;
    }

    public void setVertWord(Word w) {
        vertWord = w;
    }

    public Word horzWord() {
        return horzWord;
    }

    public Word vertWord() {
        return vertWord;
    }

    public void paintTile(Graphics2D g) {
        if (isSelected) {
            if (hasLetter()) {
                if (mult != Mult.NONE) {
                    fillTile(g, color);
                }
                letter.drawLetter(g);
            } else {
                fillTile(g, color.darker());
                drawText(g, text);
            }
            paintWordScore(g);
        } else {
            if (hasLetter()) {
                letter.drawLetter(g); 
            } else {
                fillTile(g, color);
                drawText(g, text);
            }
        } 
    }

    public void paintWordScore(Graphics2D g) {
        if (hasHorzWord()) {
            horzWord.drawScore(g);
        }
        if (hasVertWord()) {
            vertWord.drawScore(g);
        }
    }

    public void drawScore(String s, Color c, Graphics2D g) {
        // placed correctly for vert word score only for tiles with vert and horz words
        Rectangle2D strBounds = g.getFontMetrics().getStringBounds(s, g);
        double rectX = paint.getMaxX() - 10;
        double rectY = paint.getMaxY() - 5;
        double width = strBounds.getWidth();
        double padding = width / s.length();

        RoundRectangle2D.Double bubble = 
            new RoundRectangle2D.Double(rectX, rectY, width + padding, 13, 7, 7);
        g.setColor(c);
        g.fill(bubble);
        g.draw(bubble);

        g.setFont(new Font("Avenir", Font.PLAIN, (int) (fontSize / 1.5)));
        g.setColor(Color.BLACK);
        g.drawString(s, (int) (rectX + padding / 2),
                        (int) (bubble.getCenterY() + strBounds.getHeight() / 3));
    }

    private void fillTile(Graphics2D g, Color c) {
        g.setColor(c);
        g.fill(paint);
        g.draw(paint);
    }

    protected void drawText(Graphics2D g, String text) {
        g.setFont(new Font("Avenir", Font.PLAIN, fontSize));
        g.setColor(Color.WHITE);
        Rectangle2D strBounds = g.getFontMetrics().getStringBounds(text, g);
        g.drawString(text, (int) (paint.getCenterX() - strBounds.getWidth() / 2),
                           (int) (paint.getCenterY() + strBounds.getHeight() / 4));
    }

    protected void setLetter(char c) {
        letter = new Letter(c, paint);
    }

    protected void clearLetter() {
        letter = null;
    }

    protected boolean hasLetter() {
        return letter != null;
    }

    protected int getRow() {
        return r;
    }

    protected int getCol() {
        return c;
    }

    protected int getPaintX() {
        return (int) paint.getCenterX();
    }

    protected int getPaintY() {
        return (int) paint.getCenterY();
    }

    protected int r;
    protected int c;
    protected boolean isSelected;
    protected boolean multIsUsed;
    protected Word horzWord;
    protected Word vertWord;
    protected Color color;
    protected int fontSize;
    protected String text;
    protected Rectangle2D.Double paint;
    protected Mult mult;
    protected Letter letter;

    private static final String TEXT = "";
}
