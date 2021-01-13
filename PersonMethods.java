import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class groups all methods used to create and modify Person objects.
 * 
 * @author Nadia Botha
 * @version 1.0
 */
public class PersonMethods {
	
	
	/**
	 * This method lets the user create a new project architect.
	 * <p>
	 * This method requests the user to fill in the fields (name, number, email address and physical address) to define the architect Person object. 
	 * This input is used to create and return the Person object.
	 * 
	 * @see Person #Person(String, String, String, String, String)
	 * @return returns the Person object with all relevant fields, name, telephone number, email address and physical address
	 */
    public static Person createNewArchitect(){
    	// Scanner object defined to get input from the user for fields of a Person object.
        Scanner userInput = new Scanner(System.in);

        // String input values from the user to define the Person object fields.
        System.out.println("Please enter the details for the project architect: ");
        System.out.println("The architect name and surname (Format:Name Surname): ");
        String architectName = userInput.nextLine();
        System.out.println("The architect telephone number: ");
        String architectNumber = userInput.nextLine();
        System.out.println("The architect email address: ");
        String arhitectEmailAddress = userInput.nextLine();
        System.out.println("The architect physical address (please do not use commas): ");
        String architectPhysicalAddress = userInput.nextLine();

        /* Calls Person constructor to create a new Person object. 
         * The method specifically creates an architect Person, personType is set to a static value - Architect, does not change.*/
        Person newArchitect = new Person(architectName, architectNumber,arhitectEmailAddress, architectPhysicalAddress, "Architect");
        
        // returns the Person object newArchitect.
        return newArchitect;
    }

	/**
	 * This method lets the user create a new project contractor.
	 * <p>
	 * This method requests the user to fill in the fields (name, number, email address and physical address) to define the contractor Person object. 
	 * This input is used to create and return the Person object.
	 * 
	 * @see Person #Person(String, String, String, String, String)
	 * @return returns the Person object with all relevant fields, name, telephone number, email address and physical address
	 */
    public static Person createNewContractor(){
    	// Scanner object defined to get input from the user for fields of a Person object
        Scanner userInputs = new Scanner(System.in);
        
        // String input values from the user to define the Person object fields.
        System.out.println("Please enter the details for the project contractor: ");
        System.out.println("The contractor name and surname (Format:Name Surname): ");
        String contractorName = userInputs.nextLine();
        System.out.println("The contractor telephone number: ");
        String contractorNumber = userInputs.nextLine();
        System.out.println("The contractor email address: ");
        String contractorEmailAddress = userInputs.nextLine();
        System.out.println("The contractor physical address (please do not use commas): ");
        String contractorPhysicalAddress = userInputs.nextLine();

        /* Calls Person constructor to create a new Person object. 
         * The method specifically creates a contractor Person, personType is set to a static value - Contractor, does not change.*/
        Person newContractor = new Person(contractorName, contractorNumber, contractorEmailAddress, contractorPhysicalAddress, "Contractor");

        // returns the Person object newContractor.
        return newContractor;
    }

    /**
	 * This method lets the user create a new project customer.
	 * <p>
	 * This method requests the user to fill in the fields (name, number, email address and physical address) to define the customer Person object. 
	 * This input is used to create and return the Person object.
	 * 
	 * @see Person #Person(String, String, String, String, String)
	 * @return returns the Person object with all relevant fields, name, telephone number, email address and physical address
	 */
    public static Person createNewCustomer(){
    	// Scanner object defined to get input from the user for fields of a Person object
        Scanner userInput = new Scanner(System.in);

     // String input values from the user to define the Person object fields.
        System.out.println("Please enter the details for the project contractor: ");
        System.out.println("The customer name and surname (Format:Name Surname): ");
        String customerName = userInput.nextLine();
        System.out.println("The customer telephone number: ");
        String customerNumber = userInput.nextLine();
        System.out.println("The customer email address: ");
        String customerEmailAddress = userInput.nextLine();
        System.out.println("The customer physical address (please do not use commas): ");
        String customerPhysicalAddress = userInput.nextLine();

        /* Calls Person constructor to create a new Person object. 
         * The method specifically creates a customer Person, personType is set to a static value - Customer, does not change.*/
        Person newCustomer = new Person(customerName, customerNumber, customerEmailAddress, customerPhysicalAddress, "Customer");
        
        // return the Person object newCustomer.
        return newCustomer;
    }

    /**
     * This method adds a newly created Person object(architect, contractor and customer) to the existing Persons.
     * <p>
     * The newly created Person object is added to the existing Person ArrayList by means of the add method.
     * The person is added in the first empty array index. 
     * 
     * @param allPersonsDetails Person ArrayList containing all the existing Person objects.
     * @param newPerson new Person object that was created, which has not yet been added to the Person ArrayList.
     * @return returns the updated ArrayList which also includes the newly created Person object.
     */
    private static ArrayList<Person> addNewPersonToArray(ArrayList<Person> allPersonsDetails, Person newPerson){
    	
    	// Adds the newPerson object to the allPersonsDetails ArrayList using the add method for ArrayLists.
        allPersonsDetails.add(newPerson);
        
        // returns the updated Person ArrayList which now has the newPerson added to it.
        return allPersonsDetails;
    }

