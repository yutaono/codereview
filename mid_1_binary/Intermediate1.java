package intermediate;

public class Intermediate1 {
	
	/**
	 * 10進数を入力として受け取り、2進数表現の文字列を返す。
	 * 
	 * @param src 2進数表現にしたい10進数
	 * @return String srcを2新数表現の文字列
	 * @throws IllegalArgumentException 負の数が入力された時に投げる。
	 */
	public static String toBinaryString(int src) throws IllegalArgumentException {		
		if (src < 0) {
			throw new IllegalArgumentException();
		}
		if (src == 0) {
			return "0";
		}
		
		StringBuilder builder = new StringBuilder();
				
		while(src > 0) {
			builder.insert(0, src % 2);
			src /= 2;
		}
		
		return builder.toString();
	}

}
