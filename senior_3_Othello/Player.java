package senior_3;

/**
 * ゲームをするプレイヤーを表現するクラス.
 * 
 * @author yutaono
 */
public class Player {
	private String name;
	private Stone.Color color;
	
	public Player(String name, Stone.Color color) {
		this.name = name;
		this.color = color;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Stone.Color getColor() {
		return this.color;
	}
	
}
