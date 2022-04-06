package User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.lang.invoke.StringConcatException;
import java.nio.channels.NonWritableChannelException;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

import Trade.TradingBroker;
import Trade.strategies.TradingStrategy;
/*Additional attribute sto distinguish btw the broker and the user
 * */
import cryptoTrader.utils.DataFetcher;

public class User {
	
	private String username; /*the name of the user*/
	private String password; /*the password of users*/
	private ArrayList<TradingBroker> brokerList;

	
	/* constructor */
	public User(String user, String password) {
		this.username = user;
		this.password = password;
	}
	
	/* GETTER METHODS */
	public String getPassword() {
		return password;		
	}
	 
	public String getUserName() {
		return username;
	}

	/*Adds a new trading broker to the system. If a broker of this name already exists, a message is displayed indicating so*/
	public void addBroker(String name, ArrayList<String> cryptoCoinList, TradingStrategy strategy) {
	/*ATTRIBUTE ARRAY OF BROKERS
	users= are NOT the same as BROKERS
	name => brokers
		/**/
		TradingBroker newBroker = new TradingBroker(name, cryptoCoinList, strategy);
		brokerList.add(newBroker);
	} 
	/*Removes a trading broker from the system.
	 *  If no broker of this name exists, a message is displayed indicating so*/
	public void removeBroker(String name) {
		boolean isBroker = false; 
		for(int i = 0; i < brokerList.size(); i++) {
			TradingBroker broker = brokerList.get(i);
			if(broker.getName().equals(name)){
				isBroker = true;
				brokerList.remove(i);
			}
		}
		if(isBroker == false) {
			System.out.println("No broker named " + name + " exists.");
		}
		
		
		
	}
	
	
	/*Initiates the trading process for all brokers logged into the system. main method calls
	 * datatfetcher result*/
	public void performAllTrade() {
		
		//1) Initiate the retrieval of the current cryptocoin prices for all the selected coins
		// (i.e. collectively for all trading brokers in one http request).

		//	//compile all the coins. so only 1 api request for a single trade cycle

		// "08-09-2021"
		
    	DataFetcher getHttp = new DataFetcher();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		LocalDateTime now = LocalDateTime.now();
		
		HashMap<String, Double> coins = new HashMap<String, Double>();

		for(int i = 0; i < brokerList.size(); i++){
			TradingBroker broker = brokerList.get(i);
			String coin = broker.getCoinList().get(i); 
			double request = getHttp.getPriceForCoin(coin, dtf.format(now).toString()); //figure out how to get current date
			coins.put(coin, request);	
		}

		for(int i = 0; i < brokerList.size(); i++){
			TradingBroker broker = brokerList.get(i);
			for(int j = 0; j < broker.getCoinList().size(); j++){ 
				if(coins.containsKey(broker.getCoinList().get(j))){
					notifyBroker(broker, coins.get(broker.getCoinList().get(j)));
				}
			}
		}


	}

	/*Sends information from completed trades to brokers in the system*/
	private void notifyBroker(TradingBroker broker, double price) {
		broker.getCoinPriceList().add(price); 
		broker.performTrade();
	}
	/*
	
			/*
				2) Notify the different trading brokers by passing the appropriate prices to the right trading broker. 
				For example, if Trading-Broker-1 has selected BTC and ADA while Trading-Broker-2 selected ETH and BTC, 
				then Trading-Broker-1 will get the BTC and ADA prices and Trading-Broker-2 will get ETH, and BTC prices. 
				In this respect, the data fetcher module will bring prices for all BTC, ADA, ETH, but will notify appropriately 
		 		the right clients by sending them the right data
			*/


		//3) Trigger the computation of the specific trading strategy associated with each trading broker. 
		// For example, Trading-Broker-1 may say that if the BTC stock price is less than 55,000$ and ADA price 
		// is more than 2$ then buy 10 units of ETH (note, that ETH was not necessarily listed in the cryptocoins associated 
		// with Trading-Broker-1).

		//If the trading strategy does not have all the necessary information to be applied 
		// (e.g. the cryptocoins used in the trading strategy are not in the list of cryptocoins the trading broker declared
		// interest in, then a message should appear that the trading strategy can not be applied and a row is added on the table 
		// in the UI with the name of the broker, the name of the strategy, the indication “Fail”, and the date of the failed transaction. 
		// The columns related to coin traded quantity and unit price may be indicated as “Null”.


		
	}
