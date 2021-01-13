import java.util.ArrayList;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Scanner;


/**
 * This class groups all methods used to create and modify Project objects.
 * 
 * @author Nadia Botha
 * @version 1.0
 */
public class ProjectMethods {
	/**
	 * This method enables the user to create a new Project object.
	 * <p>
	 * The method requests the user to enter the values for Project fields (name, number, ERF Number, building type, total cost, total paid
	 * to date, deadline, project architect, project contractor, project customer) and creates a new Project object using the values entered.
	 * <p>
	 * If no project name is provided, the name is automatically populated as the customer surname and building type. The method also ensures that
	 * the project number is unique by calling the isProjectNumberUnique, which prevents project number duplication.
	 * <p>
	 * If the name and surname entered for a Person object (architect, contractor and customer) and the Person already exists, the field values
	 * for that Person does not need to entered again. It pulls the data from the person.txt file and saves the Person in the relevant Project field
	 * (projectArchitect, projectContractor, projectCustomer).
	 * @see ProjectMethods #isProjectNumberUnique(String, Project[])
	 * @see ProjectMethods #addExistingArchitectToProject(ArrayList, Scanner)
	 * @see ProjectMethods #addExistingContractorToProject(ArrayList, Scanner)
	 * @see ProjectMethods #addExistingCustomerToProject(ArrayList, Scanner)
	 * @see Project #Project(String, String, String, String, String, String, String, String, Person, Person, Person, String, String)	
	 *
	 * @param allPersonsDetails Person ArrayList containing all the existing Person objects.
	 * @param projectDetailsArray Project Array containing all the details of existing projects.
	 * @return returns the new Project object with all the field values defined by the user.
	 */
    public static Project createNewProject(ArrayList<Person> allPersonsDetails, Project [] projectDetailsArray){
    	// Create a scanner object to define Project object field values.     	
        Scanner userInput = new Scanner(System.in);
        
        /* Request the user the enter project number and check if the project number is unique by calling isProjectNumberUnique, which
         * compares the number entered with all of the existing project numbers.*/
        System.out.println("Enter the project number: ");
        String userProjectNumber = userInput.nextLine();
        
        // The isProjectNumberUnique provides an integer value. If the value is not zero, it means that the project number exists.
        int checkIfProjectNumberIsUnique = isProjectNumberUnique(userProjectNumber,projectDetailsArray);

        // This code block will keep executing until a unique project number is entered and the value returned by isProjectNumberUnique is zero.
        while(checkIfProjectNumberIsUnique != 0){

            if(checkIfProjectNumberIsUnique > 0){
                System.out.println("Project number entered is not unique, please enter another number:");
                userProjectNumber = userInput.nextLine();
                checkIfProjectNumberIsUnique = isProjectNumberUnique(userProjectNumber, projectDetailsArray);
            }
            // If the user entered a non-integer value.
            else{
                System.out.println("The input is not a valid integer, please enter a valid integer");
                userProjectNumber = userInput.nextLine();
                checkIfProjectNumberIsUnique = isProjectNumberUnique(userProjectNumber, projectDetailsArray);
            }
        }
        // Rest of field values are requested from the user.
        System.out.println("Enter project name: ");
        String userProjectName = userInput.nextLine();

        System.out.println("Enter building type: ");
        String userBuildingType = userInput.nextLine();

        System.out.println("Enter the physical address (please do not use commas): ");
        String userPhysicalAddress = userInput.nextLine();

        System.out.println("Enter the ERF Number: ");
        String userERFNumber = userInput.nextLine();

        System.out.println("Enter the total fee payable: ");
        String userPayable = userInput.nextLine();

        System.out.println("Enter the total paid to date: ");
        String userPaid = userInput.nextLine();

        System.out.println("Enter the project deadline: ");
        String userDeadline = userInput.nextLine();
        
        //Define the Person object fields for a Project object and initialize them.
        Person projectArchitect = new Person();
        Person projectContractor = new Person();
        Person projectCustomer = new Person();

        System.out.println("");
        System.out.println("****Project Architect****");

        /* The project architect details are requested from the user, by calling the addExistingArchitectToProject method.
         * The method request for an architect name (name and surname), checks if the Peron object already exists. If it does, 
         * the data is pulled from the text file and used to define the projectArchitect field.*/
        projectArchitect = addExistingArchitectToProject(allPersonsDetails, userInput);

        System.out.println("");
        System.out.println("****Project Contractor****");

        // The same logic as the project architect.
        projectContractor = addExistingContractorToProject(allPersonsDetails, userInput);

        System.out.println("");
        System.out.println("****Project Customer****");
        
        // The same logic as the project architect.
        projectCustomer = addExistingCustomerToProject(allPersonsDetails, userInput);
        
        /*Create a String array and with the customer name and surname as the array entries*/
        String [] customerNameArray = projectCustomer.getPersonName().split(" ");
        // Obtain the string for the customer surname. 
        String customerSurname = customerNameArray[1];
        
        // If no project name is provided this code block is executed to create a name from the customer surname and building type.
        if (userProjectName.length() == 0){
            String newProjectName = createProjectName(customerSurname,userBuildingType);
            userProjectName = newProjectName;
        }
        
        // Calls the constructor to create a Project object, with the field values defined above.
        Project newProject = new Project(userProjectNumber,userProjectName,userBuildingType,userPhysicalAddress,
                userERFNumber,userPayable,userPaid,userDeadline,projectArchitect,projectContractor,projectCustomer,
                "Incomplete","TBD");

        
        // returns the new Project object.
        return newProject;

    }
    /**
     * This method takes a customer name and surname entered and checks if the Person object already exists. If the person exists, the existing Person object is returned. 
     * If it does not exists, a new Person object is created. 
     * <p>
     * If the Person object exists, the index of the Person object in the Person ArrayList is determined and set equal to the Person object created in the method.
     * If the Person does not exists, the createNewCustomer method is called, and the new Person object is added to the existing Person ArrayList and written to the text 
     * file.
     * @see PersonMethods #personIndexInPersonArray(ArrayList, String)
     * @see PersonMethods #createNewCustomer()
     * @see PersonMethods #addPersonToArrayAndWriteToFile(ArrayList, Person)
     * 
     * @param allPersonsDetails Person ArrayList containing all the existing Person objects.
     * @param userInput String input from the to provide the contractor name and surname.
     * @return returns the Person object which is a project architect.
     */
    
