import java.io.*;
import java.util.*;


public class FileIO{
	
	/**
	 * 
	 */

	private static String ScholarPub;
	private static Paper paper;
	
	public static void getInput() throws IOException{
		
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter the name of the data file: ");
		
		ScholarPub = inputReader.readLine();
	}
	
	public static void readFile(PaperCollection papers) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(ScholarPub));
		String line;
		String output = "";
		ArrayList<String> lines = new ArrayList<String>();
		
		while((line = bufferedReader.readLine()) != null) {
			
			output += line + ";;";
		}
		
		String[] paperObjects = output.split(";;;;");
		
		for(int index = 0; index < paperObjects.length; ++index){
			String[] split = paperObjects[index].split(";;");
			
			if(split.length == 6){
				papers.addPaper(split[0], split[1], split[2], split[3], split[4], split[5]);
			} 
			else if(split.length == 7){
				papers.addPaper(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
			}
		}
	}
	
	public static void writeFile(String fileName, String output) throws FileNotFoundException, IOException{
		FileWriter outfile = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(outfile);
		bw.write(output);
		bw.close();
	}
}
