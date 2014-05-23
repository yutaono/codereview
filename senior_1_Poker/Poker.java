package senior;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * ポーカーゲームをするクラス.
 * ランダムな手札5枚からユーザが選んだカードを交換し、役を表示する.
 * Jokerは使用しない.
 * 
 * @author yutaono
 */
public class Poker {
	private Deck deck;
	private Player player;

	public Poker() {
		deck = new Deck(/*jokerNum = */ 0); 
		player = new Player();
	}
	
	public static void main(String[] args) {
		Poker poker = new Poker();
		poker.start();
	}
	
	public void start() {
		System.out.println("交換するカードの番号を入力してください(例：135)。");
		System.out.println("0を入力するとカードを交換しません。");
		
		player.setHand(deck.drawCards(5));
		player.showHand(":");

		int[] cardNumbers = inputExchangeNumbers();
		// 0以外の数字であればカードを交換する
		if (cardNumbers[0] != 0) {
			player.draw(cardNumbers, deck);
		}

		player.showHand();
		System.out.println("役は　" + PokerHand.getName(player.getHand()) + "　でした。");
	}

	private int[] inputExchangeNumbers() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> exchangeNumbers = new ArrayList<Integer>();

		String line = "";
		while (true) {
			// 入力が1〜5文字で0を含まない時にループから抜ける。
			try { 
				line = reader.readLine();
				if (line.length() == 0) {
					throw new NumberFormatException();
				} else if (line.length() > 5) {
					System.out.println("5枚以上は交換できません。");
					continue;
				} else if (line.length() > 1 && line.contains("0")) {
					System.out.println("0番のカードはありません。");
					continue;
				}
				
				for (int i = 0; i < line.length(); i++) {
					exchangeNumbers.add(Integer.parseInt("" + line.charAt(i)));
				}
				break;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("入力は数値のみです。");
				continue;
			}
		}

		return MyUtility.toArray(exchangeNumbers);
	}	
}
