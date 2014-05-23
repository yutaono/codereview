package senior_2;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RPNTest {
	
	@Test
	public void 括弧を用いた数式が計算できる() {
		 RPN rpn = new RPN("(1+2)*3");
		 String actual = rpn.calc().toString();
		 String expected = "9";
		 assertTrue(expected.equals(actual));
	}

	@Test
	public void 有理数を用いた数式が計算できる() {
		 RPN rpn = new RPN("2/3+2*3");
		 String actual = rpn.calc().toString();
		 String expected = "20/3";
		 assertTrue(expected.equals(actual));
	}

	@Test
	public void 無理数オプションを使って数式が計算できる() {
		 RPN rpn = new RPN("2/3+2*3");
		 String actual = rpn.calc().toString("d");
		 String expected = "6.666666666666667";
		 assertTrue(expected.equals(actual));
	}	
	
}
