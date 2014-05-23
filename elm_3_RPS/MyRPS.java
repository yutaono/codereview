import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * MyRPSは、コンピュータとユーザがじゃんけんをするプログラムである.
 * 結果があいこであれば、勝敗がつくまで続ける.
 * また、1,2,3以外の入力の際には再入力を促す.
 *
 * @author 大野裕太
 * @copyright by Yuta Ono.
 */
public class MyRPS {


    /**
     * じゃんけんの手を表す列挙型.
     */
    enum Hand {
        ROCK("グー", 0), SISSORS("チョキ", 1), PAPER("パー", 2);
        private final String name;
        private final int number;
        private Hand(String name, int number) {
            this.name = name;
            this.number = number;
        }

        /**
         * int型から対応するHand型を返すメソッド.
         * 
         * @param number 
         * @return numberにマッチするHand型
         */
        public static Hand numberToHand(int number){
            switch (number) {
            case 0:
                return Hand.ROCK;
            case 1:
                return Hand.SISSORS;
            case 2:
                return Hand.PAPER;
            default:
                return null;
            } 
        }
        
        /**
         * "グー"、"チョキ"等を返す.
         */ 
        @Override
        public String toString() {
            return this.name;
        }
    }

    /**
     * 1回ずつのじゃんけんの結果を表す列挙型.
     */
    enum Result {
        USER_WIN, USER_LOST, DRAW;

        /**
         * 2つのHand型の引数を受け取りResult型の結果を返す.
         *
         * @param user ユーザの手
         * @param system コンピュータの手
         * @return Result型の勝負結果
         */
        public static Result getResultByHands(Hand user, Hand system) {
            if (user == system) {
                return DRAW;
            } else if ((system.number + 1) % 3 == user.number) {
                return USER_LOST;
            } else {
                return USER_WIN;
            }
        }
    }

    /**
     * ユーザからの入力にあった列挙型Handを返すメソッド.
     */
    private Hand inputUserHand(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int handNumber = -1;

        String line = "";
        while (!(handNumber >= 0 && handNumber <= 2)) {
            try {
                System.out.print("出す手を入力 => ");
                line = reader.readLine();
                handNumber = Integer.parseInt(line) - 1;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                continue;
            }
        }

        return Hand.numberToHand(handNumber); 
    }

    /**
     * じゃんけんゲームをするメソッド.
     * 勝敗がつくまで繰り返されます.
     */
    public void playGame() {
        System.out.println("じゃんけんをしましょう！");
        System.out.println("1: グー、2: チョキ、3: パー です。");
        System.out.println("じゃーんけーん・・");
        
        Result previousResult = null;
        
        /** resultがResult.DRAW以外になったらループを抜ける */
        while(true) { 
            Hand userHand   = inputUserHand();
            Hand systemHand = Hand.numberToHand((int)(Math.random() * 3));
            Result result   = Result.getResultByHands(userHand, systemHand);
		
            System.out.println((previousResult == Result.DRAW) ? "しょ！" : "ぽん！"); // 前回があいこなら"しょ！"
            previousResult = result;
            
            System.out.println("あなた：" + userHand + "、コンピュータ：" + systemHand);
			
            if (result == Result.DRAW) {
                System.out.println("あーいこーで・・");
                continue;
            } else if (result == Result.USER_WIN) {
                System.out.println("あなたの勝ちです！");
                break;
            } else {
                System.out.println("あなたの負けです！");
                break;
            }
        }
    }

    public static void main(String[] args) {
        MyRPS myRPS = new MyRPS();
        myRPS.playGame();
    }
}
