package enums;

public enum Numbers {

	TWO(2),
	THREE(3), // 1
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUEEN(10),
	KING(10),
	ACE(11);
	
	public final int VALUE;
	Numbers(int value) {
		this.VALUE = value;
	}
	
}
