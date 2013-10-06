import java.io.IOException;


public class MainDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		PaperCollection paperCollection = new PaperCollection();
		
		IO.readFile(paperCollection);
		IO.writeFile("output.txt", paperCollection.outputPapers());
	}

}
