package senior_3;


/**
 * Coordinate関連で例外が起きた時に投げる例外.
 * 
 * @author yutaono
 */
public class IllegalCoordinateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IllegalCoordinateException() {		
	}
	
	public IllegalCoordinateException(String str) {
		super(str);
	}
}
