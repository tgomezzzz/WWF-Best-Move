import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;

public class Board extends JComponent implements MouseListener, MouseMotionListener, KeyListener {

	public Board(int frameSize_) {
		this.setFocusable(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		this.tiles = new Tile[GRID_SIZE][GRID_SIZE];
		this.selectedTile = null;
		this.words = new LinkedList<>();
		this.selectedWord = null;
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
				int xPos = j * (tileSize + LINE_WIDTH);
				int yPos = i * (tileSize + LINE_WIDTH);
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

	private void checkNewWord() {
		System.out.println("Checking new word");
		checkNewHorzWord();
		checkNewVertWord();
		for (Word w : words) {
			System.out.println(w.word() + ": " + w.val());
		}
	}

	private void checkNewHorzWord() {
		Tile wordStart = selectedTile;
		Tile prev = null;
		while (wordStart != null && wordStart.hasLetter()) {
			System.out.println("Start at tile " + wordStart.getLetter());
			prev = wordStart;
			wordStart = tileLeft(wordStart);
		}
		List<Tile> tilesInWord = new LinkedList<>();
		wordStart = prev;
		Tile wordEnd = wordStart;
		while (wordEnd != null && wordEnd.hasLetter()) {
			System.out.println("End at tile " + wordEnd.getLetter());
			tilesInWord.add(wordEnd);
			prev = wordEnd;
			wordEnd = tileRight(wordEnd);
		}
		wordEnd = prev;
		if (tilesInWord.size() > 1) {
			removeWord(wordStart.horzWord());
			removeWord(wordEnd.horzWord());
			words.add(new Word(tilesInWord, Word.Direction.HORZ));
			System.out.println("Added new word");
		}
	}

	private void checkNewVertWord() {

	}

	private void removeWord(Word w) {
		if (w != null) {
			words.remove(w);
			w.delete();
		}
	}

	private void select(Tile t) {
		if (selectedTile != null) {
			selectedTile.unselect();
		}
		t.select();
		selectedTile = t;
	}

	private void selectTile(MouseEvent e) {
		int r = mouseToGridPos(e.getY());
		int c = mouseToGridPos(e.getX());
		if (r < 0 || c < 0 || r >= GRID_SIZE || c >= GRID_SIZE) {
			return;
		}
		if (selectedTile == tiles[r][c]) {
			return;
		}
		select(tiles[r][c]);
		this.repaint();
	}

	private int mouseToGridPos(int mousePos) {
		if (mousePos < 0 || mousePos > frameSize) {
			return -1;
		}
		return mousePos / (tileSize + LINE_WIDTH);
	}

	private Tile tileUp(Tile t) {
		int row = t.getRow();
		if (row > 0) {
			return tiles[row - 1][t.getCol()];
		}
		return null;
	}

	private Tile tileDown(Tile t) {
		int row = t.getRow();
		if (row < GRID_SIZE - 1) {
			return tiles[row + 1][t.getCol()];
		}
		return null;
	}

	private Tile tileRight(Tile t) {
		int col = t.getCol();
		if (col < GRID_SIZE - 1) {
			return tiles[t.getRow()][col + 1];
		}
		return null;
	}

	private Tile tileLeft(Tile t) {
		int col = t.getCol();
		if (col > 0) {
			return tiles[t.getRow()][col - 1];
		}
		return null;
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
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if (selectedTile != null && (int) key == 8) {
			selectedTile.clearLetter();
			this.repaint();
			return;
		}
		if (selectedTile == null || (int) key < Letter.A_ASCII || 
									(int) key > Letter.Z_ASCII) {
			return;
		}
		selectedTile.setLetter(key);
		this.repaint();
		checkNewWord();
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

	private Tile[][] tiles;
	private Tile selectedTile;
	private List<Word> words;
	private Word selectedWord;
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