
public class Market_Arrive extends Event {

	public Market_Arrive(double time, Client c) {
		super(time, c);
	}

	@Override
	public void process() {
		System.out.println("ID: " + client.getID() + " has arrived at the market. Time " + client.getTime());
		advanceTime();
		// Ok let's begin processing:
		// When a customer arrives. Can he buy at least one fish?
		// if not, he goes to the ATM
		if (client.getMoney() < FishMarket.FISH_PRICE) {
			// if the ATM queue is empty, put this client there. Schedule a time for his
			// departure
			if (FishMarket.ATMList.isEmpty()) {

				FishMarket.ATMList.add(this.client);
				FishMarket.eventList
						.add(new ATM_Depart(FishMarket.clock + RandBox.expo(Main.ATM_AVG_SERVICE_TIME), this.client));
			}
			// else, add him to the queue
			else {

				FishMarket.ATMList.add(this.client);
			}
		}
		// if he can buy at least one fish, he would go to the shopping zone
		else {
			FishMarket.shoppingList.add(this.client);
			// if he's the first client in the shopping zone, schedule a depart time for him
			if (FishMarket.shoppingList.size() <= 1) {
				FishMarket.eventList.add(new Shopping_Depart(
						this.client.getFishAmount() * RandBox.expo(Main.SHOPPING_SERVICE_TIME_Per_Fish), this.client));
			}

		}
	}

}
