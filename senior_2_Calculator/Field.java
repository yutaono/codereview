package senior_2;

/**
 * 体を表すインターフェース.
 * 体とは、四則演算を自由にできる代数的構造を備えた集合である.
 * @author yutaono
 *
 */

public interface Field<T> {
	/** 加減乗除 */
	public abstract void add(T f); 
	public abstract void sub(T f);
	public abstract void mul(T f);
	public abstract void div(T f);	
}
