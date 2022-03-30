package Trade.strategies;

import Trade.TradeResult;

public abstract class TradingStrategy {
	public abstract TradeResult trade(String[] coinList, double[] coinPriceList);
}
