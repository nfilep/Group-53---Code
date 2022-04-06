package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

public class StrategyD extends TradingStrategy {
	
	@Override
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		if(!coinList.contains("XRP") || !coinList.contains("ADA") || !coinList.contains("SOL"))
			return new TradeResult(this, "N/A", null, "N/A", null, "", false);
		
		else {
			int xrpIndex = coinList.indexOf("XRP");
			int adaIndex = coinList.indexOf("ADA");
			if(coinPriceList.get(adaIndex) < 2.00 && coinPriceList.get(xrpIndex) > 1.00) {
				int solIndex = coinList.indexOf("SOL");
				double solPrice = coinPriceList.get(solIndex);
				
				//Buy 30$ worth of SOL
				
				
				return new TradeResult(this, "SOL", coinPriceList.get(ethIndex), "Buy", 0.5, "", true);
			}
			
			else
				return new TradeResult(this, "N/A", null, "N/A", null, "", false);
		}
	}

	@Override
	public String toString() {
		return "Strategy-D";
	}
}
