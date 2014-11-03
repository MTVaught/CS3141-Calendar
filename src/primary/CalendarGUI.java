package primary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buttonListeners.AddEventListener;
import buttonListeners.ChangeMonthListener;
import buttonListeners.ViewDayListener;

public class CalendarGUI extends JFrame {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private primary.Month[] months = new Month[5];
	private Integer currentMonth = 0;
	private JLabel displayMonth;
	private DayJPanel[] dayPanel = new DayJPanel[42];

	public CalendarGUI() {
		super(new SimpleDateFormat("MMM dd, yyyy").format(new java.util.Date()));

		months[0] = new Month(DayNames.Fr, 31, 2014, "August");
		months[1] = new Month(DayNames.Mo, 30, 2014, "September");
		months[2] = new Month(DayNames.We, 31, 2014, "October");
		months[3] = new Month(DayNames.Sa, 30, 2014, "November");
		months[4] = new Month(DayNames.Mo, 31, 2014, "December");
		this.setBackground(Color.white);

		loadAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Layout(this.getContentPane());
		this.setPreferredSize(new Dimension(900, 720));
		this.setMinimumSize(new Dimension(880, 700));
		this.pack();
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					PrintWriter out = new PrintWriter(new File("sept.txt"));
					out.println(months[1].toData());
					out.close();
					
					out = new PrintWriter(new File("oct.txt"));
					out.println(months[2].toData());
					out.close();
					
					out = new PrintWriter(new File("nov.txt"));
					out.println(months[3].toData());
					out.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		displayMonth();

	}
	