    /**
     * This method looks for a specific Person by means of the person name.
     * <p>
     * This method loops through the Person ArrayList and looks for a specific Person object based on the personName field.
     * The method then returns the index of this Person object within the Person ArrayList.
     * 
     * @see Person #getPersonName()
     * @param allPersonsDetails Person ArrayList containing all the existing Person objects.
     * @param personName Person object name and surname string which forms the personName field.
     * @return returns the index integer of a specific Person object within the Person ArrayList.
     */
    public static int personIndexInPersonArray(ArrayList<Person> allPersonsDetails, String personName){
    	/* Create a new String array, used to store only the person name string (name and surname) for all the Person objects.
    	 * The array has the same size as the arrayList as each Person object has a name.*/
        String [] personNameArray = new String[allPersonsDetails.size()];
        
        // Define an integer personIndex and initialize the value to -1.
        int personIndex = -1;
        
        /* Loop through the Person ArrayList allPersonsDetails, then isolate the Person object by .get(i). 
         * Then isolate the personName string value by calling the getPersonName method. 
         * Save the person name in a string variable personNameArrayEntry and add it to the String Array created above.*/
        for(int i=0; i < allPersonsDetails.size();i++){
            String personNameArrayEntry = allPersonsDetails.get(i).getPersonName();
            personNameArray[i] = personNameArrayEntry;
        }
        
        /* Compares the name entered with each index value of the String array. If it is equal the personIndex integer value is changed
         * from -1 to the index value at which the name was found. If the resulting value is -1, it means the name does not exist in the database.*/
        for (int j = 0; j < personNameArray.length;j++){

            if(personNameArray[j].equals(personName)){
                personIndex = j;
            }
        }
        
        // returns the index, where the name was found, otherwise returns -1.
        return personIndex;

    }
    
    /**
     * Method adds a newly created Person object to the exiting persons and updates the text file accordingly where all the persons are saved.
     * <p>
     * Calls the addNewPersonToArray method, to add the Person object to the Person ArrayList. Then converts the Person ArrayList to a String
     * array by calling the convertPersonArrayToString method. After that the string method is written to the person text file by calling the 
     * writeUpdatedInfoToPersonTextFile, which permanently saves the Person object to the database.
     * 
     * @see PersonMethods #addNewPersonToArray(ArrayList, Person)
     * @see readAndWriteFiles #convertPersonArrayToString(ArrayList)
     * @see readAndWriteFiles #writeUpdatedInfoToPersonTextFile(String[])
     * @see <a href="file:../person.txt">../person.txt</a>
     * 
     * @param allPersonsDetails Person ArrayList containing all the existing Person objects.
     * @param projectPerson new Person object that was created, which has not yet been added to the Person ArrayList or written to person.txt.
     */
    public static void addPersonToArrayAndWriteToFile(ArrayList<Person> allPersonsDetails, Person projectPerson) {
    	// Calls the addNewPersonToArray method to add the newly created Person object to the Person ArrayList.
        addNewPersonToArray(allPersonsDetails, projectPerson);
        
        /* Define a new String Array, and set it equal to the return value of the convertPersonArrayToString method located in readAndWriteFiles class, which 
         * returns a String array. It converts the PersonArrayList to a String array so that it can be written back to the person.txt file*/
        String [] updatedPersonStringArray = readAndWriteFiles.convertPersonArrayToString(allPersonsDetails);
        /*Calls the writeUpdatedInfoToPersonTextFile method in the readAndWriteFiles class, to write the updated array to the person.txt file. 
         * The text file will now include the newly created Person object */
        readAndWriteFiles.writeUpdatedInfoToPersonTextFile(updatedPersonStringArray);
    }
    
