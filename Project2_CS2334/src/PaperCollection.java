import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**PaperCollection is the class used to create a Paper Collection object which manages the papers imported from a file.
*
*
* @version 1.0
*/
public class PaperCollection implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = -1306506026096077696L;
        private int compareValue = 6;
        private ArrayList<Paper> paperCollection = new ArrayList<Paper>();
        private ArrayList<Scholar> authorsCollection = new ArrayList<Scholar>();
        
        public PaperCollection(){
        }
        
        /**Adds a paper with a Digital Object ID to the collection of papers.
         *
         * @param typeOfPaper the type of the paper
         * @param authors the authors of the paper
         * @param titleOfPaper the title of the paper
         * @param titleOfSerial the title of the serial of the paper
         * @param numbers the number of volumes and issues for the paper (if the paper is a journal) and the page number
         * @param publicationDate the date the paper was published
         * @param digitalObjectIdentifier the digital object identifier of the paper
         */
        public void addPaper(String typeOfPaper, ArrayList<Scholar> authors, String titleOfPaper, String titleOfSerial,
                        String numbers, String publicationDate, String digitalObjectIdentifier){
        	if(typeOfPaper.equals("Conference Paper")){
                paperCollection.add(new ConferencePaper(typeOfPaper, authors, titleOfPaper, titleOfSerial, numbers,
                        publicationDate, digitalObjectIdentifier));
        	}else if(typeOfPaper.equals("Journal Article")){
                paperCollection.add(new JournalPaper(typeOfPaper, authors, titleOfPaper, titleOfSerial, numbers,
                        publicationDate, digitalObjectIdentifier));
        	}
        }
        
        /** Adds a paper without a Digital Object ID to the collection of papers.
         *
         * @param typeOfPaper the type of the paper
         * @param authors the authors of the paper
         * @param titleOfPaper the title of the paper
         * @param titleOfSerial the title of the serial of the paper
         * @param numbers the number of volumes and issues for the paper (if the paper is a journal) and the page number
         * @param publicationDate the date the paper was published
         */
        public void addPaper(String typeOfPaper, ArrayList<Scholar> authors, String titleOfPaper, String titleOfSerial,
                        String numbers, String publicationDate){
        	if(typeOfPaper.equals("Conference Paper")){
        		paperCollection.add(new ConferencePaper(typeOfPaper, authors, titleOfPaper, titleOfSerial, numbers, publicationDate));
        	}else if(typeOfPaper.equals("Journal Article")){
        		paperCollection.add(new JournalPaper(typeOfPaper, authors, titleOfPaper, titleOfSerial, numbers, publicationDate));

        	}
                
        }
        
        /** UNOFFICIAL COMMENT, ONLY HERE TO LET YOU KNOW HOW TO USE METHOD
         * Okay, this method is to be used instead of the addPaper() method. It looks through both
         * the global authorsCollection ArrayList and the paper-local authors ArrayList.
         * I've implemented a method in the Authors' class that outputs a string that is easily
         * comparable, and this utilizes that. If the author names are the same from both ArrayLists,
         * then it removes the created author object in the paper-local authors ArrayList and places a
         * reference to the author object in the global authorsCollection ArrayList. If a match is not found,
         * then it adds the author object to the global authorsCollection ArrayList. From there, it calls
         * the addPaper() method and passes the relevant variables along, so that each paper that has the
         * same author references the exact same Authors object, while the same author object has references
         * to each paper it belongs to.
         */
        public void addAuthors(String typeOfPaper, ArrayList<Scholar> authors, String titleOfPaper, String titleOfSerial,
                        String numbers, String publicationDate, String digitalObjectIdentifier){
                for(int i=0; i<authors.size(); i++){
                        boolean noMatch = true;
                        if(authorsCollection.size() != 0){
                                for(int j=0; j<authorsCollection.size(); j++){
                                        if(authors.get(i).returnNameInString().equals(authorsCollection.get(j).returnNameInString())){
                                                authors.remove(i);
                                                authors.add(0,authorsCollection.get(j));
                                                noMatch = false;
                                        }
                                }
                                if(noMatch){
                                        authorsCollection.add(authors.get(i));
                                }
                        }
                        else{
                                authorsCollection.add(authors.get(i));
                        }
                }
                addPaper(typeOfPaper, authors, titleOfPaper, titleOfSerial, numbers,
                                publicationDate, digitalObjectIdentifier);
        }
        
        public void addAuthors(String typeOfPaper, ArrayList<Scholar> authors, String titleOfPaper, String titleOfSerial,
                        String numbers, String publicationDate){
                for(int i=0; i<authors.size(); i++){
                        boolean noMatch = true;
                        if(authorsCollection.size() != 0){
                                for(int j=0; j<authorsCollection.size(); j++){
                                        if(authors.get(i).returnNameInString().equals(authorsCollection.get(j).returnNameInString())){
                                                authors.remove(i);
                                                authors.add(0,authorsCollection.get(j));
                                                noMatch = false;
                                        }
                                }
                                if(noMatch){
                                        authorsCollection.add(authors.get(i));
                                }
                        }
                        else{
                                authorsCollection.add(authors.get(i));
                        }
                }
                addPaper(typeOfPaper, authors, titleOfPaper, titleOfSerial, numbers, publicationDate);
        }
        
        /**The sort method for the PaperCollection class that uses Collections.sort() to sort for each received case.
         *
         * @param receivedCase the case in which to sort specified by the user
         * @param paperCollection the collection of papers to sort
         */
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
        
        /**The search method for the PaperCollection class that uses binary and linear search to search for a key specified by the user.
         *
         * @param key the key being searched for by the user
         * @return the index of the found paper
         * @throws ParseException
         */
        public int search(String key) throws ParseException{
                int left = 0;
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
                                        Date key2 = new SimpleDateFormat("MMMM yyyy").parse(key);
                                        int middleIndex = ( left + right ) / 2;
                                        Date middleElement = paperCollection.get(middleIndex).getPublicationDateFormatted();
                                        int comparisonValue = middleElement.compareTo(key2);
                        
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
        /**Returns the collection of papers as an ArrayList
         *
         * @return returns the collection of the papers as an ArrayList
         */
        public ArrayList<Paper> returnPaperArray(){
                return this.paperCollection;
        }
        
        /**Returns the papers as a String in a viewable format.
         *
         * @return the papers as a String in a viewable format.
         */
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
        
        /**Returns the paper specified at a certain index as a String
         *
         * @param index the index of the paper
         * @return the paper specified at the index
         */
        public String displayPaper(int index){
                return paperCollection.get(index).getPaper();
        }
        
        /**Sets the compare values of the collection of the papers so that the papers are sorted correctly based on a specified criteria.
         *
         * @param value the specified sort option
         */
        public void setCompareValues(int value){
                compareValue = value;
                for (int i=0; i < paperCollection.size(); i++){
                        paperCollection.get(i).setCompareValue(value);
                }
        }
        
        /**Returns the compare value.
         *
         * @return the compare value as an int
         */
        public int returnCompareValue(){
                return this.compareValue;
        }
        
        /**Searches through the authors array. If a match is found, then the
         * index of the author object is returned. If a match is not found,
         * then -1 is returned.
         * 
         * @param key
         * @return
         */
        public int findAuthor(String key){
        	final int MATCH_NOT_FOUND = -1;
        	for(int i=0;i<authorsCollection.size();i++){
        		if(authorsCollection.get(i).returnNameInString().equals(key)){
        			return i;
        		}
        	}
        	return MATCH_NOT_FOUND;
        }
        
        public String displayAuthorsPapers(int index){
        	return authorsCollection.get(index).displayPapers();
        }
        
        public Scholar getScholar(int index){
        	return authorsCollection.get(index);
        }
}
