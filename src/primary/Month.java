package primary;

public class Month {

	private Day[][] days;
	private int year;
	private int startDay;
	private String name;
	private int totalNumDays;

	public Month(DayNames start, int numDays, int curYear, String aName) {
		totalNumDays = numDays;
		this.name = aName;
		year = curYear;
		days = new Day[7][6];

		int st = start.getVal();
		startDay = start.getVal();

		int dayCounter = 1;
		for (int j = 0; j < 6; j++) {
			for (int i = st; i < 7; i++) {
				Date temp = new Date(this, null, year, 0, i, dayCounter++);
				days[i][j] = new Day(temp);
				temp.setDay(days[i][j]);
				numDays--;
				if (numDays == 0) {
					break;
				}
			}
			if (numDays == 0) {
				break;
			}
			st = 0;
		}
	}

	public int getTotalNumDays() {
		return totalNumDays;
	}

	public String getName() {
		return name;
	}

	public Day[][] getDays() {
		return days;
	}

	public int getYear() {
		return year;
	}

	public void addEvent(Event theEv) {
		int num = theEv.getStartDate().getDayNum();
		int wk = (int) Math.floor(((num + startDay) / 7.0));
		days[theEv.getStartDate().getName()][wk].addEvent(theEv);
	}

	public int getStartDay() {
		return startDay;
	}

	public String toData()
	{
		String ret = "";

		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 7; i++) {
				if(days[i][j] != null)
				{
					ret += days[i][j].toData();
					ret += "\n----\n";
				}
			}
		}

		return ret;
	}

	public void loadDays(String[] input, int day) {

		int index = 0;
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 7; i++) {
				if(days[i][j] != null)
				{
					day--;
					if(day == 0)
					{
						days[i][j].loadData(input);
					}

				}
			}
		}

	}
}