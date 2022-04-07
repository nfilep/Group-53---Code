package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

/**
 * this class implements logic for strategy D that gets Trade result
 * 
 * @author Isaac
 *
 */
public class StrategyD extends TradingStrategy {
	
	/** It checks if  the coin requested in a Broker coin list 
	 * contains XRP or not ADA or not SOL
	 * @param coinList			an array list with the list of coins
	 * @param coinPriceList		an array list with all the prices for the coins requested
	 * @Override
	 * @return a new TradeResult object
	 * 
	 */
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		String date = java.time.LocalDate.now().toString();
		
		if(!coinList.contains("XRP") || !coinList.contains("ADA") || !coinList.contains("SOL"))
			return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		
		else {
			int xrpIndex = coinList.indexOf("XRP");
			int adaIndex = coinList.indexOf("ADA");
			if(coinPriceList.get(adaIndex) < 2.00 && coinPriceList.get(xrpIndex) > 1.00) {
				int solIndex = coinList.indexOf("SOL");
				double solPrice = coinPriceList.get(solIndex);
				
				//Sell 30$ worth of SOL if ADA < $2.00 AND XRP > $1.00
				double quantity = 30/solPrice;
				return new TradeResult(this, "SOL", solPrice, "Sell", quantity, date, true);
			}
			
			else
				return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		}
	}

	/**
	 * @return the String with the name Strategy-D
	 * @Override
	 */
	public String toString() {
		return "Strategy-D";
	}
}
