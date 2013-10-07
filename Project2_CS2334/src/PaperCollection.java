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
	
	public void search(){
		
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
