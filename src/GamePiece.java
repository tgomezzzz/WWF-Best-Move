import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class GamePiece extends JComponent implements MouseListener, MouseMotionListener, KeyListener {
    
    public GamePiece() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		Tile nextSelected = null;
		if (c == KeyEvent.VK_UP) {
			nextSelected = tileUp(selectedTile);
		} else if (c == KeyEvent.VK_DOWN) {
			nextSelected = tileDown(selectedTile);
		} else if (c == KeyEvent.VK_RIGHT) {
			nextSelected = tileRight(selectedTile);
		} else if (c == KeyEvent.VK_LEFT) {
			nextSelected = tileLeft(selectedTile);
		}
		if (nextSelected != null) {
			select(nextSelected);
			this.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		selectTile(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (selectedTile != null) {
			selectedTile.unselect();
			selectedTile = null;
			this.repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}
   
	@Override
	public void mouseEntered(MouseEvent e) {}
   
	@Override
	public void mouseReleased(MouseEvent e) {}
   
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) { }

    private Tile selectedTile;
}
