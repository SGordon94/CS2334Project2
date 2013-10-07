import java.io.IOException;


public class MainDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		PaperCollection paperCollection = new PaperCollection();
		boolean run = true;
		
		System.out.println("Welcome to ScholarPub!");
		IO.readFile(paperCollection);
		while(run){
			System.out.print("Please input your desired function: ");
			String temp = IO.getInput();
			switch(temp){
				case "BI":
				case "Bi":
				case "bi":
				case "bI":
					System.out.println("Sorting by the bibliographic order.");
					paperCollection.setCompareValues(1);
					paperCollection.sort("bi", paperCollection);
					System.out.println("Sorted. Searches now search by this order.\n");
					break;
					
				case "AN":
				case "An":
				case "an":
				case "aN":
					System.out.println("Sorting by the author name(s).");
					paperCollection.setCompareValues(2);
					paperCollection.sort("an", paperCollection);
					System.out.println("Sorted. Searches now search by author name.\n");
					break;
					
				case "PT":
				case "Pt":
				case "pt":
				case "pT":
					System.out.println("Sorting by the paper titles.");
					paperCollection.setCompareValues(3);
					paperCollection.sort("pt", paperCollection);
					System.out.println("Sorted. Searches now search by paper title.\n");
					break;
					
				case "ST":
				case "St":
				case "st":
				case "sT":
					System.out.println("Sorting by the serial titles.");
					paperCollection.setCompareValues(4);
					paperCollection.sort("st", paperCollection);
					System.out.println("Sorted. Searches now search by serial title.\n");
					break;
					
				case "CH":
				case "Ch":
				case "ch":
				case "cH":
					System.out.println("Sorting by chronological order.");
					paperCollection.setCompareValues(5);
					paperCollection.sort("ch", paperCollection);
					System.out.println("Sorted. Searches now search by date.\n");
					break;
					
				case "R":
				case "r":
					System.out.println("Shuffling the papers.");
					paperCollection.setCompareValues(6);
					paperCollection.sort("r", paperCollection);
					System.out.println("Shuffled. Searches now search by paper title.\n");
					break;
					
				case "PS":
				case "Ps":
				case "ps":
				case "pS":
					System.out.println("\n" + paperCollection.outputPapers() + "\n");
					break;
					
				case "PF":
				case "Pf":
				case "pf":
				case "pF":
					IO.writeFile(paperCollection.outputPapers());
					System.out.println("The file has been written.\n");
					break;
					
				case "S":
				case "s":
					
					break;
					
				case "E":
				case "e":
				case "EXIT":
				case "exit":
				case "Exit":
					run = false;
					break;
					
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
					System.out.println("CH: sort by chronological order");
					System.out.println("R:  randomly sort");
					System.out.println("PS: print the papers to the screen");
					System.out.println("PF: write the papers to a file");
					System.out.println("S:  search by the previously sorted option; default is paper title");
					System.out.println("H:  show the help menu");
					System.out.println("E:  exit the program\n");
					break;
					
				default:
					System.out.println("Invalid input. You may enter H for help.\n");
					break;
			}
		}
		
		System.out.println("Thank you for using ScholarPub!");
		System.exit(0);
	}

}
