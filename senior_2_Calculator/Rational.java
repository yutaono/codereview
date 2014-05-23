package senior_2;

/**
 * 有理数(Rational Number) a/b を表現するクラス.
 * 
 * @author yutaono
 */
public class Rational implements Field<Rational> {
	private int numerator; // 分子 
	private int denominator; // 分母

	/**
	 * 分子のみを指定されたコンストラクタ.
	 * @param numerator
	 */
	public Rational(int numerator) {
		this.numerator = numerator;
		this.denominator = 1;
	}
	
	/**
	 * 分子分母を指定されたコンストラクタ.
	 * @param numerator 分子
	 * @param denominator 分母
	 */
	public Rational(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	/**
	 * thisとanotherを加算し、約分するメソッド.
	 * @param another
	 */
	public void add(Rational another) {
		int denominator = lcm(this.denominator, another.denominator);
		int numerator = this.numerator * denominator / this.denominator
				+ another.numerator * denominator / another.denominator; 
		setNumeratorAndDenominator(numerator, denominator);
	}
	
	/**
	 * thisとanotherを減算し、約分するメソッド.
	 * @param another
	 */
	public void sub(Rational another) {
		int denominator = lcm(this.denominator, another.denominator);
		int numeraotr = this.numerator * denominator / this.denominator 
				- another.numerator * denominator / another.denominator; 
		setNumeratorAndDenominator(numeraotr, denominator);
	}
	
	/**
	 * thisとanotherを乗算し、約分するメソッド.
	 * @param another
	 */
	public void mul(Rational another) {
		int denominator = this.denominator * another.denominator;
		int numerator = this.numerator * another.numerator;
		setNumeratorAndDenominator(numerator, denominator);
	}
	
	/**
	 * thisとanotherを除算し、約分するメソッド.
	 * @param another
	 */
	public void div(Rational another) {
		int denominator = this.denominator * another.numerator;
		int numerator = this.numerator * another.denominator;
		setNumeratorAndDenominator(numerator, denominator);
	}
	
	/**
	 * 分子分母を設定し、約分するメソッド.
	 * @param numerator
	 * @param denominator
	 */
	private void setNumeratorAndDenominator(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		reduction();
	}
	
	/**
	 * 約分をするメソッド.
	 * 分子と分母を最大公約数で割ることで実現している.
	 * @return
	 */
	private void reduction() {
		int g = gcd(this.numerator, this.denominator);
		this.numerator = this.numerator / g;
		this.denominator = this.denominator / g;
	}

	/**
	 * 最小公倍数(the Least Common Multiple)を返すメソッド.
	 * 最大公約数G、最小公倍数Lとすると以下の関係式が成り立つことを利用している.
	 *     mn = GL
	 *     
	 * @param m
	 * @param n
	 * @return
	 */
	private int lcm(int m, int n) {
		if (m == 0 || n == 0) {
			throw new IllegalArgumentException("0では最小公倍数が求められません。");
		}
		
		int g = gcd(m, n);
		int l = m * n / g; 
		
		return l;
	}
	
	
	/**
	 * 最大公約数(Greatest Common Divisor)を返すメソッド.
	 * ユークリッドの互除法を用いている.
	 * @param m
	 * @param n	 
	 * @return
	 */
	private int gcd(int m, int n) {
		if (m == 0 || n == 0) {
			throw new IllegalArgumentException("0では最大公倍数が求められません。");
		}

		if (m < n) { 
			int tmp = n;
			n = m;
			m = tmp;
		}

		int r = m % n;
		if (r == 0) return n;
		return gcd(n, r);
	}
		
	/**
	 * ２つの有理数が等しいか返すメソッド.
	 * 二つの有理数 a/b, c/dがあり、
	 *    ad - bc = 0
	 * が成り立つ時に等しいとしている。
	 * つまり、1/2と2/4は等しい。
	 * 
	 * @param obj
	 * @return 
	 */
	@Override
	public boolean equals(Object obj) {
		Rational other = (Rational)obj;
		
		return (this.numerator * other.denominator - this.denominator * other.numerator == 0);
	}
	
	@Override
	public String toString() {
		return this.toString("");
	}
	
	public String toString(String d) {
		if (d.equals("d")) {
			return Double.toString((double)this.numerator / (double)this.denominator);
		} else {
			if (this.denominator == 1) { 
				return this.numerator + "";
			} 
			
			return (this.numerator + "/" + this.denominator);
		}
	}
}
