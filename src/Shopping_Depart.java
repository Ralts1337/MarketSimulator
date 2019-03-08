
public class Shopping_Depart extends Event{

	public Shopping_Depart(double time, Client c) {
		super(time, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() {
		advanceTime();
		// After departing from the ShoppingZone, the client goes to the checkout zone
		System.out.println("ID: " + client.getID() + " has departed the ShoppingZone. At Time= " + FishMarket.clock);
		
		FishMarket.checkoutList.add(this.client);
		FishMarket.shoppingList.poll(); 
		// if the Shopping Queue is not empty, schedule the depart event for
		// the next client in queue
		if (FishMarket.shoppingList.size() > 0) {
			FishMarket.eventList.add(new Shopping_Depart(FishMarket.clock + RandBox
					.expo(FishMarket.shoppingList.element().getFishAmount() * Main.SHOPPING_SERVICE_TIME_Per_Fish),
					FishMarket.shoppingList.element()));
		}
		
		//if he's the first in the checkout queue, schedule a depart time for him.
		if(FishMarket.checkoutList.size()<=1) {
			FishMarket.eventList.add(new CheckOut_Depart(FishMarket.clock + RandBox.expo(this.client.getFishAmount() * Main.AVG_CHECKOUT_TIME_PER_FISH), this.client));
		}
		
	}

}
