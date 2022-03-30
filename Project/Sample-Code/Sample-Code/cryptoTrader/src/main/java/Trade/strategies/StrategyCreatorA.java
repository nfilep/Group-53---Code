package Trade.strategies;

import Trade.Creator;

public class StrategyCreatorA extends Creator{

	@Override
	public TradingStrategy create() {
		return new StrategyA();
	}

}
