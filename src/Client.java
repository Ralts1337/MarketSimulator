
public class Client {
	private static int counter=0;
	private double time;				//the time the client arrived, Unit: seconds
	private int money;
	private int ID;
	private int fishAmount = 0;			//how many fish they want to buy? they want to spend all their money buying fish
	
	//Constructor, when called:
	//set its arrival time to the input time;
	//generate a random amount of cash the client brings with him, between $0 and $40;
	public Client(double currentTime) {
		this.time = currentTime;
		this.money = (int)(Math.random()*41);
		this.fishAmount = this.money/FishMarket.FISH_PRICE;
		this.ID=counter;
		counter++;
	}
	
	public double getTime() {
		return this.time;
	}
	public int getID() {
		return this.ID;
	}
	public int getMoney() {
		return this.money;
	}
	public void setMoney(int amount) {
		this.money=amount;
	}
	public int getFishAmount() {
		return this.fishAmount;
	}
	
	
}
