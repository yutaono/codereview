package senior_2;

/**
 * コマンドライン引数で受け取った中置記法の計算式を
 * 逆ポーランド記法によって計算する.
 * 
 * 実行例：(引数 -> 結果)
 * 1+2*3 -> 7
 * (1+2)*3 -> 9
 * 2/3+2*3 -> 20/3
 * "1 + 2 * 3" -> 7
 * -d 2/3+2*3 -> 6.666666666666667
 * 
 * @author yutaono
 */
public class Calculator {
	
	/**
	 * メインメソッド
	 * @param args optionと数式文字列
	 */
	public static void main(String[] args) {
		// コマンドライン引数のチェック
		if (args.length == 0) {
			System.out.println("数式を入力してください。");
		}
		
		if (args[0].equals("-d")) {
			// 実数で表示
			RPN rpn = new RPN(args[1]);
			System.out.println(rpn.calc().toString("d"));
		} else if (args.length == 2) {
			System.out.println("オプションが無効です。");
		} else {
			// 有理数で表示
			RPN rpn = new RPN(args[0]);
			System.out.println(rpn.calc());
		}		 
	}
}
