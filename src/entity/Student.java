
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static control.fileManager.loadStudentFile;
/**
 * Represents a student user that can access the MySTARS Application
 * A student class inherit from a User class
 * @author Lim Bing Hong Jasper
 * @version 1.0
 * @since 2020/10/08
 */
public class Student extends User implements Serializable {
	/**
	 * the name of the student
	 */
	String name;
	/**
	 * the matriculation number of the student
	 */
	String matricNumber;
	/**
	 * the nationality of the user. E.g. Singapore
	 */
	String nationality;
	/**
	 * the major programme that the user is enrolled to. E.g. CSC which stands for Computer Science
	 */
	String major;
	/**
	 * the email of the student that is provided by the university
	 */
	String email;
	/**
	 *
	 */
	Course[][] schedule = new Course[11][7];
	/**
	 * the gender of the student. M/F
	 */
	char gender;
	/**
	 * the year the student currently is
	 */
	int yearOfStudy;
	/**
	 * the student starting validity period
	 */
	Calendar startTime = Calendar.getInstance();
	/**
	 * the student ending validity period
	 */
	Calendar endTime = Calendar.getInstance();
	/**
	 * The list of course that the student have taken in the university, stored in an arraylist
	 */
	ArrayList<Course> courseTaken = new ArrayList<Course>();
	/**
	 * The list of course that the student is currently enrolled in the semester, stored in an arraylist
	 */
	ArrayList<Course> courseEnrolled = new ArrayList<Course>();
	/**
	 *
	 */
	ArrayList<Course> waitList = new ArrayList<Course>();

	/**
	 * Constructor to create a new student without any attributes
	 */
	public Student() {
		super();
	}

	/**
	 * Construction to create a new student with name, matriculation number, nationality, major, gender
	 * year of study, login ID, login password and email
	 * @param name This student's name
	 * @param matricNumber This student's matriculation number
	 * @param nationality This student's nationality
	 * @param major This student's major
	 * @param gender This student's gender
	 * @param yearOfStudy This student's year of study
	 * @param loginID This student's login ID
	 * @param loginPW This student's login password
	 * @param email This student's email
	 */
	public Student(String name, String matricNumber, String nationality, String major, char gender, int yearOfStudy,
			String loginID, String loginPW, String email) {

		super(loginID, loginPW);
		this.name = name;
		this.matricNumber = matricNumber;
		this.nationality = nationality;
		this.major = major;
		this.gender = gender;
		this.yearOfStudy = yearOfStudy;
		this.email = email;
	}

	/**
	 * Retrieve the name of this student
	 * @return this student's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Modify the name of this student
	 * @param name The new name of this student
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieve the matriculation number of this student
	 * @return this student's matriculation number
	 */
	public String getMatricNumber() {
		return matricNumber;
	}

	/**
	 * Modify the matriculation number of this student
	 * @param matricNumber The new matriculation number of this student
	 */
	public void setMatricNumber(String matricNumber) {
		this.matricNumber = matricNumber;
	}

	/**
	 * Retrieve the nationality of this student
	 * @return this student's nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Modify the nationality of this student
	 * @param nationality The new nationality of this student
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Retrieve the major of this student
	 * @return this student's major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * Modify the major of this student
	 * @param major The new major of this student
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * Retrieve the gender of this student
	 * @return this student's gender
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * Modify the gender of this student
	 * @param gender The new gender of this student
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * Retrieve the year of study of this student
	 * @return this student's year of study
	 */
	public int getYearOfStudy() {
		return yearOfStudy;
	}

	/**
	 * Modify the year of study of this student
	 * @param yearOfStudy The new year of study of this student
	 */
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	/**
	 * Retrieve the access period start time of this student
	 * @return this student's access period start time
	 */
	public Calendar getStartTime() {
		return startTime;
	}

	/**
	 * Modify the student access period start time
	 * @param startTime The new access period start time of this student
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * Retrieve the access period end time of this student
	 * @return this student's access period end time
	 */
	public Calendar getEndTime() {
		return endTime;
	}

	/**
	 * Modify the access period end time of this student
	 * @param endTime The new access period end time of this student
	 */
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	/**
	 * Retrieve the arraylist of course taken by this student
	 * @return this student's course taken
	 */
	public ArrayList<Course> getCourseTaken() {
		return courseTaken;
	}

	/**
	 * Modify the arraylist of course taken by this student
	 * @param courseTaken The new arraylist of course taken by this student
	 */
	public void setCourseTaken(ArrayList<Course> courseTaken) {
		this.courseTaken = courseTaken;
	}

