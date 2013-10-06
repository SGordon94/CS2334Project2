import java.io.*;
import java.util.*;


public class IO{
	
	/**
	 * 
	 */

	public static String getInput() throws IOException{
		String temp = null;
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		temp = inputReader.readLine();
		return temp;
	}
	
	public static void readFile(PaperCollection papers) throws FileNotFoundException, IOException, ClassNotFoundException{
		String temp = null;
		String line = null;
		System.out.print("Enter the name of the data file: ");
		temp = getInput();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(temp));
		String output = "";
		
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
		bufferedReader.close();
	}
	
	public static void writeFile(String output) throws FileNotFoundException, IOException{
		System.out.print("Enter the name of file to write to: ");
		String temp = getInput();
		FileWriter outfile = new FileWriter(temp);
		BufferedWriter bw = new BufferedWriter(outfile);
		bw.write(output);
		bw.close();
	}
}
