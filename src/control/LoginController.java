package control;

import java.util.ArrayList;
import java.util.Scanner;

import boundary.AdminUI;
import entity.Admin;
import entity.Student;
import entity.User;

/**
* This class is responsible for handing the logic to fulfill the login of the users.
* @author Raymond Goh
* @version 1.3
* @since 2020/11/20
*/
public class LoginController {

	private Admin admin = new Admin();
	private Student student = new Student();
	private ArrayList<Admin> adminList = new ArrayList<Admin>();
	private ArrayList<Student> studentList = new ArrayList<Student>();
	
	private int _typeOfUser;


	private String loginID;
	private String password;
	
	
	/**
	 * Constructor for the LoginController class
	 * @param _typeOfUser Determines whether user is admin or student
	 * @param loginID of the account
	 * @param password of the account
	 */
	
	public LoginController(int _typeOfUser, String loginID, String password){
		//Admin
		
		this.loginID=loginID;
		this.password=password;
		this._typeOfUser=_typeOfUser;

		
		if(_typeOfUser == 1) {
			this.adminList = fileManager.loadAdminFile();
			for(Admin _tempadmin: this.adminList) {
				if(_tempadmin.getLoginID().equals(this.loginID) == true) {
					this.admin = _tempadmin;
					break;
				}
			}
			
		}
		//Student
		else if(_typeOfUser == 2){
			this.studentList = fileManager.loadStudentFile();
			for(Student _tempstudent: this.studentList) {
				if(_tempstudent.getLoginID().equals(loginID)) {
					this.student=_tempstudent;

				}
			}

		}
	}
	/***
	 * This method authenticates the credentials of the admin or student account
	 * @return Returns true if the credentials are correct, returns false if credentials are wrong
	 */
	
	public boolean validateUser() {
		if (_typeOfUser==1) {
			if(this.admin == null) {
				return false;
			}
			if(admin.validateLogin(this.loginID, this.password)==true) {
				return true;
			}
			else {
				System.out.println("Wrong Login Information");
			}
		
		}
		if(_typeOfUser==2) {
			if(this.student == null) {
				return false;
			}
			if(student.validateLogin(this.loginID, this.password)==true) {
				if(student.accessPeriodValidity()==true) {
					return true;	
				}
				else {
					System.out.println("Invalid access period");
				}
				
			}
			else {
				System.out.println("Wrong login information");
			}
		}
		return false;
	}
}
