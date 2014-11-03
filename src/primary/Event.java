package primary;

public class Event {
	private Date startDate;
	private Date endDate;
	private String name;
	private String location;

	public Event(Date start, Date end, String name, String location) {
		startDate = start;
		endDate = end;
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public String toString()
	{
		return startDate.timeStr() + " - " + endDate.timeStr() + " \n" + name + " \n" + location;
	}
	
	public String toData()
	{
		String ret = "";
		
		ret += startDate.toData();
		ret += "\n";
		ret += endDate.toData();
		ret += "\n";
		ret += name;
		ret += "\n";
		ret += location;
				
		return ret;
	}

}
