package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

/**
 * this class implements logic for strategy E that gets Trade result
 * 
 * @author Isaac
 *
 */
public class StrategyE extends TradingStrategy{
	
	/** It checks if  the coin requested in a Broker coin list contains AVAX or not SHIB
	 * @param coinList			an array list with the list of coins
	 * @param coinPriceList		an array list with all the prices for the coins requested
	 * @Override
	 * @return a new TradeResult object
	 * 
	 */
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		String date = java.time.LocalDate.now().toString();
		
		if(!coinList.contains("AVAX") || !coinList.contains("SHIB"))
			return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		
		else {
			int avaxIndex = coinList.indexOf("AVAX");
			
			//Buy SHIB worth as much as the current price of AVAX if AVAX <= $110.00
			if(coinPriceList.get(avaxIndex) <= 110.00) {
				int shibIndex = coinList.indexOf("SHIB");
				double shibPrice = coinPriceList.get(shibIndex);
				double avaxPrice = coinPriceList.get(shibIndex);
				
				double quantity = avaxPrice/shibPrice;
				
				return new TradeResult(this, "SHIB", coinPriceList.get(shibIndex), "Buy", quantity, date, true);
			}
			
			else
				return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		}
	}

	/**
	 * @return the String with the name Strategy-E
	 * @Override
	 */
	public String toString() {
		return "Strategy-E";
	}
	
}
