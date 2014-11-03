package buttonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import primary.CalendarGUI;
import primary.Day;
import primary.DayGUI;

public class ViewDayListener implements ActionListener {
	private Day theDay;
	private CalendarGUI GUI;

	public ViewDayListener(Day theDay, CalendarGUI GUI) {
		this.theDay = theDay;
		this.GUI = GUI;
	}

	public void setDay(Day newDay) {
		theDay = newDay;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: Call the view day command
		//System.out.println("DO STUFF " + theDay.getDate().getDayNum());
		new DayGUI(theDay, GUI);
	}

}
