package MainUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Viewer.ChartViewer;
import Viewer.HistogramViewer;
import Viewer.TableViewer;
import cryptoTrader.utils.DataVisualizationCreator;

public class MainUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static MainUI instance; // This makes sure it follows the singleton design pattern
	
	private HistogramViewer h;
	private TableViewer t;
	private ChartViewer c;
	
	// Uses singleton method
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {
		super("Bitconnect"); 		// Set window title
		this.getContentPane().setLayout(new GridLayout(2,2));
//		frame = new JFrame();
		
		h = new HistogramViewer();
		t = new TableViewer();
		c = new ChartViewer();
		
		this.getContentPane().add(t);
		this.getContentPane().add(c);
		this.getContentPane().add(h);

		this.setPreferredSize(new Dimension(1920, 1080));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public void updateStats(JComponent component) {
		//stats.add(component);
		//stats.revalidate();
	}

	// used to be main
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
