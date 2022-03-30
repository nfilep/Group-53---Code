package Trade;

import java.util.Arrays;

import Trade.strategies.*;

public class TradeTest {
	public static void main(String[] args) {
		String[] coinList = {"BTC"};
		double[] coinPriceList = {60000.45};
		TradeResult result = new TradeResult("isaac", new StrategyA(), coinList, coinPriceList, "Buy", 100, "29-March-2022", true);
		
		System.out.println(Arrays.toString(result.getData()));
		System.out.println(Arrays.toString(result.getCoinList()));
		System.out.println(Arrays.toString(result.getCoinPriceList()));
	}
}
