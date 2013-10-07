import java.util.*;


public class PaperCollection {
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
		
		//if(receivedCase.equals("bi"))
		
		if(receivedCase.equals("an"))
			Collections.sort(paperCollection.returnPaperArray(), new Paper());
		
		
		//if(receivedCase.equals("pt"))
		
		//if(receivedCase.equals("st"))
		
		//if(receivedCase.equals("ch"))
		
		if(receivedCase.equals("r"))
			Collections.shuffle(paperCollection.returnPaperArray());
			
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
		for (int i=0; i < paperCollection.size(); i++){
			paperCollection.get(i).setCompareValue(value);
		}
	}
}
