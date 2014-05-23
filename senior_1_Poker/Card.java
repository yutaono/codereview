package senior;

enum Suit {
	CLUB(0, "クラブ"), DIAMOND(1, "ダイヤ"), HEART(2, "ハート"), SPADE(3, "スペード"), JOKER(4, "ジョーカー");
	public final int value;
	public final String name;
	private Suit(int value, String name) {
		this.value = value;
		this.name = name;
	}
}

/**
 * トランプのカードを表現するクラス
 * 
 * @author yutaono
 *
 */
public class Card implements Comparable<Card> {
	public static final int MAX_NUMBER = 13;	
	
	private int number;
	private Suit suit;
	private int score; // 値は1-53の値を持つ
	
	public Card(int number, Suit suit) {
		this.number = number;
		this.suit = suit;
		initScore();
	}
	
	public Card(Suit suit) {
		number = 1;
		this.suit = suit;
		initScore();
	}
	
	public int getScore() {
		return score;
	}
	
	public int getNumber() {
		return number;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * スートと数字から1-53の数字をscoreに割り当てる.
	 * スートがJOKERの場合はどの数字であってもscoreを53にする.
	 */
	private void initScore() {
		score = suit.value * MAX_NUMBER + number;
		
		// SuitがJOKERの場合、scoreを53に固定
		if (score > Suit.JOKER.value * MAX_NUMBER + 1) { 
			score = Suit.JOKER.value * MAX_NUMBER + 1;
		}
	}
	
	@Override
	public int compareTo(Card c) {
		return score - c.score;
	}
	
	@Override
	public String toString() {
		if (suit == Suit.JOKER) {
			return suit.name;
		}
		
		String numberString; 
		if (number == 11) {
			numberString = "J";
		} else if (number == 12) {
			numberString = "Q";
		} else if (number == 13) {
			numberString = "K";		
		} else {
			numberString = Integer.toString(number);
		}
		
		return suit.name + "の" + numberString;
	}
	
	@Override
	public boolean equals(Object obj) {
		Card other = (Card)obj;
		
		// Joker同士ならtrueを返す
		if (suit == Suit.JOKER && other.suit == Suit.JOKER) {
			return true;
		}
		
		// 数字とマークが一緒ならtrueを返す
		return (number == other.number && suit == other.suit); 
	}
}
