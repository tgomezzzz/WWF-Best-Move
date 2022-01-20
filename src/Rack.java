import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;



public class Rack extends JComponent implements MouseMotionListener {

    public Rack() {
        this.addMouseMotionListener(this);
        this.tiles = new Tile[RACK_SIZE];
        this.setPreferredSize(new Dimension(700, 80));
        for (int i = 0; i < RACK_SIZE; i++) {
            tiles[i] = new Tile(50);
        }
    }

    // @Override 
	// public void paintComponent(Graphics g_) {
	// 	Graphics2D g = (Graphics2D) g_;
	// 	for (Tile t : tiles) {
	// 		t.paintTile(g);
	// 	}
	// }

    	@Override
	public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX() + ", " + e.getY());
	}


	@Override
	public void mouseDragged(MouseEvent e) {}

    private Tile[] tiles;
    final private static int RACK_SIZE = 7;
}
