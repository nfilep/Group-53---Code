package Trade.strategies;

import Trade.Creator;

public class StrategyCreatorB extends Creator {

	@Override
	public TradingStrategy create() {
		return new StrategyB();
	}

}
