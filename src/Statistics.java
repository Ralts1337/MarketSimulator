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
	
	private static double totalFishMarketTime = 0;
	private static double totalATMWaitingTime = 0;
	private static double totalShoppingWaitingTime = 0;
	private static double totalCheckoutWaitingTime = 0;
	
	private static int customerAmount = 0;
	private static int totalATMClientAmount = 0;
	private static int totalShoppingClientAmount = 0;
	private static int totalCheckoutClientAmount = 0;
	
	
	public static void get_average_waiting_time() {
		System.out.println("-----Simulation Results Below-----");
		System.out.println("the Average time a customer spends at the Fish Market is:\n"+
				(double)totalFishMarketTime/customerAmount +" seconds");
	}
	
	
	
	
}	
