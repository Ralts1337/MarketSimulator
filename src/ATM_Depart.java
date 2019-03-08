
public class ATM_Depart extends Event {

	public ATM_Depart(double time, Client c) {
		super(time, c);
	}

	@Override
	public void process() {
		advanceTime();
		// After departing from the ATM, the client goes to the shopping zone
		System.out.println("ID: " + client.getID() + " has departed the ATM. At Time= " + FishMarket.clock);

		FishMarket.shoppingList.add(this.client);
		FishMarket.ATMList.poll();

		// if the ATM Queue is not empty, schedule the depart event for
		// the next client in queue
		if (FishMarket.ATMList.size() > 0) {
			FishMarket.eventList.add(new ATM_Depart(FishMarket.clock + RandBox.expo(Main.ATM_AVG_SERVICE_TIME),
					FishMarket.ATMList.element()));
		}

		// if this client is the first in the Shopping queue, schedule a depart time for
		// him.
		if (FishMarket.shoppingList.size() <= 1) {
			FishMarket.eventList.add(new Shopping_Depart(
					FishMarket.clock + RandBox.expo(this.client.getFishAmount() * Main.SHOPPING_SERVICE_TIME_Per_Fish),
					this.client));
		}

	}

}
