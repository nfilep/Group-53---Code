package Trade.strategies;

/**It creates a strategy object to perform an algorithm
 * @author Isaac
 */
public class StrategyCreator {

	/** it checks if stratName has contents of a strategy to be implemented 
	 * and create a new object of that specific strategy
	 * @param stratName 	a string that has the name of the strategy
	 * to be implement
	 */
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
