import java.util.*;

/**
 * This code runs the project management system for Poised.
 * <p>
 * It displays all the options a user can select to create and track projects.
 * Requests the user to select an option and calls the relevant method to carry 
 * out the option selected. All data is saved and can be edited at a later stage.
 * @see readAndWriteFiles #createPersonFile()
 * @see readAndWriteFiles #createProjectFile()
 * @see UserInterfaceMethods #displayOptions()
 * @see ProjectMethods #createNewProjectAndWriteToFile()
 * @see ProjectMethods #editExistingProjectDetails(Scanner, int)
 * @see ProjectMethods #finalizeProjectAndWriteToFile(Scanner)
 * @see UserInterfaceMethods #displayProjectStillToBeCompleted()
 * @see UserInterfaceMethods #displayProjectsOverdue()
 * @author Nadia Botha
 * @version 1.0
 */

public class Poised {
    public static void main(String[] args){
    	
    	// When the program is executed, the createPersonFile and createProjectFile methods are called.
    	// This will create a Person and Project text file if the files do not already exist.
    	
        readAndWriteFiles.createPersonFile();

        readAndWriteFiles.createProjectFile();
        
        // Calls the displayOption method, which prints out the list of options the user can select.
        
        UserInterfaceMethods.displayOptions();

        Scanner userInput = new Scanner(System.in);
   
        // The user's choice is stored as an integer.
        int userChoice = UserInterfaceMethods.getUserChoice();

        // The while loop will be executed as long as the user does not choose the exit option.
        while(userChoice != 6){

        	// If the user selects option 1, the createNewProjectAndWriteToFile method is called.
        	// All of the project details are written to the project.txt file. The persons created
        	// while defining the project is also written to the person.txt file.
        	if(userChoice == 1){
        		userChoice = ProjectMethods.createNewProjectAndWriteToFile(); 		      	
        	}
        	
        	// If the user selects option 2, the editExistingProjectDetails method is called. 
        	// The new edited details are written to the person and project text files.
        	else if (userChoice == 2){     		
        		userChoice = ProjectMethods.editExistingProjectDetails(userInput, userChoice);
        	}
        	
        	// If the user selects option 3, the finalizeProjectAndWriteToFile method is called.
        	// The status of the project is updated, written to the project file. The project is also written
        	// to the completed projects text file.
        	// If the full amount has not been paid, an invoice is generated.
        	else if (userChoice == 3){
           		userChoice = ProjectMethods.finalizeProjectAndWriteToFile(userInput);
        	}
        	
        	// If option 4 is  chosen, the displayProjectsStillToBeCompleted method is called. 
        	// Loops through the projects text file, and displays all projects that have an incomplete 
        	// status.
        	else if (userChoice == 4){
        		userChoice = UserInterfaceMethods.displayProjectStillToBeCompleted();
        	}
        	
        	// If option 5 is selected, the displayProcetsOverdue method is called. 
        	// Loops through the projects text file, compares the due date with the current date. 
        	// If the due date is earlier than the current date the project details are displayed. 
        	else if(userChoice == 5){
        		userChoice = UserInterfaceMethods.displayProjectsOverdue();
        	}
       }
        
        // Displays an exit message if option 6 is selected. 
        userInput.close();
        System.out.println("Exiting the program....");

    }
}


    

