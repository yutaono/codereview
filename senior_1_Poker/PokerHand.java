package senior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 5枚のカードからポーカーのハンド(役)が何であるか返すためのクラス.
 * 
 * @author yutaono
 */
public class PokerHand {

	/**
	 * このクラスはインスタンス化できない.
	 */
	private PokerHand() {}

	/**
	 * 5枚のカードからポーカのハンド(役)が何であるか返す.
	 * @param hand 5枚のカード
	 * @return String型の
	 */
	public static String getName(List<Card> hand) {
		if (hand.size() != 5) {
			throw new IllegalArgumentException("カードは5枚！");
		}

		boolean isStraight = isStraight(hand);
		boolean isFlush = isFlush(hand); 
		boolean isRoyalStraight = isRoyalStraight(hand); 

		if (isRoyalStraight && isFlush) {
			return "ロイヤルストレートフラッシュ";
		} else if (isStraight && isFlush) {
			return "ストレートフラッシュ";
		} else if (isFlush) {
			return "フラッシュ";
		} else if (isStraight) {
			return "ストレート";
		} 

		int[] getCountNumbers = getCountNumbersOfSameNumberCards(hand);

		if (getCountNumbers.length == 2) { // 例 : [1,4], [2,3]
			if (getCountNumbers[0] == 1) {
				return "フォーオブアカインド";
			} else if (getCountNumbers[0] == 2) {
				return "フルハウス";
			} else {
			}
		} else if (getCountNumbers.length == 3) { // 例 : [1,2,2], [1,1,3]
			if (getCountNumbers[2] == 2) {
				return "ツーペアー";	
			} else if (getCountNumbers[2] == 3) {
				return "スリーカード";
			} else {
			}
		} else if (getCountNumbers.length == 4) { // 例 : [1,2,3,5,5]
			return "ワンペア";
		} 

		return "ハイカード";	

	}

	private static boolean isFlush(List<Card> hand) {
		int count = 1;
		
		Suit suit = hand.get(0).getSuit();
		for (int i = 1; i < hand.size(); i++) {
			if (suit == hand.get(i).getSuit()) {
				count++;
			}
		}
		
		return (count == 5);
	}

	private static boolean isStraight(List<Card> hand) {
		List<Card> sortedCards = hand;
		Collections.sort(sortedCards, new CardNumberComparator());
		
		int startNumber = sortedCards.get(0).getNumber();
		for (int i = 1; i < sortedCards.size(); i++) {
			if (sortedCards.get(i).getNumber() == startNumber + i) {
				continue;
			} else {
				return false;
			}
		}

		return true;
	}

	private static boolean isRoyalStraight(List<Card> hand) {
		List<Card> sortedCards = hand;
		Collections.sort(sortedCards, new CardNumberComparator());

		if (sortedCards.get(0).getNumber() != 1)  return false;
		if (sortedCards.get(1).getNumber() != 10) return false;
		if (sortedCards.get(2).getNumber() != 11) return false;
		if (sortedCards.get(3).getNumber() != 12) return false;
		if (sortedCards.get(4).getNumber() != 13) return false;

		return true;
	}
	
	/**
	 * 同じ数字のカードの枚数の配列を返すメソッド.
	 * 枚数の配列は昇順にソートされる.
	 * 
	 * 例)　(sortedCardsは手札handが数字の昇順にソートされたカードリスト) 
	 * sortedCards = {スペード1, スペード2, ハート2, ハート4, スペード4}
	 *     -> {1, 2, 2}    
	 * sortedCards = {スペード1, ダイア1, ハート1, クローバー1, スペード4}
	 *     -> {1, 4}   
	 *     
	 * @param hand
	 * @return
	 */
	private static int[] getCountNumbersOfSameNumberCards(List<Card> hand) {
		List<Card> sortedCards = hand; 
		Collections.sort(sortedCards, new CardNumberComparator()); 
		List<Integer> countNumbers = new ArrayList<Integer>();

		int previousNumber = sortedCards.get(0).getNumber();
		int count = 1;
		for (int i = 1; i < sortedCards.size(); i++) {
			int currentNumber = sortedCards.get(i).getNumber();
			if (currentNumber == previousNumber) {
				count++;
			} else {	
				previousNumber = sortedCards.get(i).getNumber();
				countNumbers.add(count);
				count = 1;
			}
		}
		countNumbers.add(count);
		Collections.sort(countNumbers);
		
		return MyUtility.toArray(countNumbers);
	}

}
