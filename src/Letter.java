import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Font;

public class Letter {
    
    public Letter(char letter_, Rectangle2D.Double paint_) {
        this.letter = Character.toUpperCase(letter_);
        this.val = getLetterValue(letter);
        this.color = new Color(253, 217, 181);
        this.isSelected = false;
        this.paint = paint_;
    }

    private int getLetterValue(char letter) {
        char lower = Character.toLowerCase(letter);
        int ascii = (int) lower;
        if (ascii < A_ASCII || ascii > Z_ASCII) {
            return -1;
        }
        return LETTER_TO_VALUE[ascii - A_ASCII];
    }

    public void drawLetter(Graphics2D g) {
        if (isSelected) {
            g.setColor(color.darker());
        } else {
            g.setColor(color);
        }
        g.fill(paint);
        g.draw(paint);
        drawLetter(letter, g);
        drawValue(val, g);
    }

    private void drawLetter(char c, Graphics2D g) {
        String s = Character.toString(c);
        g.setFont(new Font("Avenir", Font.PLAIN, LETTER_SIZE));
        g.setColor(Color.BLACK);
        Rectangle2D strBounds = g.getFontMetrics().getStringBounds(s, g);
        g.drawString(s, (int) (paint.getCenterX() - strBounds.getWidth() / 2), 
                        (int) (paint.getCenterY() + strBounds.getHeight() / 2.7));
    }

    private void drawValue(int val, Graphics2D g) {
        String s = Integer.toString(val);
        g.setFont(new Font("Avenir", Font.PLAIN, VAL_SIZE));
        g.setColor(Color.BLACK);
        Rectangle2D strBounds = g.getFontMetrics().getStringBounds(s, g);
        g.drawString(s, (int) (paint.getCenterX() + (paint.getWidth() / 3) - strBounds.getWidth() / 2), 
                        (int) (paint.getCenterY() - (paint.getHeight() / 5.2)));
    }

    public void select() {
        isSelected = true;
    }

    public void unselect() {
        isSelected = false;
    }

    private char letter;
    private int val;
    private Color color;
    private boolean isSelected;
    private Rectangle2D.Double paint;

    static final int LETTER_SIZE = 25;
    static final int VAL_SIZE = 13;
    static final int A_ASCII = 97;
    static final int Z_ASCII = 122;
    static final int[] LETTER_TO_VALUE = {
        /* a: */ 1,
        /* b: */ 4,
        /* c: */ 4,  
        /* d: */ 2,
        /* e: */ 1,
        /* f: */ 4,
        /* g: */ 3,
        /* h: */ 3,
        /* i: */ 1,
        /* j: */ 10,
        /* k: */ 5,
        /* l: */ 2,
        /* m: */ 4,
        /* n: */ 2,
        /* o: */ 1,
        /* p: */ 4,
        /* q: */ 10,
        /* r: */ 1,
        /* s: */ 1,
        /* t: */ 1,
        /* u: */ 2,
        /* v: */ 5,
        /* w: */ 4,
        /* x: */ 8,
        /* y: */ 3,
        /* z: */ 10
    };
}
