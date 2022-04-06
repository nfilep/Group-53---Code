package Trade;

import java.util.ArrayList;

import Trade.strategies.*;

public class TradingBroker {
	/**
	 * 
	 */
	private String name;
	private ArrayList<String> coinList;
	private ArrayList<Double> coinPriceList;
	private TradingStrategy strategy;
	
	public TradingBroker(String name, ArrayList<String> coinList, TradingStrategy strategy) {
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
	
	public ArrayList<String> getCoinList() {
		return coinList;
	}
	
	public void addCoin(String coin) {
		coinList.add(coin);
	}
	
	public ArrayList<Double> getCoinPriceList() {
		return coinPriceList;
	}
	
	public TradingStrategy getStrategy() {
		return strategy;
	}
	
	public void setCoinPriceList(ArrayList<Double> coinPriceList) {
		this.coinPriceList = coinPriceList;
	}
	
	public TradeResult performTrade() {
		TradeResult result = strategy.trade(coinList, coinPriceList);
		result.setTrader(name);
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.name.equals(((TradingBroker)o).getName());
	}
}