import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;



public class Rack extends JComponent implements MouseMotionListener {

    public Rack() {
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(400, 80));
        setLayout(new GridLayout(1, 7, Board.LINE_WIDTH, Board.LINE_WIDTH));
        this.tiles = new Tile[RACK_SIZE];
        for (int i = 0; i < RACK_SIZE; i++) {
            tiles[i] = new Tile();
            add(tiles[i]);
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
