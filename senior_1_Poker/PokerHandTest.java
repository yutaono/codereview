package senior;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 手札の役が正しく取得できるかテストするクラス
 * @author yutaono
 */
public class PokerHandTest {

	@Test 
	public void ロイヤルストレートフラッシュが出力される() {
		List<Card> hand = new LinkedList<Card>();
		hand.add(new Card(1, Suit.CLUB));
		hand.add(new Card(12, Suit.CLUB));
		hand.add(new Card(13, Suit.CLUB));
		hand.add(new Card(11, Suit.CLUB));
		hand.add(new Card(10, Suit.CLUB));		

		String actual = PokerHand.getName(hand);
		String expected = "ロイヤルストレートフラッシュ";
		assertTrue(actual.equals(expected));	
	}
	
	@Test 
	public void スートが異なればロイヤルストレートフラッシュは出力されない() {
		List<Card> hand = new LinkedList<Card>();
		hand.add(new Card(1, Suit.HEART));
		hand.add(new Card(13, Suit.CLUB));
		hand.add(new Card(12, Suit.CLUB));
		hand.add(new Card(11, Suit.CLUB));
		hand.add(new Card(10, Suit.CLUB));		

		String actual = PokerHand.getName(hand);
		String expected = "ハイカード";
		assertTrue(actual.equals(expected));	
	}

	@Test
	public void ストレートフラッシュが出力される() {
		List<Card> hand = new LinkedList<Card>();
		hand.add(new Card(4, Suit.CLUB));
		hand.add(new Card(3, Suit.CLUB));
		hand.add(new Card(5, Suit.CLUB));
		hand.add(new Card(2, Suit.CLUB));
		hand.add(new Card(1, Suit.CLUB));		

		String actual = PokerHand.getName(hand);
		String expected = "ストレートフラッシュ";
		assertTrue(actual.equals(expected));
	}

	@Test
	public void フラッシュが出力される() {
		List<Card> hand = new LinkedList<Card>();
		hand.add(new Card(1, Suit.CLUB));
		hand.add(new Card(3, Suit.CLUB));
		hand.add(new Card(5, Suit.CLUB));
		hand.add(new Card(7, Suit.CLUB));
		hand.add(new Card(9, Suit.CLUB));		

		String actual = PokerHand.getName(hand);
		String expected = "フラッシュ";
		assertTrue(actual.equals(expected));
	}

	@Test
	public void ストレートが出力される() {
		List<Card> hand = new LinkedList<Card>();
		hand.add(new Card(4, Suit.SPADE));
		hand.add(new Card(3, Suit.DIAMOND));
		hand.add(new Card(5, Suit.CLUB));
		hand.add(new Card(2, Suit.HEART));
		hand.add(new Card(1, Suit.CLUB));		

		String actual = PokerHand.getName(hand);
		String expected = "ストレート";
		assertTrue(actual.equals(expected));
	}

	@Test
	public void フォーオブアカインドが出力される() {
		List<Card> hand = new LinkedList<Card>();
		hand.add(new Card(4, Suit.CLUB));
		hand.add(new Card(4, Suit.DIAMOND));
		hand.add(new Card(4, Suit.HEART));
		hand.add(new Card(4, Suit.SPADE));
		hand.add(new Card(1, Suit.CLUB));	

		String actual = PokerHand.getName(hand);
		String expected = "フォーオブアカインド";
		assertTrue(actual.equals(expected));
	}

	@Test
	public void フルハウスが出力される() {
		List<Card> hand = new LinkedList<Card>();
		hand.add(new Card(4, Suit.CLUB));
		hand.add(new Card(4, Suit.DIAMOND));
		hand.add(new Card(4, Suit.HEART));
		hand.add(new Card(1, Suit.SPADE));
		hand.add(new Card(1, Suit.CLUB));	

		String actual = PokerHand.getName(hand);
		String expected = "フルハウス";
		assertTrue(actual.equals(expected));
	}

	@Test
	public void ツーペアーが出力される() {
		List<Card> hand = new LinkedList<Card>();
		hand.add(new Card(4, Suit.CLUB));
		hand.add(new Card(4, Suit.DIAMOND));
		hand.add(new Card(3, Suit.HEART));
		hand.add(new Card(1, Suit.SPADE));
		hand.add(new Card(1, Suit.CLUB));	

		String actual = PokerHand.getName(hand);
		String expected = "ツーペアー";
		assertTrue(actual.equals(expected));
	}

	@Test
	public void スリーカードが出力される() {
		List<Card> hand = new LinkedList<Card>();
		hand.add(new Card(4, Suit.CLUB));
		hand.add(new Card(4, Suit.DIAMOND));
		hand.add(new Card(4, Suit.HEART));
		hand.add(new Card(3, Suit.SPADE));
		hand.add(new Card(1, Suit.CLUB));	

		String actual = PokerHand.getName(hand);
		String expected = "スリーカード";
		assertTrue(actual.equals(expected));
	}

	@Test
	public void ワンペアが出力される() {
		List<Card> hand = new LinkedList<Card>();
		hand.add(new Card(4, Suit.CLUB));
		hand.add(new Card(4, Suit.DIAMOND));
		hand.add(new Card(8, Suit.HEART));
		hand.add(new Card(2, Suit.SPADE));
		hand.add(new Card(1, Suit.CLUB));	

		String actual = PokerHand.getName(hand);
		String expected = "ワンペア";
		assertTrue(actual.equals(expected));
	}

	@Test
	public void ハイカードが出力される() {
		List<Card> hand = new LinkedList<Card>();
		hand.add(new Card(2, Suit.CLUB));
		hand.add(new Card(8, Suit.DIAMOND));
		hand.add(new Card(13, Suit.HEART));
		hand.add(new Card(5, Suit.SPADE));
		hand.add(new Card(1, Suit.CLUB));

		String actual = PokerHand.getName(hand);
		String expected = "ハイカード";
		assertTrue(actual.equals(expected));
	}
	

}
