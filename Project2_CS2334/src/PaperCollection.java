import java.util.*;


public class PaperCollection {
	int compareValue = 6;
	ArrayList<Paper> paperCollection = new ArrayList<Paper>();
	
	public PaperCollection(){
	}
	
	public void addPaper(String typeOfPaper, String authors, String titleOfPaper, String titleOfSerial,
			String numbers, String publicationDate, String digitalObjectIdentifier){
		paperCollection.add(new Paper(typeOfPaper, authors, titleOfPaper, titleOfSerial, numbers,
				publicationDate, digitalObjectIdentifier));
	}
	
	public void addPaper(String typeOfPaper, String authors, String titleOfPaper, String titleOfSerial,
			String numbers, String publicationDate){
		paperCollection.add(new Paper(typeOfPaper, authors, titleOfPaper, titleOfSerial, numbers, publicationDate));
	}
	
	public void sort(String receivedCase, PaperCollection paperCollection){
		switch(receivedCase){
			case "bi":
				ArrayList<Paper> papers = paperCollection.returnPaperArray();
				for(int index = 0; index < papers.size(); ++index){
					
					if(papers.get(index).getAuthors() == null){
						paperCollection.setCompareValues(2);
						Collections.sort(paperCollection.returnPaperArray(), new Paper());
					}
					else
						Collections.sort(paperCollection.returnPaperArray(), new Paper());	
				}
				break;
				
			case "an":
			case "pt":
			case "st":
			case "ch":
				Collections.sort(paperCollection.returnPaperArray(), new Paper());
				break;
				
			case "r":
				Collections.shuffle(paperCollection.returnPaperArray());
				break;
		}			
	}
	
	public int search(String key){
		int left  = 0;
		int right = paperCollection.size() - 1;
		switch(compareValue){
			case 2:
				while( right - left + 1 > 0) {
					int middleIndex = ( left + right ) / 2;
					String middleElement = paperCollection.get(middleIndex).getAuthors();
					int comparisonValue = middleElement.compareTo(key);
		
					if (comparisonValue < 0) {
						left = middleIndex + 1;
					}
					else if (comparisonValue > 0) {
						right = middleIndex - 1;				
					}
					else {
						return middleIndex;
					}
				}
				break;
		
			case 3:
				while( right - left + 1 > 0) {
					int middleIndex = ( left + right ) / 2;
					String middleElement = paperCollection.get(middleIndex).getTitleOfPaper();
					int comparisonValue = middleElement.compareTo(key);
			
					if (comparisonValue < 0) {
						left = middleIndex + 1;
					}
					else if (comparisonValue > 0) {
						right = middleIndex - 1;				
					}
					else {
						return middleIndex;
					}
				}
				break;
				
			case 4:
				while( right - left + 1 > 0) {
					int middleIndex = ( left + right ) / 2;
					String middleElement = paperCollection.get(middleIndex).getTitleOfSerial();
					int comparisonValue = middleElement.compareTo(key);
			
					if (comparisonValue < 0) {
						left = middleIndex + 1;
					}
					else if (comparisonValue > 0) {
						right = middleIndex - 1;				
					}
					else {
						return middleIndex;
					}
				}
				break;
				
			case 5:
				while( right - left + 1 > 0) {
					int middleIndex = ( left + right ) / 2;
					String middleElement = paperCollection.get(middleIndex).getPublicationDate();
					int comparisonValue = middleElement.compareTo(key);
			
					if (comparisonValue < 0) {
						left = middleIndex + 1;
					}
					else if (comparisonValue > 0) {
						right = middleIndex - 1;				
					}
					else {
						return middleIndex;
					}
				}
				break;
				
			case 1:
			case 6:
				for (int i=0; i < paperCollection.size(); i++){
					String compareElement = paperCollection.get(i).getTitleOfPaper();
					int comparisonValue = compareElement.compareTo(key);
					if (comparisonValue == 0){
						return i;
					}
				}
				break;
		}
		return -1;	
	}
	
	public ArrayList<Paper> returnPaperArray(){
		return this.paperCollection;
	}
	
	public String outputPapers(){
		String outputString = "";
		for (int i=0; i < paperCollection.size(); i++){
			outputString += paperCollection.get(i).getPaper();
			if (i != paperCollection.size() - 1){
				outputString += "\n\n";
			}
		}
		return outputString;
	}
	
	public String displayPaper(int index){
		return paperCollection.get(index).getPaper();
	}
	
	public void setCompareValues(int value){
		compareValue = value;
		for (int i=0; i < paperCollection.size(); i++){
			paperCollection.get(i).setCompareValue(value);
		}
	}
}
