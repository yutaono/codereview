/**
 * MyEratosthenesは、2からユーザの入力した数字までの素数を列挙するプログラムである。
 * アルゴリズムは、「acdエラストネテスの篩い」を用いている。
 *
 * @author 大野裕太
 * @copyright by Yuta Ono.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;

public class MyEratosthenes {
    private int max; // この数まで素数を探索する
    private BitSet primes; // 素数のリスト


    /**
     * MyEratosthenesのコンストラクタである。
     *
     * @param max この数まで素数を探索する
     */
    public MyEratosthenes(int max) {
        this.max = max;
        primes = new BitSet(max+1);
        sieve();
    }

    /**
     * 素数を探索する。
     */
    private void sieve() {
        primes.set(0, max+1); // 全てをtrueにする
        primes.set(0, false); // 0は素数でない
        primes.set(1, false); // 1は素数でない

        for(int i = 2; i <= (int)Math.sqrt(max); i++) {
            if (!primes.get(i)) { 
                continue;
            }

            for (int j = 2; j * i <= max + 1; j++) { 
                primes.set(j * i, false);
            }
        }        
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        for(int j = 0; j < primes.length(); j++) {
            if(primes.get(j)) {
                builder.append(j + " ");
            }
        }

        return builder.toString();
    }
    
    
    public static void main(String[] args) {
        System.out.println("素数を出力するプログラムです。");
        System.out.println("リストの最大値を整数で入力してください");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int max;

        try {
            while (true) {
                line = br.readLine();
                max = Integer.parseInt(line);
                if (max > 0) {
                    break;
                } else {
                    System.out.println("自然数を入力してください");
                }
            }

            MyEratosthenes myEratosthenes = new MyEratosthenes(max);
            System.out.println(myEratosthenes);                
        } catch (NumberFormatException e) {
            System.out.println("不正な値が入力されました。");
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } 
    }
}
