package senior_2;

import static org.junit.Assert.assertEquals;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	private ByteArrayOutputStream baos;
	private PrintStream out;

	/**
	 * System.outのテストのための準備
	 */
	@Before 
	public void setUp() {
		baos = new ByteArrayOutputStream();
		out = System.out;
		System.setOut(
				new PrintStream(
						new BufferedOutputStream(baos)
						)
				);
	}

	@After 
	public void tearDown() {
		System.setOut(out);
	}


	@Test
	public void 無理数オプションが正しく設定できる() {
		String[] args = {"-d", "20/3+2"};
		Calculator.main(args);

		System.out.flush();
		String expected = joinStrings("8.666666666666666");
		String actual = baos.toString();
		assertEquals(expected, actual);
	}


	@Test
	public void 無効なオプションでExceptionを出す() {
		String[] args = {"-s", "20/3+2"};
		Calculator.main(args);

		System.out.flush();
		String expected = joinStrings("オプションが無効です。");
		String actual = baos.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void オプションなしで正しく計算できる() {
		String[] args = {"20/3+2"};
		Calculator.main(args);

		System.out.flush();
		String expected = joinStrings("26/3");
		String actual = baos.toString();
		assertEquals(expected, actual);
	}


	private String joinStrings(String... strs) {
		String newLine = System.getProperty("line.separator");
		String result = "";
		for (String s : strs) {
			result += s + newLine;
		}
		return result;
	}


}