    private static Person addExistingCustomerToProject(ArrayList<Person> allPersonsDetails, Scanner userInput) {
        // Creates Person object and initializes it.
    	Person projectCustomer;
    	// Requests the name and surname of the project customer.
        System.out.println("Enter the customer name and surname: ");
        String customerName = userInput.nextLine();
        
        // Calls the personIndexInPersonArray located in the PersonMethods class to check if the customer name is already existing.
        int personIndex = PersonMethods.personIndexInPersonArray(allPersonsDetails, customerName);
        
        // If the person exists, the Person object defined earlier is set equal to the array entry where the name was found.
        if(personIndex != -1){
            projectCustomer = allPersonsDetails.get(personIndex);
            
        /* If the name was not found, a new Person object is created by calling the createNewCustomer method. The Person ArrayList is also updated and written to the
            text file.*/
        }else{
        	System.out.println("");
            System.out.println("The name entered does not exist, please create the person");
            System.out.println("");
            projectCustomer = PersonMethods.createNewCustomer();
            PersonMethods.addPersonToArrayAndWriteToFile(allPersonsDetails, projectCustomer);
        }
        // returns the Person object project customer.
        return projectCustomer;
    }
    /**
     * This method takes a contractor name and surname entered and checks if the Person object already exists. If the person exists, the existing Person object is returned. 
     * If it does not exists, a new Person object is created. 
     * <p>
     * If the Person object exists, the index of the Person object in the Person ArrayList is determined and set equal to the Person object created in the method.
     * If the Person does not exists, the createNewContractor methods is called, and the new Person object is added to the existing Person ArrayList and written to the text 
     * file.
     * @see PersonMethods #personIndexInPersonArray(ArrayList, String)
     * @see PersonMethods #createNewContractor()
     * @see PersonMethods #addPersonToArrayAndWriteToFile(ArrayList, Person)
     * 
     * @param allPersonsDetails Person ArrayList containing all the existing Person objects.
     * @param userInput String input from the to provide the contractor name and surname.
     * @return returns the Person object which is a project contractor.
     */
    private static Person addExistingContractorToProject(ArrayList<Person> allPersonsDetails, Scanner userInput) {
    	// Refer to comments in addExistingCustomerToProject, as the methods are similar.
        Person projectContractor;
        System.out.println("Enter the contractor name and surname: ");
        String contractorName = userInput.nextLine();
        int personIndex = PersonMethods.personIndexInPersonArray(allPersonsDetails, contractorName);
        if(personIndex != -1){
            projectContractor = allPersonsDetails.get(personIndex);
        }else{
        	System.out.println("");
            System.out.println("The name entered does not exist, please create the person");
            System.out.println("");
            projectContractor = PersonMethods.createNewContractor();
            PersonMethods.addPersonToArrayAndWriteToFile(allPersonsDetails, projectContractor);
        }

        return projectContractor;
    }
    
    /**
     * This method takes an architect name and surname entered and checks if the Person object already exists. If the person exists, the existing Person object is returned. 
     * If it does not exists, a new Person object is created. 
     * <p>
     * If the Person object exists, the index of the Person object in the Person ArrayList is determined and set equal to the Person object created in the method.
     * If the Person does not exists, the createNewArchitect methods is called, and the new Person object is added to the existing Person ArrayList and written to the text 
     * file.
     * @see PersonMethods #personIndexInPersonArray(ArrayList, String)
     * @see PersonMethods #createNewArchitect()
     * @see PersonMethods #addPersonToArrayAndWriteToFile(ArrayList, Person)
     * 
     * @param allPersonsDetails Person ArrayList containing all the existing Person objects.
     * @param userInput String input from the to provide the contractor name and surname.
     * @return returns the Person object which is a project architect.
     */
    private static Person addExistingArchitectToProject(ArrayList<Person> allPersonsDetails, Scanner userInput) {
    	// Refer to comments in addExistingCustomerToProject, as the methods are similar.
        Person projectArchitect;
        System.out.println("Enter the architect name and surname: ");
        String architectName = userInput.nextLine();
        int personIndex = PersonMethods.personIndexInPersonArray(allPersonsDetails, architectName);

        if(personIndex != -1){
            projectArchitect = allPersonsDetails.get(personIndex);
        }else{
        	System.out.println("");
            System.out.println("The name entered does not exist, please create the person");
            System.out.println("");
            projectArchitect = PersonMethods.createNewArchitect();
            PersonMethods.addPersonToArrayAndWriteToFile(allPersonsDetails,projectArchitect);
        }

        return projectArchitect;
    }
    
