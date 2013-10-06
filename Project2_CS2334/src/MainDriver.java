import java.io.IOException;


public class MainDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		PaperCollection paperCollection = new PaperCollection();
		boolean run = true;
		
		IO.readFile(paperCollection);
		while(run){
			System.out.println("Please input your desired function: ");
			String temp = IO.getInput();
			switch(temp){
				case "BI":
				case "Bi":
				case "bi":
				case "bI":
					break;
					
				case "AN":
				case "An":
				case "an":
				case "aN":
					break;
					
				case "PT":
				case "Pt":
				case "pt":
				case "pT":
					break;
					
				case "ST":
				case "St":
				case "st":
				case "sT":
					break;
					
				case "CH":
				case "Ch":
				case "ch":
				case "cH":
					break;
					
				case "R":
				case "r":
					break;
					
				case "PS":
				case "Ps":
				case "ps":
				case "pS":
					break;
					
				case "PF":
				case "Pf":
				case "pf":
				case "pF":
					IO.writeFile(paperCollection.outputPapers());
					break;
					
				case "S":
				case "s":
					break;
					
				case "E":
				case "e":
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
					break;
					
				case "TEST":
				case "test":
				case "Test":
					break;
					
				default:
					System.out.println("Invalid input. You may enter H for help.");
					break;
			}
		}
		
		System.out.println("Thank you for using ScholarPub!");
		System.exit(0);
	}

}
