import control.CourseManager;
import control.StudentManager;
import control.fileManager;
import entity.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Create the data required for the application
 * @author Jasper Lim
 *
 */
public class dataGeneration {
	/**
	 * Method serves as the the initialize point of the program.
	 * @param args the command line arguments
	 * @throws Exception to catch any possible errors
	 */
	public static void main(String[] args) throws Exception {
		

		String password = "password";
		password = User.hashString(password);
		
		ArrayList<Student> listofstudents = new ArrayList<Student>();
		ArrayList<Index> listofindex = new ArrayList<Index>();
		ArrayList<Course> listofcourses = new ArrayList<Course>();

		/* ------------------------- Start of Student ------------------------- */
		
		Student _student1 = new Student("Student", "001", "Singaporean", "SCSE", 'M', 1, "student",
				password, "cz2002gatsby@gmail.com");
		Student _student2 = new Student("Student2", "002", "Singaporean", "SCSE", 'F', 1, "student2",
				password, "cz2002gatsby@gmail.com");
		Student _student3 = new Student("James Graham", "A0000780B", "Singaporean", "SCSE", 'M', 2, "student3",
				password, "cz2002gatsby@gmail.com");
		Student _student4 = new Student("Willy Wonka", "C1700222D", "Singaporean", "SCSE", 'M', 3, "student4",
				password, "cz2002gatsby@gmail.com");
		Student _student5 = new Student("Cena Mareatte", "U1922333B", "Malaysian", "SCSE", 'F', 2, "student5",
				password, "cz2002gatsby@gmail.com");
		Student _student6 = new Student("Trumpity Wumpity", "U1622999D", "American", "SCSE", 'M', 1, "student6",
				password, "cz2002gatsby@gmail.com");
		
		listofstudents.add(_student1);
		listofstudents.add(_student2);
		listofstudents.add(_student3);
		listofstudents.add(_student4);
		listofstudents.add(_student5);
		listofstudents.add(_student6);
		
		/* ------------------------- End of Student ------------------------- */
		
		/* ------------------------- Start of Admin ------------------------- */
		ArrayList<Admin> listofAdmin = new ArrayList<Admin>();

		Admin _admin1 = new Admin("admin1", password);

		Admin _admin2 = new Admin("admin2", password);

		listofAdmin.add(_admin1);
		listofAdmin.add(_admin2);

		/* ------------------------- End of Admin ------------------------- */
		
		/* ------------------------- Start of Course creation ------------------------- */
		
		/*--------------------------------cz2001-----------------------*/
		
		Course cz2001 = new Course("CZ2001", "Algorithms", "SCSE", 3);
		Index _index1_1 = new Index("10124");
		Lesson _lesson1_1_1 = new Lesson("Lecture","CS2", 4,1,1);
		Lesson _lesson1_1_4 = new Lesson("Lecture","CS2", 0,3,1);
		Lesson _lesson1_1_3 = new Lesson("Tutorial","SSR1", 3,9,1);
		Lesson _lesson1_1_2 = new Lesson("Lab","SSR1", 2,2,2);

		_index1_1.addToLessons(_lesson1_1_1);
		_index1_1.addToLessons(_lesson1_1_2);
		_index1_1.addToLessons(_lesson1_1_3);
		_index1_1.addToLessons(_lesson1_1_4);
		
		cz2001.addToIndex(_index1_1);

		
		Index _index1_2 = new Index("10125");
		Lesson _lesson1_2_1 = new Lesson("Lecture","CS2", 4,2,1);
		Lesson _lesson1_2_4 = new Lesson("Lecture","CS2", 0,4,1);
		Lesson _lesson1_2_3 = new Lesson("Tutorial","SSR5", 2,6,1);
		Lesson _lesson1_2_2 = new Lesson("Lab","SSR5", 3,0,2);
		
		_index1_2.addToLessons(_lesson1_2_1);
		_index1_2.addToLessons(_lesson1_2_2);
		_index1_2.addToLessons(_lesson1_2_3);
		_index1_2.addToLessons(_lesson1_2_4);
		
		cz2001.addToIndex(_index1_2);
		
		Index _index1_3 = new Index("10900");
		Lesson _lesson1_3_1 = new Lesson("Lecture","CS2", 4,2,1);
		Lesson _lesson1_3_4 = new Lesson("Lecture","CS2", 0,4,1);
		Lesson _lesson1_3_3 = new Lesson("Tutorial","SSR5", 2,6,1);
		Lesson _lesson1_3_2 = new Lesson("Lab","SSR5", 3,0,2);
		
		_index1_2.addToLessons(_lesson1_3_1);
		_index1_2.addToLessons(_lesson1_3_2);
		_index1_2.addToLessons(_lesson1_3_3);
		_index1_2.addToLessons(_lesson1_3_4);
		
		cz2001.addToIndex(_index1_3);
		
		/*--------------------------------cz2001-----------------------*/
		
		/*--------------------------------cz2002-----------------------*/
		
		Course cz2002 = new Course("CZ2002", "Object-Oriented Design", "SCSE", 3);
		Index _index2_1 = new Index("10126");
		Lesson _lesson2_1_1 = new Lesson("Lecture","CS2", 3,2,1);
		Lesson _lesson2_1_4 = new Lesson("Lecture","CS2", 1,7,1);
		Lesson _lesson2_1_3 = new Lesson("Tutorial","FEP1", 2,1,1);
		Lesson _lesson2_1_2 = new Lesson("Lab","FEP1", 0,7,2);
		
		_index2_1.addToLessons(_lesson2_1_1);
		_index2_1.addToLessons(_lesson2_1_2);
		_index2_1.addToLessons(_lesson2_1_3);
		_index2_1.addToLessons(_lesson2_1_4);

		cz2002.addToIndex(_index2_1);

		Index _index2_2 = new Index("10127");
		Lesson _lesson2_2_1 = new Lesson("Lecture","CS2", 3,3,1);
		Lesson _lesson2_2_4 = new Lesson("Lecture","CS2", 1,8,1);
		Lesson _lesson2_2_2 = new Lesson("Lab","FEP3", 1,5,2);
		Lesson _lesson2_2_3 = new Lesson("Tutorial","FEP3", 1,0,1);
		
		_index2_2.addToLessons(_lesson2_2_1);
		_index2_2.addToLessons(_lesson2_2_2);
		_index2_2.addToLessons(_lesson2_2_3);
		_index2_2.addToLessons(_lesson2_2_4);
		
		cz2002.addToIndex(_index2_2);
		
		Index _index2_3 = new Index("10901");
		Lesson _lesson2_3_1 = new Lesson("Lecture","CS2", 4,2,1);
		Lesson _lesson2_3_4 = new Lesson("Lecture","CS2", 0,4,1);
		Lesson _lesson2_3_3 = new Lesson("Tutorial","SSR5", 2,6,1);
		Lesson _lesson2_3_2 = new Lesson("Lab","SSR5", 3,0,2);
		
		_index2_2.addToLessons(_lesson2_3_1);
		_index2_2.addToLessons(_lesson2_3_2);
		_index2_2.addToLessons(_lesson2_3_3);
		_index2_2.addToLessons(_lesson2_3_4);
		
		cz2002.addToIndex(_index2_3);
		
		/*--------------------------------cz2002-----------------------*/
		
		/*--------------------------------cz2003-----------------------*/
		Course cz2003 = new Course("CZ2003", "Computer Visualisation", "SCSE", 3);
		Index _index3_1 = new Index("10128");
		Lesson _lesson3_1_1 = new Lesson("Lecture","CS2", 3,4,1);
		Lesson _lesson3_1_2 = new Lesson("Lecture","CS2", 0,9,1);
		Lesson _lesson3_1_3 = new Lesson("Tutorial","SS1", 4,8,1);
		Lesson _lesson3_1_4 = new Lesson("Lab","SS1", 2,4,2);
		
		_index3_1.addToLessons(_lesson3_1_1);
		_index3_1.addToLessons(_lesson3_1_2);
		_index3_1.addToLessons(_lesson3_1_3);
		_index3_1.addToLessons(_lesson3_1_4);
		
		cz2003.addToIndex(_index3_1);
		
		Index _index3_2 = new Index("10129");
		Lesson _lesson3_2_1 = new Lesson("Lecture","SS1", 3,5,1);
		Lesson _lesson3_2_2 = new Lesson("Lecture","SS1", 0,10,1);
		Lesson _lesson3_2_3 = new Lesson("Tutorial","SS2", 2,7,1);
		Lesson _lesson3_2_4 = new Lesson("Lab","SS2", 4,5,2);
		
		_index3_2.addToLessons(_lesson3_2_1);
		_index3_2.addToLessons(_lesson3_2_2);
		_index3_2.addToLessons(_lesson3_2_3);
		_index3_2.addToLessons(_lesson3_2_4);
		
		cz2003.addToIndex(_index3_2);
		
		/*--------------------------------cz2004-----------------------*/


		Course cz2004 = new Course("CZ2004", "Human Computer Interaction", "SCSE", 3);
		Index _index4_1= new Index("10140");
		Lesson _lesson4_1_1 = new Lesson("Lecture","CS2", 2, 8,1);
		Lesson _lesson4_1_2 = new Lesson("Lecture","CS2", 4, 4,1);
		Lesson _lesson4_1_3 = new Lesson("Tutorial","FSP4", 3, 7,1);
		Lesson _lesson4_1_4 = new Lesson("Lab","FSP4", 1,1,2);
		
		_index4_1.addToLessons(_lesson4_1_1);
		_index4_1.addToLessons(_lesson4_1_2);
		_index4_1.addToLessons(_lesson4_1_3);
		_index4_1.addToLessons(_lesson4_1_4);
		
		cz2004.addToIndex(_index4_1);
		
		Index _index4_2= new Index("10141");
		Lesson _lesson4_2_1 = new Lesson("Lecture","CS2", 5,3,1);
		Lesson _lesson4_2_2 = new Lesson("Lecture","CS2", 0, 0,2);
		Lesson _lesson4_2_3 = new Lesson("Tutorial","FSP9", 4, 7,1);
		Lesson _lesson4_2_4 = new Lesson("Lab","FSP9", 1,3,2);
		
		_index4_2.addToLessons(_lesson4_2_1);
		_index4_2.addToLessons(_lesson4_2_2);
		_index4_2.addToLessons(_lesson4_2_3);
		_index4_2.addToLessons(_lesson4_2_4);
		
		cz2004.addToIndex(_index4_2);
		
		Index _index4_3= new Index("10142");
		Lesson _lesson4_3_1 = new Lesson("Lecture","CS2", 3,5,1);
		Lesson _lesson4_3_2 = new Lesson("Lecture","CS2", 0,5,2);
		_index4_3.addToLessons(_lesson4_3_1);
		_index4_3.addToLessons(_lesson4_3_2);
		_index4_3.setTotalSize(1);
		cz2004.addToIndex(_index4_3);




		/*--------------------------------cz2004-------------------------*/
		
		listofcourses.add(cz2001);
		listofcourses.add(cz2002);
		listofcourses.add(cz2003);
		listofcourses.add(cz2004);
		
		/* ------------------------- end of Course creation ------------------------- */
		
		
		/*-------------------------Utility--------------------------------------*/
		
		fileManager.saveStudentFile(listofstudents); // save student file
		fileManager.saveCoursesFile(listofcourses); // save student file
		fileManager.saveAdminFile(listofAdmin); // save admin file
		System.out.println("File Generated!");
		
		/*-------------------------Utility--------------------------------------*/
		
		
		
	}
}