    /**
     * This method checks if a project number entered by the user is unique.
     * <p>
     * The method loops through the existing projects, obtains the project numbers and compares the number entered with these existing numbers.
     * 
     * @param userDefinedProjectNumber Project number entered by the user when creating a new project.
     * @param currentProjects Project Array containing all the details of existing projects.
     * @return returns and integer value which indicates if the number is unique. If the value returned is zero, the number is unique.
     */
    private static int isProjectNumberUnique(String userDefinedProjectNumber, Project [] currentProjects){
        // Converts the String value entered by the user to an Integer.
        int count = 0;
        try{
            int userDefinedProjectNumberInt = Integer.parseInt(userDefinedProjectNumber);

            // Calls the numberOfCurrentProject method to determine the amount of already defined projects.
            int numberOfProjects = currentProjects.length;


            // Loops through the existing projects to check if the number entered by the user is found. If it is, the count variable
            // is indexed. If the count variable is not zero, it means that the number already exists.
            for(int j=0; j < numberOfProjects; j++){
                String currentNumber = currentProjects[j].getProjectNumber();
                int currentNumberInt = Integer.parseInt(currentNumber);
                if(userDefinedProjectNumberInt == currentNumberInt){
                    count ++;
                }

            }

        }
        catch(Exception e){
            count = -1;
        }

        // Returns the integer count value.
        return count;

    }
    /**
     * This method creates a project name from the string values of the project building type and customer surname.
     * <p>
     * @param userInputSurname String that defines the customer surname
     * @param userInputBuildingType String that defines the project building type.
     * @return returns the String value which concatenates the building type and customer surname strings.
     */
    private static String createProjectName(String userInputSurname, String userInputBuildingType){
       // Define a new String for the project name, which concatenates the building type and customer surname string.
    	String defaultProjectName = userInputBuildingType+ " "+ userInputSurname;
    	
    	// Returns the concatenated string.
        return defaultProjectName;
    }
    /**
     * This method adds the newly created Project object to the project array and writes the updated array to the Project text file.
     * <p>
     * Method determines the number of existing projects, adds the project at the first empty array entry. Converts the Project array
     * to a String array and writes the string array to the project text file by calling the convertProjectArrayToStringArray and 
     * writeUpdatedInfoToProjectTextFile methods.
     * 
     * 
     * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
     * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[])
     * @see <a href="file:../projects.txt">../projects.txt</a>
     * @param currentProjectsArray Project Array containing all the details of existing projects.
     * @param newProject New Project object which was previously created.
     */
    public static void addProjectToArrayAndWriteToFile(Project[] currentProjectsArray, Project newProject){
    	// Creates a Project Array which is 1 array entry larger than the existing Project Array. 
        Project [] newCurrentProjectsArray = new Project[currentProjectsArray.length+1];
        
        // Executes if the currentProjectsArray is not empty.
        if(currentProjectsArray.length != 0){

        	/*Loops through the currentProjectsArray and sets each index value of the newCurrentProjectsArray equal to the 
        	 * index value of the currentProjectsArray. This will ensure that the newCurrentProjectsArray is the same as currentProjectsArray, but
        	 * with one addition array entry which is empty.*/
            for (int i = 0; i<currentProjectsArray.length;i++){
                newCurrentProjectsArray[i] = currentProjectsArray[i];
            }
            
            /*Sets the empty array entry equal to the newProject Project object.
             * This array is the same as currentProjectsArray, but with the newly created Project object included*/
            newCurrentProjectsArray[currentProjectsArray.length] = newProject;


            // Converts the array with the new project included to a string array and writes it to the text file to save it permanently.
            String [] convertedStringProjectsArray = readAndWriteFiles.convertProjectArrayToStringArray(newCurrentProjectsArray);
            readAndWriteFiles.writeUpdatedInfoToProjectTextFile(convertedStringProjectsArray);

        }
        // If the original projects array (currentProjectsArray) is empty, the newProject Project object is set to the first array entry.
        else{
            newCurrentProjectsArray[0] = newProject;
            
         // Converts the array with the new project included to a string array and writes it to the text file to save it permanently.
            String [] convertedStringProjectsArray = readAndWriteFiles.convertProjectArrayToStringArray(newCurrentProjectsArray);
            readAndWriteFiles.writeUpdatedInfoToProjectTextFile(convertedStringProjectsArray);
        }

    }
    /**
     * This method looks for a specific project name within the existing Project Array.
     * <p>
     * The method will return the array index of the project name if it is found, otherwise -1 is returned.
     * @see Project #getProjectName()
     * 
     * @param userInputStringToFind Project name to look for in the Project array.
     * @param projectsDetailsArray Project Array containing all the details of existing projects.
     * @return returns an integer value which represents the index at which the project name was found or -1 if it was not found.
     */
	private static int findProjectByName(String userInputStringToFind, Project[] projectsDetailsArray){
		// Create a new String Array the size of the projectDetailsArray.
		String [] projectNameArray = new String[projectsDetailsArray.length];
		// Create an integer value used to store the value of the array index at which the project name was found within the projectsDetailsArray. Initialize to -1.
		int projectIndexToEdit = -1;
		
		/*Loop through the existing projects array, get the project names and sit it equal to the projectNameArray index. This will result 
		 * in an array with all the project names. */
		for(int i = 0; i < projectsDetailsArray.length;i++){
			projectNameArray[i] = projectsDetailsArray[i].getProjectName();			
		}
		
		// Loop through the projectNameArray and check if it is equal to the project name defined by the user as input. If it is equal return the index.
		for (int j = 0; j < projectNameArray.length; j++){
			
			if(projectNameArray[j].equals(userInputStringToFind)){
				projectIndexToEdit =j;
			}
			
		}
		// Return the index of the project in the Project array or return -1.
		return  projectIndexToEdit;
		
		
	}
    /**
     * This method looks for a specific project number within the existing Project Array.
     * <p>
     * The method will return the array index of the project number if it is found, otherwise -1 is returned.
     * @see Project #getProjectNumber()
     * 
     * @param userInputStringToFind Project number to look for in the Project array.
     * @param projectsDetailsArray Project Array containing all the details of existing projects.
     * @return returns an integer value which represents the index at which the project name was found or -1 if it was not found.
     */
	private static int findProjectByNumber(String userInputStringToFind, Project[] projectsDetailsArray){
		//Refer to the comments in findProjectByName as the logic is the same.
		String [] projectNumberArray = new String[projectsDetailsArray.length];
	
		int projectIndexToEdit = -1;
		
		for(int i = 0; i < projectsDetailsArray.length;i++){
			projectNumberArray[i] = projectsDetailsArray[i].getProjectNumber();
					
		}
		
		for(int j = 0; j < projectNumberArray.length; j++){
			
			if(projectNumberArray[j].equals(userInputStringToFind)){
			projectIndexToEdit = j;				
			} 			
		}
		
		return projectIndexToEdit;
		
	}
	/**
	 * This method looks for a project within the existing projects array using either a project name or number provided by the user.
	 * <p>
	 * The method first looks for the project number and then the project name, and returns the array index of where it was found.
	 *  If neither is found a value of -1 is returned. 
	 * @param userInputStringToFind String for the project name or number provided by the user.
	 * @param projectsDetailsArray Project Array containing all the details of existing projects.
	 * @return returns the index value of where the project name/number was found and -1 if neither was found.
	 */
	private static int findProjectByNameOrNumber(String userInputStringToFind, Project[] projectsDetailsArray){
		// Create an integer value and sets it equal to the return value of findProjectByNumber to obtain the array index value for project number userInputStringToFind.
		int indexToEdit = findProjectByNumber(userInputStringToFind, projectsDetailsArray);
		
		/* If the project was not found based on the project number, the findProjectByName method is called to look for the project based on the name. The index is returned
		if it is found, otherwise -1 is returned.*/
		if (indexToEdit == -1){
			indexToEdit = findProjectByName(userInputStringToFind, projectsDetailsArray);
		}
		
		// Returns the index value at which the project was found based on the name/number. Otherwise -1is returned to indicate the project was not found.
		return indexToEdit;
	}
	
