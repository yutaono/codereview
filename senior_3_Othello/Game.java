package senior_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * オセロゲームをするクラス.
 * 
 * @author yutaono
 *
 */
public class Game {
	public static void main(String[] args) {
		GameField field = new GameField();	
		Player playerA = new Player("A", Stone.Color.BLACK);
		Player playerB = new Player("B", Stone.Color.WHITE);

		Game game = new Game();
		game.start(field, playerA, playerB);	
	}

	/**
	 * ゲームを進行するメソッド.
	 * @param field
	 * @param playerA
	 * @param playerB
	 */
	public void start(GameField field, Player playerA, Player playerB) {
		boolean inning = true; /** trueは表回(playerAのターン)、falseは裏回(playerBのターン) */

		while(true) {
			/** 石を置く場所がなくなれば、勝者を表示し終了する. */
			if (field.countStones(Stone.Color.NON) <= 0 
					|| field.countStones(Stone.Color.BLACK) <= 0
					|| field.countStones(Stone.Color.WHITE) <= 0
					) {
				System.out.println(field);
				showWinner(field, playerA, playerB);
				break;
			}

			/** Playerを選択 */
			Player player = (inning) ? playerA : playerB;  

			System.out.println(field);

			String input = "";
			try {
				input = userInput(player);

				if (Coordinate.isCorrectCoordinate(input)) {
					Coordinate coordinate = new Coordinate(input);
					field.putStone(player.getColor(), coordinate);
				} else if (input.equals("00")) { /** パス */
					if (field.canPutStone(player.getColor())) {
						System.out.println("パスできません！おける場所があります。");
						continue;
					}

					inning = !inning;			
					continue;	
				} else if (input.equals("99")) { /** 降参 */
					showLoser(player);
					break;
				} else if (input.length() == 2) {
					System.out.println("不正な値[" + input + "]が入力されました。入力しなおしてください。");
					continue;
				} else {
					System.out.println("入力桁数が不正です。a1からh8までの範囲の値を入力してください。");
					continue;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalCoordinateException e) {
				System.out.println("\"" + input + "\"には置けません！");
				continue;
			} 

			inning = !inning;			
		}
	}

	/**
	 * ユーザの入力を受け取り、返すメソッド.
	 * @param player
	 * @return
	 * @throws IOException
	 */
	private String userInput(Player player) throws IOException {
		String name = player.getName();
		String color = player.getColor().toString();
		System.out.println(name + "さん、" + color 
				+ "石を置いてください 例）a1 ※パスは\"00\"、降参は\"99\"");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = reader.readLine();
		return line;
	}

	/**
	 * 勝者を表示するメソッド.
	 * 引き分けなら引き分けと表示する.
	 * @param field
	 * @param playerA
	 * @param playerB
	 */
	private void showWinner(GameField field, Player playerA, Player playerB) {
		int playerAStoneNumber = field.countStones(playerA.getColor());
		int playerBStoneNumber = field.countStones(playerB.getColor());

		Player winner;
		if (playerAStoneNumber == playerBStoneNumber) {
			System.out.println("引き分けでした！");
			return;
		} else if (playerAStoneNumber > playerBStoneNumber) {
			winner = playerA;
		} else {
			winner = playerB;
		}

		System.out.println(playerA.getColor() + "が" + 
				playerAStoneNumber + "個 " +
				playerB.getColor() + "が" + 
				playerBStoneNumber + "個で、" +
				winner.getName() + "の勝ち！");
	}

	/**
	 * 敗者を表示するメソッド.
	 * @param player
	 */
	private void showLoser(Player player){
		System.out.println(player.getName() + "さんの負けです。");
	}

}
