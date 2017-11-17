import java.util.*;

import java.io.*;
import java.text.SimpleDateFormat;


public class ticket {
	
	protected String ticketID;
	protected String customerRepID; 
	protected String dateCreated; 
	

	
	protected String custID; 
	protected String custName;
	protected long custPhoneNumber; 
	protected String custAddress;
	
	protected String customerServiceRepID;
	protected String serviceID;
	protected String serviceStartDate;
	protected String serviceEndDate;
	
	protected boolean isResolved;
	protected int departmentID;
	
	protected transcript t;
	
	void contactHandler ()
	{
		
	}
}


class PhoneCall extends ticket {
	
	
	void contactHandler() {
		
		Scanner scanner = new Scanner(System.in);
		String conversationText;
	
		
	//Customer info
	System.out.println("\nPlease enter ticket ID: "); 
	ticketID=scanner.nextLine();
	
	System.out.println("\nPlease enter Customer Representative ID: ");
	customerRepID = scanner.nextLine(); 
	
	 dateCreated = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
 
	 System.out.println("\nPlease enter Customer Name: ");
	 custName = scanner.nextLine();
	 String custID; 
	 
	 System.out.println("\nPlease enter Customer Phone Number: ");
	 custPhoneNumber = scanner.nextLong();
	 
	 System.out.println("\nPlease enter Customer Address: ");
	 custAddress = scanner.nextLine();
	
	 
	 
	 //Service information
	 System.out.println("\nPlease enter Service ID : ");
	 serviceID = scanner.nextLine();
	 
	 System.out.println("\nPlease enter Service Start Date : ");
	 serviceStartDate = scanner.nextLine();
	 
	 System.out.println("\nPlease enter Service End Date : ");
	 serviceEndDate = scanner.nextLine();
	 
	 
	 System.out.println("\n\n Is the issue resolved? 1/0");
	 isResolved = scanner.hasNext();
	 
	 int flag=0;
		do {
		System.out.println("\nPlease enter the department number between 1 to 10 this ticket needs to be routed to: ");
		departmentID = scanner.nextInt();
		if (departmentID>=1 && departmentID <=10)
			flag=1;
		
		}while(flag==-1);
		
		System.out.println("\n Please enter notes from the customer interaction: ");
		conversationText=scanner.nextLine();
		
		transcript t = new transcript(conversationText);

	}
	
}


class Email extends ticket {
	
	
	void contactHandler(String custID, String custName, long custPhoneNumber, String custAddress,
			String serviceID, String serviceStartDate, String serviceEndDate, String emailText) {
		
		Scanner scanner = new Scanner(System.in);
		String response;
		
		//Displays customer and ticket info to customer rep
		System.out.println("***Customer Information*** \n"+ "Customer ID: "+custID+"\n Customer Name: "+custName +"\n Customer Phone Number: "
				+ custPhoneNumber+"\nCustomer Address: "+custAddress+"\n\n***Service Information***"+"Service ID: "+
				serviceID+"\nService Start Date: "+serviceStartDate +"\n Service End Date: "+serviceEndDate);
		
		//Display email text
		System.out.println("\n\nEmail: "+emailText);
		
		System.out.println("\n\nIs the ticket resolved? Enter Yes and N for No.");
		response=scanner.nextLine();
		
		if(response.toLowerCase()=="yes"){
			departmentID=0;
			isResolved= false;
		}
		
		else
			if (response.toLowerCase()=="no") {
				int flag=0;
				do {
				System.out.println("\nPlease enter the department number between 1 to 10 this ticket needs to be routed to: ");
				departmentID = scanner.nextInt();
				if (departmentID>=1 && departmentID <=10)
					flag=1;
				
				}while(flag==-1);//Loop will run for the first time under any condition, and will continue to execute till the customer rep 
								//enters a valid response for department ID
			}
			
			transcript t = new transcript(emailText);//Passed as argument to transcript object's constructor
		
		
	}

}



class InPerson extends ticket {
	
	
	void contactHandler() {
	
		Scanner scanner = new Scanner(System.in);
		String conversationText;
	
		
	//Customer info
	System.out.println("\nPlease enter ticket ID: "); 
	ticketID=scanner.nextLine();
	
	System.out.println("\nPlease enter Customer Representative ID: ");
	customerRepID = scanner.nextLine(); 
	
	 dateCreated = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
 
	 System.out.println("\nPlease enter Customer Name: ");
	 custName = scanner.nextLine();
	 String custID; 
	 
	 System.out.println("\nPlease enter Customer Phone Number: ");
	 custPhoneNumber = scanner.nextLong(); 
	 
	 System.out.println("\nPlease enter Customer Address: ");
	 custAddress = scanner.nextLine();
	
	 
	 
	 //Service information
	 System.out.println("\nPlease enter Service ID : ");
	 serviceID = scanner.nextLine();
	 
	 System.out.println("\nPlease enter Service Start Date : ");
	 serviceStartDate = scanner.nextLine();
	 
	 System.out.println("\nPlease enter Service End Date : ");
	 serviceEndDate = scanner.nextLine();
	 
	 
	 System.out.println("\n\n Is the issue resolved? 1/0");
	 isResolved = scanner.hasNext();
	 
	 int flag=0;
		do {
		System.out.println("\nPlease enter the department number between 1 to 10 this ticket needs to be routed to: ");
		departmentID = scanner.nextInt();
		if (departmentID>=1 && departmentID <=10)
			flag=1;
		
		}while(flag==-1);
		
		System.out.println("\n Please enter notes from the customer interaction: ");
		conversationText=scanner.nextLine();
		
		transcript t = new transcript(conversationText);

	}


}

