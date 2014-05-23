import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * MyRPSは、コンピュータとユーザがじゃんけんをするプログラムである.
 *
 * @author 大野裕太
 * @copyright by Yuta Ono.
 */

public class MyRPS {
    /** じゃんけんの手 */
    private final static String[] HANDS = {"グー", "チョキ", "パー"};

    /**
     * ユーザの入力を受け付ける.
     *
     * @return ユーザの入力をint型で返す
     */
    private static int userHand() {
        System.out.print("出す手を入力 => ");

        int handNumber = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = reader.readLine(); 
            handNumber = Integer.parseInt(line) - 1;

            if (handNumber < 0 || handNumber > 2) {
                System.out.println("1～3で入力してください！");
                System.exit(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("1～3で入力してください！");
            System.exit(1);
        }

        return handNumber;
    }


    /**
     * 勝ち負け判定をし、試合結果を返す.
     *
     * @param userHand
     * @param systemHand
     * @return じゃんけんの試合結果をString型で返す。
     */
    private static String rpsResult(int userHand, int systemHand) {
        StringBuilder builder = new StringBuilder();

        builder.append("ぽん！\n");
        builder.append("あなた：" + HANDS[userHand] + "、コンピュータ：" + HANDS[systemHand] + "\n");

        /** 勝ち負け判定 */
        if (systemHand == userHand) {
            builder.append("あいこでした！");
        } else if ((systemHand + 1) % 3 == userHand) {
            builder.append("あなたの負けです！");
        } else {
            builder.append("あなたの勝ちです！");
        }

        return builder.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("じゃんけんをしましょう！");
        System.out.println("1: グー、2: チョキ、3: パー です。");
        System.out.println("じゃーんけーん・・");
        
        int systemHand = (int)(Math.random() * 3);
        System.out.println(rpsResult(userHand(), systemHand));
       
    }
}
