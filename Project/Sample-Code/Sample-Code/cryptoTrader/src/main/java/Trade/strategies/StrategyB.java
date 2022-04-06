package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

public class StrategyB extends TradingStrategy {

	@Override
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		if(!coinList.contains("BTC") || !coinList.contains("ETH") || !coinList.contains("ADA"))
			return new TradeResult(this, null, 0, null, 0, "", false);
		
		else {
			int btcIndex = coinList.indexOf("BTC");
			int ethIndex = coinList.indexOf("ETH");
			if(coinPriceList.get(btcIndex) >= 55000 && coinPriceList.get(ethIndex) >= 4000) {
				int adaIndex = coinList.indexOf("ADA");
				return new TradeResult(this, "ADA", coinPriceList.get(adaIndex), "Buy", 300, "", true);
			}
			
			else
				return new TradeResult(this, null, 0, null, 0, "", false);
		}
	}

	@Override
	public String toString() {
		return "Strategy-B";
	}
	
}
