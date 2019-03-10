
public class ATM_Depart extends Event {

	public ATM_Depart(double time, Client c) {
		super(time, c);
	}

	@Override
	public void process() {
		advanceTime();
		// After departing from the ATM, the client goes to the shopping zone
		System.out.println("ID: " + client.getID() + " has departed the ATM. At Time= " + FishMarket.clock);

		// Update ATM tracker
		FishMarket.ATMTracker.track(FishMarket.ATMList.size(), FishMarket.clock);
		// System.out.println("totalAccum="+FishMarket.ATMTracker.gettotalAccum());

		// Update ShoppingList tracker
		FishMarket.shoppingListTracker.track(FishMarket.shoppingList.size(), FishMarket.clock);
		
		// Add money to client
		this.client.setMoney(this.client.getMoney() + 10 + (int) (Math.random() * 31));
		// Remove him from ATM queue
		Client temp = FishMarket.ATMList.poll();
		FishMarket.ATMTracker.trackEndTime(temp, FishMarket.clock);
		// Add him to shoppingList Queue
		FishMarket.shoppingList.add(temp);
		FishMarket.shoppingListTracker.trackStartingTime(temp, FishMarket.clock);

		// if the ATM Queue is not empty, schedule the depart event for
		// the next client in queue
		if (FishMarket.ATMList.size() > 0) {
			FishMarket.eventList.add(new ATM_Depart(FishMarket.clock + RandBox.expo(Main.ATM_AVG_SERVICE_TIME),
					FishMarket.ATMList.element()));

		} else {
			// if the ATM Queue is empty
		}

		// if this client is the first in the Shopping queue, schedule a depart time for
		// him.
		if (FishMarket.shoppingList.size() <= 1) {
			FishMarket.eventList.add(new Shopping_Depart(
					FishMarket.clock + this.client.getFishAmount() * RandBox.expo(Main.SHOPPING_SERVICE_TIME_Per_Fish),
					this.client));
		}

	}

}
