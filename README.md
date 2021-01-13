# Task24_Lvl02_Capstone3
Poised is a java file that creates and modifies Project and Person objects and write these objects to text files (person.txt and projects.txt). 

## Functions
Database records are created and updated using JDBC. The program has the following functionality:
- Create a new project.
- Change the total fee of an existing project.
- Change the total paid to date of an existing project.
- Change the due date of an existing project.
- Update an existing architect's contact details.
- Update an existing contractor's contact details..
- Update an existing customer's.
- Finalize an existing project.
- Display all incomplete projects.
- Display all overdue projects.

All new projects and persons created as well as any changes are written the the projects.txt and person.txt files.
Methods were created for each of these options.

### Create a new project
The program allows the user to create new projects and capture all the details of a project. This includes:
- Project number.
- Project name.
- Building type (house/apartment/store etc)
- Physical address of the project.
- ERF number.
- Total fee for the project.
- Total paid to date.
- Deadline of the project.
- Name, telephone number, email address and physical address of the project architect.
- Name, telephone number, email address and physical address of the project contractor.
- Name, telephone number, email address and physical address of the project customer.
- Project status which is set to 'Incomplete' by default for all new projects.
- Project completion date that is set to 'TBD' in the database by default for all new projects.

If the user does not enter a project name, one will automatically be generated by the customer's last name and the type of building being designed.

A Project object is then created and written to the projects.txt file. If an architect, contractor and/or customer was selected that does not exists, a new
person is also created and written to the database.

### Change the total fee
The user can change an existing project's total fee, by entering the project number to udpate. The updated fee is requested. The updated project is written to the projects.txt file.

### Change the total paid to date
The user can change an existing project's total paid, by entering the project number to udpate. The updated total paid is requested. The updated project is written to the projects.txt file.

### Change the due date
The user can change an existing project's due date, by entering the project number to udpate. The updated due date is requested. The updated project is written to the projects.txt file.

### Update the architect's details
The user can change a project architect's contact details by selecting which contact details to update, number, email address or physical address, as well as providig the project number that the architect is assigned to. The updated contact details are then written to both the projects.txt and person.txt files.


### Update the contractor's details
The user can change a project contractor's contact details by selecting which contact details to update, number, email address or physical address, as well as providig the project number that the contractor is assigned to. The updated contact details are then written to both the projects.txt and person.txt files.

### Update the customer's details
The user can change a project customer's contact details by selecting which contact details to update, number, email address or physical address, as well as providig the project number that the contractor is assigned to. The updated contact details are then written to both the projects.txt and person.txt files.

### Finalize the project
The user can finalize a project, by entering the project number to finalize. If this option is selected the program checks if the total paid is equal to the fee of the project. If it is not, it means that there is an outstanding balance and an invoice is generated. An invoice is then displayed with the customer's details as well as the amount payable. The project status is also changed to 'Finalized' in the projects.txt file and a completion date is requested and added to the projects.txt file.

### Display all incomplete projects
The program loops through all of the project records in the projects.txt text file and displays a project if the project status is 'Incomplete'.

### Display all overdue projects
The program loops through all of the project records in the projects.txt text file and displays a project if the project status is 'Incomplete' and the project deadline is earlier than today's date.

## Use 
This program is useful for small architecture companies that want to manage project, personnel and customer data. It allows users to allocate people to projects and to edit the data, as the project progresses. 

## Contributors
Contributors include Nadia Botha and HyperionDev. 

Please send an email to nadiamarais@live.co.za regarding any issues. Provide include a brief description and screenshot of the issue in the email, with Poised as the email subject. 

## Installing and running the program
Install the Java Development kit, by following the steps provided [here](https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_HowTo.html#jdk-install).

If not done yet, install an IDE capable of running java. Two popular IDE's are IntelliJIdea, which can be downloaded [here](https://www.jetbrains.com/idea/) or Eclipse, which can be downloaded [here](https://www.eclipse.org/downloads/).

After successfully installing an IDE, download and save all the .java files in the GitHub repository to the IDE workspace. Make sure that all the files are in the same folder.

Copy all java files in the same folder and run Poised.java in Eclipse. A projects.txt and person.txt file will automatically be generated after running the Poised.java file. 

Samples of the text files are also provided in the repository.
