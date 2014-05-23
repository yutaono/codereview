/**
 * コマンドライン引数から入力された数列をヒストグラムとして表示するプログラム
 *
 * @author yutaono
 */ 

public class MyHistogram {
    private int max;
    private int n;
    private int[] graph; 

    public MyHistogram(int[] graph){
        this.max = maxNumberOfArray(graph);
        this.n = graph.length;
        this.graph = graph;
    }

    /**
     * ヒストグラムを表示する
     */
    public void show() {
        for(int i = 0; i < this.max; i++) {
            for(int j = 0; j < this.n; j++) {
                if (graph[j] >= max - i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     *  最大値を求める
     *  @param arr
     */
    private int maxNumberOfArray(int[] arr) {
        int max = arr[0];
        for(int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("グラフにプロットする値を引数に指定してください。");
            System.out.println("例）java Histogram 1 2 3 3 2 1");
            return;
        }

        int[] graph = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            try {
                graph[i] = Integer.parseInt(args[i]);
                if (graph[i] < 0) {
                    System.out.println("引数に指定できるのは正の数だけです。");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("引数に指定できるのは数値のみです。");
                return;
            }
        }

        // ヒストグラムを表示
        MyHistogram myHistogram = new MyHistogram(graph);
        myHistogram.show();
    }
}
