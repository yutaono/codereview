package senior;

import java.util.List;

public class MyUtility {
	
	/**
	 * 整数型のリストを整数型配列に変換して返すメソッド.
	 * @param list
	 * @return
	 */
	public static int[] toArray(List<Integer> list) {
		int[] array = new int[list.size()];

		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		
		return array;
	}
	
}
