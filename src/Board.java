import java.awt.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Board extends JComponent {
    final static int GRID_SIZE = 15;

    public Board(int frameSize) {
        this.gridLines = new Line2D.Double[(GRID_SIZE - 1) * 2];
        buildEmptyBoard(frameSize);
    }

    private void buildEmptyBoard(int frameSize) {
        int space = frameSize / GRID_SIZE;
        for (int i = 1; i < GRID_SIZE; i++){
            int pos = i * space;
            Line2D.Double l_vert = new Line2D.Double(pos, 0, pos, frameSize);
            Line2D.Double l_horz = new Line2D.Double(0, pos, frameSize, pos);
            gridLines[i - 1] = l_vert;
            gridLines[i - 1 + GRID_SIZE - 1] = l_horz;
        }
        tiles = new Tile[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int xPos = i * space;
                int yPos = j * space;
                Rectangle2D.Double tile = new Rectangle2D.Double(xPos, yPos, space, space);
                tiles[i][j] = new Tile(i, j, tile);
            }
        }
    }

    @Override 
    public void paintComponent(Graphics g_) {
        Graphics2D g = (Graphics2D) g_;
        for (Tile[] r : tiles) {
            for (Tile t : r) {
                t.paintTile(g);
            }
        }
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        for (Line2D.Double l : gridLines) {
            g.draw(l);
        }
    }

    private Line2D.Double[] gridLines;
    private Tile[][] tiles;

}