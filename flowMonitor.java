import java.util.ArrayList;


public class flowMonitor {

	static String[] arrayRepresentativeIDs;
	static String[] arrayServiceIDs;
	
	ArrayList<ticket> ticketList = new ArrayList<ticket> ();
	
	
	void updateTickets(){
		//method. After each ticket is created, the flow monitor loops over all of the tickets in the array to make sure there is no suspended ticket. If it finds any suspended ticket, it checks the similarity between its three more frequent words of that ticket with the three most frequent words of all other tickets in the array, and sets the departmentID of that ticket to be the same as the departmentID of the ticket with highest similarity.
		for(int i = 0; i < ticketList.size(); i++) {
			if(ticketList.get(i).departmentID == -1) {
				int nTicket = 0;
				int topScore = 0;
				int oldScore = 0;
				for(int j = 0; j < ticketList.size(); j++) {
					if(j == i) {
						continue;
					}
					if(ticketList.get(j).departmentID != -1) {
						for(int l = 0; l < 3; l++) {
							if(ticketList.get(i).t.getTopFreqWords()[l].equals(ticketList.get(j).t.getTopFreqWords()[l])) {
								topScore += 1;
							}
						}
						if(topScore > oldScore) {
							nTicket = j;
							oldScore = topScore;
							topScore = 0;
						}
					}
				}
				ticketList.get(i).departmentID = ticketList.get(nTicket).departmentID;
				
			}
		}
	}
	

	int getSentiment(int ticketNum){//returns the sentiment of a ticket in numerical form
		
		ticket ticket = ticketList.get(ticketNum);
		int neg=0;
		int pos=0;
		int senti=0;
		
		for(int i=0; i<3; i++){//iterate through the current object's top 3 most frequent words and count the number of negative ones
			for (int j=0; j<ticket.t.negativeLexicon.length; j++)
				if(ticket.t.topFreqWords[i].equals(ticket.t.negativeLexicon[j]))
					neg+=1;
		}
		
		
		for(int i=0; i<3; i++){//iterate through the current object's top 3 most frequent words and count the number of positive ones
			for (int j=0; j<ticket.t.positiveLexicon.length; j++)
				if(ticket.t.topFreqWords[i].equals(ticket.t.positiveLexicon[j]))
					pos+=1;
		}
		
		if(neg>pos)
			senti=1;
		else
			if(pos>neg)
				senti=3;
		
		return senti;
	}
	
	ArrayList <Object> sentimentAnalysis () {
		
		ArrayList<Object> ticketScores = new ArrayList<Object>();
		Integer totalScore=0;
		Double avgSentimentScore=0.0;
		Double avgServiceSentimentScore=0.0;
		Double repLen = 0.0;
		Double sLen = 0.0;
		ArrayList<String> csIDs = new ArrayList<String>();
		ArrayList<String> ssIDs = new ArrayList<String>();
		for(int i = 0; i < ticketList.size(); i++) {
			totalScore += getSentiment(i);
			if(!csIDs.contains(ticketList.get(i).customerRepID)) {
				csIDs.add(ticketList.get(i).customerRepID);
				repLen += 1;
			}
			if(!ssIDs.contains(ticketList.get(i).serviceID)) {
				ssIDs.add(ticketList.get(i).serviceID);
				sLen += 1;
			}
		}
		avgSentimentScore = totalScore / repLen;
		avgServiceSentimentScore = totalScore / sLen;
		ticketScores.add(totalScore);
		ticketScores.add(avgSentimentScore);	
		ticketScores.add(avgServiceSentimentScore);
		/*Object one is an integer, which is the overall sentiment scores of the Tickets, 
		object two is the average sentiment scores of the ticket for each customer representative, 
		and object three is the average sentiment scores for the services*/
		
		return ticketScores;
	}
	
	
	void printAnalysis(int i) {
	
		ArrayList<Object> ticketScores = sentimentAnalysis();
		System.out.println(ticketScores.get(i));
	}
	
	
	
	void printFlowStatus(){
		
		for(int i=0; i<ticketList.size(); i++) {
			System.out.println("***Ticket Information***");
			System.out.println("Date Created"+ticketList.get(i).dateCreated + "\n Is the Ticket Solved? "+String.valueOf(ticketList.get(i).isResolved));
			
			if(ticketList.get(i).isResolved== false)
				System.out.println("This ticket is currently with Department #"+ticketList.get(i).departmentID);
		}
		
	}
	
}
