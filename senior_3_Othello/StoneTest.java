package senior_3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StoneTest {

	@Test
	public void 石を裏返すことができる() {
		Stone stone = new Stone(Stone.Color.BLACK);
		Stone.Color actual = stone.reverse();  
		Stone.Color expected = Stone.Color.WHITE;
				
		assertEquals(expected, actual);
	}
}
