package primary;

import java.util.ArrayList;

public class Day {

	private Date date;
	ArrayList<Event> events;

	public Day(Date temp) {
		date = temp;
		events = new ArrayList<Event>();
	}

	public void addEvent(Event e) {
		events.add(e);
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public Date getDate() {
		return date;
	}

	public String toString()
	{
		String ret = "";

		for(int i = 0; i < events.size(); i++)
		{
			ret += events.get(i).toString();
			ret += "<br>";
		}

		return ret;
	}

	public String toData()
	{
		String ret = "";

		for(int i = 0; i <  events.size(); i++)
		{
			ret += events.get(i).toData();
			if(i+1 < events.size())
			{
				ret += "\n";
			}
		}

		return ret;
	}

	public void loadData(String[] ind) {
		Date st = new Date(date.getMonth(),this,date.getYear(),Integer.parseInt(ind[0]),date.getName(),date.getDayNum());
		Date end = new Date(date.getMonth(),this,date.getYear(),Integer.parseInt(ind[1]),date.getName(),date.getDayNum());
		Event temp = new Event(st,end,ind[2],ind[3]);
		addEvent(temp);
	}

}
