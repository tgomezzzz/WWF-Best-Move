import java.util.ArrayList;

public class Word {
	
	public Word(ArrayList<Tile> tiles_) {
		this.tiles = tiles_;
		computeWordValue();
		getWordFromTiles();
	}

	// NEED TO CONSIDER WHEN TILES ARE ALREADY IN USE
	private void computeWordValue() {
		int sum = 0;
		int wordMult = 1;
		for (Tile t : tiles) {
			if (!t.hasLetter()) {
				System.out.println("Error: Tile in Word has no Letter.");
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
				System.out.println("Error: Tile in Word has no Letter.");
				tiles.clear();
				val = -1;
				word = "";
			}
			s.append(t.getLetter());
		}
		word = s.toString();
	}

	private ArrayList<Tile> tiles;
	private int val;
	private String word;
}