    /**
     * This method enables the user to edit an existing customer's contact details for a specific project.
     * <p>
     * The method displays which details can be edited, request the user to select and option and then executes the 
     * option by calling the relevant method. If the user desires to update the customer number, editProjectCustomerNumber is called.
     * If the user selects the customer email address, the editProjectCustomerEmailAddress is called. If the user selects the
     * customer physical address, the editProjectCustomerPhysicalAddress method is called. The code to update the person.txt and project.txt files
     * are located within editProjectCustomerPhysicalAddress, editProjectCustomerNumber and editProjectCustomerEmailAddress.
     * After editing the contact details, the user is requested to enter an option for the main menu by calling runMainMenu method.
     * 
     * 
     * @see PersonMethods #personIndexInPersonArray(ArrayList, String)
     * @see UserInterfaceMethods #runMainMenu() 
     * @see PersonMethods #editProjectCustomerNumber(Scanner, ArrayList, Project[], int, int)
     * @see PersonMethods #editProjectCustomerEmailAddress(Scanner, ArrayList, Project[], int, int)
     * @see PersonMethods #editProjectCustomerPhysicalAddress(Scanner, ArrayList, Project[], int, int)
     * 
     * @param userInput Integer entered by the user which determines if it is the customer number, email address or physical address that should be updated.
     * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
     * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
     * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
     * @return returns the integer for the next option chosen by the user on the main menu.
     */
	 public static int editProjectCustomerContactDetails(Scanner userInput,
			ArrayList<Person> allPersonsDetailsPersonArrayList, Project[] allProjectsDetailsArray, int indexToEdit) {
		int userChoice;
		/* Define a new Person object and initialize it by setting it equal to the project to edit's customer.
		 * The project to edit is obtained to getting the indexToEdit array entry of the Project Array allProjectsDetailsArray.
		 * The customer is then obtained by calling the getProjectCustomer method.*/
		Person customerDetailsToEditName = allProjectsDetailsArray[indexToEdit].getProjectCustomer();
		// The customer name is then obtained by calling the getPersonName for the specific Person object.
		String nameToLookFor = customerDetailsToEditName.getPersonName();
		
		/*The name obtained in the previous step and the existing Persons ArrayList is then used as parameters for the personIndexInPersonArray method
		 * which provides the index for the customer to edit within the Person ArrayList*/
		int personIndexToEdit = personIndexInPersonArray(allPersonsDetailsPersonArrayList, nameToLookFor);
		
		// Displays options that the user can edit and requests the user to make a selection.
		System.out.println("Please select the option you want to edit:");
		System.out.println("1. Customer's number");
		System.out.println("2. Customer's email address");
		System.out.println("3. Customer's physical address");
			
		int userChoiceToEdit = userInput.nextInt();
		userInput.nextLine();
		
		/*If the user chooses option 1, the customer's number will be edited. The editProjectCustomerNumber method is called located in the PersonMethods class, 
		 * which updates the telephoneNumber field value according to the new string the user provides. The text files are also updated.*/
		if(userChoiceToEdit == 1){
			PersonMethods.editProjectCustomerNumber(userInput, allPersonsDetailsPersonArrayList, allProjectsDetailsArray,
					indexToEdit, personIndexToEdit);
		} 
		/*If the user chooses option 2, the customer's email will be edited. The editProjectCustomerEmailAddress method is called located in the PersonMethods class, 
		 * which updates the emailAddress field value according to the new string the user provides. The text files are also updated.*/
		else if(userChoiceToEdit == 2){
			PersonMethods.editProjectCustomerEmailAddress(userInput, allPersonsDetailsPersonArrayList,
					allProjectsDetailsArray, indexToEdit, personIndexToEdit);
		}
		
		/*If the user chooses option 3, the customer's physical will be edited. The editProjectCustomerPhysicalAddress method is called located in the PersonMethods class, 
		 * which updates the physicalAddress field value according to the new string the user provides. The text files are also updated.*/
		else if(userChoiceToEdit == 3){
			PersonMethods.editProjectCustomerPhysicalAddress(userInput, allPersonsDetailsPersonArrayList,
					allProjectsDetailsArray, indexToEdit, personIndexToEdit);
		}
		// If the user inputs anything other than 1,2 or 3, the input is invalid and the program returns to the main menu.
		else{
			System.out.println("Invalid option, returning to the main menu...");
			System.out.println("");
		}
		
		// Message displayed to the user after contact details have been updated.
		System.out.println("Customer contact details successfully updated, returning to main menu...");
		System.out.println("");
		
		// Calls the runMainMenu method located in UserInterfaceMethods class to return to the main menu.
		userChoice = UserInterfaceMethods.runMainMenu();
		
		// returns the integer for the new option chosen.
		return userChoice;
	}
	 
	 /**
	  * This method enables the user to edit an existing customer's physical address for a specific project.
	  * <p>
	  * The method looks for the specific project within the existing Project array and obtains the index. The project details for
	  * this index is then obtained, of which the customer Person object is one. The customer Person object is then accessed to get
	  * the existing physical address which is displayed. A new value is requested from the user, and the customer Person object 
	  * physicalAddress field is set equal to this new value. The person.txt and projects.txt is updated accordingly.
	  * 
	  * @see Project #getProjectCustomer()
	  * @see Person #getPhysicalAddress()
	  * @see Person #setPersonPhysicalAddress(String)
	  * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[])
	  * @see readAndWriteFiles #convertPersonArrayToString(ArrayList)
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[][])
	  * 
	  * @param userInput String input from the user to define the new physical address.
	  * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
	  * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
	  * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
	  * @param personIndexToEdit Index integer of the person to be edited within the Person ArrayList.
	  */
	 private static void editProjectCustomerPhysicalAddress(Scanner userInput,
			ArrayList<Person> allPersonsDetailsPersonArrayList, Project[] allProjectsDetailsArray, int indexToEdit,
			int personIndexToEdit) {
		System.out.println("Current physical address is:");
		/* Defines a new Person object (currentCustomerDetails) and initialize it to the project to edit's customer field(projectCustomer) by
		 * calling the getProjectCustomer method.*/
		Person currentCustomerDetails = allProjectsDetailsArray[indexToEdit].getProjectCustomer();
		/*Define a new String variable and set it equal to the project customer's physical address by calling the getPhysicalAddress method.*/
		String currentCustomerPhysicalAddress = currentCustomerDetails.getPhysicalAddress();
		// Display the string above as it is the current physical address defined for the project customer.
		System.out.println(currentCustomerPhysicalAddress);
				
		System.out.println("Enter the new physical address: ");
		// Request the user tp input the new physical address for the customer.
		String newCustomerPhysicalAddress = userInput.nextLine();
		// Update the Person object by calling the setPersonPhysicalAddress method.
		currentCustomerDetails.setPersonPhysicalAddress(newCustomerPhysicalAddress);
		// Update the project customer within the project array.
		allProjectsDetailsArray[indexToEdit].setProjectCustomer(currentCustomerDetails);
		// Update the customer within the Person arrayList. 
		allPersonsDetailsPersonArrayList.get(personIndexToEdit).setPersonPhysicalAddress(newCustomerPhysicalAddress);
		
		// Convert the updated Project array to a string array and write it to the projects.txt file.
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
	  	
		// Convert the updated Person ArrayList to a string array and write it to the person.txt file.
		String [] updatedPersonStringArray = readAndWriteFiles.convertPersonArrayToString(allPersonsDetailsPersonArrayList);
		readAndWriteFiles.writeUpdatedInfoToPersonTextFile(updatedPersonStringArray);
	}
	 
