import java.io.IOException;
import java.text.ParseException;


public class MainDriver {
	private ScholarshipModel model;
	private SelectionView mainView = new SelectionView();
	private ScholarPubController controller = new ScholarPubController();
	
	public MainDriver(){
		ScholarshipModel model = new ScholarshipModel();
		controller.setModel(model);
		controller.setView(mainView);
		controller.setSelectionViewActionListeners();
		mainView.setModel(model);
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
		PaperCollection paperCollection = new PaperCollection();
		boolean run = true;
		boolean fileOpened = false;
		
		System.out.println("Welcome to ScholarPub!");
		
		while(!fileOpened){
			// Reads the file
			System.out.println("What would you like to do?");
			System.out.println("RF = Read a provided text file that has the proper formatting.");
			System.out.println("LO = Read a file that was previously saved using this program.");
			System.out.println("UI = Bring up an user interface for manipulation. (Project 4)");
			System.out.println("E  = Exit the program.");
			System.out.println("Please input your selection here: ");
			
			String response = IO.getInput();
		
			switch(response){
			
				// Reads from a specified file
				case "RF":
				case "Rf":
				case "rF":
				case "rf":
					IO.readFile(paperCollection);
					fileOpened = true;
					break;
					
				// Reads objects from a file
				case "LO":
				case "lO":
				case "Lo":
				case "lo":
					paperCollection = IO.loadObject(paperCollection);
					fileOpened = true;
					break;
					
				// Brings up the GUI
				case "UI":
				case "uI":
				case "Ui":
				case "ui":
					fileOpened = true;
					run = false;
					new MainDriver();
					break;
				
				// Exits the program
				case "E":
				case "e":
				case "EXIT":
				case "exit":
				case "Exit":
					System.out.println("Thank you for using ScholarPub!");
					System.exit(0);
					break;
					
				// If one of the above statements are not entered
				default:
					System.out.println("Invalid command.");
					break;
			}
		}
		
		// Enters main program
		while(run){
			System.out.print("Please input your desired function: ");
			String temp = IO.getInput();
			switch(temp){
			
					// Sorts the papers based on the order used by cited Scholarly Paper
			
				case "BI":
				case "Bi":
				case "bi":
				case "bI":
					System.out.println("Sorting by the bibliographic order.");
					paperCollection.setCompareValues(1);
					paperCollection.sort("bi", paperCollection);
					System.out.println("Sorted. Searches now search by this order.\n");
					break;
					
					// Sorts the papers based on author names in a lexicographical order
				case "AN":
				case "An":
				case "an":
				case "aN":
					System.out.println("Sorting by the author name(s).");
					paperCollection.setCompareValues(2);
					paperCollection.sort("an", paperCollection);
					System.out.println("Sorted. Searches now search by author name.\n");
					break;
					
					// Sorts the paper based on paper titles in a lexicographical order
				case "PT":
				case "Pt":
				case "pt":
				case "pT":
					System.out.println("Sorting by the paper titles.");
					paperCollection.setCompareValues(3);
					paperCollection.sort("pt", paperCollection);
					System.out.println("Sorted. Searches now search by paper title.\n");
					break;
					
					// Sorts the paper based on serial titles in a lexicographical order
				case "ST":
				case "St":
				case "st":
				case "sT":
					System.out.println("Sorting by the serial titles.");
					paperCollection.setCompareValues(4);
					paperCollection.sort("st", paperCollection);
					System.out.println("Sorted. Searches now search by serial title.\n");
					break;
					
					// Sorts the papers chronologically 
				case "CH":
				case "Ch":
				case "ch":
				case "cH":
					System.out.println("Sorting by chronological order.");
					paperCollection.setCompareValues(5);
					paperCollection.sort("ch", paperCollection);
					System.out.println("Sorted. Searches now search by date.\n");
					break;
					
					// Shuffles the papers into a random order
				case "R":
				case "r":
					System.out.println("Shuffling the papers.");
					paperCollection.setCompareValues(6);
					paperCollection.sort("r", paperCollection);
					System.out.println("Shuffled. Searches now search by paper title.\n");
					break;
					
					// Prints the papers to the screen
				case "PS":
				case "Ps":
				case "ps":
				case "pS":
					System.out.println("\n" + paperCollection.outputPapers() + "\n");
					break;
					
					// Prints the papers to a file
				case "PF":
				case "Pf":
				case "pf":
				case "pF":
					IO.writeFile(paperCollection.outputPapers());
					System.out.println("The file has been written.\n");
					break;
					
					// Searches by a certain criteria
				case "S":
				case "s":
					switch(paperCollection.returnCompareValue()){
						case 1:
						case 3:
						case 6:
							System.out.println("The search function will search for paper titles.");
							break;
						case 2:
							System.out.println("The search function will search for author names.");
							break;
						case 4:
							System.out.println("The search function will search for serial titles.");
							break;
						case 5:
							System.out.println("The search function will search for dates.");
							break;
						default:
							System.out.println("Internal Error: illegal compareValue");
							System.exit(-1);
							break;
					}
					System.out.print("Please input your search string: ");
					String temp2 = IO.getInput();
					int result = paperCollection.search(temp2);
					if (result == -1){
						System.out.println("The search did not yield any results.\n");
					}
					else{
						System.out.println("Your search was found at index " + result + ".\n");
						System.out.println(paperCollection.displayPaper(result) + "\n");
					}
					break;
					
					// Finds authors
				case "FA":
				case "Fa":
				case "fa":
				case "fA":
					System.out.print("Please enter the name of the author you wish to find: ");
					String temp3 = IO.getInput();
					int result2 = paperCollection.findAuthor(temp3);
					if(result2 <= -1){
						System.out.println("The author was not found.");
					}
					else{
						System.out.println(paperCollection.displayAuthorsPapers(result2) + "\n");
					}
					break;
					
				case "LD":
				case "Ld":
				case "ld":
				case "lD":
					paperCollection = IO.loadObject(paperCollection);
					break;
					
				case "SV":
				case "Sv":
				case "sv":
				case "sV":
					IO.saveObject(paperCollection);
					break;
					
					// Used to display options for the graph
				case "G":
				case "g":
					System.out.print("Please enter the name of an author: ");
					String temp4 = IO.getInput();
					int authorFoundIndex = paperCollection.findAuthor(temp4);
					if(authorFoundIndex != -1){
						System.out.println("The author has been found. The graph has not been implemented.");
					}
					else{
						System.out.println("The author was not found.");
					}
					break;
					
					// Exits the program
				case "E":
				case "e":
				case "EXIT":
				case "exit":
				case "Exit":
					run = false;
					// Closes main program
					System.out.println("Thank you for using ScholarPub!");
					System.exit(0);
					break;
					
					// Gives the user help with input
				case "H":
				case "h":
				case "HE":
				case "He":
				case "hE":
				case "he":
				case "HELP":
				case "help":
				case "Help":
					System.out.println("These are the available inputs.");
					System.out.println("BI: sort by bibliographic order");
					System.out.println("AN: sort by author names");
					System.out.println("PT: sort by paper title");
					System.out.println("ST: sort by serial title");
					System.out.println("CH: sort by chronological order; dates must be in \"MONTH YEAR\" format");
					System.out.println("R:  randomly sort");
					System.out.println("PS: print the papers to the screen");
					System.out.println("PF: write the papers to a file");
					System.out.println("FA: finds and displays the papers associated with an author");
					System.out.println("S:  search by the previously sorted option; default is paper title");
					System.out.println("G:  display graphs related to an author");
					System.out.println("LD: loads a previously saved file");
					System.out.println("SV: saves a file to the disk");
					System.out.println("H:  show the help menu");
					System.out.println("E:  exit the program\n");
					break;
					
				default:
					System.out.println("Invalid input. You may enter H for help.\n");
					break;
			}
		}
	}

}
