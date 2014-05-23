package senior_2;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class RationalTest {
	
	@Test
	public void 加算が正しくできるか() {
		Rational r1 = new Rational(2, 3);
		Rational r2 = new Rational(5, 4);
		
		r1.add(r2);
		
		Rational actual = r1;
		Rational expected = new Rational(23, 12);
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void 減算が正しくできるか() {
		Rational r1 = new Rational(8, 5);
		Rational r2 = new Rational(3, 4);
		
		r1.sub(r2);
		
		Rational actual = r1;
		Rational expected = new Rational(17, 20);
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void 乗算が正しくできるか() {
		Rational r1 = new Rational(5, 7);
		Rational r2 = new Rational(3, 4);
		
		r1.mul(r2);
		
		Rational actual = r1;
		Rational expected = new Rational(15, 28);
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void 除算が正しくできるか() {
		Rational r1 = new Rational(5, 7);
		Rational r2 = new Rational(3, 4);
		
		r1.div(r2);
		
		Rational actual = r1;
		Rational expected = new Rational(20, 21);
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void 約分が正しくできるか() {
		Rational r1 = new Rational(1, 4);
		Rational r2 = new Rational(4, 8);
		
		r1.add(r2);
		
		Rational actual = r1;
		Rational expected = new Rational(3, 4);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void 最大公約数が正しく取得できるか() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		Method lcm = Rational.class.getDeclaredMethod(
					"lcm", 
					new Class[] {
							int.class, 
							int.class 
							}						
					);
		lcm.setAccessible(true);
		
		Rational r = new Rational(1);
		int actual = (Integer)lcm.invoke(r, 30, 42);
		int expected = 210;
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void 最大公倍数が正しく取得できるか() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		Method gcd = Rational.class.getDeclaredMethod(
					"gcd", 
					new Class[] {
							int.class, 
							int.class 
							}						
					);
		gcd.setAccessible(true);
		
		Rational r = new Rational(1);
		int actual = (Integer)gcd.invoke(r, 1071, 1029);
		int expected = 21;
		
		assertEquals(expected, actual);
	}
}
