import java.util.*;

public class Statistics {
	/*
	 * At the end we want to output
	 * 
	 * a) the Average time customers spend waiting in the ATM and SHOPPING and
	 * CHECKOUT queue.
	 * 
	 * b) the Average length of both of these queues
	 * 
	 * c) the Average time a customer spends at the Fish Market
	 */
	
	//Tracks average time a client spends in the fish market
	static double totalFishMarketTime = 0;
	static int customerAmount = 0;
	
	//How long this simulation has ran
	private static double totalTime = 0;
	
	
	
	//Tracks the Queues
	//Variables for Queue 1
	
	
	
	
	
	
	public static void get_average_waiting_time() {
		System.out.println("-----Simulation Results Below-----");
		System.out.println("-----Part C-----");
		System.out.println("the Average time a customer spends at the Fish Market is:\n"+
				(double)totalFishMarketTime/customerAmount +" seconds");
		//Part B. Average Queue Length
		System.out.println("-----Part B-----");
		System.out.println("Average ATM Queue Length="+FishMarket.ATMTracker.averageQueueLength(totalTime));
		System.out.println("Average Shopping Queue Length="+FishMarket.shoppingListTracker.averageQueueLength(totalTime));
		System.out.println("Average Checkout Queue Length="+FishMarket.checkoutListTracker.averageQueueLength(totalTime));
		//Part A. Average Queue Waiting time
		System.out.println("-----Part A-----");
		System.out.println("Average ATM wait time="+FishMarket.ATMTracker.avgWaitingTime());
		System.out.println("Average Shopping Queue wait time="+FishMarket.shoppingListTracker.avgWaitingTime());
		System.out.println("Average Checkout Queue wait time="+FishMarket.checkoutListTracker.avgWaitingTime());
	}
	
	public static void setTotalTime(double time) {
		totalTime = time;
	}
	public double getTotalTime() {
		return this.totalTime;
	}
	
	
	
}	
