package Trade;

import java.util.ArrayList;
import java.util.Arrays;

import Trade.strategies.*;

public class TradeTest {
	public static void main(String[] args) {
		ArrayList<String> coinList = new ArrayList<String>();
		coinList.add("BTC");
		coinList.add("ETH");
		
		ArrayList<Double> coinPriceList = new ArrayList<Double>();
		coinPriceList.add(40000.45);
		coinPriceList.add(3500.0);
		
		StrategyCreatorA cr = new StrategyCreatorA();
		
		TradingBroker isaac = new TradingBroker("Isaac", coinList, cr.create());
		isaac.setCoinPriceList(coinPriceList);
		
		TradeResult res = isaac.performTrade();
		System.out.println(Arrays.toString(res.getData()));
	}
}
