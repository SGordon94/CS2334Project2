import java.io.IOException;


public class MainDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		PaperCollection paperCollection = new PaperCollection();
		
		FileIO.getInput();
		FileIO.writeFile("output.txt", paperCollection.outputPapers());
		//Paper testPaper1 = Paper("Conference Paper", "Woehrer, Mark; Hougen, Dean; Schlupp, Ingo",
		//		"Sexual Selection, Resource Distribution, and Population Size in Synthetic Sympatric Speciation",);
	}

}
