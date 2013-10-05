import java.io.*;
import java.util.*;


public class FileIO{
	
	/**
	 * 
	 */

	private static String ScholarPub;
	private static Paper paper;
	
	private static ArrayList<Paper> papers = new ArrayList<Paper>();
	
	public static void getInput() throws IOException{
		
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter the name of the data file: ");
		
		ScholarPub = inputReader.readLine();
	}
	
	public static void readFile(PaperCollection papers) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(ScholarPub));
		String line;
		ArrayList<String> lines = new ArrayList<String>();
		
		while((line = bufferedReader.readLine()) != null) {
			
			lines.add(line);
		}
	}
	
	public static void writeFile(String fileName, String output) throws FileNotFoundException, IOException{
		FileWriter outfile = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(outfile);
		bw.write(output);
		bw.close();
	}
}
