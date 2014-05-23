package senior;

import java.util.Comparator;


/**
 * カードの数字のみで大小を比較するためのComparatorを実装したクラス.
 * 
 * @author yutaono
 */
public class CardNumberComparator implements Comparator<Object> {

	public int compare(Object o1, Object o2) {
		Card c1 = (Card)o1;
		Card c2 = (Card)o2;		
		
		return c1.getNumber() - c2.getNumber();		
	}
	
}
