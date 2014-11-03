package primary;

public class Date {
	private Month theMonth;
	private Day theDay;
	private int year;
	private int time;
	private int name;
	private int dayNum;

	public Date(Month month, Day day, int yr, int tm, int nm, int daynm) {
		theMonth = month;
		theDay = day;
		year = yr;
		time = tm;
		name = nm;
		dayNum = daynm;
	}

	public Month getMonth() {
		return theMonth;
	}

	public Day getDay() {
		return theDay;
	}

	public int getYear() {
		return year;
	}

	public int getTime() {
		return time;
	}

	public int getName() {
		return name;
	}

	public int getDayNum() {
		return dayNum;
	}

	public void setDay(Day d) {
		theDay = d;
	}
	
	public String toString()
	{
		String ret = "";
		ret = (theMonth.getName() + " " + Integer.toString(dayNum) + " " + DayNames.getDay(name));
		ret += (" " + Integer.toString(year) + "\n");
		return Integer.toString(time);
	}
	
	public String timeStr()
	{
		String ret = "";
		int temp = 0;
		temp = time%72;
		
		if ((temp/6) == 0)
		{
			ret += "12";
		}
		else
		{
			ret += Integer.toString((temp/6));
		}
		
		temp = temp%6;
		
		if(temp == 0)
		{
			ret += ":00";
		}
		else
		{
			ret += ":" + Integer.toString((temp*10));
		}
		
		if(time > 71)
		{
			ret += " PM";
		}
		else
		{
			ret += " AM";
		}
		
		return ret;
	}

	public String toData()
	{
		//String ret = Integer.toString(year) + " " + Integer.toString(time) + " " + Integer.toString(name) + " " + Integer.toString(dayNum) ;
		String ret = Integer.toString(time);
		return ret;
	}

}