import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Board extends JComponent implements MouseListener, MouseMotionListener {

	public Board(int frameSize_) {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.tiles = new Tile[GRID_SIZE][GRID_SIZE];
		this.selected = null;
		this.frameSize = frameSize_;
		this.tileSize = (frameSize - ((GRID_SIZE - 1) * LINE_WIDTH)) / GRID_SIZE;
		this.multTiles = new HashMap<>();
		buildEmptyBoard();
	}

	private void buildEmptyBoard() {
		loadMultiplierPositions();
		int fSize = tileSize / 2;
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				int xPos = i * (tileSize + LINE_WIDTH);
				int yPos = j * (tileSize + LINE_WIDTH);
				Rectangle2D.Double tile = new Rectangle2D.Double(xPos, yPos, tileSize, tileSize);
				if (multTiles.containsKey(Arrays.asList(i, j))) {
					switch (multTiles.get(Arrays.asList(i, j))) {
						case DOUBLE_LETTER:
							tiles[i][j] = new DLTile(i, j, fSize, tile);
							break;
						case DOUBLE_WORD:
							tiles[i][j] = new DWTile(i, j, fSize, tile);
							break;
						case TRIPLE_LETTER:
							tiles[i][j] = new TLTile(i, j, fSize, tile);
							break;
						case TRIPLE_WORD:
							tiles[i][j] = new TWTile(i, j, fSize, tile);
							break;
						default:
							tiles[i][j] = new Tile(i, j, fSize, tile);
					}
				} else {
					tiles[i][j] = new Tile(i, j, fSize, tile);
				}
			}
		}
	}

	private void selectTile(MouseEvent e) {
		int r = mouseToGridPos(e.getX());
		int c = mouseToGridPos(e.getY());
		if (r < 0 || c < 0 || r >= GRID_SIZE || c >= GRID_SIZE) {
			return;
		}
		if (selected == tiles[r][c]) {
			return;
		}
		if (selected != null) {
			selected.unselect();
		}
		selected = tiles[r][c];
		selected.select();
		this.repaint();
	}

	private int mouseToGridPos(int mousePos) {
		if (mousePos < 0 || mousePos > frameSize) {
			return -1;
		}
		return mousePos / (tileSize + LINE_WIDTH);
	}

	@Override 
	public void paintComponent(Graphics g_) {
		Graphics2D g = (Graphics2D) g_;
		for (Tile[] r : tiles) {
			for (Tile t : r) {
				t.paintTile(g);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		selectTile(e);
	}

    @Override
    public void mouseExited(MouseEvent e) {
		if (selected != null) {
			selected.unselect();
			selected = null;
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

	private Tile[][] tiles;
	private Tile selected;
	private int frameSize;
	private int tileSize;
	private HashMap<List<Integer>, Tile.Mult> multTiles; 

	final static int GRID_SIZE = 15;
	final static int LINE_WIDTH = 3;

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