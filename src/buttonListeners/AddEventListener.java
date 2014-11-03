package buttonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import primary.CalendarGUI;
import primary.Day;
import primary.EventGUI;

public class AddEventListener implements ActionListener {

	private Day theDay;
	private CalendarGUI GUI;

	public AddEventListener(Day theDay, CalendarGUI GUI) {
		this.theDay = theDay;
		this.GUI = GUI;
	}

	public void setDay(Day newDay) {
		theDay = newDay;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new EventGUI(theDay,GUI);
	}

}
