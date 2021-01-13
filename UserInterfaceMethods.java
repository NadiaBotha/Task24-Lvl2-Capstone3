import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class groups all methods that are required to run the system, without creating or editing Person or Project objects.
 * <p>
 * The methods in this class consists of repeating or lengthy display options as well as checking if user input is valid or not.
 * The methods do pull information form the Project and Person objects, but do not create or modify it.
 * 
 * @author Nadia Botha
 * @version 1.0  
 *
 */
public class UserInterfaceMethods {
	/**
	 * This method displays the options of the main menu. 
	 * <p>
	 * The options include creating a new object, editing an existing project, display projects that still have to be completed, dispay
	 * projects that are overdue and to exit the program.
	 */
	public static void displayOptions(){
		// When the method is called the options below are displayed.
		System.out.println("**********MAIN MENU************");
		System.out.println("");
		System.out.println("Please select one of the following options");
		System.out.println("");
		System.out.println("1. Create a new project.");
		System.out.println("2. Update an existing project.");
		System.out.println("3. Finalize an existing project");
		System.out.println("4. Display projects that still have to be completed.");
		System.out.println("5. Display projects that are overdue.");
		System.out.println("6. Exit the program");
			
	}
	/**
	 * This method requests the user to input an integer to execute an option from the main menu. 
	 * <p>
	 * The method request the user to input an integer form 1-6. After receiving the input, it checks
	 * if the input is valid. If not, it requests the user to try again.
	 * @see UserInterfaceMethods #displayEditOptions()
	 * @return returns the integer for the main menu option chosen
	 */
	
	public static int getUserChoice(){
		
		// Trying to create a method that returns the userChoice in integer form. Also displays an error if it is not a number. 
		// Must check if it is 1,2,3,4,5,6.
		// Define the scanner object to receive input from the user.
		Scanner userInput = new Scanner(System.in);
		// Create a string variable an initialize it - this will store the user input.
		String userChoice = "";
		// Create an integer variable and initialize it - will store the user input if it can be converted to an intger.
		int userChoiceNumber = 0;
		
		// Create a boolean variable and initialize it to false.
		boolean isUserChoiceValid = false;
		
		// The while loop will keep executing as long as the isUserChoiceValid is false.
		while(isUserChoiceValid == false){
			System.out.println("");
			// Gets a string input from the user.
			System.out.println("Please enter your option here:");
			userChoice = userInput.nextLine();
			
			// Try to convert the string value to an integer value.
			try{
				userChoiceNumber = Integer.parseInt(userChoice);
				/* If the string value can be converted to an integer value, still need to check if it is within range.
				 * If the integer value is smaller than 1 or bigger than 6, an error message is displayed and isUserChoiceValid is
				 * set to false so that the while loop is executed again.*/
				if(userChoiceNumber < 1 || userChoiceNumber > 6){
					System.out.println("Integer entered is not valid, please select a valid option.");
					isUserChoiceValid = false;
				}
				/* If the string value can be converted to an integer and is within range, the isUserChoiceValid is set to true.
				 * The while loop wil be exited.*/
				else{
					isUserChoiceValid = true;
				}		
			}	
			// If the user input string cannot be converted to an integer an error message is dipslayed and the while loop is executed again.
			catch(Exception e){
				System.out.println("Incorrect input, please enter an integer.");
				isUserChoiceValid = false;
			}		
		}
		
		// Returns an integer between 1-6.
		return userChoiceNumber;
						
		}
	/**
	 * This method displays all the project options which can be edited.
	 * <p>
	 * The options include to update the project fee, the total paid to date, the project deadline, the project arhitect's contact
	 * details, the project contractor's contact details and the project customer's contact details.
	 */
	public static void displayEditOptions(){
		System.out.println("1. Total fee charged");
		System.out.println("2. Total paid to date");
		System.out.println("3. Update the deadline");
		System.out.println("4. Update project architect's contact details");
		System.out.println("5. Update project contractor's contact details");
		System.out.println("6. Update project customer's contact details");
		
	}
	/**
	 * This method displays an invoice to the user when called.
	 * <p>
	 * The method displays the specific project's customer name, customer telephone number, customer email address
	 * and customer physical address. It also calculates the amount outstanding on the project by calling the 
	 * amountOutstandingToBePaid method, and displays this as part of the invoice.
	 * @see Project #getProjectCustomer()
	 * @see Person #getPersonName()
	 * @see Person #getTelephoneNumber()
	 * @see Person #getPhysicalAddress()
	 * @see Person #getEmailAddress()
	 * @param indexToFinalize Index integer of the project within the projects array.
	 * @param allProjectDetailsArray Project Array containing all the details of existing projects.
	 */
	public static void generateInvoive(int indexToFinalize, Project [] allProjectDetailsArray){
		/* Create string variables for the customer name, number, email address and physical address.
		 * Set the variables equal to the specific project's customer details, by using getter methods.*/
		String customerName = allProjectDetailsArray[indexToFinalize].getProjectCustomer().getPersonName();
		String customerNumber = allProjectDetailsArray[indexToFinalize].getProjectCustomer().getTelephoneNumber();
		String customerEmailAddress = allProjectDetailsArray[indexToFinalize].getProjectCustomer().getEmailAddress();
		String customerPhysicalAddress = allProjectDetailsArray[indexToFinalize].getProjectCustomer().getPhysicalAddress();
		
		/* Create a double variable and set it equal to the return value of amountOutstandingToBePaid in ProjectMethods.
		 * It calculates the amount to still be paid.*/
		double amountPayable = ProjectMethods.amountOutstandingToBePaid(indexToFinalize, allProjectDetailsArray);
		
		// The invoice displayed to the user.
		System.out.println("");
		System.out.println("**********INVOICE*************");
		System.out.println("");
		System.out.println("Customer information: ");
		System.out.println("Name: "+ customerName);
		System.out.println("Number: "+ customerNumber);
		System.out.println("Email address: "+ customerEmailAddress);
		System.out.println("Physical address: "+ customerPhysicalAddress);
		
		System.out.println("");
		System.out.println("Outstanding amount payable within 30 days: R" +amountPayable );
		System.out.println("");
		System.out.println("*******************************");
	
	}
	/**
	 * This method displays all the projects that are overdue.
	 * <p>
	 * This method focuses on getting the project details that are overdue and displaying it in a readable format. The overdue 
	 * projects are placed in an array by calling the createArrayListForProjectsOverdue method.
	 * 
	 * @see readAndWriteFiles #readInputAndSaveProjectArray()
	 * @see readAndWriteFiles #convertStringArrayListToProjectArray(ArrayList)
	 * @see ProjectMethods #createArrayListForProjectsOverdue(Project[])
	 * @see Project #getProjectNumber()
	 * @see Project #getProjectName()
	 * @see Project #getProjectDeadline()
	 * @see UserInterfaceMethods #runMainMenu()
	 * 
	 * @return returns the integer for the user's next choice on the main menu
	 */
	
	
	public static int displayProjectsOverdue() {
		int userChoice;
		// Read the project file, save each line as an array entry in a String ArrayList. Convert the String ArrayList to a Project Array.
		ArrayList<String> allProjectsDetailsStringArrayList = readAndWriteFiles.readInputAndSaveProjectArray();
		Project[] allProjectsDetailsArray = readAndWriteFiles.convertStringArrayListToProjectArray(allProjectsDetailsStringArrayList);
		
		/* Calls the createArrayListForProjectsOverdue method to create an array, with the current Project array allProjectsDetailsArray as input, that 
		 saves as the projects overdue.*/
		ArrayList<Project> overdueProjects = ProjectMethods.createArrayListForProjectsOverdue(allProjectsDetailsArray);
		
		// Display message to the user.
		System.out.println("-------------------------------------------");
		System.out.println("Projects overdue are: ");
		System.out.println("");
		// Loops through the overdue Project array and prints the project details to the user.
		for (int i = 0; i < overdueProjects.size(); i++){
			System.out.println("Project Number: " + overdueProjects.get(i).getProjectNumber());
			System.out.println("Project Name: " + overdueProjects.get(i).getProjectName());
			System.out.println("Project Deadline: " + overdueProjects.get(i).getProjectDeadline());
			System.out.println("");
		}
		System.out.println("-------------------------------------------");
		System.out.println("");
		
		// Returns back to the main menu and requests the user to input the option to execute.
		userChoice = UserInterfaceMethods.runMainMenu();
		// Returns the integer for the next option on the main menu.
		return userChoice;
	}
	
