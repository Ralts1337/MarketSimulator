
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
			// Update ATM tracker
			FishMarket.ATMTracker.track(FishMarket.ATMList.size(), FishMarket.clock);
			// Add client to  ATM Queue
			FishMarket.ATMList.add(this.client);
			FishMarket.ATMTracker.trackStartingTime(this.client, FishMarket.clock);
			
			// If he's the only one in queue, schedule a depart time for him
			if(FishMarket.ATMList.size()<=1) {
				double tempTimeInterval = RandBox.expo(Main.ATM_AVG_SERVICE_TIME);
				FishMarket.eventList
				.add(new ATM_Depart(FishMarket.clock + tempTimeInterval , this.client));
			}
			
		}
		// if he can buy at least one fish, he would go to the shopping zone
		else {
			// Update ShoppingList Tracker
			FishMarket.shoppingListTracker.track(FishMarket.shoppingList.size(), FishMarket.clock);
			// Add him to Shopping Queue
			FishMarket.shoppingList.add(this.client);
			FishMarket.shoppingListTracker.trackStartingTime(this.client, FishMarket.clock);
			
			
			// if he's the first client in the shopping zone, schedule a depart time for him
			if (FishMarket.shoppingList.size() <= 1) {
				double tempT = FishMarket.clock+ this.client.getFishAmount() * RandBox.expo(Main.SHOPPING_SERVICE_TIME_Per_Fish);
				FishMarket.eventList.add(new Shopping_Depart(tempT, this.client));
				//System.out.println("**ID: " + client.getID() + " will arrive at the ShoppingZone. Time " + tempT);
			}

		}
	}

}
