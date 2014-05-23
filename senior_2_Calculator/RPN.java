package senior_2;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 中置記法を逆ポーランド記法(Reverse Polish Notation, RPN)に変換し、計算するクラス.
 * 
 * @author yutaono
 *
 */
public class RPN {
	private List<String> rpn;

	/**
	 * @param infixNotation 中置記法の数式
	 */
	public RPN(String infixNotation) {
		rpn = RPNParserFromInfix(infixNotation);
	}

	/**
	 * 逆ポーランド記法を計算した結果を返す.
	 * @return 有理数文字列の計算結果
	 */
	public Rational calc() {
		Stack<Rational> stack = new Stack<Rational>(); 

		for (String n : rpn) {
			if (isOperator(n)) {
				Rational o1 = stack.pop();
				Rational o2 = stack.pop();
				
				switch (n) {
				case "+":
					o1.add(o2);
					stack.push(o1);
					break;
				case "-":
					o2.sub(o1);
					stack.push(o2);
					break;
				case "*":
					o1.mul(o2);
					stack.push(o1);	
					break;
				case "/":
					o2.div(o1);
					stack.push(o2);
					break;
				default:
					break;
				}
			} else {
				stack.push(new Rational(Integer.parseInt(n)));
			}
		}

		return stack.pop();
	}
	
	/**
	 * String型の式の要素を分解するメソッド.
	 * (例)
	 * "1 * 3 + 2" -> ["1", "*", "3", "+", "2"]
	 * @param expression 
	 * @return
	 */
	private List<String> toElementsFromExpression(String expression) {
		List<String> list = new LinkedList<String>();

		String tempNumber = "";
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if ('0' <= c && c <= '9') {
				tempNumber += c + "";
			} else {
				if (tempNumber.length() > 0) {
					list.add(tempNumber);
					tempNumber = "";
				}

				if (c != ' ') {
					list.add(c + "");
				}
			}
		}

		if (tempNumber.length() > 0) {
			list.add(tempNumber);
		}	

		return list;		
	}

	/**
	 * 中置記法の数式を逆ポーランド記法の数式に変換する.
	 * @param infix String型の中置記法
	 * @return 逆ポーランド記法の数式リスト
	 */
	private List<String> RPNParserFromInfix(String infix) {
		List<String> infixList = toElementsFromExpression(infix);
		Stack<String> tmpOperators = new Stack<String>();
		List<String> rpnList = new LinkedList<String>();

		for (String l : infixList) {		
			if (l.equals("*") || l.equals("/")) {
				tmpOperators.push(l);
			} else if (l.equals("+") || l.equals("-")) {
				if ( !tmpOperators.empty() 
						&& (tmpOperators.peek().equals("*") || tmpOperators.peek().equals("/"))) {
						rpnList.add(tmpOperators.pop());					
				}
				
				tmpOperators.push(l);
			} else if (l.equals("(")) {
				tmpOperators.push(l);
			} else if (l.equals(")")) {
				while(!tmpOperators.peek().equals("(")) {
					rpnList.add(tmpOperators.pop());
				}
				tmpOperators.pop(); // "("をpop
			} else {
				rpnList.add(l);
			}
		}

		while (tmpOperators.size() != 0) { 
			rpnList.add(tmpOperators.pop());
		}

		return rpnList;
	}
	
	/**
	 * sが演算子かどうか調べる.
	 * @return 演算子ならtrue、違うならfalse
	 */
	private boolean isOperator(String s) {
		if (s.length() >= 2) {
			return false;
		}

		switch (s) {
		case "+":
		case "-":
		case "*":
		case "/":			
			return true; 
		default:
			break;
		}
		
		return false;
	}

}
