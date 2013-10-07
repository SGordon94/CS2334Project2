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
	
	public int search(ArrayList<Paper> acceptedList, String key){
		int left  = 0;
		int right = acceptedList.size() - 1;
		switch(compareValue){
		
			case 3:
				while( right - left + 1 > 0) {
					int middleIndex = ( left + right ) / 2;
					String middleElement = acceptedList.get(middleIndex).getTitleOfPaper();
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
	
	public void setCompareValues(int value){
		compareValue = value;
		for (int i=0; i < paperCollection.size(); i++){
			paperCollection.get(i).setCompareValue(value);
		}
	}
}