	/**
	 * The method changes the completion date and status fields of a specific Project object.
	 * <p>
	 * The method loops through the existing Project array and obtains the index of the Project object within the array. 
	 * It then edits the specific Project completion date to the user defined value and the status to Finalized.
	 * 
	 * @see Project #setprojectCompletionDate(String)
	 * @see Project #setProjectStatus(String)
	 * @param indexToFinalize Index of the project to finalize within the Project Array.
	 * @param allProjectDetailsArray Project Array containing all the details of existing projects.
	 * @return the updated Project array, with the updated Project status and completion date.
	 */
	public static Project[] finalizeProject(int indexToFinalize, Project [] allProjectDetailsArray){
		
		// Create a scanner object to obtain input from the user for the completion date.
		Scanner userInput = new Scanner(System.in);
		
		//s
		System.out.println("Enter the project completion date (dd/mm/yyyy): ");
		String completionDate = userInput.nextLine();
		
		// Change the field values projectStatus and projectCompletion date by calling the relevant setters.
		allProjectDetailsArray[indexToFinalize].setProjectStatus("Finalized");
		allProjectDetailsArray[indexToFinalize].setprojectCompletionDate(completionDate);
		
		// Returns the updated project array with new field values.
		return allProjectDetailsArray;
		
		
	}
	
