package buttonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import primary.EventGUI;

public class EventEditListener implements ActionListener {

	private EventGUI GUI;
	int function;
	
	public EventEditListener (EventGUI gui, int func)
	{
		GUI = gui;
		function = func;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		GUI.editEvent(function);
	}

}
