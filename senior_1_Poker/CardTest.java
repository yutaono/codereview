package senior;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class CardTest {

	@Test
	public void トランプのSuitとnumberとvalueが設定できる() {
		Card card = new Card(1, Suit.CLUB);
		
		assertEquals(card.getSuit(), Suit.CLUB);
		assertEquals(card.getNumber(), 1);
		assertEquals(card.getScore(), 1);
	}
	
	@Test 
	public void ジョーカーが設定できる() {
		Card card = new Card(Suit.JOKER);
		
		assertEquals(card.getSuit(), Suit.JOKER);		
		assertEquals(card.getScore(), 53);
	}
	
	@Test
	public void 同じマークで１から１３で順序を持つ() {
		Card card1 = new Card(1, Suit.CLUB);
		Card card13 = new Card(13, Suit.CLUB);
		
		assertTrue(card1.compareTo(card13) < 0);
	}
	
	@Test
	public void 同じ数字でもクラブダイヤハートスペードジョーカーの順序を持つ() {
		Card club1    = new Card(1, Suit.CLUB);
		Card diamond1 = new Card(1, Suit.DIAMOND);
		Card heart1   = new Card(1, Suit.HEART);
		Card spade1   = new Card(1, Suit.SPADE);
		Card joker    = new Card(1, Suit.JOKER);
		
		assertTrue( club1.compareTo(diamond1) < 0);
		assertTrue( diamond1.compareTo(heart1) < 0);
		assertTrue( heart1.compareTo(spade1) < 0);
		assertTrue( spade1.compareTo(joker) < 0);
	}
	
	@Test 
	public void クラブの13よりダイアの1の方が強い () {
		Card club13 = new Card(13, Suit.CLUB);
		Card diamond1 = new Card(1, Suit.DIAMOND);
		
		assertTrue(diamond1.compareTo(club13) > 0);
	}
	
	@Test
	public void ソート可能である() {
		Card card1 = new Card(1, Suit.CLUB);
		Card card2 = new Card(2, Suit.CLUB);
		Card card3 = new Card(3, Suit.CLUB);
		List<Card> cardList = new ArrayList<Card>();
		
		cardList.add(card2);
		cardList.add(card3);		
		cardList.add(card1);
		Collections.sort(cardList);
				
		assertTrue(cardList.get(0) == card1);
		assertTrue(cardList.get(1) == card2);
		assertTrue(cardList.get(2) == card3);		
	}
	
	@Test
	public void CardNumberComparatorを使えば番号のみでソート可能である() {
		Card club1 = new Card(1, Suit.CLUB);
		Card heart2 = new Card(2, Suit.HEART);
		Card diamond3 = new Card(3, Suit.DIAMOND);
		Card spade4 = new Card(4, Suit.SPADE);
		List<Card> cardList = new ArrayList<Card>();
		
		cardList.add(heart2);
		cardList.add(spade4);
		cardList.add(diamond3);
		cardList.add(club1);
		Collections.sort(cardList, new CardNumberComparator());
				
		assertTrue(cardList.get(0) == club1);
		assertTrue(cardList.get(1) == heart2);
		assertTrue(cardList.get(2) == diamond3);	
		assertTrue(cardList.get(3) == spade4);	
	}
	
}