	/**
	 * The method determines the amount that must still be paid for a specific project.
	 * <p>
	 * The method converts the string total fee field value to a double. It also does it for the total paid to date. 
	 * It then determines the amount to be paid by completing a subtraction calculation (total fee - total paid to date).
	 * @param indexToFinalize Index of the project to finalize within the Project Array.
	 * @param allProjectDetailsArray Project Array containing all the details of existing projects.
	 * @return returns the total amount still to be paid by the customer
	 */
	public static double amountOutstandingToBePaid(int indexToFinalize, Project [] allProjectDetailsArray){
		// Obtain the string for the total fee by calling the getProject method for the Project Array index which should be finalized.
		String totalFeePayableString = allProjectDetailsArray[indexToFinalize].getProjectFee();
		// Same is done with the total paid to date than the total fee.
		String totalPaidString = allProjectDetailsArray[indexToFinalize].getProjectTotalPaid();
		
		// Create two double varaibles and initialize them to zero.
		double totalFeePayableDouble = 0;
		double totalPaidDouble = 0;
		
		// Try convert the total fee from a string to a double.
		try{
			totalFeePayableDouble = Double.parseDouble(totalFeePayableString);
		}
		// If the string could not be converted to a double an error message is displayed.
		catch(Exception e){
			System.out.println("The amount entered is not valid. Please edit the amount and try again");
		}
		// Try convert the total paid to date from a string to a double.
		try{
			totalPaidDouble = Double.parseDouble(totalPaidString);
		}
		// If the string could not be converted to a double an error message is displayed.
		catch(Exception e){
			System.out.println("The amount entered is not valid. Please edit the amount and try again");
		}
		
		// Calculate the amount to still be paid by subtracting the total paid from the total fee.
		double amountOutstanding = totalFeePayableDouble-totalPaidDouble;
		
		// Return the amount that must still be paid.
		return amountOutstanding;
		
		
	}
	/**
	 * This method creates an array which stores all of the finalized projects.
	 * <p>
	 * It loops through the existing projects array and checks if the projectStatus field value is equal to Finalized.
	 * @see Project #getProjectStatus()
	 * 
	 * @param allProjectDetailsArray Project Array containing all the details of existing projects.
	 * @return returns the Project array with all of the finalized projects.
	 */
	private static Project [] createFinalizedProjectsArray(Project [] allProjectDetailsArray){
		// Create a Project ArrayList - unsure what the size of the array should be.
		ArrayList<Project> completedProjectArrayList = new ArrayList<Project>();
		
		// Loops through the existing projects array and obtains the project status.
		for (int i = 0; i< allProjectDetailsArray.length; i++){
			String checkIfProjectFinalized = allProjectDetailsArray[i].getProjectStatus();
			
			// If the project status is equal to Finalized, it is added to the ArrayList.
			if(checkIfProjectFinalized.equals("Finalized")){
				completedProjectArrayList.add(allProjectDetailsArray[i]);
			}
		}
		
		// Creates a new Project Array with the same size as the Project ArrayList.
		Project [] completedProjectArray = new Project[completedProjectArrayList.size()];
		
		// Set the indices of the Array equal to the ArrayList/ Convert an ArrayList to an Array.
		for(int j = 0; j<completedProjectArrayList.size();j++){
			completedProjectArray[j] = completedProjectArrayList.get(j);
		}
		
		// Returns the completed Project array.
		return completedProjectArray;
	}
	/**
	 * This method creates an array which stores all of the incomplete projects.
	 * <p>
	 * It loops through the existing projects array and checks if the projectStatus field value is Incomplete..
	 * @see Project #getProjectStatus()
	 * @param allProjectDetailsArray Project Array containing all the details of existing projects.
	 * @return returns the Project array with Projects that have an incomplete status field value.
	 */
	
	public static Project[] createIncompleteProjectsArray(Project [] allProjectDetailsArray){
		// Refer to the comments in createFinalizedProjectsArray as the methods share the same logic.
		
		ArrayList<Project> incompleteProjectArrayList = new ArrayList<Project>();
		
		for (int i = 0; i < allProjectDetailsArray.length;i++){
			String checkIfProjectIsIncomplete = allProjectDetailsArray[i].getProjectStatus();
			
			if(checkIfProjectIsIncomplete.equals("Incomplete")){
				incompleteProjectArrayList.add(allProjectDetailsArray[i]);
			}
			
		}
		
		Project [] incompleteProjectArray = new Project[incompleteProjectArrayList.size()];
		
		for(int j = 0; j < incompleteProjectArrayList.size(); j++){
			incompleteProjectArray[j] = incompleteProjectArrayList.get(j);
		}
		
		return incompleteProjectArray;
	}
	/**
	 * This method loops through all existing projects and places them in an array if they are overdue.
	 * <p>
	 * The method compares the due date of each Project object with the current date. If the due date is earlier than
	 * the current, the project is overdue.
	 * @see Project #getProjectDeadline()
	 * @param allProjectDetailsArray Project Array containing all the details of existing projects.
	 * @return returns the Project array with Projects that have a due date earlier than the current date.
	 */
	public static ArrayList<Project> createArrayListForProjectsOverdue(Project [] allProjectDetailsArray){
		// Create a new Project ArrayList - which will be used to store all the overdue Projects.
		ArrayList<Project> overdueProjectsArrayList = new ArrayList<Project>();
		// Create a new date - which is today's date.
		Date currentDate = new Date();
		
		// Loop through all the projects in the existing projects array.
		for (int i=0; i< allProjectDetailsArray.length;i++){
			// Obtain the the due date string for the specific project.
			String projectDueDate = allProjectDetailsArray[i].getProjectDeadline();
			String projectStatus = allProjectDetailsArray[i].getProjectStatus();
			SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
			// Try to convert the project deadline to the date format dd/MM/yy.
			try{
				Date projectDeadlineDate = formatter1.parse(projectDueDate);
				/* Compare the two dates by calling the comaperTo method. If it is 0, they are equal, if it is smaller than 0, the 
				 deadline is earlier than today's date and if it is larger than 1, the current date is earlier than the deadline.*/
				int dateComparison = projectDeadlineDate.compareTo(currentDate);
				
				/* If the compareTo function provides a result smaller than 0, the project is overdue and added to the ArrayList created above.
				The project's status must also be incomplete for it to register as overdue.*/
				if(dateComparison < 0 && projectStatus.equals("Incomplete")){
					overdueProjectsArrayList.add(allProjectDetailsArray[i]);
				}
			
			}
			// If the deadline cannot be formatted and error message is displayed.
			catch(Exception e){
				System.out.println("The date entered is not in the correct format (dd/mm/yyy),"
						+ "please edit the date and try again ");
			}
			
		}
		
		// Returns the Project array which contains all the projects that are overdue.
		return overdueProjectsArrayList;
	}

