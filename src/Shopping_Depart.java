
public class Shopping_Depart extends Event {

	public Shopping_Depart(double time, Client c) {
		super(time, c);
	}

	@Override
	public void process() {
		advanceTime();
		// After departing from the ShoppingZone, the client goes to the checkout zone
		System.out.println("ID: " + client.getID() + " has departed the ShoppingZone. At Time= " + FishMarket.clock);

		// Update Shopping Tracker
		FishMarket.shoppingListTracker.track(FishMarket.shoppingList.size(), FishMarket.clock);
		// Update CheckoutList Tracker
		FishMarket.checkoutListTracker.track(FishMarket.checkoutList.size(), FishMarket.clock);

		// Update ShoppingList and CheckoutList
		Client temp = FishMarket.shoppingList.poll();
		FishMarket.shoppingListTracker.trackEndTime(temp, FishMarket.clock);

		FishMarket.checkoutList.add(this.client);
		FishMarket.checkoutListTracker.trackStartingTime(temp, FishMarket.clock);

		// if the Shopping Queue is not empty, schedule the depart event for
		// the next client in queue
		if (FishMarket.shoppingList.size() > 0) {
			double tempT = FishMarket.clock + FishMarket.shoppingList.element().getFishAmount()
					* RandBox.expo(Main.SHOPPING_SERVICE_TIME_Per_Fish);
			FishMarket.eventList.add(new Shopping_Depart(tempT, FishMarket.shoppingList.element()));
			//System.out.println("**ID: " + client.getID() + " will arrive at the ShoppingZone. Time " + tempT);

		}

		// if he's the first in the checkout queue, schedule a depart time for him.
		if (FishMarket.checkoutList.size() <= 1) {
			FishMarket.eventList.add(new CheckOut_Depart(
					FishMarket.clock + this.client.getFishAmount() * RandBox.expo(Main.AVG_CHECKOUT_TIME_PER_FISH),
					this.client));
		}

	}

}
