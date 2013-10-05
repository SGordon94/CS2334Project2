import java.io.*;
import java.util.*;


public class FileIO {
	private static  String ScholarPub;
	private static Paper paper;
	private static ArrayList<Paper> papers = new ArrayList<Paper>();
	
	public static void getInput() throws IOException{
		
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter the name of the data file");
		
		ScholarPub = inputReader.readLine();
	}
	
	public static void readFile() throws FileNotFoundException, IOException{
		
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ScholarPub));
		
	}
}
