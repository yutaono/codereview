package senior;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class DeckTest {

	@Test
	public void ジョーカーを0枚としてデッキをつくると基本の52枚のカードが保持される () {
		Deck deck = new Deck(0);
		
		assertTrue(deck.getCurrentCardsNumber() == 52);
		
		List<Card> cards = deck.drawCards(52);
		for(Suit suit : Suit.values()) {
			if (suit == Suit.JOKER) {
				continue;
			}
			for(int i=1; i<=13; i++){			
				assertTrue(cards.indexOf(new Card(i, suit)) != -1);				
			}
		}	
	}
	
	@Test
	public void ジョーカーの枚数を指定できる () {
		Deck deck = new Deck(2);
		
		// カードの枚数が正しいか確認
		assertTrue(deck.getCurrentCardsNumber() == 54);
		
		// カードが全てあるか確認		
		List<Card> cards = deck.drawCards(54);
		for(Suit mark : Suit.values()) {
			for(int i=1; i<=13; i++){			
				assertTrue(cards.contains(new Card(i, mark)));
			}
		}
		
		// Jokerの枚数が正しいか確認
		int jokerNumber = 0;
		for (Card card : cards) {
			if (card.getSuit() == Suit.JOKER) {
				jokerNumber++;
			}
		}
		assertTrue(jokerNumber == 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void ジョーカーの枚数が0枚未満の場合は例外を投げる () {
		new Deck(-1);
	}
	
	@Test
	public void デッキの残り枚数を確認することができる () {
		Deck deck = new Deck(1);
		
		assertTrue(deck.getCurrentCardsNumber() == 53);
	}
	
	@Test
	public void デッキから指定枚数のカードをひくことができる () {
		Deck deck = new Deck(1);
		
		assertTrue(deck.getCurrentCardsNumber() == 53);
		deck.drawCards(2);
		assertTrue(deck.getCurrentCardsNumber() == 51);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void デッキからカードを引く時に残り枚数よりも大きな数を引こうとすると例外が投げられる () {
		Deck deck = new Deck(0);
		deck.drawCards(deck.getCurrentCardsNumber() + 1);
	}

}
