package staticValues;

public enum MineFieldSettings{
	MIN_HEIGHT("10"),
	MIN_WIDTH("10"),
	MAX_HEIGHT("50"),
	MAX_WIDTH("50"),
	PRESET_HEIGHT("15"),
	PRESET_WIDTH("15"),
	PRESET_DIFFICULTY("0.1"), // type double constructor parameters for Minefield 
	EASY_DIFFICULTY("0.05"),
	MEDIUM_DIFFICULTY("0.1"),
	HARD_DIFFICULTY("0.2"),
	EXTRA_HARD_DIFFICULTY("0.25");
	
	private String value;
	
	private MineFieldSettings(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
