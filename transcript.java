import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class transcript {
	
	int totalWordCount=0;
	ArrayList<String> wordsList = new ArrayList<String>();
	ArrayList<ArrayList<String>> sentencesList= new ArrayList<ArrayList<String>>(); 
	double averageSentenceLength;
	String[] topFreqWords = new String[3];
	LinkedHashMap<String, Integer> wordFreqMap = new LinkedHashMap<String, Integer>();

	
	
	
	public LinkedHashMap<String, Integer> getWordFreqMap() {
		return wordFreqMap;
	}


	public void setWordFreqMap() {
		
		int value=0;
		
		
		for(int i=0; i< sentencesList.size();i++) {
			
			for(int j=0; j<sentencesList.get(i).size();j++) {
				
				if(wordFreqMap.containsKey(sentencesList.get(i).get(j))) {
					
					value = wordFreqMap.get(sentencesList.get(i).get(j)) + 1;
					wordFreqMap.put(sentencesList.get(i).get(j), value);
				}
				
		
				else
					wordFreqMap.put(sentencesList.get(i).get(j), 1);
				
			}
		
			
			
		}
	}


	public int getTotalWordCount() {
		return totalWordCount;
	}


	public void setTotalWordCount() {
		
		for(int i=0; i< sentencesList.size();i++)
			for(int j=0; j<sentencesList.get(i).size();j++) 
				totalWordCount+=1;

	}


	public String[] getTopFreqWords() {
		return topFreqWords;
	}


	public void setTopFreqWords(String[] topFreqWords) {
		
		int value=0;
		ArrayList<String> finalwordList= new ArrayList<String>();
		ArrayList<Integer> finalwordFreq= new ArrayList<Integer>();
		
		setWordFreqMap();
		//populates the linkedhashmap for words and corresponding frequencies
		
		//sort this Hash map, copy the keys for the three highest values into the array
		LinkedHashMap<String, Integer> finalFreqMap = (LinkedHashMap<String, Integer>) MapUtil.sortByValue(wordFreqMap);
		
			
		for (Entry<String, Integer> entry : finalFreqMap.entrySet()) {
		    finalwordList.add(entry.getKey());
		    finalwordFreq.add(entry.getValue());
		}
		
		for(int i=0;i<3;i++)
			topFreqWords[i]=finalwordList.get(i).toString();
		
	}


	public double getAverageSentenceLength() {
		return averageSentenceLength;
	}


	public void setAverageSentenceLength() { 
										
		//determine the averageSentenceLength= total number of words/sentencesList.size
		
			setTotalWordCount();
			averageSentenceLength = totalWordCount/sentencesList.size();
	}


	final String [] stopWords = {"a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are",
			"aren't", "as", "at", "be",	"because", "been", "before", "being", "below", "between", "both", "but", "by", "can't", "cannot",
			"could", "couldn't", "did",  "didn't", "do",  "does",  "doesn't",  "doing",	"don't", "down", "during", "each", "few", "for",
			"from", "further", "had", "hadn't", "has", "hasn't", "have", "haven't", "having",  "he", "he'd", "he'll", "he's", "her",
			"here", "here's", "hers", "herself", "him", "himself", "his", "how", "how's", "I", "I'd", "I'll", "I'm", "I've", "if",
			"in", "into", "is", "isn't", "it",	"it's", "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself",
			"no", "nor", "not", "of", "off", "on", "once", "only", "or", "other", "ought", "our", "ours", "ourselves", "out", "over",
			"own", "same", "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't", "so", "some", "such", "than", "that",
			"that's", "the", "their", "theirs", "them", "themselves", "then", "there", "there's", "these","they", "they'd",	"they'll",
			"they're", "they've", "this", "those", "through", "to", "too", "under", "until", "up", "very", "was", "wasn't", "we",
			"we'd",	"we'll", "we're", "we've", "were", "weren't", "what", "what's", "when", "when's", "where", "where's", "which",
			"while", "who", "who's", "whom", "why", "why's", "with", "won't", "would", "wouldn't", "you", "you'd", "you'll", "you're",
			"you've", "your", "yours", "yourself", "yourselves"};
	
	final String [] positiveLexicon = {"great", "grateful", "good", "helpful", "thankful", "best", "better", "best",
			"beyond", "comfortable", "commitment", "committed", "renew", "register", "unbelievable", "unbelievably", "awesome",
			"wonderful"};
	
	final String [] negativeLexicon = {"bad", "worse", "worst", "crazy", "confuse", "confused", "confusing", "disturb", "disturbance",
			"disturbed", "angry", "annoy", "annoyed", "annoying", "annoyingly", "refuse", "refused", "regret", "reject", "rejected", "repetitive",
			"repetitively", "cancel", "discontinue", "terminate", "mad", "unhappy", "unhelpful", "unlucky"};
	

	void getSimilarity(transcript t) {
		//checks the similarity between most frequent words of that Transcript object (i.e. inputTranscript.topFreqWords) 
		//with its own object (i.e. this.topFreqWords[]).
		 int flag = 0;
		 t.getTopFreqWords();
		 this.getTopFreqWords();
		for(int i=0; i<3; i++) {
			if(t.topFreqWords[i].equals(this.topFreqWords[i]))
				flag++;
		}
		
		System.out.println("These two transcripts have "+flag+" words common in their list of three most frequent words.");
	}
	

	transcript(String text) {	
		String[] delimiter = { "\n", ".", "!", "?"};
		
		StringBuffer regexp = new StringBuffer("");
		regexp.append("[");
		for(String s : delimiter) {
		    regexp.append("[");
		    //System.out.println("appending to regexp");
		    regexp.append(Pattern.quote(s));
		    regexp.append("]");
		}
		regexp.append("]");
		 
		
		String[] sentenceArr = text.split(regexp.toString());
		//for(String string : sentenceArr) 
		  //  System.out.println(string);
		

		
		for(int i=0; i<sentenceArr.length; i++) {
			for(String word: sentenceArr[i].split(regexp.toString())) {
				wordsList.add(word);
			}
		
			sentencesList.add(wordsList);
		}
		


	}//overloaded constructor ends
	
	
void removeStopWords(ArrayList<ArrayList<String>> sentList) {
	
	for(int i=0;i<sentList.size();i++) {
		
		for(int j=0; j<sentList.get(i).size();j++) {
			
			for(int k=0; k<stopWords.length;k++) {
				if(sentList.get(i).get(j).equals(stopWords[k]))
					sentList.get(i).remove(j);
			}
		}
		
	}
	
}


}//class ends


class MapUtil {
	public static <K, V extends Comparable<? super V>> Map<K, V> 
    sortByValue(Map<K, V> map) {
    List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
    Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
        @Override
        public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            return (o1.getValue()).compareTo(o2.getValue());
        }
    });

    Map<K, V> result = new LinkedHashMap<>();
    for (Map.Entry<K, V> entry : list) {
        result.put(entry.getKey(), entry.getValue());
    }
    return result;
}
}