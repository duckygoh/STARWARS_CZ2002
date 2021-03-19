package control;

import entity.Course;
import entity.Index;
import entity.Student;

import java.util.ArrayList;

/**
 * CourseManager handles the input and ouput of data into the different entity courses.
 * @author Jasper Lim
 *
 */

public class CourseManager {

	/**
	 * List of available courses in the system.
	 */
	static ArrayList<Course> listOfCourses = new ArrayList<Course>();

	/**
	 * loading of the course file
	 */
	public CourseManager() {
		listOfCourses = fileManager.loadCoursesFile();
		
	}
	
	/**
	 * Searches for a particular course object and returns a copied item.
	 * @param courseID of a course eg. CZ2001
	 * @return copied course object or NULL course object.
	 */

	public static Course findCourseObject(String courseID) {

		for (Course course : listOfCourses) {
			if (course.getCourseID().equals(courseID)) {
				
				Course copycourse = new Course(course);
				
				return copycourse;
			}
		}
		Course emptycourse = new Course();
		return emptycourse;
	}
	
	/**
	 * Searches for a list of indexes that a course has.
	 * @param courseID of a course eg. CZ2001
	 * @return a list of indexes contained in a course
	 */

	public static ArrayList<Index> findIndexGroup(String courseID){
		for (int i = 0; i < CourseManager.listOfCourses.size(); i++) {
			if (CourseManager.listOfCourses.get(i).getCourseID().equals(courseID)) {
				return CourseManager.listOfCourses.get(i).getIndex();
			}
		}
		return new ArrayList<Index>();
	}
	
	/**
	 * Adds a student to a course
	 * @param Student Student Object
	 * @param courseID of a course eg. CZ2001
	 * @param indexID of a index belonging to a course eg. 10124
	 * @return boolean indicated if student was successfully enrolled
	 */

	public boolean addStudentToCourse(Student Student, String courseID, String indexID) {

		for (Course course : listOfCourses) {
			if (course.getCourseID().equals(courseID)) { // remove this check if index is unique
				ArrayList<Index> templist = course.getIndex();
				for (Index index : templist) {
					if (index.getIndexID().equals(indexID)) {
						index.addStudentToEnrolled(Student);
						//System.out.println("Student Added");
						saveCoursesFile();
						// MailManager.sendMail(Student.getEmail());
						return true;
					}
				}
			}
		}
		System.out.println("Course not found");
		return false;
	}
	
	/**
	 * Returns a student from a course
	 * @param student Object
	 * @param courseID of a course eg. CZ2001
	 * @return boolean indicated if a student has been successfully removed from a course.
	 */

	public boolean removeStudentFromCourse(Student student, String courseID) {

		for (Course course : listOfCourses) {
			if (course.getCourseID().equals(courseID)) {
				ArrayList<Index> tempindexlist = course.getIndex();

				for (Index index : tempindexlist) {

					ArrayList<Student> tempstudentlist = index.getStudentsEnrolled();

					for (Student targetstudent : tempstudentlist) {
						if (targetstudent.getMatricNumber().equals(student.getMatricNumber())) {
							index.removeStudentFromEnrolled(student);
							System.out.println("Student: " + student.getName() + " removed from course " + courseID);
							saveCoursesFile();
							return true;
						}
					}

				}
			}

		}
		System.out.println("Something went wrong - removeStudent function");
		return false;
	}

	
	/**
	 * Adds a student to the waitlist of a course and index.
	 * @param student Object containing attributes of a student
	 * @param CourseID of a course eg. CZ2001
	 * @param indexID of a index belonging to a course eg. 10124
	 * @return boolean indicating if the student has successfully entered the waitlist.
	 */
	public boolean addStudentToWaitlist(Student student, String CourseID, String indexID){

		for (Course course : listOfCourses) {

			if (course.getCourseID().equals(CourseID)) {
				ArrayList<Index> templistindex = new ArrayList<Index>();
				templistindex = course.getIndex();
				for (Index index : templistindex) {
					if (index.getIndexID().equals(indexID)) {
						index.addStudentToWaitlist(student);
						course.setIndex(templistindex);
						saveCoursesFile();
						return true;
					}
				}
			}
		}
		return false;
	}

	
	/**
	 * remove a student from the waitlist.
	 * @param CourseID of a course eg. CZ2001
	 * @param indexID of a index belonging to a course eg. 10124
	 * @return Student object that has been dropped from the waitlist.
	 */
	public Student removeStudentFromWaitlist(String CourseID, String indexID) {

		for (Course course : listOfCourses) {

			if (course.getCourseID().equals(CourseID)) {
				ArrayList<Index> templistindex = new ArrayList<Index>();
				templistindex = course.getIndex();
				for (Index index : templistindex) {
					if (index.getIndexID().equals(indexID)) {
						Student tempstudent = index.removeStudentFromWaitlist();
						course.setIndex(templistindex);
						saveCoursesFile();
						return tempstudent;
					}
				}
			}
		}
		Student emptystudent = new Student();
		return emptystudent;

	}

	/**
	 * Check the number of slots left in a course/index.
	 * @param courseID of a course eg. CZ2001
	 * @param indexID of a index belonging to a course eg. 10124
	 * @return number of vacancies in the course/index
	 */
	public static int checkVacancy(String courseID, String indexID) {

		for (Course course : listOfCourses) {
			if (course.getCourseID().equals(courseID)) {

				ArrayList<Index> templist = course.getIndex();
				for (Index index : templist) {
					if (index.getIndexID().equals(indexID)) {
						return index.checkVacancy();
					}
				}
			}
		}
		return -1;
	}

	
	/**
	 * Saves updates to the course files
	 */
	public static void saveCoursesFile() {
		try {
			fileManager.saveCoursesFile(listOfCourses);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a list of courses
	 * @return The total list of courses
	 */

	public static ArrayList<Course> getListOfCourses() {
		return listOfCourses;
	}

	/**
	 * Sets the list of courses in the system
	 * @param listOfCourses total list of courses
	 */
	public static void setListOfCourses(ArrayList<Course> listOfCourses) {
		CourseManager.listOfCourses = listOfCourses;
	}
	
	/**
	 * Print Courses in the System
	 */
	public static void printCourseList() {
		for (Course course : listOfCourses) {
			System.out.println("School: "+ course.getCourseSchool() + "\nCourse Name: " + course.getCourseName() + "\nCourse ID: " + course.getCourseID()
					+ "\nCourse AU: " + course.getAu() + "\n");
		}
	}

	/**
	 * To find an index object based on indexID
	 * 
	 * @param courseID of the index object
	 * @param indexID of the index object
	 * @return the index object if found, else return null index object
	 */
	public static Index findIndex(String courseID, String indexID) {
		for (int i = 0; i < listOfCourses.size(); i++) {
			if (listOfCourses.get(i).getCourseID().equals(courseID)) {
				ArrayList<Index> indexArrayList = listOfCourses.get(i).getIndex();
				for (int j = 0; j < indexArrayList.size(); j++) {
					if (indexArrayList.get(j).getIndexID().equals(indexID)) {
						Index index = indexArrayList.get(j);
						return index;
					}
				}
			}
		}
		Index emptyIndex = new Index();
		return emptyIndex;
	}


}
