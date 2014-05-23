package intermediate;

import java.util.ArrayList;

public class Intermediate2 {

	/**
	 * 連続した重複を取り除いた配列を返す。
	 * 
	 * @param array
	 * @return 連続した重複を除いた配列
	 * @throws NullPointerException
	 */
	public static int[] uniq(int[] array) throws NullPointerException {
		if (array == null) {
			throw new NullPointerException();
		}
		if (array.length == 0) {
			return array;
		}
		
		ArrayList<Integer> uniqList = new ArrayList<Integer>();
		
		uniqList.add(array[0]);
		for (int i = 1; i < array.length; i++) {
			if (uniqList.get(uniqList.size()-1) != array[i]) {
				uniqList.add(array[i]);
			} 
		}
		
		return toArray(uniqList);
	}
	
	private static int[] toArray(ArrayList<Integer> list) {
		int[] array = new int[list.size()]; 
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}

}
