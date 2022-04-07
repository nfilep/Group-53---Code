package Trade;

import java.util.ArrayList;

import Trade.strategies.*;

/**
 * Returns a TradeResult object depending on the information obtained by performing a trade 
 * @author Isaac
 */
public class TradingBroker {
	/**
	 * name			the broker's name
	 * coinList		an arrayList of coins added
	 * strategy		 the type of strategy invoked
	 * numSuccessful Trades number of successful trades
	 */
	private String name;
	private ArrayList<String> coinList;
	private ArrayList<Double> coinPriceList;
	private TradingStrategy strategy;
	private int numSuccessfulTrades;
	
	/**
	 * @param name   	 the name of the coin
	 * @param coinList	 an arraylist of coins
	 * @param strategy   the type of strategy invoked
	 * 
	 */
	public TradingBroker(String name, ArrayList<String> coinList, TradingStrategy strategy) {
		this.name = name;
		this.coinList = coinList;
		this.strategy = strategy;
		coinPriceList = new ArrayList<Double>();
		numSuccessfulTrades = 0;
	}
	
	/**
	 * @return name of broker
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return coinList
	 */
	public ArrayList<String> getCoinList() {
		return coinList;
	}
	
	/**
	 * adds the coin to the coinList
	 * @param coin the name of the coin
	 */
	public void addCoin(String coin) {
		coinList.add(coin);
	}
	
	/**
	 * @return coinPriceList
	 */
	public ArrayList<Double> getCoinPriceList() {
		return coinPriceList;
	}
	
	/**
	 * @param price
	 * adds a specific price to coinPriceList
	 */
	public void addPrice(Double price) {
		coinPriceList.add(price);
	}
	
	/**
	 * @return strategy  (strategy used)
	 */
	public TradingStrategy getStrategy() {
		return strategy;
	}
	
	/**
	 * initiates the strategy for a single broker
	 * @return result (used in displaying)
	 */
	public TradeResult performTrade() {
		TradeResult result = strategy.trade(coinList, coinPriceList);
		result.setTrader(name);
		if(result.getSuccess()) {
			numSuccessfulTrades++;
		}
		return result;
	}
	
	/**
	 * @return number of successful trade
	 */
	public int getNumSuccessfulTrades() {
		return numSuccessfulTrades;
	}
	
	@Override
	/**
	 *
	 * @param o
	 *  equal method
	 * compares the two trading the brokers
	 * @return true or false
	 */
	public boolean equals(Object o) {
		return this.name.equals(((TradingBroker)o).getName());
	}
}