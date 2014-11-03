package primary;

public enum DayNames {
	Su(0), Mo(1), Tu(2), We(3), Th(4), Fr(5), Sa(6);
	private int value;

	private DayNames(int value) {
		this.value = value;
	}

	public int getVal() {
		return value;
	}

	public static String getDay(int i)
	{
		switch(i){
		case 1: return "Sunday";
		case 2: return "Monday";
		case 3: return "Tuesday";
		case 4: return "Wednesday";
		case 5: return "Thursday";
		case 6: return "Friday";
		case 7: return "Saturday";
		}
		return "";
	}
}
