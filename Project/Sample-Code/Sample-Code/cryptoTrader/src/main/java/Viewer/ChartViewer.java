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
import Trade.strategies.StrategyCreator;
import User.User;

/**
 * @author
 * this class
 */
public class ChartViewer extends JPanel implements ActionListener{
	/**
	 * tradtton
	 * dtm
	 * table
	 */
	private JButton tradeButton;
	private DefaultTableModel dtm;
	private JTable table;
	
	/**
	 * 
	 */
	public ChartViewer() {
		table = new JTable();
		
		this.setLayout(new BorderLayout());
		
		tradeButton = new JButton("Perform Trade");
		tradeButton.setActionCommand("refresh");
		tradeButton.addActionListener(this);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		
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

		this.add(scrollPane, BorderLayout.NORTH);
		this.add(addRow, BorderLayout.WEST);
		this.add(remRow, BorderLayout.EAST);
		tradeButton.setAlignmentX(CENTER_ALIGNMENT);
		this.add(tradeButton, BorderLayout.SOUTH);

		this.setSize(800,800);
	}

	@Override
	/**
	 * @param e
	 * 	
	 */
	public void actionPerformed(ActionEvent e) {
		int index = 0;
		
		String command = e.getActionCommand();
		if("refresh".equals(command)) {
			for(int count = Bitconnect.systemUser.getNumBrokers(); count < dtm.getRowCount(); count++){
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
					ArrayList<String> coinList = new ArrayList<String>();
					for(int i =0; i < coinNames.length; i++) {
						coinNames[i] = coinNames[i].strip();
						System.out.println(coinNames[i]);
						coinList.add(coinNames[i]);
					}
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					String strategyName = strategyObject.toString();
					StrategyCreator cr = new StrategyCreator();
					boolean success = Bitconnect.systemUser.addBroker(traderName, coinList, cr.create(strategyName)); 
					if(!success) {
						JOptionPane.showMessageDialog(this, "broker with name " + (traderName) + " has already been added" );
						return;
					}
					Bitconnect.systemUser.printBrokerList();
	        }
			System.out.println(Bitconnect.systemUser.getNumBrokers());
			System.out.println("Trade performed");
			Bitconnect.systemUser.performAllTrade();
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
/**
 * For my own testing purposes. 
 * @param args
 */	
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
