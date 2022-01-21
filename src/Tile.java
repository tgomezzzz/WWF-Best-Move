import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class Tile extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    public enum Mult {
        NONE,
        DOUBLE_LETTER,
        DOUBLE_WORD,
        TRIPLE_LETTER,
        TRIPLE_WORD
    }

    public Tile() {
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        this.color = COLOR;
        this.font = new Font("Avenir", Font.PLAIN, (int) (getWidth() / 2));
        this.multIsUsed = false;
        this.horzWord = null;
        this.vertWord = null;
        this.text = TEXT;
        this.mult = Mult.NONE;
        this.letter = null;
        setBackground(color);
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

    @Override
    public void paintComponent(Graphics g_) {
    //public void paintTile(Graphics2D g) {
        super.paintComponent(g_);
        Graphics2D g = (Graphics2D) g_;
        // if (hasLetter()) {
        //     letter.drawLetter(g);
        //     System.out.println("has letter");
        // }
        // if (isSelected) {
        //     if (hasLetter()) {
        //         if (mult != Mult.NONE) {
        //             fillTile(g, color);
        //         }
        //         letter.drawLetter(g);
        //     } else {
        //         fillTile(g, color.darker());
        //         drawText(g, text);
        //     }
        //     paintWordScore(g);
        // } else {
        //     if (hasLetter()) {
        //         letter.drawLetter(g); 
        //     } else {
        //         fillTile(g, color);
        //         drawText(g, text);
        //     }
        // } 
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

        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(s, (int) (rectX + padding / 2),
                        (int) (bubble.getCenterY() + strBounds.getHeight() / 3));
    }

    private void fillTile(Graphics2D g, Color c) {
        // g.setColor(c);
        // g.fill(paint);
        // g.draw(paint);
    }

    protected void drawText(Graphics2D g, String text) {
        // g.setFont(new Font("Avenir", Font.PLAIN, fontSize));
        // g.setColor(Color.WHITE);
        // Rectangle2D strBounds = g.getFontMetrics().getStringBounds(text, g);
        // g.drawString("test", 100, 100);
        // g.drawString(text, (int) ((getWidth() / 2) - strBounds.getWidth() / 2),
        //                    (int) ((getHeight() / 2) + strBounds.getHeight() / 4));
    }

    protected void setLetter(char c) {
        letter = new Letter(c, new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight()));
        add(letter);
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

    protected int r;
    protected int c;
    protected boolean isSelected;
    protected boolean multIsUsed;
    protected Word horzWord;
    protected Word vertWord;
    protected Color color;
    protected Font font;
    protected String text;
    protected Rectangle2D.Double paint;
    protected Mult mult;
    protected Letter letter;

    private static final String TEXT = "";
    private static final Color COLOR = new Color(209, 209, 209);

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        System.out.println(getX());
		if (hasLetter() && (int) key == 8) {
			clearLetter();
			//removeWord(horzWord);
			//removeWord(selectedTile.vertWord);
			//checkNewVertWord(tileUp(selectedTile));
			//checkNewVertWord(tileDown(selectedTile));
			//checkNewHorzWord(tileLeft(selectedTile));
			//checkNewHorzWord(tileRight(selectedTile));
			this.repaint();
			return;
		}
		if ((int) key < Letter.A_ASCII || (int) key > Letter.Z_ASCII) {
			return;
		}
		setLetter(key);
		this.repaint();
		//checkNewWord();
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(color.darker());     
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(color);
    }
}