	public void loadAll()
	{
		String[] days = new String[4];

		try {
			Scanner in = new Scanner(new File("sept.txt"));
			int day = 1;
			while(in.hasNext())
			{
				days[0] = in.nextLine();
				if(days[0].compareTo("") == 0)
				{
					in.nextLine();
					day++;
					continue;
				}
				else if(days[0].compareTo("----") == 0)
				{
					day++;
					continue;
				}
				days[1] = in.nextLine();
				days[2] = in.nextLine();
				days[3] = in.nextLine();
				months[1].loadDays(days, day);
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}

		try {
			Scanner in = new Scanner(new File("oct.txt"));
			int day = 1;
			while(in.hasNext())
			{
				days[0] = in.nextLine();
				if(days[0].compareTo("") == 0)
				{
					in.nextLine();
					day++;
					continue;
				}
				else if(days[0].compareTo("----") == 0)
				{
					day++;
					continue;
				}
				days[1] = in.nextLine();
				days[2] = in.nextLine();
				days[3] = in.nextLine();
				months[2].loadDays(days, day);
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		
		try {
			Scanner in = new Scanner(new File("nov.txt"));
			int day = 1;
			while(in.hasNext())
			{
				days[0] = in.nextLine();
				if(days[0].compareTo("") == 0)
				{
					in.nextLine();
					day++;
					continue;
				}
				else if(days[0].compareTo("----") == 0)
				{
					day++;
					continue;
				}
				days[1] = in.nextLine();
				days[2] = in.nextLine();
				days[3] = in.nextLine();
				months[3].loadDays(days, day);
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		
	}

	public void reformat() {
		displayMonth();
	}

	private void Layout(Container pane) {
		// Create the first two rows on screen
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.white);
		topPanel.setLayout(new GridLayout(2, 0));
		pane.add(topPanel, BorderLayout.PAGE_START);

		// first row
		JPanel titleRow = new JPanel();
		titleRow.setBackground(Color.white);
		topPanel.add(titleRow);
		titleRow.setLayout(new GridLayout(0, 4));
		// Month navigation and label
		JPanel monthLabel = new JPanel();
		monthLabel.setBackground(Color.white);
		titleRow.add(monthLabel);
		JButton temp;
		monthLabel.add(temp = new JButton("<"));

		temp.addActionListener(new ChangeMonthListener(-1, this));
		monthLabel.add(temp = new JButton(">"));
		temp.addActionListener(new ChangeMonthListener(1, this));
		monthLabel.add(displayMonth = new JLabel("MONTH"));
		// insert blank panels
		// titleRow.add(new JPanel());
		JPanel tempPanel;
		titleRow.add(tempPanel = new JPanel());
		tempPanel.setBackground(Color.white);
		titleRow.add(tempPanel = new JPanel());
		tempPanel.setBackground(Color.white);
		// Display year
		JPanel year = new JPanel();
		year.setBackground(Color.white);
		year.setLayout(new BorderLayout());
		year.add(new JLabel("2014  "), BorderLayout.EAST);
		titleRow.add(year);

		// second row
		JPanel weekLabels = new JPanel();
		weekLabels.setBackground(Color.white);
		weekLabels.setBorder(BorderFactory.createLineBorder(Color.black));
		weekLabels.setLayout(new GridLayout(0, 7));
		topPanel.add(weekLabels);
		weekLabels.add(new JLabel(" Sunday"));
		weekLabels.add(new JLabel(" Monday"));
		weekLabels.add(new JLabel(" Tuesday"));
		weekLabels.add(new JLabel(" Wednesday"));
		weekLabels.add(new JLabel(" Thursday"));
		weekLabels.add(new JLabel(" Friday"));
		weekLabels.add(new JLabel(" Saturday"));

		// Make Calendar Grid
		JPanel grid = new JPanel();
		grid.setBackground(Color.white);
		grid.setLayout(new GridLayout(6, 7));
		pane.add(grid, BorderLayout.CENTER);
		generateGrid(grid);

	}


	private void generateGrid(JPanel grid) {
		for (int i = 0; i < 7 * 6; i++) {
			grid.add(createSquare(i, null));
		}
	}

	private void displayMonth() {

		if(currentMonth< 1){
			currentMonth++;
		}
		if(currentMonth > 3){
			currentMonth--;
		}
		Month tempMonth = months[currentMonth];
		displayMonth.setText(tempMonth.getName());
		for (int i = 0; i < tempMonth.getStartDay(); i++) {
			dayPanel[i].deactivate(months[currentMonth-1].getTotalNumDays());
		}
		Day[][] days = tempMonth.getDays();
		int j = 7 * 5;
		for (int i = tempMonth.getStartDay(); i < tempMonth.getStartDay()
				+ tempMonth.getTotalNumDays(); i++) {
			dayPanel[i].setDate(days[i % 7][i / 7].getDate());
			j = i;
		}
		for (int i = j + 1; i < 7 * 6; i++) {
			dayPanel[i].deactivate(i-j);
		}

	}

	private JPanel createSquare(int gridVal, Date date) {
		DayJPanel rtn = new DayJPanel(gridVal, this);
		dayPanel[gridVal] = rtn;
		return rtn;
	}

	public boolean changeMonth(int amount) {
		try {
			months[amount + currentMonth].getClass();
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		currentMonth += amount;
		return true;
	}

	private class DayJPanel extends JPanel {

		private JLabel day;
		private JLabel eventMsg;
		private JButton addEvent;
		private JButton viewDay;
		private Date date;
		private ViewDayListener vdl;
		private AddEventListener ael;
		private JPanel events;
		private JPanel bottom;

		public DayJPanel(int gridVal, CalendarGUI GUI) {
			super();
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			this.setLayout(new BorderLayout());
			this.add(day = new JLabel(" " + gridVal), BorderLayout.PAGE_START);

			events = new JPanel();
			events.setLayout(new FlowLayout());
			events.add(eventMsg = new JLabel(0 + " Event(s)"));
			this.add(events, BorderLayout.CENTER);

			bottom = new JPanel();
			this.add(bottom, BorderLayout.PAGE_END);
			bottom.setLayout(new FlowLayout());
			bottom.add(addEvent = new JButton("+"));
			bottom.add(viewDay = new JButton("View"));
			addEvent.addActionListener(ael = new AddEventListener(null, GUI));
			viewDay.addActionListener(vdl = new ViewDayListener(null, GUI));

			setColor(Color.white);
		}

		private void setColor(Color color) {
			this.setBackground(color);
			events.setBackground(color);
			bottom.setBackground(color);
		}

		public void setDate(Date newDate) {
			this.date = newDate;
			// System.out.println(date.getDayNum());
			day.setText(" " + date.getDayNum());
			eventMsg.setText(date.getDay().getEvents().size() + " Event(s)");
			addEvent.setVisible(true);
			viewDay.setVisible(true);
			vdl.setDay(date.getDay());
			ael.setDay(date.getDay());

			setColor(Color.white);
		}

		public void deactivate(int dateNumber) {
			setColor(Color.gray);
			date = null;
			day.setText(" " + dateNumber);
			addEvent.setVisible(false);
			viewDay.setVisible(false);
			eventMsg.setText("");
		}

	}


}
