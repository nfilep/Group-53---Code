package Trade.strategies;

import java.util.ArrayList;

import Trade.TradeResult;

public class StrategyD extends TradingStrategy {
	
	@Override
	public TradeResult trade(ArrayList<String> coinList, ArrayList<Double> coinPriceList) {
		String date = java.time.LocalDate.now().toString();
		
		if(!coinList.contains("XRP") || !coinList.contains("ADA") || !coinList.contains("SOL"))
			return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		
		else {
			int xrpIndex = coinList.indexOf("XRP");
			int adaIndex = coinList.indexOf("ADA");
			if(coinPriceList.get(adaIndex) < 2.00 && coinPriceList.get(xrpIndex) > 1.00) {
				int solIndex = coinList.indexOf("SOL");
				double solPrice = coinPriceList.get(solIndex);
				
				//Sell 30$ worth of SOL if ADA < $2.00 AND XRP > $1.00
				double quantity = 30/solPrice;
				return new TradeResult(this, "SOL", solPrice, "Sell", quantity, date, true);
			}
			
			else
				return new TradeResult(this, "N/A", null, "N/A", null, date, false);
		}
	}

	@Override
	public String toString() {
		return "Strategy-D";
	}
}
