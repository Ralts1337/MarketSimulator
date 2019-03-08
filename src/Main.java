
// Xuanqi Xu  CS2255673 March 7 2019
// Single server, double phase queue
// Fish Market

import java.util.*; //import everything. who knows what I'm going to use?

public class Main {
	//HardCoded final variables. 
	final static int DEFAULT_SIMULATION_TIME = 5;				//default simulation time is 5 hours.
	static final double INTERARRIVAL_TIME					=	120 ;
	static final double ATM_AVG_SERVICE_TIME 				= 	30 	;
	static final double SHOPPING_SERVICE_TIME_Per_Fish		=	120 ;
	static final double AVG_CHECKOUT_TIME_PER_FISH			=	30 	;
	
	public static void main(String[] args) {
		FishMarket fishmarket;
		int hour = -1,minute=-1,seconds=-1;
		System.out.println("Fish Market Simulation Starting...\nPlease choose a length of time:\n "
				+ "1.Default:5 hours\n" + " 2.Customized time in format of xxhxxmxxs where h=hours m=minutes s=seconds; h must <24");
		Scanner scan = new Scanner(System.in);
		boolean repeat = true;
		while (repeat) {
			String choice = scan.nextLine();
			if (choice.equals("1")) {
				hour = DEFAULT_SIMULATION_TIME;
				repeat = false;
			} else if (choice.equals("2")) {
				
				boolean hRepeat=true,mRepeat=true,sRepeat = true;
				
				while (hRepeat) {
					System.out.println("Please enter the value for h(hours) h<24");
					String hInput = scan.nextLine();
					try {
						hour = Integer.parseInt(hInput);
					}catch(Exception e) {
						System.out.println("Invalid input for hour.Please enter again");
						continue;
					}
					
					if (hour > 24 || hour < 0) {
						System.out.println("Invalid input for hour.Please enter again");
					}else hRepeat = false;
				}
				while (mRepeat) {
					System.out.println("Please enter the value for m(minutes)");
					String mInput = scan.nextLine();
					try {
						minute = Integer.parseInt(mInput);
					}catch(Exception e) {
						System.out.println("Invalid input for minute.Please enter again");
						continue;
					}
					if (minute>60||minute < 0) {
						System.out.println("Invalid input for minute.Please enter again");
					}else mRepeat = false;
				}
				while (sRepeat) {
					System.out.println("Please enter the value for s(seconds)");
					String sInput = scan.nextLine();
					try {
						seconds = Integer.parseInt(sInput);
					}catch(Exception e) {
						System.out.println("Invalid input for seconds.Please enter again");
						continue;
					}
					if (seconds>60||seconds < 0) {
						System.out.println("Invalid input for seconds.Please enter again");
					}else sRepeat = false;
				}
				
				
				fishmarket = new FishMarket(hour,minute,seconds);
				
				repeat = false;
			} else {
				System.out.println("Invalid choice. Please enter your choice again.");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.printf("Your input is: \nhour=%d minute = %d second = %d \nWelcome to the Matrix\n",
				hour==-1?0:hour,minute==-1?0:minute,seconds==-1?0:seconds);
		
		
		int totalSimulationTime =  400;//hour*3600 + minute*60 + seconds;
		//End of While, for simulation time
		//To not cause any more harm to your eyes, constants below are hardCoded:
		
		
		FishMarket F = new FishMarket(INTERARRIVAL_TIME,ATM_AVG_SERVICE_TIME,AVG_CHECKOUT_TIME_PER_FISH);
		F.run(totalSimulationTime);

	}
}
