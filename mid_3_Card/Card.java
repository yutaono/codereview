package intermediate;

enum Suit {
	Club(0), Diamond(1), Heart(2), Spade(3), Joker(4);
	public final int value;
	private Suit(int value) {
		this.value = value;
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
	private Suit mark;	
	private int score; // 値は1-53の値を持つ
	
	public Card(int number, Suit mark) {
		this.number = number;
		this.mark = mark;
		initScore();
	}
	
	public Card(Suit _mark) {
		number = 1;
		mark = _mark;
		initScore();
	}
	
	public int getScore() {
		return score;
	}
	
	public int getNumber() {
		return number;
	}
	
	public Suit getMark() {
		return mark;
	}
	
	private void initScore() {
		score = mark.value * MAX_NUMBER+ number;
		if (score > Suit.Joker.value * MAX_NUMBER + 1) {
			score = Suit.Joker.value * MAX_NUMBER + 1;
		}
	}
	
	@Override
	public int compareTo(Card c) {
		return score - c.score;
	}
	
	@Override
	public String toString() {
		return number + ", " + mark + "\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		Card other = (Card)obj;
		
		// Joker同士ならtrueを返す
		if (mark == mark.Joker && other.mark == mark.Joker) {
			return true;
		}
		
		// 数字とマークが一緒ならtrueを返す
		return (number == other.number && mark == other.mark); 
	}
}
