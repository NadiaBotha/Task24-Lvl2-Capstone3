import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * This class groups all the methods that read or write data to the person.txt, projects.txt and CompletedProject.txt files.
 * <p>
 * The class also includes methods that convert Arrays and ArrayLists so that the data is usable for following steps.
 * @author Nadia Botha
 * @version 1.0
 *
 */
public class readAndWriteFiles {

	/**
	 * This method creates an empty text file called projects.txt where all the Project object details are stored permanently. 
	 * <p>
	 * If the text file already exists, nothing is done. If the text file does not exist and cannot be created an error message is 
	 * displays.
	 * @see <a href="file:../projects.txt">../projects.txt</a>
	 */
    public static void createProjectFile(){
        String successCreateFile = "";
        
        // Try to create the file.
        try{
            File myObj = new File("projects.txt");
            // Executes if the file can be created and does not exist.
            if(myObj.createNewFile()){
                //System.out.println("File created: " + myObj.getName());
                successCreateFile = "Sucessfull Creation";
            // Executes if the file can be created but already exists.
            }else{
                //System.out.println("File already exists.");
                successCreateFile = "File already exists";
            }
        // Displays an error message if there is an error creating the file.
        }catch(IOException e){
            successCreateFile = "An error occured";
            System.out.println(successCreateFile);
            e.printStackTrace();
        }

    }
    /**
     * This method creates an empty text file called person.txt where all the Person object details are stored permanently. 
     * <p>
     * If the text file already exists, nothing is done. If the text file does not exist and cannot be created an error message is 
	 * displays
	 * @see <a href="file:../person.txt">../person.txt</a>
     */
    public static void createPersonFile(){
    	// Refer to the comments in createProjectFile as the methods share the same logic. 
        String successCreateFile = "";
        try{
            File myObj = new File("person.txt");

            if(myObj.createNewFile()){
                //System.out.println("File created: " + myObj.getName());
                successCreateFile = "Sucessfull Creation";
            }else{
                //System.out.println("File already exists.");
                successCreateFile = "File already exists";
            }
        }catch(IOException e){
            successCreateFile = "An error occured";
            System.out.println(successCreateFile);
            e.printStackTrace();
        }

    }
    /**
     * This method creates an empty text file called CompletedProject.txt where all the Project object details are stored permanently, 
     * for projects that have been finalized. 
     * <p>
     * If the text file already exists, nothing is done. If the text file does not exist and cannot be created an error message is 
	 * displays
	 * @see <a href="file:../CompletedProject.txt">../CompletedProject.txt</a>
     */
    public static void createCompletedProjectFile(){
        String successCreateFile = "";
     // Refer to the comments in createProjectFile as the methods share the same logic.
        try{
            File myObj = new File("CompletedProject.txt");

            if(myObj.createNewFile()){
                //System.out.println("File created: " + myObj.getName());
                successCreateFile = "Sucessfull Creation";
            }else{
                //System.out.println("File already exists.");
                successCreateFile = "File already exists";
            }
        }catch(IOException e){
            successCreateFile = "An error occured";
            System.out.println(successCreateFile);
            e.printStackTrace();
        }

    }
    /**
     * This method reads the project.txt file saves each line as an array entry in a String ArrayList.
     * 
     * @return returns a String ArrayList with all the project detail lines in the text file as array entries.
     */
    public static ArrayList<String> readInputAndSaveProjectArray(){
        try{
        	// Create a String ArrayList.
            ArrayList<String> allProjectsDetails = new ArrayList<String>();
            // Access the file.
            File projectDetails = new File("projects.txt");
            
            // Try to read the project.txt file.
            try{
                Scanner myReader = new Scanner(projectDetails);
                
                // If the file was read successfully, add each line as an array entry in the String ArrayList.
                while(myReader.hasNextLine()){
                    allProjectsDetails.add(myReader.nextLine());
                }
                // Close the reader object.
                myReader.close();
            }
            // If the file could not be read, return an error message.
            catch(FileNotFoundException e){
                System.out.println("An error occured");
                e.printStackTrace();
            }
            // Return the String ArrayList.
            return allProjectsDetails;
        // If the file could not be opened/found display an error message.
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
        // If the file could not be accessed return a null ArrayList.
        return null;
    }
    /**
     * This method reads the person.txt file saves each line as an array entry in a String ArrayList.
     * 
     * @return returns a String ArrayList with all the person detail lines in the text file as array entries.
     */

    public static ArrayList<String> readInputAndSavePersonArrayString(){
    	
    	// Refer to the comments in readInputAndSaveProjectArray as the two methods share the same logic. 
        try{
            ArrayList<String> allPersonsDetailsString = new ArrayList<String>();

            File personDetails = new File("person.txt");

            try{
                Scanner myReader = new Scanner(personDetails);

                while(myReader.hasNextLine()){
                    allPersonsDetailsString.add(myReader.nextLine());
                }
                myReader.close();
            }
            catch(FileNotFoundException e){
                System.out.println("An error occured");
                e.printStackTrace();
            }

            return allPersonsDetailsString;

        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * This method converts a String ArrayList to a Project Array.
     * 
     * @param allProjectsDetails Project Array containing all the details of existing projects.
     * @return returns the Project Array with all the existing Project object field values.
     */
    
    public static Project [] convertStringArrayListToProjectArray(ArrayList<String> allProjectsDetails){
    	// Create a new Project Array, with the same size as the String ArrayList. Represents the amount of rows of the array.
        Project [] allProjectDetailsArray;
        allProjectDetailsArray = new Project[allProjectsDetails.size()];
        
        /* Create a new String Array, with the size equal to the entries in a single line of the text file.
         * This represents the amount of columns of the array.*/ 
        String [] stringToConvertArray = new String[22];
        

        // Loops through each row of the ArrayList.
        for(int i = 0; i < allProjectsDetails.size();i++){
        	// Set the Project Array row equal to a new Project object.
            allProjectDetailsArray[i] = new Project();
            // Create 3 new Person objects for each Project Array row - architect, customer and contractor.
            Person projectArchitect = new Person();
            Person projectContractor = new Person();
            Person projectCustomer = new Person();
            
            // Create a new String variable and set it equal to the index entry of the ArrayList.
            String stringToConvert = allProjectsDetails.get(i);
            // Split the string into separate words and save each word as an array entry.
            stringToConvertArray = stringToConvert.split(",");

            // Refine the string to exclude unwanted "[" and "]" characters.
            String refinedArchitectName = stringToConvertArray[8].replace("[", "");
            String refinedArchitectPhysicalAddress = stringToConvertArray[11].replace("]", "");


            String refinedContractorName = stringToConvertArray[12].replace("[", "");
            String refinedContractorPhysicalAddress = stringToConvertArray[15].replace("]", "");
 

            String refinedCustomerName = stringToConvertArray[16].replace("[", "");
            String refinedCustomerPhysicalAddress = stringToConvertArray[19].replace("]", "");

            /* Set the Project fields equal to specific array indices of the split array - stringToConvertArray.
             * For example in the project number is found at index number 0 of stringToConvertArray*/
            allProjectDetailsArray[i].setProjectNumber(stringToConvertArray[0]);
            allProjectDetailsArray[i].setProjectName(stringToConvertArray[1]);
            allProjectDetailsArray[i].setBuildingType(stringToConvertArray[2]);
            allProjectDetailsArray[i].setPhysicalAddress(stringToConvertArray[3]);
            allProjectDetailsArray[i].setERFNumber(stringToConvertArray[4]);
            allProjectDetailsArray[i].setTotalFee(stringToConvertArray[5]);
            allProjectDetailsArray[i].setTotalPaid(stringToConvertArray[6]);
            allProjectDetailsArray[i].setDueDate(stringToConvertArray[7]);

            projectArchitect.setPersonName(refinedArchitectName);
            projectArchitect.setPersonNumber(stringToConvertArray[9]);
            projectArchitect.setPersonEmailAddress(stringToConvertArray[10]);
            projectArchitect.setPersonPhysicalAddress(refinedArchitectPhysicalAddress);


            allProjectDetailsArray[i].setProjectArchitect(projectArchitect);
 


            projectContractor.setPersonName(refinedContractorName);
            projectContractor.setPersonNumber(stringToConvertArray[13]);
            projectContractor.setPersonEmailAddress(stringToConvertArray[14]);
            projectContractor.setPersonPhysicalAddress(refinedContractorPhysicalAddress);

            allProjectDetailsArray[i].setProjectContractor(projectContractor);

            projectCustomer.setPersonName(refinedCustomerName);
            projectCustomer.setPersonNumber(stringToConvertArray[17]);
            projectCustomer.setPersonEmailAddress(stringToConvertArray[18]);
            projectCustomer.setPersonPhysicalAddress(refinedCustomerPhysicalAddress);

            allProjectDetailsArray[i].setProjectCustomer(projectCustomer);

            allProjectDetailsArray[i].setProjectStatus(stringToConvertArray[20]);
            allProjectDetailsArray[i].setprojectCompletionDate(stringToConvertArray[21]);

            

        }
        // Return the Project Array.
        return allProjectDetailsArray;


    }
    
    /**
     * Converts a Project Array to a String Array, so that it can be written to the projects.txt text file.
     * 
     * @param allProjectDetailsArray Project Array containing all the details of existing projects.
     * @return returns a String Array with all the Project object details.
     */

    public static String [] convertProjectArrayToStringArray(Project [] allProjectDetailsArray){
    	
    	// Creates a new String Array and sets it equal to the size of the Project Array.
        String [] stringArrayToWriteToText = new String[allProjectDetailsArray.length];

        // Loop through each line of the Project Array. 
        for(int i = 0; i < allProjectDetailsArray.length;i++){
        	// Create a string variable for each project field and set it equal to that fields value by using getter methods. 
            String projectNumber = allProjectDetailsArray[i].getProjectNumber();
            String projectName = allProjectDetailsArray[i].getProjectName();
            String projectBuildingType = allProjectDetailsArray[i].getBuildingType();
            String projectPhysicalAddress = allProjectDetailsArray[i].getAddress();
            String projectERFNumber = allProjectDetailsArray[i].getERFNumber();
            String projectTotalCost = allProjectDetailsArray[i].getProjectFee();
            String projectTotalPaid = allProjectDetailsArray[i].getProjectTotalPaid();
            String projectDeadline = allProjectDetailsArray[i].getProjectDeadline();

            String projectArchitectName = allProjectDetailsArray[i].getProjectArchtect().getPersonName();
            String projectArchitectNumber = allProjectDetailsArray[i].getProjectArchtect().getTelephoneNumber();
            String projectArchitectEmailAddress = allProjectDetailsArray[i].getProjectArchtect().getEmailAddress();
            String projectArchitectPhysicalAddress = allProjectDetailsArray[i].getProjectArchtect().getPhysicalAddress();

            String projectContractorName = allProjectDetailsArray[i].getProjectContractor().getPersonName();
            String projectContractorNumber = allProjectDetailsArray[i].getProjectContractor().getTelephoneNumber();
            String projectContractorEmailAddress = allProjectDetailsArray[i].getProjectContractor().getEmailAddress();
            String projectContractorPhysicalAddress = allProjectDetailsArray[i].getProjectContractor().getPhysicalAddress();

            String projectCustomerName = allProjectDetailsArray[i].getProjectCustomer().getPersonName();
            String projectCustomerNumber = allProjectDetailsArray[i].getProjectCustomer().getTelephoneNumber();
            String projectCustomerEmailAddress = allProjectDetailsArray[i].getProjectCustomer().getEmailAddress();
            String projectCustomerPhysicalAddress = allProjectDetailsArray[i].getProjectCustomer().getPhysicalAddress();

            String projectStatus = allProjectDetailsArray[i].getProjectStatus();
            String projectCompletion = allProjectDetailsArray[i].getProjectCompletionDate();
            
            // Create the final string (an entry in the String Array) by concatenating all the string variables above.
            stringArrayToWriteToText[i] = projectNumber +","+projectName+","+projectBuildingType+","+projectPhysicalAddress
                    +","+projectERFNumber+","+projectTotalCost+","+projectTotalPaid+","+projectDeadline+",["+projectArchitectName+","+
                    projectArchitectNumber+","+projectArchitectEmailAddress+","+projectArchitectPhysicalAddress+"],"+"["+projectContractorName+
                    ","+projectContractorNumber+","+projectContractorEmailAddress+","+projectContractorPhysicalAddress+"],"+"["+projectCustomerName+
                    ","+projectCustomerNumber+","+projectCustomerEmailAddress+","+projectCustomerPhysicalAddress+"],"+projectStatus+","+projectCompletion;
        }

        // Return the String Array for Project objects.
        return stringArrayToWriteToText;
    }
    /**
     * This method writes project String Arrays to the projects.txt file.
     * @see <a href="file:../projects.txt">../projects.txt</a>
     * @param projectDetailsArray String Array containing all the details of existing projects.
     */
    public static void writeUpdatedInfoToProjectTextFile(String [] projectDetailsArray){
        // Try to open the file.
    	try{
    		// Create a FileWriter object to write to the projects.txt file.
            FileWriter myWriter = new FileWriter("projects.txt");
            
            // Loop through each line in the String Array and write each entry as line in the text file.
            for(int i = 0; i < projectDetailsArray.length; i++){
                myWriter.write(projectDetailsArray[i] + "\n");
            }
            // Close the writer object.
            myWriter.close();
        // Display an error message if the file cannot be opened.
        }catch(IOException e){
            System.out.println("An error occured");
            e.printStackTrace();

        }
    }
    /**
     * This method writes person String Arrays to the person.txt file.
     * @see <a href="file:../person.txt">../person.txt</a>
     * @param personDetailsToPrint String Array containing all the existing Person objects as strings.
     */
    public static void writeUpdatedInfoToPersonTextFile(String[] personDetailsToPrint){
        // Refer to comments in writeUpdatedInfoToProjectTextFile as the methods share the same logic.
    	try{
            FileWriter myWriter = new FileWriter("person.txt");

            for(int i = 0; i < personDetailsToPrint.length; i++){
                myWriter.write(personDetailsToPrint[i] + "\n");
            }

            myWriter.close();

        }catch(IOException e){
            System.out.println("An error occured");
            e.printStackTrace();

        }
    }
    /**
     * This method writes Project String Arrays to the CompletedProject.txt file.
     * @see <a href="file:../CompletedProject.txt">../CompletedProject.txt</a>
     * @param completedProjectsArray String Array containing all the existing Project objects that have been finalized.
     */
    public static void writeUpdatedInfoToCompletedProjectTextFile(String [] completedProjectsArray){
    	// Refer to comments in writeUpdatedInfoToProjectTextFile as the methods share the same logic.
    	try{
            FileWriter myWriter = new FileWriter("CompletedProject.txt");

            for(int i = 0; i < completedProjectsArray.length; i++){
                myWriter.write(completedProjectsArray[i] + "\n");
            }

            myWriter.close();

        }catch(IOException e){
            System.out.println("An error occured");
            e.printStackTrace();

        }
    }

    /**
     * This method converts a String ArrayList to a Person ArrayList.
     * 
     * @param allPersonsDetailsString String Array containing all the existing Person objects as strings.
     * @return returns a Person ArrayList with all Person objects.
     */
    public static ArrayList<Person> convertStringToPersonArray(ArrayList<String> allPersonsDetailsString){
    	// Refer to convertStringArrayListToProjectArray as the two methods share the same logic.
        ArrayList<Person> allPersonsDetails = new ArrayList<Person>();
        String personDetails;

        for(int i = 0; i<allPersonsDetailsString.size(); i++){
            personDetails = allPersonsDetailsString.get(i);
            String [] personDetialsString =  personDetails.split(",");
            Person readPerson = new Person(personDetialsString[0], personDetialsString[1], personDetialsString[2], personDetialsString[3], personDetialsString[4]);
            allPersonsDetails.add(readPerson);
        }

        return allPersonsDetails;

    }

    /**
     * This method converts a Person ArrayList to a String Array so that it can be written to the person.txt file.
     * 
     * @param allPersonsDetails Person ArrayList containing all the existing Person objects.
     * @return returns the String Array with all the Person objects.
     */
    public static String[] convertPersonArrayToString(ArrayList<Person> allPersonsDetails){
        String [] personDetailsToPrint = new String[allPersonsDetails.size()];

        for(int i= 0; i < allPersonsDetails.size(); i++){
            Person personDetails = allPersonsDetails.get(i);
            String personName = personDetails.getPersonName();
            String personTelephone = personDetails.getTelephoneNumber();
            String personEmailAddress = personDetails.getEmailAddress();
            String personPhysicalAddress = personDetails.getPhysicalAddress();
            String personType = personDetails.getPersonType();

            personDetailsToPrint[i] = personName+","+personTelephone+","+personEmailAddress+","+personPhysicalAddress+","+personType;

        }


        return personDetailsToPrint;

    }

/*    *//**
     * This method 
     * @param projectDetails
     *//*
    public static void writeUpdatedInfoToProjectTextFile(String [][] projectDetails){
        try{
            FileWriter myWriter = new FileWriter("projects.txt");

            for(int i=0; i<projectDetails.length;i++){

                for(int j=0; j<((projectDetails[0].length)-1);j++){
                    myWriter.write(projectDetails[i][j]+",");
                }
                myWriter.write("\n");
            }
            myWriter.close();

        }catch(IOException e){
            System.out.println("An error occured");
            e.printStackTrace();

        }
    }
*/


}
