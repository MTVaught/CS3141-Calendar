package buttonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import primary.DayGUI;

public class DayGuiListener implements ActionListener {

	private DayGUI GUI;
	int function;
	
	public DayGuiListener (DayGUI gui, int func)
	{
		GUI = gui;
		function = func;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		GUI.buttonPress(function);
	}

}
