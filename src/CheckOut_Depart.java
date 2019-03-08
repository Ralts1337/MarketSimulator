
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
		
		
		FishMarket.checkoutList.poll();

		// if the the checkout Queue is not empty, schedule the depart event for
		// the next client in queue
		if (FishMarket.checkoutList.size() > 0) {
			FishMarket.eventList.add(new CheckOut_Depart(
					FishMarket.clock + RandBox
							.expo(FishMarket.checkoutList.element().getFishAmount() * Main.AVG_CHECKOUT_TIME_PER_FISH),
					FishMarket.checkoutList.element()));
		}

	}

}
