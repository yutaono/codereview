package senior_3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void 座標を表す文字列から座標をつくることができる(){
		Coordinate actual = new Coordinate("b2");
		Coordinate expected = new Coordinate(2, 2);
		
		assertEquals(expected, actual);
	}
	
	@Test 
	public void 文字で指定したフィールドの外の座標は正しくない() {
		boolean actual = Coordinate.isCorrectCoordinate("j1");
		assertFalse(actual);
	}
	
	@Test 
	public void 座標値で指定したフィールドの外の座標は正しくない() {
		boolean actual = Coordinate.isCorrectCoordinate(0, 2);
		assertFalse(actual);
	}
}