	 /**
	  * This method enables the user to edit an existing customer's email address for a specific project.
	  * <p>
	  * The method looks for the specific project within the existing Project array and obtains the index. The project details for
	  * this index is then obtained, of which the customer Person object is one. The customer Person object is then accessed to get
	  * the existing email address which is displayed. A new value is requested from the user, and the customer Person object 
	  * physicalAddress field is set equal to this new value. The person.txt and projects.txt is updated accordingly.
	  * 
	  * @see Project #getProjectCustomer()
	  * @see Person #getEmailAddress()
	  * @see Person #setPersonEmailAddress(String)
	  * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[])
	  * @see readAndWriteFiles #convertPersonArrayToString(ArrayList)
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[][])
	  * 
	  * @param userInput String input from the user to define the new email address.
	  * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
	  * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
	  * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
	  * @param personIndexToEdit Index integer of the person to be edited within the Person ArrayList.
	  */
	private static void editProjectCustomerEmailAddress(Scanner userInput, ArrayList<Person> allPersonsDetailsPersonArrayList,
			Project[] allProjectsDetailsArray, int indexToEdit, int personIndexToEdit) {
		// Refer to comments for editProjectCustomerPhysicalAddress as the methods are very similar.
		System.out.println("Current email address is:");
		Person currentCustomerDetails = allProjectsDetailsArray[indexToEdit].getProjectCustomer();
		String currentCustomerEmailAddress = currentCustomerDetails.getEmailAddress();
		System.out.println(currentCustomerEmailAddress);
				
		System.out.println("Enter the new email address: ");
		String newCustomerEmailAddress = userInput.nextLine();
		currentCustomerDetails.setPersonEmailAddress(newCustomerEmailAddress);
		allProjectsDetailsArray[indexToEdit].setProjectCustomer(currentCustomerDetails);
		allPersonsDetailsPersonArrayList.get(personIndexToEdit).setPersonEmailAddress(newCustomerEmailAddress);
				
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
	  			
		String [] updatedPersonStringArray = readAndWriteFiles.convertPersonArrayToString(allPersonsDetailsPersonArrayList);
		readAndWriteFiles.writeUpdatedInfoToPersonTextFile(updatedPersonStringArray);
	}
	
