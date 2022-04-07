package Viewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Trade.TradeResult;
import Trade.strategies.*;
/**
 * @author
 * This class generates a table that displays the trading activity for each user.
 * When the perform trade button is pressed, all the brokers activities get updated on the table.
 * It extends JPanel because it will be added to a JFrame (MainUI)
 */
public class TableViewer extends JPanel implements iViewer{
	/**
	 * columnNames The names of the columns in the trading log
	 * table The JTable object that holds the log
	 * model The model for the table
	 */
	private final Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
	private JTable table;
	private DefaultTableModel model;
	
	/**
	 * Constructor that sets up the table panel.
	 */
	public TableViewer() {
		table = new JTable();
		table.setEnabled(false);
		model = new DefaultTableModel();
		model.addRow(columnNames);
		
		for(int i = 0; i < columnNames.length; i++) {
			model.addColumn(columnNames[i]);
		}
		
		model.removeRow(0);
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
			
		scrollPane.setPreferredSize(new Dimension(600, 300));
		this.add(scrollPane);
		this.setSize(500, 200);
        this.setVisible(true);
	}
	
	/**
	 * This method displays the new result by adding the result as a new row to the table.
	 * @param result
	 */
	@Override
	public void draw(TradeResult result) {
		Object[] displayData = result.getData();
		model.addRow(displayData);
		table.setModel(model);
	}
}
