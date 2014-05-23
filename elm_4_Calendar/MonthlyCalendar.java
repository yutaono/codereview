import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

/**
 * MonthlyCalendarは、ユーザから受け取った年月からカレンダーを表示するプログラムである。
 * 2次元配列calenderMatrixにカレンダー情報を格納する.
 *
 * @author 大野裕太
 * @copyright by Yuta Ono.
 */
public class MonthlyCalendar {
    private int year; 
    private int month;

    private static final int MAX_COLUMN = 7; // calenderMatrixの行数
    private static final int MAX_ROW = 6; // calenderMatrixの列数
    private int[][] calendarMatrix = new int[MAX_ROW][MAX_COLUMN]; // カレンダー情報
    private static final String[] DAYS_OF_WEEK = {"日", "月", "火", "水", "木", "金", "土"}; // 曜日
    
    /**
     * カレンダー表オブジェクトを作成.
     *
     * @param year 西暦年
     * @param month 月
     */
    public MonthlyCalendar(int year, int month) {
        this.year = year;
        this.month = month;
        init();
    }

    /**
     * calendarMatrixを設定する
     *
     */
    private void init() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(this.year, this.month-1, 1);

        int startDay = cal.get(Calendar.DAY_OF_WEEK) - 1;  // その月の1日の曜日(0:日曜)
        int lastDate = cal.getActualMaximum(Calendar.DATE); // その月の最後の日にち

        int row = startDay; 
        int column = 0;
        for (int i = 1; i <= lastDate; i++) {
            calendarMatrix[column][row] = i;
            if (row >= 6) {
                row = 0;
                column++;
            } else {
                row++;
            }
        }
        
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.year).append("年").append(this.month).append("月のカレンダーを出力します\n");
        sb.append(this.year).append("年").append(String.format("%2d", this.month)).append("月\n");

        /** 曜日を設定 */
        for (String d: DAYS_OF_WEEK) {
            sb.append(d).append(" ");
        }        
        sb.append("\n");

        /** 日にちを設定 */
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                if(calendarMatrix[i][j] == 0) {
                    sb.append("   ");
                } else {
                    sb.append(String.format("%2d ", calendarMatrix[i][j]));
                }
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("カレンダーを出力します");
        System.out.println("年を入力してください（例：2014）");

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            /** 年の入力*/
            String line = reader.readLine();
            int year = Integer.parseInt(line);
            if (year <= 0) {
                System.out.println("年は１以上の自然数を入力してください。");
                return;
            }

            /** 月の入力 */
            System.out.println("月を入力してください（例：4）");
            line = reader.readLine();
            int month = Integer.parseInt(line);
            if (month < 1 || month > 12) {
                System.out.println("月は１〜１２の間で入力してください。");
                return;
            }
                        
            System.out.println(new MonthlyCalendar(year, month));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("数値の形式が正しくありません。");
        }
    }
}
