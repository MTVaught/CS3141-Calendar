package buttonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import primary.CalendarGUI;

public class ChangeMonthListener implements ActionListener {
	private int increment;
	private CalendarGUI GUI;

	public ChangeMonthListener(int increment, CalendarGUI GUI) {
		super();
		this.increment = increment;
		this.GUI = GUI;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		GUI.changeMonth(increment);
		GUI.reformat();
	}

}
