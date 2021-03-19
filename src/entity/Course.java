
package entity;

import java.io.Serializable;
import java.util.ArrayList;

import static control.fileManager.loadCoursesFile;
import static control.fileManager.saveCoursesFile;
/**
 * Represents a Course which a Student can take
 * @author Raymond Goh Kang Sheng
 * @version 1.0
 * @since 2020/10/08
 */
public class Course implements Serializable {
	
    private static final long serialVersionUID = 4L;

	/**
	 * The course ID of the course. E.g. CZ2001
	 */
	private String courseID;
	/**
	 * The name of the course. E.g. Computer Graphics and Visualisation
	 */
	private String courseName;
	/**
	 * The school that the course belongs to. E.g. SCSE
	 */
	private String courseSchool; // to create a school dat file (enum)
	/**
	 * The Academic Unit value of the course.
	 */
	private int au;
	/**
	 * List of Index in a courses, stored in an arraylist.
	 */
	private ArrayList<Index> index = new ArrayList<Index>();
	/**
	 * Constructor to create a course without attributes.
	 */
	public Course() {

	}
	/**
	 * Constructor to create a copy of a course.
	 * @param copycourse Course that needs to be copied.
	 */
	public Course(Course copycourse) {
		this.courseID = copycourse.courseID;
		this.courseName=copycourse.courseName;
		this.index=copycourse.index;
		this.au=copycourse.au;
	}
	/**
	 * Constructor to create a course with all its attributes, including indexes.
	 * @param courseID The course ID of the course
	 * @param courseName The name of the course
	 * @param courseSchool The school that offers this course
	 * @param au The amount of AU this course has.
	 * @param index The list of indexes this course has.
	 */
	public Course(String courseID, String courseName, String courseSchool, int au, ArrayList index) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseSchool = courseSchool;
		this.au = au;
		this.index = index;
	}
	/**
	 * Constructor to create a course with its attributes except for indexes.
	 * @param courseID The course ID of the course
	 * @param courseName The name of the course
	 * @param courseSchool The school that offers this course
	 * @param au The amount of AU this course has.
	 */
	
	public Course(String courseID, String courseName, String courseSchool, int au) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseSchool = courseSchool;
		this.au = au;
		this.index = index;
	} // to remove this constructor, for data generation
	/**
	 * Adds an index to the course.
	 * @param index Index to be added to course.
	 */
	public void addToIndex(Index index) {
		this.index.add(index); //to remove this, for data generation
	}
	/**
	 * Function to find an index object based off a string.
	 * @param indexID IndexID in string.
	 * @return The Index object that we are searching for
	 */
	public Index findIndexObject(String indexID) {
		for (Index index : this.index) {
			if (index.getIndexID().equals(indexID)) {
				return index;
			}
		}
		System.out.println("Index not found");
		Index emptyindex = new Index();
		return emptyindex;
	}

	/**
	 * Retrieves the course name of the course.
	 * @return The name of the course
	 */
	public String getCourseName() {
		return this.courseName;
	}
	/**
	 * Modify the name of the course.
	 * @param courseName The new name of the course.
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * Retrieves the course ID of the course.
	 * @return The course ID of the course.
	 */
	public String getCourseID() {
		return this.courseID;
	}
	/**
	 * Modify the course ID of the course.
	 * @param courseID The new course ID of the course.
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	/**
	 * Retrieves the AU value of the course.
	 * @return The AU value of the course.
	 */
	public int getAu() {
		return this.au;
	}
	/**
	 * Modifies the AU value of the course.
	 * @param au The new AU value of the course.
	 */
	public void setAu(int au) {
		this.au = au;
	}
	/**
	 * Retrieves the school offering the course.
	 * @return The school that is offering the course in String.
	 */
	public String getCourseSchool() {
		return courseSchool;
	}
	/**
	 * Modifies the school that is offering the course.
	 * @param courseSchool The new school that is offering the course in String.
	 */
	public void setCourseSchool(String courseSchool) {
		this.courseSchool = courseSchool;
	}
	/**
	 * Retrieves the list of index a course has.
	 * @return The list of index that a course has.
	 */
	public ArrayList<Index> getIndex() {
		return this.index;
	}
	/**
	 * Modifies the list of index a course has.
	 * @param index The new list of index for the course.
	 */
	public void setIndex(ArrayList<Index> index) {
		this.index = index;
	}

//    public static Course findCourse(String courseID) {
//
//        ArrayList<Course> courseList = loadCoursesFile();
//
//        for(Course course: courseList) {
//            if(course.getCourseID().equals(courseID)) {
//                return course;
//            }
//        }
//        System.out.println("Course not found");
//        Course emptyCourse = new Course();
//        return emptyCourse;
//    }
	/**
	 * Function to remove a course based off its course ID
	 * @param courseID Course ID of the course to remove
	 * @return True/False value to tell us if the function is successful.
	 */
	public static boolean removeCourse(String courseID) {
		ArrayList<Course> courseArrayList = loadCoursesFile();

		for (int i = 0; i < courseArrayList.size(); i++) {
			if (courseArrayList.get(i).getCourseID().equals(courseID)) {
				courseArrayList.remove(i);
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
	 * 
	 */
	 @Override public String toString() {
		    StringBuilder result = new StringBuilder();
		    String NL = System.getProperty("line.separator");

		    result.append(this.courseID);

		    return result.toString();
	 }
}
