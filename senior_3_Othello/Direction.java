package senior_3;

/**
 * 方向を表す列挙型.
 * 値はそれぞれ以下のイメージである.
 * 
 *  +-------+-------+-------+
 *  | -1,-1 |  0,-1 |  1,-1 |
 *  +-------+-------+-------+
 *  | -1, 0 |  0, 0 |  1, 0 |
 *  +-------+-------+-------+
 *  | -1, 1 |  0, 1 |  1, 1 |
 *  +-------+-------+-------+
 * 
 *  @author yutaono
 */
public enum Direction {
	ADOVE_LEFT(-1,-1),  ADOVE(0,-1),  ADOVE_RIGHT(1,-1),
    LEFT(-1, 0),                      RIGHT(1, 0),
    BOTTOM_LEFT(-1, 1), BOTTOM(0, 1), BOTTOM_RIGHT(1, 1);

	public int x;
	public int y;
	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
