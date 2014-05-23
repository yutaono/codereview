package senior_3;


/**
 * 座標を表現するクラス.
 * 
 * @author yutaono
 */
public class Coordinate {
	public static final int X_LENGTH = 8; // x座標の最大値
	public static final int Y_LENGTH = 8; // y座標の最大値
	
	private int x; // x座標
	private int y; // y座標

	public Coordinate(String str) {
		if (!isCorrectCoordinate(str)) {
			throw new IllegalCoordinateException();
		}
		
		this.x = Integer.parseInt("" + (str.charAt(0) - 'a')) + 1;
		this.y = Integer.parseInt("" + str.charAt(1));
	}
	
	public Coordinate(int x, int y) {
		if (!isCorrectCoordinate(x, y)) {
			throw new IllegalCoordinateException();
		}
		
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/**
	 * 文字列が定義している座標の中に収まるかチェックするメソッド.
	 * @param coordinate
	 * @return
	 * @throws StringIndexOutOfBoundsException
	 */
	public static boolean isCorrectCoordinate(String coordinate) throws StringIndexOutOfBoundsException {
		if(coordinate.length() > 2) {
			return false;
		}
		
		if(coordinate.charAt(0) < 'a' || ('a' + X_LENGTH - 1) < coordinate.charAt(0)) {
			return false;
		}
		
		if(coordinate.charAt(1) < '1' || ('1' + Y_LENGTH - 1) < coordinate.charAt(1)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 座標が定義している座標の中に収まるかチェックするメソッド.
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean isCorrectCoordinate(int x, int y) {
		if (x < 1 || X_LENGTH < x) {
			return false;
		}
		
		if (y < 1 || Y_LENGTH < y) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		Coordinate another = (Coordinate)obj;
		return (this.x == another.x && this.y == another.y);
	}

	
}
