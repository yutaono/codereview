package senior_3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;

import senior_3.Stone.Color;

public class GameFieldTest {
	
	@Test
	public void 指定された色の石の数を正しく返す() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		GameField gameField = new GameField();	
		putStoneForce(gameField, Color.BLACK, 2, 2);
		putStoneForce(gameField, Color.WHITE, 1, 1);
		putStoneForce(gameField, Color.NON, 3, 6);
		putStoneForce(gameField, Color.WHITE, 7, 2);
		putStoneForce(gameField, Color.BLACK, 2, 2);		
		
		int blackNumber = gameField.countStones(Color.BLACK);
		int whiteNumber = gameField.countStones(Color.WHITE);
		
		assertEquals(3, blackNumber);
		assertEquals(4, whiteNumber);
	}

	@Test
	public void 石が置ける場所があることが判断できる(){
		GameField gameField = new GameField();
		
		assertTrue(gameField.canPutStone(Color.BLACK)); // 5, 3がおける
	}
	
	@Test
	public void 石が置ける場所がないことが判断できる() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		GameField gameField = new GameField();
		putStoneForce(gameField, Color.BLACK, 3, 2);
		putStoneForce(gameField, Color.BLACK, 3, 1);
		putStoneForce(gameField, Color.BLACK, 3, 0);
		putStoneForce(gameField, Color.NON, 4, 3);
		putStoneForce(gameField, Color.NON, 4, 4);		
		
		assertFalse(gameField.canPutStone(Color.WHITE));
	}
	
	@Test
	public void 石を置いて挟まれた反対の色を裏返すことができる() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		GameField gameField = new GameField();
		putStoneForce(gameField, Color.BLACK, 3, 2);
		putStoneForce(gameField, Color.BLACK, 3, 1);
		putStoneForce(gameField, Color.BLACK, 4, 1);
		putStoneForce(gameField, Color.BLACK, 5, 2);
		putStoneForce(gameField, Color.WHITE, 6, 3);
	
		gameField.putStone(Color.WHITE, new Coordinate(4, 1));
		
		assertEquals(gameField.countStones(Color.WHITE), 9);
	}
	

	/**
	 * プライベート変数にアクセスするためのメソッド.
	 * @param target_object
	 * @param field_name
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static Object getPrivateField(Object target_object, String field_name) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        @SuppressWarnings("rawtypes")
		Class c = target_object.getClass();
        Field fld = c.getDeclaredField(field_name);
        fld.setAccessible(true);

        return fld.get(target_object);
    }
	
	/**
	 * 強制的に座標に石を置くメソッド.
	 * @param gameField
	 * @param color
	 * @param x
	 * @param y
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static void putStoneForce(GameField gameField, Color color, int x, int y) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Stone[][] field = (Stone[][])getPrivateField(gameField, "field");
		field[y][x].setColor(color); 
	}
	
}
