import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Letter {
    
    public Letter() {

    }

    public Letter(char letter_, Rectangle2D.Double paint_) {
        this.letter = letter_;
        this.val = getLetterValue(letter_);
        this.paint = paint;
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

    }

    private char letter;
    private int val;

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
