package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

public class StrategyE extends TradingStrategy{
	
	@Override
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		String date = java.time.LocalDate.now().toString();
		
		if(!coinList.contains("AVAX") || !coinList.contains("SHIB"))
			return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		
		else {
			int avaxIndex = coinList.indexOf("AVAX");
			
			//Buy SHIB worth as much as the current price of AVAX if AVAX <= $110.00
			if(coinPriceList.get(avaxIndex) <= 110.00) {
				int shibIndex = coinList.indexOf("SHIB");
				double shibPrice = coinPriceList.get(shibIndex);
				double avaxPrice = coinPriceList.get(shibIndex);
				
				double quantity = avaxPrice/shibPrice;
				
				return new TradeResult(this, "SHIB", coinPriceList.get(shibIndex), "Buy", quantity, date, true);
			}
			
			else
				return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		}
	}

	@Override
	public String toString() {
		return "Strategy-E";
	}
	
}
