package Trade;

import java.util.ArrayList;
import java.util.Arrays;

import Trade.strategies.TradingStrategy;

/** holds informations related to the trade executed
 * @author Isaac
 */
public class TradeResult {
	/**
	 * strategy 		The strategy used from A-E
	 * coinTraded 	The coin traded
	 * coinPrice 	the price of the coin
	 * action    	either buying or selling of coins
	 * quantity  	how many coins traded
	 * timeStamp 	current date of trade
	 * success   	whether trade successful or not
	 */
	 */
	private String trader;
	private TradingStrategy strategy;
	private String coinTraded;
	private Double coinPrice;
	private String action;
	private Double quantity;
	private String timeStamp;
	private boolean success;
	
	/**
	 * constructor class
	 * @param strategy 		The strategy used from A-E
	 * @param coinTraded 	The coin traded
	 * @param coinPrice 	the price of the coin
	 * @param action    	either buying or selling of coins
	 * @param quantity  	how many coins traded
	 * @param timeStamp 	current date of trade
	 * @param success   	whether trade successful or not
	 */
	public TradeResult(TradingStrategy strategy, String coinTraded, Double coinPrice, String action, Double quantity, String timeStamp, boolean success) {
		trader = "N/A";
		this.strategy = strategy;
		this.coinTraded = coinTraded;
		this.coinPrice = coinPrice;
		this.action = action;
		this.quantity = quantity;
		this.timeStamp = timeStamp;
		this.success = success;
	}
	
	/**
	 * @return trader name of the broker's result
	 */
	public String getTrader() {
		return trader;
	}
	
	/**
	 * @param the name of the trader
	 */
	public void setTrader(String trader) {
		this.trader = trader;
	}
	
	/**
	 * @return the date of the trade
	 */
	public String getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * @return strategy used
	 */
	public TradingStrategy getStrategy() {
		return strategy;
	}
	
	/**
	 * @return coinTraded
	 */
	public String getCoinTraded() {
		return coinTraded;
	}
	
	/**
	 * @return coinPrice
	 */
	public Double getCoinPrice() {
		return coinPrice;
	}
	
	/**
	 * @return if trade was successful
	 */
	public boolean getSuccess() {
		return success;
	}
	
	/**
	 * @return all the information from the trade result in an array row
	 */
	public Object[] getData() {
		Object[] row = {trader, strategy.toString(), coinTraded, action, quantity, coinPrice, timeStamp}; 
		return row;
	}
	
}