	/**
	 * Retrieve the arraylist of course currently enrolled by this student
	 * @return this student's course enrolled
	 */
	public ArrayList<Course> getCourseEnrolled() {
		return courseEnrolled;
	}

	/**
	 * Modify the arraylist of course enrolled by this student
	 * @param courseEnrolled The new arraylist of course enrolled by this student
	 */
	public void setCourseEnrolled(ArrayList<Course> courseEnrolled) {
		this.courseEnrolled = courseEnrolled;
	}

	/**
	 *
	 * @return a list of courses a student is to enroll in.
	 */
	public ArrayList<Course> getWaitList() {
		return waitList;
	}

	/**
	 *
	 * @param waitList a list of courses a student is to enroll in.
	 */
	public void setWaitList(ArrayList<Course> waitList) {
		this.waitList = waitList;
	}

	/**
	 * Retrieve the email of this student
	 * @return this student's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modify the email of this student
	 * @param email The new email of this student
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * to find a student object based on matriculation number
	 * 
	 * @param matricNumber unique key of student object
	 * @return the student object if found, else return null student object
	 */
	public static Student findStudent(String matricNumber) {
		ArrayList<Student> studentArrayList = loadStudentFile(); // load student object to variable
		for (int i = 0; i < studentArrayList.size(); i++) {
			if (studentArrayList.get(i).getMatricNumber().equals(matricNumber)) {
				Student student = studentArrayList.get(i);
				return student;
			}
		}
		System.out.println("Student not found");
		Student emptyStudent = new Student();
		return emptyStudent;
	}

	/**
	 * To determine if a student can access the STARS Application based on their access start time and access end time
	 * @return boolean to indicate if the access period of this student is valid or not
	 */
	public boolean accessPeriodValidity() {
		Calendar now = Calendar.getInstance();

		Calendar startTime = this.startTime;
		Calendar endTime = this.endTime;

		if (now.before(endTime) && now.after(startTime)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *Retrieves the schedule of a student.
	 * @return timetable of a student
	 */
	public Course[][] getSchedule() {
		return schedule;
	}

	/**
	 *Modifies the schedule of a student.
	 * @param schedule timetable of a student
	 */
	public void setSchedule(Course[][] schedule) {
		this.schedule = schedule;
	}

	/**
	 * Populates the schedule table of a student based on their enrolled courses.
	 */
	public void populateSchedule() {
//		
		this.schedule = new Course[schedule.length][schedule[0].length];

		for (Course course : this.courseEnrolled) {
			ArrayList<Index> tempindex = course.getIndex();
			
			for(Index index: tempindex) {
				ArrayList<Lesson> listoflessons = index.getLessons();
				for (Lesson lesson : listoflessons) {

					for (int j = lesson.getStartTime(); j < (lesson.getStartTime() + lesson.getDuration()); j++) {
						this.schedule[j][lesson.getDay()] = course;
					}
				}
			}

		}
	}

	/**
	 * Populates the schedule of a student based on their waitlist courses.
	 * @return future timetable of student with waitlist courses added.
	 */
	public Course[][] populateWaitlistCourse() {
//		
		this.populateSchedule();
		
		Course[][] tempschedule = this.schedule;
		
		ArrayList<Course> waitlistcourses = this.waitList;

		for (Course course : waitlistcourses) {
			ArrayList<Index> tempindex = course.getIndex();
			
			for(Index index: tempindex) {
				ArrayList<Lesson> listoflessons = index.getLessons();
				for (Lesson lesson : listoflessons) {

					for (int j = lesson.getStartTime(); j < (lesson.getStartTime() + lesson.getDuration()); j++) {
						tempschedule[j][lesson.getDay()] = course;
					}
				}
			}

		}
		return tempschedule;
	}
	
	/**
	 * Checks if there is a clash in the schedule of a student.
	 * @param targetindex check if student's timetable is clashing with the particular index.
	 * @return true indicates clashing.
	 */
	public boolean checkClash(Index targetindex) {

		this.populateSchedule();
		
		Course[][] tempschedule = this.populateWaitlistCourse();
				
		ArrayList<Lesson> listoflesson = targetindex.getLessons();

		for (Lesson lesson : listoflesson) {

			int day = lesson.getDay();
			int start = lesson.getStartTime();
			int end = lesson.getStartTime() + lesson.getDuration();

			for (int i = start; i < end; i++) {
				if (tempschedule[i][day] != null) {
					return true;
				}
			}

		}
		return false;
	}

}
