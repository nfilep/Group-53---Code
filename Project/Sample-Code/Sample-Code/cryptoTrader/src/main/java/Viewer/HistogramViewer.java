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

public class HistogramViewer extends JPanel implements iViewer {
	private final String[] STRATEGIES = {"Strategy A","Strategy B","Strategy C","Strategy D","Strategy E"}; 
	DefaultCategoryDataset dataset;
	CategoryPlot plot;
	
	public HistogramViewer() {
		dataset = new DefaultCategoryDataset();
		
		plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();
		
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
		chartPanel.setPreferredSize(new Dimension(700, 400));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		
		// Stops the user from being able to zoom in and out
		chartPanel.setRangeZoomable(false);
		chartPanel.setDomainZoomable(false);
		
		this.add(chartPanel);
		this.setSize(700, 400);
        this.setVisible(true);
	}
	
	@Override
	public void draw(TradeResult result) {
		if(result.getSuccess()) {
			dataset.setValue(1,result.getTrader(), STRATEGIES[getStrategyIndex(result.getStrategy())]);
			plot.setDataset(0, dataset);
		}else {
			JOptionPane.showMessageDialog(this, "Trade failed.");
			return;
		}
	}
	
	private int getStrategyIndex(TradingStrategy strategy) {
		String str = strategy.toString();
		return 65 - str.charAt(str.length()-1);
	}
	
	public static void main(String[] args) {
		HistogramViewer t = new HistogramViewer();
		JFrame frame = new JFrame();
		frame.add(t);
		frame.pack();
		frame.setVisible(true);
		
		TradingStrategy stratA = new StrategyA();
		String[] traderNames = {"Natalie", "Anusha", "Ewere", "Isaac", "Kostas"};
		String[] coins = {"BTC", "ETH"};
		double[] prices = {1.00, 2.50};
		boolean[] passFail = {true,true,true,true,true};
		
		TradeResult testResult;
		for(int i = 0; i < traderNames.length; i++) {
			testResult = new TradeResult(traderNames[i], stratA, coins, prices, "buy", 100, "2022/04/02", passFail[i]);
			t.draw(testResult);
		}
	}
}
