package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

/**
 * this class implements logic for strategy A 
 * 
 * @author Isaac
 *
 */
public class StrategyA extends TradingStrategy {

	/** It checks if  the coin requested in a Broker coin list contains 
	 * BTC 	or not ETH
	 * @param coinList			an array list with the list of coins
	 * @param coinPriceList		an array list with all the prices for the coins requested
	 * @Override
	 * @return a new TradeResult object
	 * 
	 */
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		String date = java.time.LocalDate.now().toString();

		if (!coinList.contains("BTC") || !coinList.contains("ETH"))
			return new TradeResult(this, "N/A", null, "N/A", null, date, false);

		else {
			int btcIndex = coinList.indexOf("BTC");
			if (coinPriceList.get(btcIndex) < 55000.00) {

				// TO-DO Buy 0.5 ETH if BTC < $55000
				int ethIndex = coinList.indexOf("ETH");
				return new TradeResult(this, "ETH", coinPriceList.get(ethIndex), "Buy", 0.5, date, true);
			}

			else
				return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		}
	}

	/**
	 * @return the String with the name Strategy-A
	 * @Override
	 */
	public String toString() {
		return "Strategy-A";
	}
}
