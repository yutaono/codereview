package senior_3;

import senior_3.Stone.Color;

/**
 * オセロのゲーム盤を扱うクラス.
 * 
 * @author yutaono
 */
public class GameField {
	private Stone[][] field; // 各マスにある石
	
	public GameField() {
		/** fields を確保 */
		field = new Stone[Coordinate.Y_LENGTH][Coordinate.X_LENGTH];
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j] = new Stone();
			}
		}
		
		initField();
	}

	/**
	 * colorで指定された色のfieldにある石の数を返すメソッド.
	 * @param color
	 * @return colorの石の数
	 */
	public int countStones(Color color) {
		int count = 0;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j].getColor() == color) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * 石が置ける場所があるかが判断するメソッド.
	 * @param color
	 * @return
	 */
	public boolean canPutStone(Color color) {
		for (int y = 1; y < Coordinate.Y_LENGTH; y++) {
			for (int x = 1; x < Coordinate.X_LENGTH; x++) {
				if (canPutStoneForCoodinate(color, new Coordinate(x, y))) {
					return true;
				}
			}			
		}
		
		return false;
	}

	/**
	 * 石を置き、反対の色の石を裏返すメソッド.
	 * @param color
	 * @param coordinate
	 */
	public void putStone(Stone.Color color, Coordinate coordinate) {
		int x = coordinate.getX() - 1;
		int y = coordinate.getY() - 1;
		
		if (field[y][x].getColor() != Color.NON) {
			throw new IllegalCoordinateException();
		}
		
		boolean reversed = false; // 一枚でも裏返すことができたか
		
		for (Direction d : Direction.values()) {
			// 現在のd方向にはマスがない場合、次の方向にいく.  
			if(!Coordinate.isCorrectCoordinate(x + d.x, y + d.y)) {
				continue;
			}
			// 現在のd方向に反対の色がなければ、次の方向にいく.
			if(field[y + d.y][x + d.x].getColor() != color.inverse()) {
				continue;
			}
			
			// i = 0 : 自分自身
			// i = 1 : 隣の石
			int i = 2; // 隣の石の先
			while(Coordinate.isCorrectCoordinate(x + i * d.x, y + i * d.y)) {
				// もし石がおいてなければ、(x, y)には石が置けない. 
				if (field[y + i * d.y][x + i * d.x].getColor() == Color.NON) {
					break;
				}
				
				// 隣の石の先に自分の色があれば、裏返す.
				if (field[y + i * d.y][x + i * d.x].getColor() == color) {
					for (int j = i - 1; j >= 1; j--) {
						field[y + j * d.y][x + j * d.x].reverse();
					}
					reversed = true;
					break;
				} 	
				i++;
			}
		}
		
		if (reversed) {
			field[y][x].setColor(color);
		} else {
			throw new IllegalCoordinateException();
		}
	}
	

	/**
	 * 指定した座標に石がおけるか判断しbooleanで返すメソッド.
	 * @param color
	 * @param coordinate
	 * @return
	 */
	private boolean canPutStoneForCoodinate(Color color, Coordinate coordinate) {
		int x = coordinate.getX() - 1;
		int y = coordinate.getY() - 1;

		if (field[y][x].getColor() != Color.NON) {
			return false;
		}
		
		// (x, y)の周囲を調べる
		for (Direction d : Direction.values()) {
			// 現在のd方向にはマスがない場合、次の方向にいく.  
			if(!Coordinate.isCorrectCoordinate(x + d.x, y + d.y)) {
				continue;
			}
			// 現在のd方向に反対の色がなければ、次の方向にいく.
			if(field[y + d.y][x + d.x].getColor() != color.inverse()) {
				continue;
			}

			// i = 0 : 自分自身
			// i = 1 : 隣の石
			int i = 2;
			while(Coordinate.isCorrectCoordinate(x + i * d.x, y + i * d.y)) {
				// もし石がおいてなければ、(x, y)には石が置けない. 次の方向にいく。
				if (field[y + i * d.y][x + i * d.x].getColor() == Color.NON) {
					break;
				}
				// 自分と同じ色があれば、(x, y)に石が置ける.
				if (field[y + i * d.y][x + i * d.x].getColor() == color) {
					return true;
				} 	
				i++;
			}
		}
		
		return false;
	}
	
	/**
	 * 盤面の初期化をするメソッド.
	 * 中央の4マスに対角に白石、黒石を置く.
	 */
	private void initField() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j].setColor(Stone.Color.NON);	
			}
		}
		
		int mx = Coordinate.X_LENGTH / 2;
		int my = Coordinate.Y_LENGTH / 2;
		field[mx - 1][my - 1].setColor(Stone.Color.BLACK);
		field[mx][my].setColor(Stone.Color.BLACK);		
		field[mx - 1][my].setColor(Stone.Color.WHITE);
		field[mx][my - 1].setColor(Stone.Color.WHITE);
	}
	
	/**
	 * fieldを表示するためのメソッド.
	 * 
	 * 例 :
	 *      a  b  c  d  e  f  g  h
     *   ┏━┳━┳━┳━┳━┳━┳━┳━┓
     * 1 ┃　┃　┃　┃　┃　┃　┃　┃　┃
     *   ┣━╋━╋━╋━╋━╋━╋━╋━┫
     * 2 ┃　┃　┃　┃　┃　┃　┃　┃　┃
     *   ┣━╋━╋━╋━╋━╋━╋━╋━┫
     * 3 ┃　┃　┃　┃　┃　┃　┃　┃　┃
  	 *   ┣━╋━╋━╋━╋━╋━╋━╋━┫
     * 4 ┃　┃　┃　┃●┃○┃　┃　┃　┃
     *   ┣━╋━╋━╋━╋━╋━╋━╋━┫
	 * 5 ┃　┃　┃　┃○┃●┃　┃　┃　┃
     *   ┣━╋━╋━╋━╋━╋━╋━╋━┫
     * 6 ┃　┃　┃　┃　┃　┃　┃　┃　┃
     *   ┣━╋━╋━╋━╋━╋━╋━╋━┫
     * 7 ┃　┃　┃　┃　┃　┃　┃　┃　┃
     *   ┣━╋━╋━╋━╋━╋━╋━╋━┫
     * 8 ┃　┃　┃　┃　┃　┃　┃　┃　┃
  	 *   ┗━┻━┻━┻━┻━┻━┻━┻━┛
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		String space = "  "; 
		
		// Header
		builder.append(space);
		for (int i = 0; i < Coordinate.X_LENGTH; i++) {
			builder.append(space + (char)('a' + i));
		}
		builder.append("\n");
		
		// Body-Top
		builder.append(space).append("┏");
		for (int i = 0; i < Coordinate.X_LENGTH - 1; i++) {
			builder.append("━┳");
		}
		builder.append("━┓").append("\n");
		
		// Body
		for (int i = 1; i < 2 * Coordinate.Y_LENGTH; i++) {
			if (i % 2 == 1) {
				builder.append(i / 2 + 1).append(" ");
				for (int j = 1; j <= 2 * Coordinate.X_LENGTH + 1; j++) {
					if (j % 2 == 0) {
						builder.append(field[(i - 1) / 2][(j - 1) / 2].getColor());
					} else {
						builder.append("┃");
					}
				}
				builder.append("\n");
			} else {
				builder.append(space).append("┣");
				for (int j = 0; j < Coordinate.X_LENGTH - 1; j++) {
					builder.append("━╋");
				}
				builder.append("━┫").append("\n");
			}	
		}
		
		// Body-Bottom
		builder.append(space).append("┗");
		for (int i = 0; i < Coordinate.X_LENGTH - 1; i++) {
			builder.append("━┻");
		}
		builder.append("━┛").append("\n");
		
		return builder.toString();
	}
}
