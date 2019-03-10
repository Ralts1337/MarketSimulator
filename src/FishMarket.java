import java.util.*;

public class FishMarket {
	static double clock; // a global clock. keeps track of everything in seconds
	double simulationTime; // how long do we want to simulate this market?

	static final int FISH_PRICE = 10; // $10 for each fish. Pretty expensive huh?

	protected final double INTERARRIVAL_TIME;
	protected final double ATM_AVG_SERVICE_TIME;
	protected final double AVG_CHECKOUT_TIME_PER_FISH;
	
	
	
	// Default Constructor. Sets total simulationTime to 5 hours;
	
	public FishMarket(double interval, double serviceTime, double checkoutTime) {
		clock = 0;

		this.INTERARRIVAL_TIME = interval;
		this.ATM_AVG_SERVICE_TIME = serviceTime;
		this.AVG_CHECKOUT_TIME_PER_FISH = checkoutTime;

	}

	// Three Servers: ATM, Shopping, Checkout
	static LinkedList<Client> ATMList = new LinkedList<Client>();
	static LinkedList<Client> shoppingList = new LinkedList<Client>();
	static LinkedList<Client> checkoutList = new LinkedList<Client>();
	// Trackers for the servers
	static QueueTracker ATMTracker 			= new QueueTracker();
	static QueueTracker shoppingListTracker = new QueueTracker();
	static QueueTracker checkoutListTracker = new QueueTracker();
	
	// One Event List
	static PriorityQueue<Event> eventList = new PriorityQueue<Event>();

	public void run(int timeLength) {
		simulationTime = timeLength;
		double arriveTime = RandBox.expo(INTERARRIVAL_TIME); // Temporary counter to keep track of the arrival queue
		System.out.println("RandBox:" + arriveTime);

		// Customers start arriving
		while (arriveTime <= timeLength) {

			// Generate a client and its arrival time
			double interval = RandBox.expo(INTERARRIVAL_TIME);
			Client temp = new Client(arriveTime);

			// After generation, put the arrival event into the event Queue
			eventList.add(new Market_Arrive(arriveTime, temp));
			Statistics.customerAmount++;
			arriveTime += interval;
		}
		// End of While loop. Finished adding all Clients.
		// -------------------------------------------------------------//
		// Start processing the events:
		while (!eventList.isEmpty()) {

			Event e = eventList.poll();
			e.process();

		}

		// finished processing the events

		// Printing the results:
		Statistics.get_average_waiting_time();
	}

	
}
