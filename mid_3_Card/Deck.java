package intermediate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * トランプのデッキ(デック、カードの山)を表現するクラス
 * 
 * @author yutaono
 *
 */
public class Deck {
	private List<Card> cards;

	public Deck(int jokerNumber) {
		if (jokerNumber < 0) {
			throw new IllegalArgumentException("Jokerの枚数は0枚以上にしてください。");
		} 

		cards = new LinkedList<Card>();
		initDeck(jokerNumber);
	}

	public Deck(int jokerNumber, String cardType) {
		if (jokerNumber < 0) {
			throw new IllegalArgumentException("Jokerの枚数は0枚以上にしてください。");
		} 
		
		if (cardType.equals("poker")) {
//			cards = new LinkedList<PokerCard>();
		} else {
			cards = new LinkedList<Card>();
		}
		
		initDeck(jokerNumber);
	}
	
	
	/**
	 * カードの残り枚数を返すメソッド
	 * 
	 * @return int 残りカード枚数 
	 */
	public int getCurrentCardsNumber () {
		return cards.size();
	}

	/**
	 * 指定された枚数のカードを返すメソッド
	 * 
	 * @param number
	 * @return
	 */
	public List<Card> drawCards(int number)  {
		if (number > cards.size()) {
			throw new IllegalArgumentException();
		}

		List<Card> drawnCards = new ArrayList<Card>();
		for (int i = 0; i < number; i++) {
			drawnCards.add(cards.remove(0));
		}
		return drawnCards;
	}	

	/**
	 * 全ての数字とマークのカードをcardsリストに追加し、シャッフルするメソッド
	 *	
	 * @param jokerNumber
	 */
	private void initDeck(int jokerNumber) {
		for (Suit mark : Suit.values()) {
			if (mark == Suit.Joker) {
				for (int i = 0; i < jokerNumber; i++) {
					cards.add(new Card(Suit.Joker));
				}
			} else {
				for (int i = 1; i <= Card.MAX_NUMBER; i++) {
					cards.add(new Card(i, mark));
				}
			}
		}
		Collections.shuffle(cards);
	}

}
