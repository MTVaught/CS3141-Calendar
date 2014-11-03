package primary;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import buttonListeners.EventEditListener;

@SuppressWarnings("serial")
public class EventGUI extends JFrame{

	JComboBox<String> combo1;
	JComboBox<String> combo2;
	JTextField que1;
	JTextField que2;

	Day theDay;
	int index;
	CalendarGUI GUI;

	public EventGUI(Day inDay, CalendarGUI gui)
	{
		super ("Edit event");

		theDay = inDay;
		index = -1;
		GUI = gui;

		render(this.getContentPane());

		this.pack();
		this.setVisible(true);
	}

	public EventGUI(Day inDay, int selectedIndex, CalendarGUI gui) {
		super ("Edit event");

		theDay = inDay;
		index = selectedIndex;
		GUI = gui;

		render(this.getContentPane());

		this.pack();
		this.setVisible(true);
	}

	public void render(Container pane)
	{
		String [] times = new String[24*6];

		times[0] = "12:00 AM";
		times[1] = "12:10 AM";
		times[2] = "12:20 AM";
		times[3] = "12:30 AM";
		times[4] = "12:40 AM";
		times[5] = "12:50 AM";
		
		for(int i = 1; i <= 11; i ++)
		{
			for(int j = 0; j <= 60; j += 10)
			{
				if(j == 0){
					times[6+((i-1)*6)+(j/10)] = Integer.toString(i) + ":" + Integer.toString(j) + "0 AM";
				}
				else
				{
					times[6+((i-1)*6)+(j/10)] = Integer.toString(i) + ":" + Integer.toString(j) + " AM";
				}
			}
		}

		times[(24*3)] = "12:00 PM";
		times[1+(24*3)] = "12:10 PM";
		times[2+(24*3)] = "12:20 PM";
		times[3+(24*3)] = "12:30 PM";
		times[4+(24*3)] = "12:40 PM";
		times[5+(24*3)] = "12:50 PM";
		
		for(int i = 1; i <= 11; i ++)
		{
			for(int j = 0; j < 60; j += 10)
			{
				if(j == 0){
					times[6+(24*3)+((i-1)*6)+(j/10)] = Integer.toString(i) + ":" + Integer.toString(j) + "0 PM";
				}
				else
				{
					times[6+(24*3)+((i-1)*6)+(j/10)] = Integer.toString(i) + ":" + Integer.toString(j) + " PM";
				}
			}
		}

		//Make the center component big, since that's the
		//typical usage of BorderLayout.
		JPanel InputPanel = new JPanel();
		InputPanel.setLayout(new GridLayout(4,2));
		InputPanel.setPreferredSize(new Dimension(400,150));

		JLabel prompt = new JLabel("Start time:");
		InputPanel.add(prompt);
		combo1 = new JComboBox<String>(times);
		InputPanel.add(combo1);
		
		if(index >= 0)
		{
			combo1.setSelectedIndex(theDay.getEvents().get(index).getStartDate().getTime());
		}

		prompt = new JLabel("End time:");
		InputPanel.add(prompt);
		combo2 = new JComboBox<String>(times);
		InputPanel.add(combo2);
		
		if(index >= 0)
		{
			combo2.setSelectedIndex(theDay.getEvents().get(index).getEndDate().getTime());
		}

		prompt = new JLabel("Description:");
		InputPanel.add(prompt);
		que1 = new JTextField();
		InputPanel.add(que1);
		
		if(index >= 0)
		{
			que1.setText(theDay.getEvents().get(index).getName());
		}

		prompt = new JLabel("Location:");
		InputPanel.add(prompt);
		que2 = new JTextField();
		InputPanel.add(que2);
		
		if(index >= 0)
		{
			que2.setText(theDay.getEvents().get(index).getLocation());
		}

		pane.add(InputPanel, BorderLayout.CENTER);

		JPanel OptionPanel = new JPanel();
		OptionPanel.setLayout(new GridLayout(0,2));

		JButton op = new JButton("Add/Change");
		op.addActionListener(new EventEditListener(this,1));
		OptionPanel.add(op);
		op = new JButton("Cancel");
		op.addActionListener(new EventEditListener(this,2));
		OptionPanel.add(op);

		pane.add(OptionPanel, BorderLayout.PAGE_END);
	}

	public void editEvent(int func) {

		if (func == 1){
			if(index >= 0)
			{
				theDay.getEvents().remove(index);
			}
			
			int time1 = combo1.getSelectedIndex();
			int time2 = combo2.getSelectedIndex();
			
			Date date1 = new Date(theDay.getDate().getMonth(), theDay, theDay.getDate().getYear(), time1, theDay.getDate().getName(), theDay.getDate().getDayNum());
			Date date2 = new Date(theDay.getDate().getMonth(), theDay, theDay.getDate().getYear(), time2, theDay.getDate().getName(), theDay.getDate().getDayNum());

			theDay.addEvent(new Event(date1,date2,que1.getText(),que2.getText()));
			GUI.reformat();
			this.hide();
		}
		else{
			GUI.reformat();
			this.hide();
		}

	}

}
