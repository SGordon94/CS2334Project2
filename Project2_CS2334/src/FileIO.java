import java.io.*;
import java.util.*;


public class FileIO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7343823647743067146L;
	private static String ScholarPub;
	private static Paper paper;
	
	private static ArrayList<Paper> papers = new ArrayList<Paper>();
	
	public static void getInput() throws IOException{
		
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter the name of the data file: ");
		
		ScholarPub = inputReader.readLine();
	}
	
	public static void readFile() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ScholarPub));
		paper = (Paper) objectInputStream.readObject();
		
		papers.add(paper);
		objectInputStream.close();
	}
	
	public static void writeFile(String fileName, ArrayList<Paper> papers) throws FileNotFoundException, IOException{
		FileWriter outfile = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(outfile);
		bw.write("Test Write");
		bw.newLine();
		bw.close();
	}
}
