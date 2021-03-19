/**
 * Represents a Index Group of a Course Object which a Student can take
 * @author Quah Dian Wei
 * @version 1.0
 * @since 2020/10/08
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static control.fileManager.loadCoursesFile;
import static control.fileManager.saveCoursesFile;
/**
 * Index object containing the details of a course such as the lessons and size.
 * @author Jasper Lim
 * @version 1.0
 * @since 2020/10/08
 */
public class Index implements Serializable {
	/**
	 * Serialization standardization
	 */
    private static final long serialVersionUID = 4L;
	/**
	 * The total size allowed of the index group
	 */
	private int totalSize = 5;
	/**
	 * The current size of the index group based on the number of student enrolled
	 */
	private int currentSize = 0;
	/**
	 * The index ID of the index group
	 */
	private String indexID;
	/**
	 * The list of student that enrolled in the index group, stored in an array list
	 */
	private ArrayList<Student> studentsEnrolled = new ArrayList<Student>();
	/**
	 * The list of lessons (Lecture / Tutorial / Laboratory) that the index has
	 */
	private ArrayList<Lesson> lessons = new ArrayList<Lesson>();
	/**
	 * Queue structure waitlist for the Index
	 */
	private Queue<Student> waitlist = new LinkedList<Student>();

	/**
	 * Constructor to create a new index group without any attributes
	 */
	public Index() {
	}

	/**
	 * Constructor to create a new index with index ID only
	 * @param indexID this index's index id
	 */
	public Index(String indexID) {
		this.indexID = indexID;
	}

	/**
	 * Constructor to create a new index with index ID, total size of index and array list of lessons
	 * @param indexID this index's index ID
	 * @param totalSize this index's totalsize
	 * @param lessons this index's lessons
	 */
	public Index(String indexID, int totalSize, ArrayList<Lesson> lessons) {

		this.indexID = indexID;

		this.totalSize = totalSize;

		this.lessons = lessons;
	}
	
	/**
	 * Add lesson objects to the index
	 * @param lesson object containing attributes of the lesson
	 */
	public void addToLessons(Lesson lesson) {
		this.lessons.add(lesson);
	}
	
	/**
	 * Print all the students in the queue for a Index
	 */
	public void printQueue() {

		for (Student student : this.waitlist) {
			System.out.println(student.getName());
		}

	}
	/**
	 * Print students enrolled in an index
	 */
	public void printStudentsEnrolled() {

		if (studentsEnrolled.isEmpty()) {
			System.out.println("No students enrolled in this Index");
		}

		for (Student student : this.studentsEnrolled) {
			System.out.println(
					"Student Name: " + student.getName() + "\nStudent Matric Number: " + student.getMatricNumber());
		}
	}

	/**
	 * Enroll a student to a Index
	 * @param student object containing attributes of the student
	 */
	public void addStudentToEnrolled(Student student) {
		this.studentsEnrolled.add(student);
		this.currentSize++;
	}

	/**
	 * Remove a student from a Index
	 * @param student object removed from list of enrolled students
	 */
	public void removeStudentFromEnrolled(Student student) {
		for (int i = 0; i < this.studentsEnrolled.size(); i++) {
			if (studentsEnrolled.get(i).getMatricNumber().equals(student.getMatricNumber())) {
				studentsEnrolled.remove(i);
			}
		}
		this.currentSize--;
	}
/**
 * Gets the total size of the Index
 * @return total size of the index
 */
	public int getTotalSize() {
		return totalSize;
	}
/**
 * Sets the total slots available for students to enroll into the Index
 * @param totalSize of the index
 */
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
/**
 * Get the current number of students enrolled in the Index
 * @return number of students currently enrolled
 */
	public int getCurrentSize() {
		return currentSize;
	}
/**
 * Sets the current size of the Index
 * @param currentSize number of students currently enrolled
 */
	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
	}
/**
 * Get the ID of the current Index
 * @return ID of the current index
 */
	public String getIndexID() {
		return indexID;
	}
/**
 * Set the ID of the Index
 * @param indexID of the index object
 */
	public void setIndexID(String indexID) {
		this.indexID = indexID;
	}
/**
 * Get students enrolled in the Index
 * @return a list of students enrolled
 */
	public ArrayList<Student> getStudentsEnrolled() {
		return studentsEnrolled;
	}
/**
 * Set students enrolled in the Index
 * @param studentsEnrolled a list of students enrolled
 */
	public void setStudentsEnrolled(ArrayList<Student> studentsEnrolled) {
		this.studentsEnrolled = studentsEnrolled;
	}
/**
 * Get list of Lesson objects in the Index
 * @return a list of lessons in the Index
 */
	public ArrayList<Lesson> getLessons() {
		return lessons;
	}
/**
 * Set the list of Lessons in the Index
 * @param lessons list of lessons in the Index
 */
	public void setLessons(ArrayList<Lesson> lessons) {
		this.lessons = lessons;
	}
/**
 * Get the current queue of students on the waitlist
 * @return the queue of students waiting to enroll in the index
 */
	public Queue<Student> getWaitlist() {
		return waitlist;
	}
/**
 * Sets the Waitlist of the Index
 * @param waitlist a queue of students waiting to enroll in the index
 */
	public void setWaitlist(Queue<Student> waitlist) {
		this.waitlist = waitlist;
	}
/**
 * Add a student to the Waitlist of the Index
 * @param student object
 */
	public void addStudentToWaitlist(Student student) {
		this.waitlist.add(student);
	}
/**
 * Remove student from the Waitlist of an Index
 * @return Student object removed from the Waitlist
 */
	public Student removeStudentFromWaitlist() {

		Student tempstudent = this.waitlist.remove();
		return tempstudent;
	}
/**
 * Checks the number of available slots in the index
 * @return number of slots available in the Index
 */
	public int checkVacancy() {
		int vacancy = this.totalSize - this.currentSize;
		return vacancy;
	}

	/**
	 * To remove an index object based on indexID
	 * 
	 * @param courseID  of the index object
	 * @param indexID  of the index object
	 * @return true or false depending if is success
	 */
	public static boolean removeIndex(String courseID, String indexID) {
		ArrayList<Course> courseArrayList = loadCoursesFile();

		for (int i = 0; i < courseArrayList.size(); i++) {
			if (courseArrayList.get(i).getCourseID().equals(courseID)) {
				ArrayList<Index> indexArrayList = courseArrayList.get(i).getIndex();
				for (int j = 0; j < indexArrayList.size(); j++) {
					if (indexArrayList.get(i).getIndexID().equals(indexID)) {
						indexArrayList.remove(i);
					}
				}
			}
		}
		try {
			saveCoursesFile(courseArrayList);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
	/**
	 * Override toString function to allow the object to be printed
	 */
	 @Override public String toString() {
		    StringBuilder result = new StringBuilder();
		    String NL = System.getProperty("line.separator");
		    
		    
		    result.append(this.indexID);
		    
		    return result.toString();
	 }
}
