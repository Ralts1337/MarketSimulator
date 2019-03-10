
public class CheckOut_Depart extends Event {

	public CheckOut_Depart(double time, Client c) {
		super(time, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() {
		advanceTime();
		// After departing from the checkout, the client leaves
		System.out.println("ID: " + client.getID() + " has departed the market. At Time= " + FishMarket.clock);
		
		// Tracks the total time the customer has spent at the fish market
		Statistics.totalFishMarketTime+=(FishMarket.clock-this.client.getTime());
		
		// Update CheckoutList Tracker
		FishMarket.checkoutListTracker.track(FishMarket.checkoutList.size(), FishMarket.clock);
		
		Client temp = FishMarket.checkoutList.poll();
		FishMarket.checkoutListTracker.trackEndTime(temp, FishMarket.clock);

		// if the the checkout Queue is not empty, schedule the depart event for
		// the next client in queue
		if (FishMarket.checkoutList.size() > 0) {
			FishMarket.eventList.add(new CheckOut_Depart(
					FishMarket.clock + RandBox
							.expo(FishMarket.checkoutList.element().getFishAmount() * Main.AVG_CHECKOUT_TIME_PER_FISH),
					FishMarket.checkoutList.element()));
		}else {
		// if the checkout Queue is empty, do nothing. 
		// However, if he's the last client, we record the total time for simulation
			if(FishMarket.ATMList.isEmpty()&& FishMarket.shoppingList.isEmpty()&& FishMarket.eventList.isEmpty()) {
				Statistics.setTotalTime(FishMarket.clock);
			}
		}
	}

}
