import java.util.*;

public class QueueTracker {
	
	private double totalAccum = 0;
	
	private double previousTime = 0;
	//private int previousSize = 0;
	private HashMap<Integer,Double> waitTimeTracker = new HashMap();
	private double totalWaitTime = 0;
	
	//Call track when there's a change in queue length
	public void track(int size , double currentTime) {
		assert(currentTime>previousTime):"input time error";
		
		totalAccum += size*(currentTime - previousTime);
		previousTime = currentTime;
		//previousSize = size;
	}
	
	public double averageQueueLength(double totalTime) {
		return (double)totalAccum/totalTime;
	}
	
	//For hashMap
	public void trackStartingTime(Client c,double inputTime) {
		waitTimeTracker.put(c.getID(), inputTime);
	}
	
	public void trackEndTime(Client c, double currentTime) {
		this.totalWaitTime+= currentTime - waitTimeTracker.get(c.getID());
	}
	public double avgWaitingTime() {
		return this.totalWaitTime/waitTimeTracker.size();
	}
	
	//For Testing
	public double gettotalAccum() {
		
		return totalAccum;
	}
	
}


