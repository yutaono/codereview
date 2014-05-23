package senior_3;

/**
 * オセロの石を表現するクラス.
 * ●は黒、○は白.
 * 
 * @author yutaono
 */
public class Stone {

	/** 石の色の列挙型 */
	public static enum Color {
		NON("　", "NON"), WHITE("○", "BLACK"), BLACK("●", "WHITE");
		
		public final String mark;
		public final String inverse;
		
		public final Color inverse() {
			return Color.valueOf(inverse);
		}
		
		Color(String mark, String inverse) {
			this.mark= mark;
			this.inverse = inverse;
		}

		@Override
		public String toString() {
			return mark;
		}
	}	

	private Color color;		

	public Stone() {
		this.color = Color.NON;
	}

	public Stone(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * 自分の色と反対の色を返すメソッド.
	 * @return 反対の色
	 */
	public Color reverse() {
		this.color = this.color.inverse();
		return this.color;
	}

}