	/**
	 * This method enables the user to edit existing project details.
	 * <p>
	 * This method reads the existing projects and persons from the text files, and saves it in separate arrays.
	 * The existing projects are then displayed to the user and requests the user to select a project(by number or name)
	 * to edit. The relevant project field is then edited and written back to the project and/or persons file.
	 * The user can edit the project total fee, total paid to date, deadline, architect contact details, contractor
	 * contact details and the customer contact details.
	 * @see readAndWriteFiles #readInputAndSavePersonArrayString()
	 * @see readAndWriteFiles #convertStringToPersonArray(ArrayList)
	 * @see readAndWriteFiles #readInputAndSaveProjectArray()
	 * @see readAndWriteFiles #convertStringArrayListToProjectArray(ArrayList)
	 * @see UserInterfaceMethods #displayEditOptions()
	 * @see ProjectMethods #editTotalProjectFee(Scanner, Project[], int)
	 * @see ProjectMethods #editProjectTotalPaid(Scanner, Project[], int)
	 * @see ProjectMethods #editProjectDeadline(Scanner, Project[], int)
	 * @see ProjectMethods #findProjectByNameOrNumber(String, Project[])
	 * @see PersonMethods #editProjectArchitectContactDetails(Scanner, ArrayList, Project[], int)
	 * @see PersonMethods #editProjectContractorContactDetails(Scanner, ArrayList, Project[], int)
	 * @see PersonMethods #editProjectCustomerContactDetails(Scanner, ArrayList, Project[], int)
	 * @param userInput The string value for the project name or number that should be edited.
	 * @param userChoice Integer representing what project parameter should be edited.
	 * @return returns the integer for the user's next choice on the main menu
	 */
	public static int editExistingProjectDetails(Scanner userInput, int userChoice) {
		
		// Reads the person.txt file and saves each line as an array entry in a String ArrayList.
		ArrayList<String> allPersonsDetailsStringArrayList = readAndWriteFiles.readInputAndSavePersonArrayString();
		// Converts the String ArrayList to a Person ArrayList.
		ArrayList<Person> allPersonsDetailsPersonArrayList = readAndWriteFiles.convertStringToPersonArray(allPersonsDetailsStringArrayList);
		
		// Reads the person.txt file and saves each line as an array entry in a String ArrayList.
		ArrayList<String> allProjectsDetailsStringArrayList = readAndWriteFiles.readInputAndSaveProjectArray();
		// Converts the String ArrayList to a Project Array.
		Project[] allProjectsDetailsArray = readAndWriteFiles.convertStringArrayListToProjectArray(allProjectsDetailsStringArrayList);
		
		// Displays all the existing project names and numbers.
		System.out.println(" ");
		System.out.println("Existing projects are:");
		for (int m = 0; m < allProjectsDetailsArray.length;m++){
			System.out.println("");
			System.out.println(allProjectsDetailsArray[m].printProjectSummary());
			System.out.println("************************************");
		}
		
		// Requests the user to input a string for the project name/number to be edited.
		System.out.println("Enter the project name or number you want to edit: ");
		String userProjectToEdit = userInput.nextLine();
		
		// Call the findProjectByNameOrNumber method to obtain the index of the project to edit in the existing projects array.
		int indexToEdit = findProjectByNameOrNumber(userProjectToEdit, allProjectsDetailsArray);
		        		
		// While loop keeps executing until the user provides a project name/number that exists.
		while(indexToEdit == -1){
			System.out.println("Project does not exist, please enter a valid name.number:");
			userProjectToEdit = userInput.nextLine();
				
			indexToEdit = findProjectByNameOrNumber(userProjectToEdit, allProjectsDetailsArray);
			
		}
		
		
		// Displays a menu of project options that can be edited, and requests the user to select an option.
		System.out.println();
		System.out.println("Please select one of the following options to edit:");
		UserInterfaceMethods.displayEditOptions();
		
		System.out.println();
		System.out.println("Please enter your option here:");
		int optionToEdit = userInput.nextInt();
		userInput.nextLine();
		boolean isValidOption = false;
		
		/* This while loop will keep executing as long as the user enters an invalid option. When the user selects 
		 a valid option the boolean value is set to true and the while loop is exited.*/
		while(isValidOption == false){
		
			/* If the user selects option 1, the project fee is updated by calling the editTotalProjectFee method. 
			 The method returns to the main menu and request what option the user would like to execute next. This choice is set equal 
			 to userChoice.
			 The boolean variable isValidOption is also set to true so that the while loop is exited.*/
			if(optionToEdit == 1){
				isValidOption = true;
				userChoice = editTotalProjectFee(userInput, allProjectsDetailsArray, indexToEdit);				
			}	
			
			// If the user selects option 2, the project fee is updated by calling the editProjectTotalPaid method. UserChoice same as above.
			else if(optionToEdit == 2){
				isValidOption = true;
				userChoice = editProjectTotalPaid(userInput, allProjectsDetailsArray, indexToEdit);
			}
			
			// If the user selects option 3, the project fee is updated by calling the editProjectDeadline method. UserChoice same as above.
			else if(optionToEdit == 3){
				isValidOption = true;
				userChoice = editProjectDeadline(userInput, allProjectsDetailsArray, indexToEdit);
			}
			
			// If the user selects option 4, the contact details of the project architect is updated. UserChoice same as above.
			else if(optionToEdit == 4){
				isValidOption = true;
				userChoice = PersonMethods.editProjectArchitectContactDetails(userInput, allPersonsDetailsPersonArrayList,
						allProjectsDetailsArray, indexToEdit);
			}
			
			// If the user selects option 5, the contact details of the project contractor is updated. UserChoice same as above.
			else if(optionToEdit == 5){
				isValidOption = true;
				userChoice = PersonMethods.editProjectContractorContactDetails(userInput, allPersonsDetailsPersonArrayList,
						allProjectsDetailsArray, indexToEdit);
			
			}
			
			// If the user selects option 5, the contact details of the project customer is updated. UserChoice same as above.
			else if(optionToEdit == 6){
				isValidOption = true;
				userChoice = PersonMethods.editProjectCustomerContactDetails(userInput, allPersonsDetailsPersonArrayList,
						allProjectsDetailsArray, indexToEdit);
			}
			
			// If anything other than the options above is entered, it is an invalid selection and the user needs to enter another value.
			else{
				
				System.out.println("Invalid option, avaliable options are:");
				UserInterfaceMethods.displayEditOptions();
				
				System.out.println("");
				System.out.println("Please enter your option here: ");
				optionToEdit = userInput.nextInt();
				userInput.nextLine();
				
				isValidOption = false;
			}
			
	
		}
		// Return the integer of the option the user would want to execute next on the main menu.
		return userChoice;
	}

