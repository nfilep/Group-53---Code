package Viewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Bitconnect.Bitconnect;
import Trade.*;
import User.User;

public class ChartViewer extends JPanel implements ActionListener{
	private List<String> selectedList;
	private JButton tradeButton;

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
	
	public ChartViewer() {
		table = new JTable();
		
		this.setLayout(new BorderLayout());
		//GridBagConstraints c = new GridBagConstraints();
		
		tradeButton = new JButton("Perform Trade");
		tradeButton.setActionCommand("refresh");
		tradeButton.addActionListener(this);

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
		
		//this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.NORTH);
		//JPanel buttons = new JPanel();
		//buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		//buttons.add(addRow);
		//buttons.add(remRow);
		this.add(addRow, BorderLayout.WEST);
		this.add(remRow, BorderLayout.EAST);
		tradeButton.setAlignmentX(CENTER_ALIGNMENT);
		this.add(tradeButton, BorderLayout.SOUTH);

		// Set charts region
		/*JPanel west = new JPanel();
		//west.setPreferredSize(new Dimension(900, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);*/
		
		//frame.add(scrollPane);
		this.setSize(800,800);
		//this.setPreferredSize(new Dimension(800,800));
		//this.setBounds(960, 0, 960, 1080);
		//this.pack();
        //this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = 0;
		
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		int count = 0;
		if("refresh".equals(command) && index < count) {
			for(count = index; count < dtm.getRowCount(); count++){
					Object traderObject = dtm.getValueAt(count, 0);
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						return;
					}
					String traderName = traderObject.toString();
					System.out.println(traderName);
					Object coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					String[] coinNames = coinObject.toString().split(",");
					for(int i =0; i < coinNames.length; i++) {
						System.out.println(coinNames[i]);
					}
					//System.out.println(coinNames);
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					String strategyName = strategyObject.toString();
					boolean success = Bitconnect.systemUser.addBroker(traderName, coinNames, strategyName); 
					if(!success) {
						JOptionPane.showMessageDialog(this, "broker with name " + (traderName) + " has already been added" );
						return;
					}else {
						index++;
					}
					Bitconnect.systemUser.printBrokerList();
	        }
			System.out.println(Bitconnect.systemUser.getBrokerList().size());
			System.out.println("Trade performed");
		} else if ("addTableRow".equals(command)) {
			dtm.addRow(new String[3]);
		} else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				dtm.removeRow(selectedRow);
				Bitconnect.systemUser.removeBroker(selectedRow);
				Bitconnect.systemUser.printBrokerList();
			}
		}
	}
	
	public static void main(String[] args) {
		ChartViewer c = new ChartViewer();
		JFrame frame = new JFrame();
		frame.getContentPane().add(c);
		frame.setSize(new Dimension(800,800));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
