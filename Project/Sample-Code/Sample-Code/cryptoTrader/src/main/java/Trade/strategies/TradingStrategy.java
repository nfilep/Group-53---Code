package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

public abstract class TradingStrategy {
	public abstract TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList);
}
