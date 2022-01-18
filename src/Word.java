import java.util.List;
import java.awt.Graphics2D;
import java.awt.Color;

public class Word {

	public enum Direction {
		HORZ,
		VERT
	}
	
	public Word(List<Tile> tiles_, Direction direction_) {
		this.tiles = tiles_;
		this.direction = direction_;
		computeWordValue();
		getWordFromTiles();
	}

	// NEED TO CONSIDER WHEN TILES ARE ALREADY IN USE
	private void computeWordValue() {
		int sum = 0;
		int wordMult = 1;
		for (Tile t : tiles) {
			if (!t.hasLetter()) {
				System.err.println("Error: Tile in Word has no Letter.");
				tiles.clear();
				val = -1;
			}
			switch (t.mult) {
				case NONE:
					sum += t.getVal();
					break;
				case DOUBLE_LETTER:
					sum +=  2 * t.getVal();
					break;
				case DOUBLE_WORD:
					sum += t.getVal();
					wordMult *= 2;
					break;
				case TRIPLE_LETTER:
					sum += 3 * t.getVal();
					break;
				case TRIPLE_WORD:
					sum += t.getVal();
					wordMult *= 3;
			}
		}
		val = sum * wordMult;
	}

	private void getWordFromTiles() {
		StringBuilder s = new StringBuilder();
		for (Tile t : tiles) {
			if (!t.hasLetter()) {
				System.err.println("Error: Tile in Word has no Letter.");
				tiles.clear();
				val = -1;
				word = "";
			}
			if (direction == Direction.HORZ) {
				t.setHorzWord(this);
			} else {
				t.setVertWord(this);
			}
			s.append(t.getLetter());
		}
		word = s.toString();
	}

	public void drawScore(Graphics2D g) {
		if (tiles.isEmpty()) {
			return;
		}
		Tile last = tiles.get(tiles.size() - 1);
		if (val == 4) {
			last.drawScore("!", INVALID_COLOR, g);
		} else {
			last.drawScore(Integer.toString(val), SCORE_COLOR, g);
		}
	}

	public void delete() {
		for (Tile t : tiles) {
			if (direction == Direction.HORZ) {
				t.setHorzWord(null);
			} else {
				t.setVertWord(null);
			}
		}
		val = 0;
		word = "";
	}

	public int val() {
		return val;
	}

	public String word() {
		return word;
	}

	private List<Tile> tiles;
	private Direction direction;
	private int val;
	private String word;

	private static final Color SCORE_COLOR = new Color(145, 255, 153);
	private static final Color INVALID_COLOR = new Color(255, 131, 117);
}
