package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

public class StrategyC extends TradingStrategy{

	@Override
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		String date = java.time.LocalDate.now().toString();
		
		if(!coinList.contains("DOGE"))
			return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		
		else {
			int dogeIndex = coinList.indexOf("DOGE");
			if(coinPriceList.get(dogeIndex) < 0.20)
				//Buy 10000 DOGE if DOGE < $0.20
				return new TradeResult(this, "DOGE", coinPriceList.get(dogeIndex), "Buy", 10000.0, date, true);
			
			else
				return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		}
	}

	@Override
	public String toString() {
		return "Strategy-C";
	}
}
