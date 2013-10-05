import java.io.IOException;


public class MainDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileIO fileIO = new FileIO();
		PaperCollection paperCollection = new PaperCollection();
		
		fileIO.getInput();
		fileIO.writeFile("output.txt", paperCollection.returnPaperArray());
		//Paper testPaper1 = Paper("Conference Paper", "Woehrer, Mark; Hougen, Dean; Schlupp, Ingo",
		//		"Sexual Selection, Resource Distribution, and Population Size in Synthetic Sympatric Speciation",);
	}

}
