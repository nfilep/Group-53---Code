package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

/**
 * this class implements logic for strategy C that gets Trade result
 * 
 * @author Isaac
 *
 */
public class StrategyC extends TradingStrategy{

	/** It checks if  the coin requested in a Broker coin list contains DOGES
	 * @param coinList			an array list with the list of coins
	 * @param coinPriceList		an array list with all the prices for the coins requested
	 * @Override
	 * @return a new TradeResult object
	 * 
	 */
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		String date = java.time.LocalDate.now().toString();
		
		if(!coinList.contains("DOGE"))
			return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		
		else {
			int dogeIndex = coinList.indexOf("DOGE");
			if(coinPriceList.get(dogeIndex) < 0.20)
				//Buy 10000 DOGE if DOGE < $0.20
				return new TradeResult(this, "DOGE", coinPriceList.get(dogeIndex), "Buy", 10000.0, date, true);
			
			else
				return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		}
	}

	/**
	 * @return the String with the name Strategy-C
	 * @Override
	 */
	public String toString() {
		return "Strategy-C";
	}
}
