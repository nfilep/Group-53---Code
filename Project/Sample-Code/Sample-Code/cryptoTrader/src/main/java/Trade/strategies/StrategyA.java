package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

/**
 * 
 * @author Natalie
 *
 */
public class StrategyA extends TradingStrategy {

	/**
	 * @param coinList
	 * @param coinPriceList
	 * @Override
	 * @return
	 */
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		String date = java.time.LocalDate.now().toString();
		
		if(!coinList.contains("BTC") || !coinList.contains("ETH"))
			return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		
		else {
			int btcIndex = coinList.indexOf("BTC");
			if(coinPriceList.get(btcIndex) < 55000.00) {
				
				//Buy 0.5 ETH if BTC < $55000
				int ethIndex = coinList.indexOf("ETH");
				return new TradeResult(this, "ETH", coinPriceList.get(ethIndex), "Buy", 0.5, date, true);
			}
			
			else
				return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		}
	}
	/**
	 * @return 
	 * @Override
	 */
	public String toString() {
		return "Strategy-A";
	}
}
