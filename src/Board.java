import java.awt.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Board extends JComponent {

    public Board(int frameSize) {
        this.gridLines = new Line2D.Double[(GRID_SIZE - 1) * 2];
        this.multTiles = new HashMap<>();
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
        loadMultiplierPositions();
        tiles = new Tile[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int xPos = i * space;
                int yPos = j * space;
                Rectangle2D.Double tile = new Rectangle2D.Double(xPos, yPos, space, space);
                if (multTiles.containsKey(Arrays.asList(i, j))) {
                    switch (multTiles.get(Arrays.asList(i, j))) {
                        case DOUBLE_LETTER:
                            tiles[i][j] = new DLTile(i, j, tile);
                            break;
                        case DOUBLE_WORD:
                            tiles[i][j] = new DWTile(i, j, tile);
                            break;
                        case TRIPLE_LETTER:
                            tiles[i][j] = new TLTile(i, j, tile);
                            break;
                        case TRIPLE_WORD:
                            tiles[i][j] = new TWTile(i, j, tile);
                            break;
                        default:
                            tiles[i][j] = new Tile(i, j, tile);
                    }
                } else {
                    tiles[i][j] = new Tile(i, j, tile);
                }
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
    private HashMap<List<Integer>, Tile.Mult> multTiles; 

    final static int GRID_SIZE = 15;

    // Data and code for populating special multiplier tiles.
    final static int[][] DL_POS = {
        {1, 2}, {1, 12},
        {2, 1}, {2, 4}, {2, 10}, {2, 13},
        {4, 2}, {4, 6}, {4, 8}, {4, 12},
        {6, 4}, {6, 10},
        {8, 4}, {8, 10},
        {10, 2}, {10, 6}, {10, 8}, {10, 12},
        {12, 1}, {12, 4}, {12, 10}, {12, 13},
        {13, 2}, {13, 12}
    };
    final static int[][] DW_POS = {
        {1, 5}, {1, 9},
        {3, 7},
        {5, 1}, {5, 13},
        {7, 3}, {7, 11},
        {9, 1}, {9, 13},
        {11, 7},
        {13, 5}, {13, 9}
    };
    final static int[][] TL_POS = {
        {0, 6}, {0, 8},
        {3, 3}, {3, 11},
        {5, 5}, {5, 9},
        {6, 0}, {6, 14},
        {8, 0}, {8, 14},
        {9, 5}, {9, 9},
        {11, 3}, {11, 11},
        {14, 6}, {14, 8}
    };
    final static int[][] TW_POS = {
        {0, 3}, {0, 11},
        {3, 0}, {3, 14},
        {11, 0}, {11, 14},
        {14, 3}, {14, 11}
    };

    private void loadMultiplierPositions() {
        for (int[] i : DL_POS) {
            multTiles.put(Arrays.asList(i[0], i[1]), Tile.Mult.DOUBLE_LETTER);
        }
        for (int[] i : DW_POS) {
            multTiles.put(Arrays.asList(i[0], i[1]), Tile.Mult.DOUBLE_WORD);
        }
        for (int[] i : TL_POS) {
            multTiles.put(Arrays.asList(i[0], i[1]), Tile.Mult.TRIPLE_LETTER);
        }
        for (int[] i : TW_POS) {
            multTiles.put(Arrays.asList(i[0], i[1]), Tile.Mult.TRIPLE_WORD);
        }
    }
}