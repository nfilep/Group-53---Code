package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

/**
 * this class implements logic for strategy B that gets Trade result
 * 
 * @author Isaac
 *
 */
public class StrategyB extends TradingStrategy {

	/**  It checks if  the coin requested in a Broker coin list contains 
	 * BTC or not ETH or not ADA
	 * @param coinList			an array list with the list of coins
	 * @param coinPriceList		an array list with all the prices for the coins requested
	 * @Override
	 * @return a new TradeResult object
	 * 
	 */
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		String date = java.time.LocalDate.now().toString();
		
		if(!coinList.contains("BTC") || !coinList.contains("ETH") || !coinList.contains("ADA"))
			return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		
		else {
			int btcIndex = coinList.indexOf("BTC");
			int ethIndex = coinList.indexOf("ETH");
			if(coinPriceList.get(btcIndex) >= 55000.00 && coinPriceList.get(ethIndex) >= 4000.00) {
				
				//Sell 300 ADA if BTC >= $55000 AND ETH >= $4000
				int adaIndex = coinList.indexOf("ADA");
				return new TradeResult(this, "ADA", coinPriceList.get(adaIndex), "Sell", 300.0, date, true);
			}
			
			else
				return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		}
	}
	/**
	 * @return the String with the name Strategy-B
	 * @Override
	 */
	public String toString() {
		return "Strategy-B";
	}
	
}
