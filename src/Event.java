

// We have 4 types of events, which all inherits from this abstract class
public abstract  class Event implements Comparable<Event>{
	
	protected double time;						//the time it will happen. in seconds
	protected Client client;					//who does this event happen to?
	
	public abstract void process();		//what happens to clients
	
	public Event(double time, Client c) {
		this.time=time;
		this.client=c;
	}
	
	//advance the clock to this event
	public void advanceTime() {
		FishMarket.clock = this.time;
	}
	
	public double getTime() {
		return this.time;
	}
	
	@Override
	public int compareTo(Event e) {
		if(this.time-e.time>0) {
			return 2;
		}
		else if(this.time-e.time<0) {
			return -2;
		}
		else {
			return 0;
		}
	}
	
	
}
