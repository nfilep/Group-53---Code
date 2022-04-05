package Trade;

import Trade.strategies.*;

public class TradingBroker {
	private String name;
	private String[] coinList;
	private double[] coinPriceList;
	private TradingStrategy strategy;
	
	public TradingBroker(String name, String[] coinList, String strategy) {
		this.name = name;
		this.coinList = coinList;
		if(strategy.equals("Strategy-A")) {
			StrategyCreatorA cr = new StrategyCreatorA();
			this.strategy = cr.create();
		} else if(strategy.equals("Strategy-B")) {
			StrategyCreatorA cr = new StrategyCreatorA();
			this.strategy = cr.create();
		} else if(strategy.equals("Strategy-C")) {
			StrategyCreatorA cr = new StrategyCreatorA();
			this.strategy = cr.create();
		} else if(strategy.equals("Strategy-D")) {
			StrategyCreatorA cr = new StrategyCreatorA();
			this.strategy = cr.create();
		}else if(strategy.equals("Strategy-E")) {
			StrategyCreatorA cr = new StrategyCreatorA();
			this.strategy = cr.create();
		}else {
			System.out.println("Fatal error");
			this.strategy = null;
		}
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