	 /**
	  * This method enables the user to edit an existing customer's telephone number for a specific project.
	  * <p>
	  * The method looks for the specific project within the existing Project array and obtains the index. The project details for
	  * this index is then obtained, of which the customer Person object is one. The customer Person object is then accessed to get
	  * the existing telephone number which is displayed. A new value is requested from the user, and the customer Person object 
	  * telephoneNumber field is set equal to this new value. The person.txt and projects.txt is updated accordingly.
	  * 
	  * @see Project #getProjectCustomer()
	  * @see Person #getTelephoneNumber()
	  * @see Person #setPersonNumber(String)
	  * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[])
	  * @see readAndWriteFiles #convertPersonArrayToString(ArrayList)
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[][])
	  * 
	  * @param userInput String input from the user to define the new telephone number.
	  * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
	  * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
	  * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
	  * @param personIndexToEdit Index integer of the person to be edited within the Person ArrayList.
	  */
	private static void editProjectCustomerNumber(Scanner userInput, ArrayList<Person> allPersonsDetailsPersonArrayList,
			Project[] allProjectsDetailsArray, int indexToEdit, int personIndexToEdit) {
		// Refer to comments for editProjectCustomerPhysicalAddress as the methods are very similar.
		System.out.println("Current number is:");
		Person currentCustomerDetails = allProjectsDetailsArray[indexToEdit].getProjectCustomer();
		String currentCustomerNumber = currentCustomerDetails.getTelephoneNumber();
		System.out.println(currentCustomerNumber);
		
		System.out.println("Enter the new number: ");
		String newCustomerNumber = userInput.nextLine();
		currentCustomerDetails.setPersonNumber(newCustomerNumber);
		allProjectsDetailsArray[indexToEdit].setProjectCustomer(currentCustomerDetails);
		allPersonsDetailsPersonArrayList.get(personIndexToEdit).setPersonNumber(newCustomerNumber);
				
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
	  			
		String [] updatedPersonStringArray = readAndWriteFiles.convertPersonArrayToString(allPersonsDetailsPersonArrayList);
		readAndWriteFiles.writeUpdatedInfoToPersonTextFile(updatedPersonStringArray);
	}
    /**
     * This method enables the user to edit an existing contractor's contact details.
     * <p>
     * The method displays which details can be edited, request the user to select and option and then executes the 
     * option by calling the relevant method. If the user desires to update the customer number, editProjectContractorNumber is called.
     * If the user selects the customer email address, the editProjectContractorEmailAddress is called. If the user selects the
     * customer physical address, the editProjectContractorPhysicalAddress method is called. The code to update the person.txt and project.txt files
     * are located within editProjectContractorPhysicalAddress, editProjectContractorNumber and editProjectContractorEmailAddress.
     * After editing the contact details, the user is requested to enter an option for the main menu by calling runMainMenu method.
     * 
     * @see PersonMethods #personIndexInPersonArray(ArrayList, String)
     * @see UserInterfaceMethods #runMainMenu() 
     * @see PersonMethods #editProjectContractorNumber(Scanner, ArrayList, Project[], int, int)
     * @see PersonMethods #editProjectContractorEmailAddress(Scanner, ArrayList, Project[], int, int)
     * @see PersonMethods #editProjectContractorEmailAddress(Scanner, ArrayList, Project[], int, int)
     * 
     * @param userInput Integer entered by the user which determines if it is the contractor number, email address or physical address that should be updated.
     * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
     * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
     * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
     * @return returns the integer for the next option chosen by the user on the main menu.
     */
	public static int editProjectContractorContactDetails(Scanner userInput,
			ArrayList<Person> allPersonsDetailsPersonArrayList, Project[] allProjectsDetailsArray, int indexToEdit) {
		// Refer to comments in editProjectCustomerContactDetails as the methods are very similar.
		int userChoice;
		Person contractorDetailsToEditName = allProjectsDetailsArray[indexToEdit].getProjectContractor();
		String nameToLookFor = contractorDetailsToEditName.getPersonName();
			
		int personIndexToEdit = personIndexInPersonArray(allPersonsDetailsPersonArrayList, nameToLookFor);
			
		System.out.println("Please select the option you want to edit:");
		System.out.println("1. Contractor's number");
		System.out.println("2. Contractor's email address");
		System.out.println("3. Contractor's physical address");
			
		int userChoiceToEdit = userInput.nextInt();
		userInput.nextLine();
			
		if(userChoiceToEdit == 1){
			PersonMethods.editProjectContractorNumber(userInput, allPersonsDetailsPersonArrayList,
					allProjectsDetailsArray, indexToEdit, personIndexToEdit);
		
		} 
			
		else if(userChoiceToEdit == 2){
			PersonMethods.editProjectContractorEmailAddress(userInput, allPersonsDetailsPersonArrayList,
					allProjectsDetailsArray, indexToEdit, personIndexToEdit);
		
		}
			
		else if(userChoiceToEdit == 3){
			PersonMethods.editProjectContractorPhysicalAddress(userInput, allPersonsDetailsPersonArrayList,
					allProjectsDetailsArray, indexToEdit, personIndexToEdit);
		}
		else{
			System.out.println("Invalid option, returning to the main menu...");
			System.out.println("");
		}
		System.out.println("Contractor contact details successfully updated, returning to main menu...");
		System.out.println("");
		
		userChoice = UserInterfaceMethods.runMainMenu();
		return userChoice;
	}
	 /**
	  * This method enables the user to edit an existing contractor's physical address for a specific project.
	  * <p>
	  * The method looks for the specific project within the existing Project array and obtains the index. The project details for
	  * this index is then obtained, of which the contractor Person object is one. The contractor Person object is then accessed to get
	  * the existing physical address which is displayed. A new value is requested from the user, and the contractor Person object 
	  * physicalAddress field is set equal to this new value. The person.txt and projects.txt is updated accordingly.
	  * 
	  * @see Project #getProjectContractor()
	  * @see Person #getPhysicalAddress()
	  * @see Person #setPersonPhysicalAddress(String)
	  * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[])
	  * @see readAndWriteFiles #convertPersonArrayToString(ArrayList)
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[][])
	  * 
	  * @param userInput String input from the user to define the new physical address.
	  * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
	  * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
	  * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
	  * @param personIndexToEdit Index integer of the person to be edited within the Person ArrayList.
	  */
	private static void editProjectContractorPhysicalAddress(Scanner userInput,
			ArrayList<Person> allPersonsDetailsPersonArrayList, Project[] allProjectsDetailsArray, int indexToEdit,
			int personIndexToEdit) {
		// Refer to comments for editProjectCustomerPhysicalAddress as the methods are very similar.
		System.out.println("Current physical address is:");
		Person currentContractorDetails = allProjectsDetailsArray[indexToEdit].getProjectContractor();
		String currentContractorPhysicalAddress = currentContractorDetails.getPhysicalAddress();
		System.out.println(currentContractorPhysicalAddress);
				
		System.out.println("Enter the new physical address: ");
		String newContractorPhysicalAddress = userInput.nextLine();
		currentContractorDetails.setPersonPhysicalAddress(newContractorPhysicalAddress);
		allProjectsDetailsArray[indexToEdit].setProjectContractor(currentContractorDetails);
		allPersonsDetailsPersonArrayList.get(personIndexToEdit).setPersonPhysicalAddress(newContractorPhysicalAddress);
				
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
	  			
		String [] updatedPersonStringArray = readAndWriteFiles.convertPersonArrayToString(allPersonsDetailsPersonArrayList);
		readAndWriteFiles.writeUpdatedInfoToPersonTextFile(updatedPersonStringArray);
	}
	 /**
	  * This method enables the user to edit an existing contractor's email address for a specific project.
	  * <p>
	  * The method looks for the specific project within the existing Project array and obtains the index. The project details for
	  * this index is then obtained, of which the contractor Person object is one. The contractor Person object is then accessed to get
	  * the existing email address which is displayed. A new value is requested from the user, and the contractor Person object 
	  * physicalAddress field is set equal to this new value. The person.txt and projects.txt is updated accordingly.
	  * 
	  * @see Project #getProjectContractor()
	  * @see Person #getEmailAddress()
	  * @see Person #setPersonEmailAddress(String)
	  * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[])
	  * @see readAndWriteFiles #convertPersonArrayToString(ArrayList)
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[][])
	  * 
	  * @param userInput String input from the user to define the new email address.
	  * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
	  * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
	  * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
	  * @param personIndexToEdit Index integer of the person to be edited within the Person ArrayList.
	  */
	private static void editProjectContractorEmailAddress(Scanner userInput,
			ArrayList<Person> allPersonsDetailsPersonArrayList, Project[] allProjectsDetailsArray, int indexToEdit,
			int personIndexToEdit) {
		// Refer to comments for editProjectCustomerPhysicalAddress as the methods are very similar.
		System.out.println("Current email address is:");
		Person currentContractorDetails = allProjectsDetailsArray[indexToEdit].getProjectContractor();
		String currentContractorEmailAddress = currentContractorDetails.getEmailAddress();
		System.out.println(currentContractorEmailAddress);
				
		System.out.println("Enter the new email address: ");
		String newContractorEmailAddress = userInput.nextLine();
		currentContractorDetails.setPersonEmailAddress(newContractorEmailAddress);
		allProjectsDetailsArray[indexToEdit].setProjectContractor(currentContractorDetails);
		allPersonsDetailsPersonArrayList.get(personIndexToEdit).setPersonEmailAddress(newContractorEmailAddress);
				
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
	  			
		String [] updatedPersonStringArray = readAndWriteFiles.convertPersonArrayToString(allPersonsDetailsPersonArrayList);
		readAndWriteFiles.writeUpdatedInfoToPersonTextFile(updatedPersonStringArray);
	}
	 /**
	  * This method enables the user to edit an existing contractor's telephone number for a specific project.
	  * <p>
	  * The method looks for the specific project within the existing Project array and obtains the index. The project details for
	  * this index is then obtained, of which the contractor Person object is one. The contractor Person object is then accessed to get
	  * the existing telephone number which is displayed. A new value is requested from the user, and the contractor Person object 
	  * telephoneNumber field is set equal to this new value. The person.txt and projects.txt is updated accordingly.
	  * 
	  * @see Project #getProjectCustomer()
	  * @see Person #getTelephoneNumber()
	  * @see Person #setPersonNumber(String)
	  * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[])
	  * @see readAndWriteFiles #convertPersonArrayToString(ArrayList)
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[][])
	  * 
	  * @param userInput String input from the user to define the new telephone number.
	  * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
	  * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
	  * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
	  * @param personIndexToEdit Index integer of the person to be edited within the Person ArrayList.
	  */
	private static void editProjectContractorNumber(Scanner userInput,
			ArrayList<Person> allPersonsDetailsPersonArrayList, Project[] allProjectsDetailsArray, int indexToEdit,
			int personIndexToEdit) {
		// Refer to comments for editProjectCustomerPhysicalAddress as the methods are very similar.
		System.out.println("Current number is:");
		Person currentContractorDetails = allProjectsDetailsArray[indexToEdit].getProjectContractor();
		String currentContractorNumber = currentContractorDetails.getTelephoneNumber();
		System.out.println(currentContractorNumber);
				
		System.out.println("Enter the new number: ");
		String newContractorNumber = userInput.nextLine();
		currentContractorDetails.setPersonNumber(newContractorNumber);
		allProjectsDetailsArray[indexToEdit].setProjectContractor(currentContractorDetails);
		allPersonsDetailsPersonArrayList.get(personIndexToEdit).setPersonNumber(newContractorNumber);
				
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
	  			
		String [] updatedPersonStringArray = readAndWriteFiles.convertPersonArrayToString(allPersonsDetailsPersonArrayList);
		readAndWriteFiles.writeUpdatedInfoToPersonTextFile(updatedPersonStringArray);
	}
	
