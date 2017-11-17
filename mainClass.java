//Derrick Marable dmarab2
//Ritika Upadhyay rupadh5
//Anurag Banerjee abaner22

import java.util.Scanner;

public class mainClass {
	public static void main(String[] args) {
		flowMonitor flow = new flowMonitor();
		int selection = 0;
		Scanner scan = new Scanner(System.in);
		AnalysisClass analyzer = new AnalysisClass();
		while(true) {
			System.out.println("1:Phone");
			System.out.println("2:Email");
			System.out.println("3:In Person");
			System.out.println("4:Analysis");
			System.out.println("5:Exit");
			System.out.println("Please select what you would like to do: ");
			selection = scan.nextInt();
			if(selection < 1 || selection > 5) {
				System.out.println("Invalid Anwser!");
				continue;
			}
			switch(selection) {
			case 1: 
				PhoneCall phone = new PhoneCall();
				phone.contactHandler();
				flow.ticketList.add(phone);
				break;
			
			case 2:
				Email email = new Email();
				String custID = new String();
				String custName = new String();
				long custPhoneNumber = 0;
				String custAddress = new String();
				String serviceID = new String();
				String serviceStartDate = new String();
				String serviceEndDate = new String();
				String emailText = new String();
				System.out.println("\nPlease enter Customer Representative ID: ");
				custID = scan.nextLine();
				System.out.println("\nPlease enter Customer Name: ");
				custName = scan.nextLine();
				System.out.println("\nPlease enter Customer Phone Number: ");
				custPhoneNumber = scan.nextLong();
				System.out.println("\nPlease enter Customer Address: ");
				custAddress = scan.nextLine();
				System.out.println("\nPlease enter Service ID : ");
				serviceID = scan.nextLine();
				System.out.println("\nPlease enter Service Start Date : ");
				serviceStartDate = scan.nextLine();				 
				System.out.println("\nPlease enter Service End Date : ");
				serviceEndDate = scan.nextLine();
				System.out.println("\nPlease enter email text : ");
				emailText = scan.nextLine();
				email.contactHandler(custID, custName, custPhoneNumber, custAddress, serviceID, serviceStartDate, serviceEndDate, emailText);
				flow.ticketList.add(email);
				break;
				
			case 3:
				InPerson person = new InPerson();
				person.contactHandler();
				flow.ticketList.add(person);
				break;
			
			case 4:
				analyzer.Analysis(flow);
				break;
				
			case 5:
				return;
				
			}
		}
		
	}
}
class AnalysisClass{
	void Analysis(flowMonitor flow) {
		Scanner scan = new Scanner(System.in);
		int selection = 0;
		while(true) {
			System.out.println("1:Analyze Tickets");
			System.out.println("2:Print Flow Status");
			System.out.println("3:Update Ticket Status");
			System.out.println("4:Exit");
			System.out.println("Please select what you would like to do: ");
			selection = scan.nextInt();
			if(selection < 1 || selection > 4) {
				System.out.println("Invalid Anwser!");
				continue;
			}
			switch(selection) {
			case 1:
				if(flow.ticketList.size() == 0) {
					break;
				}
				System.out.println("0:Total");
				System.out.println("1:Average Representative");
				System.out.println("2:Average Service");
				System.out.println("Which would you like to see? ");
				selection = scan.nextInt();
				if(selection < 0 || selection > 2) {
					System.out.println("Invalid Anwser!");
					break;
				}
				flow.printAnalysis(selection);
				break;
			case 2:
				if(flow.ticketList.size() == 0) {
					break;
				}
				flow.printFlowStatus();
				break;
			case 3:
				if(flow.ticketList.size() == 0) {
					break;
				}
				flow.updateTickets();
				break;
			case 4:
				return;
			}
		}
		
	}
}