package Trade.strategies;

import Trade.Creator;

public class StrategyCreatorE extends Creator {

	@Override
	public TradingStrategy create() {
		return new StrategyE();
	}

}