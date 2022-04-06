package User;

import java.util.ArrayList;

import Trade.TradingBroker;
/*Additional attribute sto distinguish btw the broker and the user
 * */
import Trade.strategies.TradingStrategy;

public class User {
	
	private String username; /*the name of the user*/
	private String password; /*the password of users*/
	private ArrayList<TradingBroker> brokerList;

	
	/* constructor */
	public User(String user, String password) {
		this.username = user;
		this.password = password;
		this.brokerList = new ArrayList<TradingBroker>();
	}
	
	/* GETTER METHODS */
	public String getPassword() {
		return password;		
	}
	 
	public String getUserName() {
		return username;
	}

	/*Adds a new trading broker to the system. If a broker of this name already exists, a message is displayed indicating so*/
	public boolean addBroker(String name, String[] cryptoCoinList, String strategy) {
	/*ATTRIBUTE ARRAY OF BROKERS
	users= are NOT the same as BROKERS
	name => brokers
		/**/
		TradingBroker newBroker = new TradingBroker(name, cryptoCoinList, strategy);
		if(!brokerList.contains(newBroker)) {
			brokerList.add(newBroker);
			return true;
		}
		return false;
	} 
	/*Removes a trading broker from the system.
	 *  If no broker of this name exists, a message is displayed indicating so*/
	public void removeBroker(int index) {
		brokerList.remove(index);
		/*
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
		*/
	}
	
	
	/*Initiates the trading process for all brokers logged into the system. main method calls
	 * datatfetcher result*/
	public void performTrade() {
		
		
	}
	
	/*Sends information from completed trades to brokers in the system*/
	public void notifyBrokers() {
		/* broker a is interested in sth
		 * do 
		 * notify them for prices of cons interested in */
	}
	
	public ArrayList<TradingBroker> getBrokerList() {
		return this.brokerList;
	}
	
	public void printBrokerList() {
		for(int i = 0; i < brokerList.size(); i++) {
			System.out.println(brokerList.get(i).getName());
		}
	}
	
	/* 
	 * QUESTIONS
	 * TO GET USERNAME AND PASSWORD, DO I HAVE TO READ EACH LINE IN THE TEXT FILE
	 * how would i call TradingStrategy class
	 * */

}
