package senior;

import java.util.LinkedList;
import java.util.List;


/**
 * ポーカーをするプレイヤーの手札を操作するクラス.
 * 
 * @author yutaono
 */
public class Player {
	private List<Card> hand = new LinkedList<Card>();
	
	public Player() {
	}
	
	public void setHand(List<Card> cards) {
		hand = cards;
	}
	
	public List<Card> getHand() {
		return hand;
	}
	
	/**
	 * 受け取った5枚の手札の数字とマーク名を標準出力する.
	 */
	public void showHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.get(i));
		}
	}

	/**
	 * 何枚目のカードかと手札の数字とマーク名を、文字列strで区切って標準出力する.
	 * 例) displayHand(":", hand); 
	 *     1:スペードの2
	 *     2:ハートの4 (以降略)
	 * @param str
	 */
	public void showHand(String str) {
		for (int i = 0; i < hand.size(); i++) {
			System.out.println((i + 1) + str + hand.get(i));
		}
	}
	
	public void draw(int[] numbers, Deck deck) {
		for (int i = 0; i < numbers.length; i++) {
			hand.remove(numbers[i]- 1);
			hand.add(numbers[i] - 1, deck.drawCards(1).get(0));
		}
	}
	
}
