package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

public class StrategyA extends TradingStrategy {

	@Override
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		if(!coinList.contains("BTC") || !coinList.contains("ETH"))
			return new TradeResult(this, null, 0, null, 0, "", false);
		
		else {
			int btcIndex = coinList.indexOf("BTC");
			if(coinPriceList.get(btcIndex) < 55000) {
				int ethIndex = coinList.indexOf("ETH");
				return new TradeResult(this, "ETH", coinPriceList.get(ethIndex), "Buy", 0.5, "", true);
			}
			
			else
				return new TradeResult(this, null, 0, null, 0, "", false);
		}
	}

	@Override
	public String toString() {
		return "Strategy-A";
	}
}
