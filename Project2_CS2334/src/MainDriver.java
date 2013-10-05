import java.io.IOException;


public class MainDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		PaperCollection paperCollection = new PaperCollection();
		
		FileIO.getInput();
		FileIO.readFile(paperCollection);
		FileIO.writeFile("output.txt", paperCollection.outputPapers());
	}

}
