package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

public class StrategyC extends TradingStrategy{

	@Override
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		if(!coinList.contains("DOGE"))
			return new TradeResult(this, null, 0, null, 0, "", false);
		
		else {
			int dogeIndex = coinList.indexOf("DOGE");
			if(coinPriceList.get(dogeIndex) < 0.20)
				return new TradeResult(this, "DOGE", coinPriceList.get(dogeIndex), "Buy", 10000, "", true);
			
			else
				return new TradeResult(this, null, 0, null, 0, "", false);
		}
	}

	@Override
	public String toString() {
		return "Strategy-C";
	}
}