    /**
     * This method enables the user to edit an existing architect's contact details.
     * <p>
     * The method displays which details can be edited, request the user to select and option and then executes the 
     * option by calling the relevant method. If the user desires to update the customer number, editProjectArchitectNumber is called.
     * If the user selects the customer email address, the editProjectArchitectEmailAddress is called. If the user selects the
     * customer physical address, the editProjectArchitectPhysicalAddress method is called. After editing the contact details, 
     * the user is requested to enter an option for the main menu by calling runMainMenu method. 
     * 
     * @see PersonMethods #personIndexInPersonArray(ArrayList, String)
     * @see UserInterfaceMethods #runMainMenu() 
     * 
     * @param userInput Integer entered by the user which determines if it is the architect number, email address or physical address that should be updated.
     * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
     * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
     * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
     * @return returns the integer for the next option chosen by the user on the main menu.
     */
	public static int editProjectArchitectContactDetails(Scanner userInput,
			ArrayList<Person> allPersonsDetailsPersonArrayList, Project[] allProjectsDetailsArray, int indexToEdit) {
		// Refer to comments in editProjectCustomerContactDetails as the methods are very similar.
		int userChoice;
		Person architectDetailsToEditName = allProjectsDetailsArray[indexToEdit].getProjectArchtect();
		String nameToLookFor = architectDetailsToEditName.getPersonName();
			
		int personIndexToEdit = personIndexInPersonArray(allPersonsDetailsPersonArrayList, nameToLookFor);
			
			
		System.out.println("Please select the option you want to edit:");
		System.out.println("1. Architect's number");
		System.out.println("2. Architect's email address");
		System.out.println("3. Architect's physical address");
			
		int userChoiceToEdit = userInput.nextInt();
		userInput.nextLine();
			
		if(userChoiceToEdit == 1){
			PersonMethods.editProjectArchitectNumber(userInput, allPersonsDetailsPersonArrayList,
					allProjectsDetailsArray, indexToEdit, personIndexToEdit);
		
		} 
			
		else if(userChoiceToEdit == 2){
			PersonMethods.editProjectArchitectEmailAddress(userInput, allPersonsDetailsPersonArrayList,
					allProjectsDetailsArray, indexToEdit, personIndexToEdit);
		
		}
			
		else if(userChoiceToEdit == 3){
			PersonMethods.editProjectArchitectPhysicalAddress(userInput, allPersonsDetailsPersonArrayList,
					allProjectsDetailsArray, indexToEdit, personIndexToEdit);
		}
		else{
			System.out.println("Invalid option, returning to the main menu...");
			System.out.println("");
		}
		
		System.out.println("Architect contact details successfully updated, returning to main menu...");
		System.out.println("");
		
		userChoice = UserInterfaceMethods.runMainMenu();
		return userChoice;
	}
	 /**
	  * This method enables the user to edit an existing architect's physical address for a specific project.
	  * <p>
	  * The method looks for the specific project within the existing Project array and obtains the index. The project details for
	  * this index is then obtained, of which the architect Person object is one. The architect Person object is then accessed to get
	  * the existing physical address which is displayed. A new value is requested from the user, and the architect Person object 
	  * physicalAddress field is set equal to this new value. The person.txt and projects.txt is updated accordingly.
	  * 
	  * @see Project #getProjectArchtect()
	  * @see Person #getPhysicalAddress()
	  * @see Person #setPersonPhysicalAddress(String)
	  * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[])
	  * @see readAndWriteFiles #convertPersonArrayToString(ArrayList)
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[][])
	  * 
	  * @param userInput String input from the user to define the new physical address.
	  * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
	  * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
	  * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
	  * @param personIndexToEdit Index integer of the person to be edited within the Person ArrayList.
	  */
	private static void editProjectArchitectPhysicalAddress(Scanner userInput,
			ArrayList<Person> allPersonsDetailsPersonArrayList, Project[] allProjectsDetailsArray, int indexToEdit,
			int personIndexToEdit) {
		// Refer to comments for editProjectCustomerPhysicalAddress as the methods are very similar.
		System.out.println("Current physical address is:");
		Person currentArchitectDetails = allProjectsDetailsArray[indexToEdit].getProjectArchtect();
		String currentArchitectPhysicalAddress = currentArchitectDetails.getPhysicalAddress();
		System.out.println(currentArchitectPhysicalAddress);
				
		System.out.println("Enter the new physical address: ");
		String newArchitectPhysicalAddress = userInput.nextLine();
		currentArchitectDetails.setPersonPhysicalAddress(newArchitectPhysicalAddress);
		allProjectsDetailsArray[indexToEdit].setProjectArchitect(currentArchitectDetails);
		allPersonsDetailsPersonArrayList.get(personIndexToEdit).setPersonPhysicalAddress(newArchitectPhysicalAddress);
				
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
	  			
		String [] updatedPersonStringArray = readAndWriteFiles.convertPersonArrayToString(allPersonsDetailsPersonArrayList);
		readAndWriteFiles.writeUpdatedInfoToPersonTextFile(updatedPersonStringArray);
	}
	 /**
	  * This method enables the user to edit an existing architect's email address for a specific project.
	  * <p>
	  * The method looks for the specific project within the existing Project array and obtains the index. The project details for
	  * this index is then obtained, of which the architect Person object is one. The architect Person object is then accessed to get
	  * the existing email address which is displayed. A new value is requested from the user, and the architect Person object 
	  * physicalAddress field is set equal to this new value. The person.txt and projects.txt is updated accordingly.
	  * 
	  * @see Project #getProjectArchtect()
	  * @see Person #getEmailAddress()
	  * @see Person #setPersonEmailAddress(String)
	  * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[])
	  * @see readAndWriteFiles #convertPersonArrayToString(ArrayList)
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[][])
	  * 
	  * @param userInput String input from the user to define the new email address.
	  * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
	  * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
	  * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
	  * @param personIndexToEdit Index integer of the person to be edited within the Person ArrayList.
	  */
	private static void editProjectArchitectEmailAddress(Scanner userInput,
			ArrayList<Person> allPersonsDetailsPersonArrayList, Project[] allProjectsDetailsArray, int indexToEdit,
			int personIndexToEdit) {
		// Refer to comments for editProjectCustomerPhysicalAddress as the methods are very similar.
		System.out.println("Current email address is:");
		Person currentArchitectDetails = allProjectsDetailsArray[indexToEdit].getProjectArchtect();
		String currentArchitectEmailAddress = currentArchitectDetails.getEmailAddress();
		System.out.println(currentArchitectEmailAddress);
				
		System.out.println("Enter the new email address: ");
		String newArchitectEmailAddress = userInput.nextLine();
		currentArchitectDetails.setPersonEmailAddress(newArchitectEmailAddress);
		allProjectsDetailsArray[indexToEdit].setProjectArchitect(currentArchitectDetails);
		allPersonsDetailsPersonArrayList.get(personIndexToEdit).setPersonEmailAddress(newArchitectEmailAddress);
				
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
	  			
		String [] updatedPersonStringArray = readAndWriteFiles.convertPersonArrayToString(allPersonsDetailsPersonArrayList);
		readAndWriteFiles.writeUpdatedInfoToPersonTextFile(updatedPersonStringArray);
	}
	