	/**
	 * This method finalizes a project, saves it to the finalized projects array and writes it to the CompletedProjects.txt file.
	 * <p>
	 * The method displays all the existing projects by name and number and requests the user to select a project to finalize.
	 * Determines the index array of the project by project name/number and then finalizes the project by calling the finalizeProject method.
	 * The project status is updated to Finalized, the updated project details are written to the projects.txt file, the outstanding account balance is determined, 
	 * an invoice is displayed if there is an outstanding amount and all the Finalized projects are written to the CompltedProjects.txt file.
	 * 
	 * @see ProjectMethods #findProjectByNameOrNumber(String, Project[])
	 * @see ProjectMethods #finalizeProject(int, Project[])
	 * @see ProjectMethods #amountOutstandingToBePaid(int, Project[])
	 * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
	 * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[]) 
	 * @see <a href="file:../CompletedProject.txt">../CompletedProject.txt</a>
	 * 
	 * @param userInput String value for the project name or number the user wants to finalize.
	 * @return returns the integer for the user's next choice on the main menu
	 */
	public static int finalizeProjectAndWriteToFile(Scanner userInput) {
		int userChoice;

		// Reads the project.txt file and saves each line as an array entry in an ArrayList.
		ArrayList<String> allProjectsDetailsStringArrayList = readAndWriteFiles.readInputAndSaveProjectArray();
		// Converts the String ArrayList to a Project Array - allProjectsDetailsArray.
		Project[] allProjectsDetailsArray = readAndWriteFiles.convertStringArrayListToProjectArray(allProjectsDetailsStringArrayList);
		
		// Displays all the existing projects and requests the user to enter the name/number of the project to finalize.
		System.out.println(" ");
		System.out.println("Existing projects are:");
		for (int m = 0; m < allProjectsDetailsArray.length;m++){
			System.out.println("");
			System.out.println(allProjectsDetailsArray[m].printProjectSummary());
			System.out.println("************************************");
		}
		
		System.out.println("Enter the project name or number you want to finalize: ");
		String userProjectToEdit = userInput.nextLine();
		
		// Determines the array index of the project to enter based on the name/number provided by the user.
		int indexToEdit = findProjectByNameOrNumber(userProjectToEdit, allProjectsDetailsArray);
		        		
		// While loop will keep executing until the user enters and existing project name/number.
		while(indexToEdit == -1){
			System.out.println("Project does not exist, please enter a valid name.number:");
			userProjectToEdit = userInput.nextLine();
				
			indexToEdit = findProjectByNameOrNumber(userProjectToEdit, allProjectsDetailsArray);	
		}
		
		// Define a new ProjectArray that has the updated Finalized project.
		Project [] updatedProjectsarray = finalizeProject(indexToEdit, allProjectsDetailsArray); 
	 	
		// Convert the Project Array to a String Array and write the String Array to the projects.txt file.
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(updatedProjectsarray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
		
		// Create a double variable and set it equal to the return value of the amountOutstandingToBePaid method, which calculated the amount still to be paid.
		double amountPayable = amountOutstandingToBePaid(indexToEdit, allProjectsDetailsArray);
		
		// If the full project fee is not paid, display an invoice to the user.
		if(amountPayable != 0){
			UserInterfaceMethods.generateInvoive(indexToEdit, allProjectsDetailsArray);
		}
		
		// Define a new Project Array which stores all the finalized projects by calling the createFinalizedProjectsArray method.s		
		Project [] completedProjects = createFinalizedProjectsArray(allProjectsDetailsArray);
		// Convert the completed projects array to a string array and write it to the CompletedProjects.txt file.
		String [] completedProjectsStringArray = readAndWriteFiles.convertProjectArrayToStringArray(completedProjects);
		readAndWriteFiles.writeUpdatedInfoToCompletedProjectTextFile(completedProjectsStringArray);
		
		
	 	
		System.out.println("Project successfully finalized, returning to main menu...");
		System.out.println("");
		
		
		// Returns to the main and options the next option form the user.
		userChoice = UserInterfaceMethods.runMainMenu();
		return userChoice;
	}

	/**
	 * This method enables the user to edit a project deadline.
	 * <p>
	 * The method displays the current project deadline, requests the user to enter the updated deadline.
	 * The Project object and Project Array is updated and written to the project.txt file.
	 * @see Project #getProjectDeadline()
	 * @see Project #setDueDate(String)
	 * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
	 * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[])
	 * @param userInput String value for the project name or number the user wants to edit.
	 * @param allProjectsDetailsArray Project Array containing all the details of existing projects..
	 * @param indexToEdit Project Array index for which the deadline should be updated.
	 * @return returns the integer for the user's next choice on the main menu 
	 */
	private static int editProjectDeadline(Scanner userInput, Project[] allProjectsDetailsArray, int indexToEdit) {
		int userChoice;
		// Obtains the project deadline for the specific project to be edited.
		System.out.println("");
		System.out.println("The current deadline is: ");
		System.out.println(allProjectsDetailsArray[indexToEdit].getProjectDeadline());
			
		// Request the user to input the updated project deadline.
		System.out.println("");
		System.out.println("What is the updated deadline(dd/mm/yyy): ");
		String newDueDate = userInput.nextLine();
		
		// Set the specific project deadline field to the new value entered by the user.
		allProjectsDetailsArray[indexToEdit].setDueDate(newDueDate);
		
		// Update the project array and write the updated array to the text file.
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
		
		System.out.println("Deadline successfully updated, returning to main menu...");
		System.out.println("");
		
		
		// Return to the main menu and request the option to be executed.
		userChoice = UserInterfaceMethods.runMainMenu();
		return userChoice;
	}

	/**
	 * This method enables the user to edit a project's total paid to date.
	 * <p>
	 * The method displays the current project total paid, requests the user to enter the updated total paid.
	 * The Project object and Project Array is updated and written to the project.txt file.
	 * @param userInput String value for the project name or number the user wants to edit.
	 * @param allProjectsDetailsArray Project Array containing all the details of existing projects..
	 * @param indexToEdit Project Array index for which the deadline should be updated.
	 * @return returns the integer for the user's next choice on the main menu
	 * @return returns the integer for the user's next choice on the main menu 
	 */
	private static int editProjectTotalPaid(Scanner userInput, Project[] allProjectsDetailsArray, int indexToEdit) {
		// Refer to the editProjectDeadline as the method logic is the same.
		int userChoice;
		System.out.println("");
		System.out.println("The total paid to date is:");
		System.out.println(allProjectsDetailsArray[indexToEdit].getProjectTotalPaid());
			
		System.out.println("");
		System.out.println("What is the updated amount paid to date:");
		String newTotalPaid = userInput.nextLine();
		allProjectsDetailsArray[indexToEdit].setTotalPaid(newTotalPaid);
			
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
		
		System.out.println("Fee paid successfully updated, returning to main menu...");
		System.out.println("");
		
		userChoice = UserInterfaceMethods.runMainMenu();
		return userChoice;
	}
	/**
	 * This method enables the user to edit a project fee.
	 * <p>
	 * The method displays the current project total fee, requests the user to enter the updated fee.
	 * The Project object and Project Array is updated and written to the project.txt file.
	 * @param userInput String value for the project name or number the user wants to edit.
	 * @param allProjectsDetailsArray Project Array containing all the details of existing projects..
	 * @param indexToEdit Project Array index for which the deadline should be updated.
	 * @return returns the integer for the user's next choice on the main menu
	 * @return returns the integer for the user's next choice on the main menu 
	 */
	private static int editTotalProjectFee(Scanner userInput, Project[] allProjectsDetailsArray, int indexToEdit) {
		// Refer to the editProjectDeadline as the method logic is the same.
		int userChoice;
		
		System.out.println();
		System.out.println("The fee charged is:");
		System.out.println(allProjectsDetailsArray[indexToEdit].getProjectFee());
			
		System.out.println();
		System.out.println("What is the updated fee:");
		String newFeeCharged = userInput.nextLine();
		allProjectsDetailsArray[indexToEdit].setTotalFee(newFeeCharged);
			
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
		
		System.out.println("Total fee charged successfully updated, returning to main menu...");
		System.out.println("");
		
		userChoice = UserInterfaceMethods.runMainMenu();
		return userChoice;
	}

	/**
	 * This method creates a new project, adds it to the existing projects array and writes it to the project.txt file.
	 * <p>
	 * This method calls the createNewProject method to create a new Project object . Then it calls the addProjectToArrayAndWriteToFile
	 * method to add the new project to the project array and write it to the projects.txt file.
	 * @see ProjectMethods #createNewProject(ArrayList, Project[])
	 * @see ProjectMethods #addProjectToArrayAndWriteToFile(Project[], Project)
	 * @see <a href="file:../projects.txt">../projects.txt</a>
	 * 
	 * @return returns the integer for the user's next choice on the main menu
	 */
	public static int createNewProjectAndWriteToFile() {
		// Create the return integer variable.
		int userChoice;
		
		// Please refer to editExistingProjectDetails to explain the 4 lines of code below.
		ArrayList<String> allPersonsDetailsStringArrayList = readAndWriteFiles.readInputAndSavePersonArrayString();
		ArrayList<Person> allPersonsDetailsPersonArrayList = readAndWriteFiles.convertStringToPersonArray(allPersonsDetailsStringArrayList);
		
		ArrayList<String> allProjectsDetailsStringArrayList = readAndWriteFiles.readInputAndSaveProjectArray();
		Project [] allProjectsDetailsArray = readAndWriteFiles.convertStringArrayListToProjectArray(allProjectsDetailsStringArrayList);
		
		//Creates an new Project object by calling createNewProject, which returns the new Project.
		Project newProject = createNewProject(allPersonsDetailsPersonArrayList, allProjectsDetailsArray);
		
		// Add the project to the projects array and writes the updated project array to the text file.
		addProjectToArrayAndWriteToFile(allProjectsDetailsArray, newProject);
		
		System.out.println("");
		System.out.println("The project has been succesfully created.");
		System.out.println("");
		
		// Returns to the main and options the next option form the user.
		userChoice = UserInterfaceMethods.runMainMenu();
		return userChoice;
	}
	
	
}
