package Trade.strategies;

import Trade.Creator;

public class StrategyCreatorC extends Creator {

	@Override
	public TradingStrategy create() {
		return new StrategyC();
	}

}