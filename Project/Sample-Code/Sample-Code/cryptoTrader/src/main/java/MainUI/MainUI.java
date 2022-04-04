package MainUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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

import cryptoTrader.utils.DataVisualizationCreator;

public class MainUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static MainUI instance; // This makes sure it follows the singleton design pattern
	private JPanel stats, chartPanel, tablePanel;

	// Should be a reference to a separate object in actual implementation
	private List<String> selectedList;

	private JTextArea selectedTickerList;
	private JTextArea tickerText;
	private JTextArea BrokerText;
	private JComboBox<String> strategyList;
	private Map<String, List<String>> brokersTickers = new HashMap<>();
	private Map<String, String> brokersStrategies = new HashMap<>();
	private List<String> selectedTickers = new ArrayList<>();
	private String selectedStrategy = "";
	private DefaultTableModel dtm;
	private JTable table;
	
	private HistogramViewer h;

	// Uses singleton method
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {
		super("Bitconnect"); 		// Set window title

		JPanel north = new JPanel(); // Set top bar
		JPanel south = new JPanel();		
		
		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("refresh");
		trade.addActionListener(this);
		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		//scrollPane.setPreferredSize(new Dimension(800, 300));
		
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");
		strategyNames.add("Strategy-D");
		strategyNames.add("Strategy-E");
		
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		table.setFillsViewportHeight(true);
		
		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);

		// Set charts region
		/*JPanel west = new JPanel();
		//west.setPreferredSize(new Dimension(900, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);*/

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		//getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
	}

	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	// used to be main
	public static void run() {
		JFrame frame = MainUI.getInstance();
		//frame.setSize(1920, 1080);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("refresh".equals(command)) {
			for (int count = 0; count < dtm.getRowCount(); count++){
					Object traderObject = dtm.getValueAt(count, 0);
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						return;
					}
					String traderName = traderObject.toString();
					Object coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					String[] coinNames = coinObject.toString().split(",");
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					String strategyName = strategyObject.toString();
					System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName);
	        }
			stats.removeAll();
			System.out.println("Trade performed");
		} else if ("addTableRow".equals(command)) {
			dtm.addRow(new String[3]);
		} else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
	}
	
}
