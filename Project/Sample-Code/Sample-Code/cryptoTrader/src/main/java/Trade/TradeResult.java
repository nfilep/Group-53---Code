package Trade;

public class TradeResult {
	private String traderName;
	private String strategyName;
	private String coinType;
	private int quantityTraded;
	private double price;
	private boolean success;
	
	public TradeResult(String traderName, String strategyName, String coinType, int quantityTraded, double price, boolean success) {
		this.traderName = traderName;
		this.strategyName = strategyName;
		this.coinType = coinType;
		this.quantityTraded = quantityTraded;
		this.price = price;
		this.success = success;
	}
	
	public String getTraderName() {
		return traderName;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	
	public String[] returnData() {
		//return new String{traderName, strategyName, coinType, Double.toString(quantityTraded), Integer.toString(quantityTraded), Boolean.toString(success)};
		return null;
	}
}
