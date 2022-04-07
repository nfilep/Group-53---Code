package MainUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Viewer.*;

public class MainUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static MainUI instance; // This makes sure it follows the singleton design pattern
	
	public static HistogramViewer h;
	public static TableViewer t;
	public static ChartViewer c;
	
	// Uses singleton method
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {
		super("Bitconnect"); 		// Set window title
		this.setLayout(new GridLayout(1,2));
		
		JPanel leftSide = new JPanel(new GridLayout(2,1));
		
		h = new HistogramViewer();
		t = new TableViewer();
		c = new ChartViewer();
		leftSide.add(t);
		leftSide.add(h);
		
		JPanel rightSide = new JPanel();
		rightSide.add(c);
		
		this.add(leftSide);
		this.add(rightSide);

		this.setPreferredSize(new Dimension(1920, 1080));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public void updateStats(JComponent component) {
		//stats.add(component);
		//stats.revalidate();
	}

	public static void main(String[] args) {
		MainUI mainTest = MainUI.getInstance();
	}
	
	public static void run() {
		JFrame frame = MainUI.getInstance();
		//frame.setSize(1920, 1080);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//String command = e.getActionCommand();
	}
	
}
