package Trade.strategies;

public class StrategyCreator {

	public TradingStrategy create(String stratName) {
		if(stratName.equals("Strategy-A")) 
			return new StrategyA();
		
		else if(stratName.equals("Strategy-B")) 
			return new StrategyB();
		
		else if(stratName.equals("Strategy-C")) 
			return new StrategyC();
		
		else if(stratName.equals("Strategy-D")) 
			return new StrategyD();
		
		else if(stratName.equals("Strategy-E")) 
			return new StrategyE();
		
		else {
			throw new StrategyException("Invalid trading strategy name.");
		}
	}
}
