package intermediate;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CardTest {

	@Test
	public void トランプのmarkとnumberとvalueが設定できる() {
		Card card = new Card(1, Suit.Club);
		
		assertEquals(card.getMark(), Suit.Club);
		assertEquals(card.getNumber(), 1);
		assertEquals(card.getScore(), 1);
	}
	
	@Test 
	public void ジョーカーが設定できる() {
		Card card = new Card(Suit.Joker);
		
		assertEquals(card.getMark(), Suit.Joker);		
		assertEquals(card.getScore(), 53);
	}
	
	@Test
	public void 同じマークで１から１３で順序を持つ() {
		Card card1 = new Card(1, Suit.Club);
		Card card13 = new Card(13, Suit.Club);
		
		assertTrue(card1.compareTo(card13) < 0);
	}
	
	@Test
	public void 同じ数字でもクラブダイヤハートスペードジョーカーの順序を持つ() {
		Card club1    = new Card(1, Suit.Club);
		Card diamond1 = new Card(1, Suit.Diamond);
		Card heart1   = new Card(1, Suit.Heart);
		Card spade1   = new Card(1, Suit.Spade);
		Card joker    = new Card(1, Suit.Joker);
		
		assertTrue( club1.compareTo(diamond1) < 0);
		assertTrue( diamond1.compareTo(heart1) < 0);
		assertTrue( heart1.compareTo(spade1) < 0);
		assertTrue( spade1.compareTo(joker) < 0);
	}
	
	@Test 
	public void クラブの13よりダイアの1の方が強い () {
		Card club13 = new Card(13, Suit.Club);
		Card diamond1 = new Card(1, Suit.Diamond);
		
		assertTrue(diamond1.compareTo(club13) > 0);
	}
	
	@Test
	public void ソート可能である() {
		Card card1 = new Card(1, Suit.Club);
		Card card2 = new Card(2, Suit.Club);
		List<Card> cardList = new ArrayList<Card>();
		
		cardList.add(card2);
		cardList.add(card1);
		Collections.sort(cardList);
				
		assertTrue(cardList.get(0) == card1);
		assertTrue(cardList.get(1) == card2);
	}
	
}