	 /**
	  * This method enables the user to edit an existing architect's telephone number for a specific project.
	  * <p>
	  * The method looks for the specific project within the existing Project array and obtains the index. The project details for
	  * this index is then obtained, of which the architect Person object is one. The architect Person object is then accessed to get
	  * the existing telephone number which is displayed. A new value is requested from the user, and the architect Person object 
	  * telephoneNumber field is set equal to this new value. The person.txt and projects.txt is updated accordingly.
	  * 
	  * @see Project #getProjectArchtect()
	  * @see Person #getTelephoneNumber()
	  * @see Person #setPersonNumber(String)
	  * @see readAndWriteFiles #convertProjectArrayToStringArray(Project[])
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[])
	  * @see readAndWriteFiles #convertPersonArrayToString(ArrayList)
	  * @see readAndWriteFiles #writeUpdatedInfoToProjectTextFile(String[][])
	  * 
	  * @param userInput String input from the user to define the new telephone number.
	  * @param allPersonsDetailsPersonArrayList Person ArrayList containing all the existing Person objects.
	  * @param allProjectsDetailsArray Project Array containing all the details of existing projects.
	  * @param indexToEdit Array index integer of the project to be edited within the Projects Array.
	  * @param personIndexToEdit Index integer of the person to be edited within the Person ArrayList.
	  */
	private static void editProjectArchitectNumber(Scanner userInput,
			ArrayList<Person> allPersonsDetailsPersonArrayList, Project[] allProjectsDetailsArray, int indexToEdit,
			int personIndexToEdit) {
		// Refer to comments for editProjectCustomerPhysicalAddress as the methods are very similar.
		System.out.println("Current number is:");
		Person currentArchitectDetails = allProjectsDetailsArray[indexToEdit].getProjectArchtect();
		String currentArchitectNumber = currentArchitectDetails.getTelephoneNumber();
		System.out.println(currentArchitectNumber);
				
		System.out.println("Enter the new number: ");
		String newArchitectNumber = userInput.nextLine();
		currentArchitectDetails.setPersonNumber(newArchitectNumber);
		allProjectsDetailsArray[indexToEdit].setProjectArchitect(currentArchitectDetails);
		allPersonsDetailsPersonArrayList.get(personIndexToEdit).setPersonNumber(newArchitectNumber);
				
				
		String [] updatedProjectStringArray = readAndWriteFiles.convertProjectArrayToStringArray(allProjectsDetailsArray);
		readAndWriteFiles.writeUpdatedInfoToProjectTextFile(updatedProjectStringArray);
	  			
		String [] updatedPersonStringArray = readAndWriteFiles.convertPersonArrayToString(allPersonsDetailsPersonArrayList);
		readAndWriteFiles.writeUpdatedInfoToPersonTextFile(updatedPersonStringArray);
	} 
	
}
