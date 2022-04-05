package Trade;

import Trade.strategies.TradingStrategy;

public class TradingBroker {
	private String name;
	private String[] coinList;
	private double[] coinPriceList;
	private TradingStrategy strategy;
	
	public TradingBroker(String name, String[] coinList, TradingStrategy strategy) {
		this.name = name;
		this.coinList = coinList;
		this.strategy = strategy;
		coinPriceList = null;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getCoinList() {
		return coinList;
	}
	
	public double[] getCoinPriceList() {
		return coinPriceList;
	}
	
	public TradingStrategy getStrategy() {
		return strategy;
	}
	
	public void setCoinPriceList(double[] coinPriceList) {
		this.coinPriceList = coinPriceList;
	}
}