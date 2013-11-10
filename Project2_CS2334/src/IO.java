import java.io.*;
import java.util.ArrayList;

/**Class that reads input from the user as well as writes output to either the screen or to a file.
 * 
 * @version 1.0
 */
public class IO{

	/**Reads in and returns a String specified by the user.
	 * 
	 * @return the String input by the user
	 * @throws IOException
	 */
	public static String getInput() throws IOException{
		String temp = null;
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		temp = inputReader.readLine();
		return temp;
	}
	
	/** Reads in papers from a file and stores them into a Paper Collection object.
	 * 
	 * @param papers the Paper Collection to be used to store the papers from the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void readFile(PaperCollection papers) throws FileNotFoundException, IOException, ClassNotFoundException{
		String temp = null;
		String line = null;
		System.out.print("Enter the name of the data file: ");
		temp = getInput();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(temp));
		String output = "";
		
		while((line = bufferedReader.readLine()) != null) {
			
			if(line.endsWith(",")){
				String superline = "";
				do{
					superline += line;
					line += " " + bufferedReader.readLine();
				}while(line.endsWith(","));
				
				output += superline + ";;";
			}else{
			output += line + ";;";
			}
		}
		
		String[] paperObjects = output.split(";;;;");
		
		for(int index = 0; index < paperObjects.length; ++index){
			String[] split = paperObjects[index].split(";;");
			
			String[] individualAuthorNames = split[1].split(";");
			ArrayList<Authors> authorList = new ArrayList<Authors>();
			Authors newAuthor = new Authors();
			
			for(int i = 0; i < individualAuthorNames.length; ++i){
				char[] characters = individualAuthorNames[i].toCharArray();
				for(int f = 0; f < characters.length; ++f){
					if(characters[f] == ','){
						newAuthor.makeAuthors(individualAuthorNames[i]);
						authorList.add(newAuthor);
					}else{
						newAuthor.makeSingleNameAuthors(individualAuthorNames[i]);
					}
				}
				
			}
			
			if(split.length == 6){
				papers.addAuthors(split[0], authorList, split[2], split[3], split[4], split[5]);
			} 
			else if(split.length == 7){
				papers.addAuthors(split[0], authorList, split[2], split[3], split[4], split[5], split[6]);
			}
		}
		bufferedReader.close();
	}
	
	/**Writes the output to a file.
	 * 
	 * @param output the name of the output file specified by the user
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeFile(String output) throws FileNotFoundException, IOException{
		System.out.print("Enter the name of file to write to: ");
		String temp = getInput();
		FileWriter outfile = new FileWriter(temp);
		BufferedWriter bw = new BufferedWriter(outfile);
		bw.write(output);
		bw.close();
	}
	
	public static void saveObject(PaperCollection papers){
		
	}
	
	public static void loadObject(PaperCollection papers) throws IOException{
		System.out.print("Enter the name of file to write to: ");
		String fileName = getInput();
		FileInputStream fileIn = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		String line = null;
		String output = "";
		
		while((line = in.readLine()) != null) {
					
			output += line + ";;";
		}
		
		String[] paperObjects = output.split(";;;;");
		
		for(int index = 0; index < paperObjects.length; ++index){
			String[] split = paperObjects[index].split(";;");
			
			String[] individualAuthorNames = split[1].split(";");
			ArrayList<Authors> authorList = new ArrayList<Authors>();
			Authors newAuthor = new Authors();
			
			for(int i = 0; i < individualAuthorNames.length; ++i){
				char[] characters = individualAuthorNames[i].toCharArray();
				for(int f = 0; f < characters.length; ++f){
					if(characters[f] == ',')
						newAuthor.makeAuthors(individualAuthorNames[i]);
						authorList.add(newAuthor);
				}
				
			}
			
			
			if(split.length == 6){
				papers.addAuthors(split[0], authorList, split[2], split[3], split[4], split[5]);
			} 
			else if(split.length == 7){
				papers.addAuthors(split[0], authorList, split[2], split[3], split[4], split[5], split[6]);
			}
		}
		in.close();
	}
}
