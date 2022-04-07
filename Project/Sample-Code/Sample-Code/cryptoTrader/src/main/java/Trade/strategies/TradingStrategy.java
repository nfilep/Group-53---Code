package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;


/**abstract class that represents a TradingStrategy
 * @author Isaac
 */
public abstract class TradingStrategy {
	public abstract TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList);
}
