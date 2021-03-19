import boundary.AdminUI;

import boundary.StudentUI;
import control.LoginController;


import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class serves as the the entry point of the program.
 * @author Raymond Goh
 * @version 1.0
 * @since 2020/11/20
 */
public class mainApp {

//	private static ArrayList<Student> studentList = new ArrayList<Student>();

	/**
	 * Method serves as the the initialize point of the program.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		String loginID;
		String loginPW;

		Console console = System.console();

		int choice = 0;

		Scanner sc = new Scanner(System.in);
		do {
			boolean validInput = false;
			System.out.println(
					"Enter 1 for Admin, 2 for Student, 3 to exit");
			do {
				try {
					choice = sc.nextInt();
					sc.nextLine();
					if (choice >= 1 && choice<=3) {
						validInput = true;
					}
				} catch (InputMismatchException e) {
					System.out.println("Enter a valid integer!");
					sc.nextLine();
				}
			} while (!validInput);


			switch (choice) {
			case 1:
				System.out.println("Enter login ID");
				loginID = sc.nextLine();
				//System.out.println("Enter login PW");
				//loginPW = sc.nextLine();
				loginPW = new String(console.readPassword("Please enter login password."));
				LoginController logincontrol_admin = new LoginController(choice,loginID,loginPW);
				if(logincontrol_admin.validateUser() == true) {
					AdminUI.mainAdminUI();
				}
				break;
			case 2:
				System.out.println("Enter login ID");
				loginID = sc.nextLine();
				//System.out.println("Enter login PW");
				//loginPW = sc.nextLine();
				loginPW = new String(console.readPassword("Please enter login password."));
				LoginController logincontrol_student = new LoginController(choice,loginID,loginPW);
				if(logincontrol_student.validateUser() == true) {
					StudentUI.mainStudentUI(loginID);
				}
				break;
			case 3:
				break;

			default:
				System.out.println("");
				break;
			}
		} while (choice != 3);
		
		System.out.println("System Exiting...");
	}

}
