package control;

import entity.Admin;
import entity.Course;
import entity.Student;

import java.io.*;
import java.util.ArrayList;

/***
 * This class is in charge of saving and loading student, admin and courses data files. 
 * @author Jasper Lim
 *
 */

public class fileManager {

	private final static String OUTSTUDENTFILENAME = "StudentFile";
	private final static String OUTCOURSESFILENAME = "CoursesFile";
	private final static String OUTADMINFILENAME = "AdminFile";

	// STUDENT OBJECTS READER

	/***
	 * This method saves the list of all the students' information into a file
	 * @param listOfStudent This contains the list of students
	 * @throws Exception To catch errors when file does not save properly
	 */
	public static void saveStudentFile(ArrayList<Student> listOfStudent) throws Exception {

		String outputfilepath = String.format("src\\\\%s.ser", OUTSTUDENTFILENAME);

		FileOutputStream fileOut = new FileOutputStream(outputfilepath);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(listOfStudent);
		out.close();
		fileOut.close();
	}
	/**
	 * This method loads the information of all the students from a file
	 * @return the list of students
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Student> loadStudentFile() {

		String filepath = String.format("src\\\\%s.ser", OUTSTUDENTFILENAME);
		ArrayList<Student> value = new ArrayList<Student>();

		try {
			FileInputStream fileIn = new FileInputStream(filepath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			value = (ArrayList<Student>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Student class not found");
			c.printStackTrace();
		}

		return value;
	}

	// END OF STUDENT OBJECT READER

	// COURSE OBJECT READER
	
	/***
	 * This method saves the list of all the courses information into a file
	 * @param listOfCourses The list of all courses information
	 * @throws Exception To catch errors when file does not save properly
	 */

	public static void saveCoursesFile(ArrayList<Course> listOfCourses) throws Exception {

		String outputfilepath = String.format("src\\\\%s.ser", OUTCOURSESFILENAME);

		FileOutputStream fileOut = new FileOutputStream(outputfilepath);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(listOfCourses);
		out.close();
		fileOut.close();
	}
	
	/**
	 * This method loads the list of all course information from a file
	 * @return the list of courses
	 */

	@SuppressWarnings("unchecked")
	public static ArrayList<Course> loadCoursesFile() {

		String filepath = String.format("src\\\\%s.ser", OUTCOURSESFILENAME);
		ArrayList<Course> value = new ArrayList<Course>();

		try {
			FileInputStream fileIn = new FileInputStream(filepath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			value = (ArrayList<Course>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Course class not found");
			c.printStackTrace();
		}

		return value;
	}

	// END OF COURSE OBJECT READER

	// ADMIN OBJECT READER
	
	/***
	 * This method saves the list of all the admin information into a file
	 * @param listOfAdmin The list of all admins information
	 * @throws Exception To catch errors when file does not save properly
	 */
	public static void saveAdminFile(ArrayList<Admin> listOfAdmin) throws Exception {

		String outputfilepath = String.format("src\\\\%s.ser", OUTADMINFILENAME);

		FileOutputStream fileOut = new FileOutputStream(outputfilepath);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(listOfAdmin);
		out.close();
		fileOut.close();
	}
	
	/**
	 * This method loads the list of all the admin information from a file
	 * @return a list of admin objects
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Admin> loadAdminFile() {

		String filepath = String.format("src\\\\%s.ser", OUTADMINFILENAME);
		ArrayList<Admin> value = new ArrayList<Admin>();

		try {
			FileInputStream fileIn = new FileInputStream(filepath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			value = (ArrayList<Admin>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Admin class not found");
			c.printStackTrace();
		}

		return value;
	}

	// END OF Admin OBJECT READER
}
