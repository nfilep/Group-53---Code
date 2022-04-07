package Viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

import MainUI.MainUI;
import Trade.*;
import Trade.strategies.*;
import Bitconnect.Bitconnect;

/**
 * This class is responsible for generating the bar chart. This chart keeps track of how many trades each
 * broker performed, and using what strategy.
 * @author Natalie Filep
 */
public class HistogramViewer extends JPanel implements iViewer {
	private final String[] STRATEGIES = {"Strategy A","Strategy B","Strategy C","Strategy D","Strategy E"}; 
	DefaultCategoryDataset dataset;
	CategoryPlot plot;
	
	/**
	 * Constructor which sets up the panel, the chart, and everything needed for displaying.
	 */
	public HistogramViewer() {
		dataset = new DefaultCategoryDataset();
		
		plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		// Initialize the empty dataset (so it's blank when starting up)
		for(int i = 0; i < STRATEGIES.length; i++) {
			dataset.setValue(0, "", STRATEGIES[i]);
		}
		
		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		NumberAxis rangeAxis = new NumberAxis("Actions(Buys or Sells)");
		
		rangeAxis.setTickUnit(new NumberTickUnit(5));
		rangeAxis.setRange(new Range(0, 30));
		plot.setRangeAxis(rangeAxis);
		
		// Get rid of legend
		plot.getRenderer().setSeriesVisibleInLegend(0, Boolean.FALSE, true);
		
		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);
		
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		
		// Stops the user from being able to zoom in and out
		chartPanel.setRangeZoomable(false);
		chartPanel.setDomainZoomable(false);
		
		this.add(chartPanel);
		this.setSize(600, 300);
        this.setVisible(true);
	}
	
	/**
	 * If the trade is successful, update the histogram by updating the count. Otherwise,
	 * display a message in a pop-up window indicating the trade has failed.
	 * @param result
	 */
	@Override
	public void draw(TradeResult result) {
		if(result.getSuccess()) {
			
			TradingBroker broker = Bitconnect.systemUser.getBroker(result.getTrader());
			dataset.setValue(broker.getNumSuccessfulTrades(),result.getTrader(), STRATEGIES[getStrategyIndex(result.getStrategy())]);
			
			plot.setDataset(0, dataset);
		}else {
			JOptionPane.showMessageDialog(this, "Trade failed.");
			return;
		}
	}
	
	/**
	 * This private helper method turns the strategy into an integer index to be used for adding values
	 * to a specific location in the dataset.
	 * @param strategy
	 * @return int index that is where the data to be put in the dataset
	 */
	private int getStrategyIndex(TradingStrategy strategy) {
		String str = strategy.toString();
		return str.charAt(str.length()-1) - 65;
	}
}
