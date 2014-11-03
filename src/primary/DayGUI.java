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

import buttonListeners.DayGuiListener;

@SuppressWarnings("serial")
public class DayGUI extends JFrame {

	Day myDay;
	CalendarGUI gui;
	JComboBox<String> combo;

	public DayGUI(Day theDay, CalendarGUI gUI)
	{
		super((DayNames.getDay(theDay.getDate().getName()) + " " + theDay.getDate().getDayNum()));

		myDay = theDay;
		gui = gUI;

		render(this.getContentPane());

		this.pack();
		this.setVisible(true);
	}

	public void render(Container pane)
	{
		//Container pane = aFrame.getContentPane()...
		//String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus aliquet, est nec maximus dignissim, lectus quam cursus ante, in viverra lectus metus sit amet dolor. Quisque non libero dignissim, porttitor ex et, volutpat nunc. Vivamus rutrum massa nunc.";
		String html = "<html><body style='width: 300px'>";
		JLabel label = new JLabel(html + myDay.toString());

		//Make the center component big, since that's the
		//typical usage of BorderLayout.
		label.setPreferredSize(new Dimension(250, 300));
		label.setMaximumSize(new Dimension(200, 10000));
		pane.add(label, BorderLayout.CENTER);

		JPanel OptionPanel = new JPanel();
		OptionPanel.setLayout(new GridLayout(2,3));

		String[] evNum = new String[myDay.getEvents().size()];
		for(int i = 0; i < evNum.length; i++)
		{
			evNum[i] = Integer.toString(1+i);
		}

		combo = new JComboBox<String>(evNum);
		OptionPanel.add(combo);

		JButton op = new JButton("Edit Event");
		op.addActionListener(new DayGuiListener(this,1));
		OptionPanel.add(op);
		op = new JButton("Delete Event");
		op.addActionListener(new DayGuiListener(this,2));
		OptionPanel.add(op);

		op = new JButton("Done");
		op.addActionListener(new DayGuiListener(this,3));
		OptionPanel.add(op);
		op = new JButton("Add Event");
		op.addActionListener(new DayGuiListener(this,4));
		OptionPanel.add(op);

		pane.add(OptionPanel, BorderLayout.PAGE_END);

	}

	public void buttonPress(int function) {
		if(function == 1)
		{
			new EventGUI(myDay,combo.getSelectedIndex(),gui);
			gui.reformat();
			this.hide();
		}
		if(function == 2)
		{
			if(myDay.getEvents().size() != 0)
				myDay.getEvents().remove(0);
			gui.reformat();
			this.hide();
		}
		if(function == 3)
		{
			gui.reformat();
			this.hide();
		}
		if(function == 4)
		{
			new EventGUI(myDay,gui);
			gui.reformat();
			this.hide();
		}

	}

}