	/**
	 * This method displays all the projects that still have to be completed.
	 * <p>
	 * This method focuses on getting the project details that still have to completed and displaying it in a readable format. The incomplete
	 * projects are placed in an array by calling the createIncompleteProjectsArray method.
	 * @see readAndWriteFiles #readInputAndSaveProjectArray()
	 * @see readAndWriteFiles #convertStringArrayListToProjectArray(ArrayList)
	 * @see ProjectMethods #createIncompleteProjectsArray(Project[])
	 * @see Project #getProjectNumber()
	 * @see Project #getProjectName()
	 * @see Project #getProjectDeadline()
	 * @see UserInterfaceMethods #runMainMenu()
	 * @return returns the integer for the user's next choice on the main menu
	 */
	
	public static int displayProjectStillToBeCompleted() {
			int userChoice;
			
			// Refer to the comments in displayProjectsOverdue as the two methods share the same logic.
			ArrayList<String> allProjectsDetailsStringArrayList = readAndWriteFiles.readInputAndSaveProjectArray();
			Project[] allProjectsDetailsArray = readAndWriteFiles.convertStringArrayListToProjectArray(allProjectsDetailsStringArrayList);
			
			Project[] projectStillToBeCompleted = ProjectMethods.createIncompleteProjectsArray(allProjectsDetailsArray);
			
			System.out.println("-------------------------------");
			System.out.println("Projects to be completed are: ");
			System.out.println("");
			
			
			for(int i = 0; i <projectStillToBeCompleted.length;i++){
				System.out.println("Project Number: " + projectStillToBeCompleted[i].getProjectNumber());
				System.out.println("Project Name: " + projectStillToBeCompleted[i].getProjectName());
				System.out.println("Project Deadline: " + projectStillToBeCompleted[i].getProjectDeadline());
			}
			
			System.out.println("-------------------------------");
			     		
	/*	System.out.println("Project successfully finalized, returning to main menu...");
			System.out.println("");*/
			
			
			
			userChoice = UserInterfaceMethods.runMainMenu();
			return userChoice;
		}
	/**
	 * This method displays the main menu and requests the user to input an option.
	 * <p>
	 * @see UserInterfaceMethods #displayOptions()
	 * @see UserInterfaceMethods #getUserChoice()
	 * @return returns the integer for the user's next choice on the main menu
	 */
	public static int runMainMenu() {
		int userChoice;
		// Displays the main menu options by calling the displayOption method.
		displayOptions();
		// Obtains the user selection and checks if it is valid by calling the getUserChoice method.
		userChoice = getUserChoice();
		return userChoice;
	}
	
}
