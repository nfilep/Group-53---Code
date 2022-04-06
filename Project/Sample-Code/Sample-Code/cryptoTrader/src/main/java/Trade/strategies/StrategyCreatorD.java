package Trade.strategies;

import Trade.Creator;

public class StrategyCreatorD extends Creator {

	@Override
	public TradingStrategy create() {
		return new StrategyD();
	}

}