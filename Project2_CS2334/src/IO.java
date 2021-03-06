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
                        output += (line + ";;");
                }
                
                String[] paperObjects = output.split(";;;;");
                
                for(int index = 0; index < paperObjects.length; ++index){
                        String[] split = paperObjects[index].split(";;");
                        if(split.length > 1){
	                        String[] individualAuthorNames = split[1].split(";");
	                        ArrayList<Scholar> authorList = new ArrayList<Scholar>();
	                        
	                        for(int i = 0; i < individualAuthorNames.length; ++i){
	                        	Scholar newAuthor = new Scholar(individualAuthorNames[i]);
	                        	authorList.add(newAuthor);
	                        }
	                        
	                        if(split.length == 6){
	                                papers.addAuthors(split[0], authorList, split[2], split[3], split[4], split[5]);
	                        }
	                        else if(split.length == 7){
	                                papers.addAuthors(split[0], authorList, split[2], split[3], split[4], split[5], split[6]);
	                        }
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
        
        /**Writes a PaperCollection object to a file to be read by this particular program
         * at a later date.
         * 
         * @param papers the object that will be written to disk
         * @throws IOException
         */
        public static void saveObject(PaperCollection papers) throws IOException{
                System.out.print("Enter the name of file to save to: ");
                String temp = getInput();
                FileOutputStream fileOut = new FileOutputStream(temp);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                
                objectOut.writeObject(papers);
                
                objectOut.close();
        }
        
        /**Loads a PaperCollection object that was previously written to a file by this
         * program.
         * 
         * @return the PaperCollection object that was read from the disk
         * @throws IOException
         * @throws ClassNotFoundException
         */
        public static PaperCollection loadObject() throws IOException, ClassNotFoundException{
                System.out.print("Enter the name of file: ");
                String fileName = getInput();
                FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                
                PaperCollection papers = (PaperCollection) in.readObject();
                
                in.close();
                
                return papers;
        }
}