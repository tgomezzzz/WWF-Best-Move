import javax.swing.JComponent;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;



public class Rack extends JComponent implements MouseMotionListener {

    public Rack() {
        this.addMouseMotionListener(this);
        this.tiles = new Tile[RACK_SIZE];
        this.setPreferredSize(new Dimension(700, 80));
        int tileSize = 50;
        int totalWidth = (tileSize * RACK_SIZE) + (Board.LINE_WIDTH * (RACK_SIZE - 1));
        for (int i = 0; i < RACK_SIZE; i++) {
            int xPos = 350 - (totalWidth / 2) + i * (tileSize + Board.LINE_WIDTH);
            Rectangle2D.Double tile = new Rectangle2D.Double(xPos, 0, tileSize, tileSize);
            tiles[i] = new Tile(0, i, 5, tile);
        }
    }

    @Override 
	public void paintComponent(Graphics g_) {
		Graphics2D g = (Graphics2D) g_;
		for (Tile t : tiles) {
			t.paintTile(g);
		}
	}

    	@Override
	public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX() + ", " + e.getY());
	}


	@Override
	public void mouseDragged(MouseEvent e) {}

    private Tile[] tiles;
    final private static int RACK_SIZE = 7;
}
