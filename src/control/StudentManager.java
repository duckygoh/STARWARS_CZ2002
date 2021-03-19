package control;

import entity.Course;
import entity.Index;
import entity.Lesson;
import entity.Student;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
/**
 * Manages the functionalities avaliable to a student
 * @author Jasper Lim
 *
 */
public class StudentManager {

	Student currentStudent = new Student();
	CourseManager cmngr = new CourseManager();
	int studentIndex;
	static ArrayList<Student> listOfStudents = new ArrayList<Student>();

	/**
	 * to load the list of students
	 */
	public StudentManager() {
		listOfStudents = fileManager.loadStudentFile();

	}
	
	/***
	 * Constructor for the StudentManager class
	 * @param loginID This is the login ID of the student
	 */

	public StudentManager(String loginID) {
		listOfStudents = fileManager.loadStudentFile();
		studentIndex = 0;
		for (Student student : listOfStudents) {
			if (student.getLoginID().equals(loginID)) {
				this.currentStudent = student;
				studentIndex = listOfStudents.indexOf(currentStudent);
			}
		}
		//System.out.println("The index is : "+studentIndex);

	}
	/***
	 *  Constructor for the StudentManager class
	 * @param index This is the position of the current logged in student in the list
	 */

	public StudentManager(int index) {
		listOfStudents = fileManager.loadStudentFile();
		studentIndex = index;
		currentStudent = listOfStudents.get(studentIndex);

	}
	/***
	 * 
	 * @return The current student that is logged in now
	 */
	
	public Student findCurrentStudent()
	{
		return this.currentStudent;
	}
	
	/***
	 * This method finds a student based on his matriculation number
	 * @param matriculationNumber This is the matric number of the student
	 * @return Returns the student that is found, empty if student is not found
	 */

	public static Student findStudentObject(String matriculationNumber) {
		for (Student student : listOfStudents) {
			if (student.getMatricNumber().equals(matriculationNumber)) {
				return student;
			}
		}
		System.out.println("Student not found");
		Student emptyStudent = new Student();
		return emptyStudent; // NOTE: STRING DEFAULTS ARE NULL, HENCE TO CHECK IF OBJECT IS EMPTY, CHECK IF A
		// STRING ATTRIBUTE IS NULL
	}
	
	/***
	 * This method saves the stores the current information of all the students into a file
	 */
	public static void saveStudentsFile() {
		
		try {
			fileManager.saveStudentFile(listOfStudents);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/***
	 * This method prints out details of all the students
	 */

	public static void printStudentList() {
		for (Student student : listOfStudents) {
			System.out.println("Student Name: "+ student.getName() + "\nStudent Matriculation Number: " + student.getMatricNumber() + "\nStudent Gender: " + student.getGender()
					+ "\nStudent Nationality: " + student.getNationality() + "\n");
		}
	}
	
	/***
	 * This method returns a list of all the students and their data
	 * @return Returns the list of students
	 */

	public static ArrayList<Student> getListOfStudents() {
		return listOfStudents;
	}
	
	/***
	 * This method updates the current list of students with a new list of students taken in
	 * @param listOfStudents the updated list of students to
	 */

	public static void setListOfStudents(ArrayList<Student> listOfStudents) {
		StudentManager.listOfStudents = listOfStudents;
	}

	/**
	 * Adds the student that is logged in into a course.
	 * @param course The course to that the student wants to enroll in String.
	 * @param indexID The index ID of the course that the student wants to enroll in String.
	 * @return Returns a true/false to let us know if the method is successful.
	 */
	public boolean addCourse(String course, String indexID) {
		boolean waitlist = false;
		System.out.println("Adding Course ID : " + course + " and Index ID : " + indexID);
		// Searching if course exists
		Course newcourse = CourseManager.findCourseObject(course);
		if (newcourse.getCourseID() != null) {
			Index checkindex = CourseManager.findIndex(course, indexID);
			if(checkindex.getIndexID()==null) {
				return false;
			}
			// Add in a check for wait list
			if(CourseManager.checkVacancy(course, indexID)<1) {
				waitlist = true;
				
			}
			ArrayList<Course> tempWaitList = currentStudent.getWaitList();
			if(tempWaitList!=null) {
				for(Course courseinWaitlist : tempWaitList) {
					if (courseinWaitlist.getCourseID().equals(course)) {
						System.out.println("Already in waitlist for this course!");
						return false;
					}
				}
			}
			ArrayList<Course> enrolledCourses = currentStudent.getCourseEnrolled();
			
			if (enrolledCourses != null) {
				for (Course enrolledCourse : enrolledCourses) {
					if (enrolledCourse.getCourseID().equals(course)) {
						System.out.println("Already enrolled in this course!");
						return false;
					}

				}
				
				if(waitlist) {
					
					Index newindex = newcourse.findIndexObject(indexID);
					ArrayList<Index> indexlist = new ArrayList<Index>();
					indexlist.add(newindex);
					newcourse.setIndex(indexlist);
					if(currentStudent.checkClash(newindex)) {
						System.out.println("Timetable clashes!");
						return false;
					}
					try {
						if(!cmngr.addStudentToWaitlist(currentStudent, course, indexID)) {
							System.out.println("Failed to add student to waitlist!");
							return false;
						}
						ArrayList<Course> newwaitlist = currentStudent.getWaitList();
						if(newwaitlist==null) {
							newwaitlist = new ArrayList<Course>();
						}
						newwaitlist.add(newcourse);
						listOfStudents.get(studentIndex).setWaitList(newwaitlist);
						System.out.println("Successfully added student to waitlist!");
						saveStudentsFile();
						return true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				else {
					ArrayList<Index> indexlist = new ArrayList<Index>();
					Index newindex = newcourse.findIndexObject(indexID);
					if(currentStudent.checkClash(newindex)) {
						System.out.println("Timetable clashes!");
						return false;
					}
					if(!cmngr.addStudentToCourse(currentStudent, course, indexID)) {
						System.out.println("Failed to add student to course!");
						return false;
					}
					indexlist.add(newindex);
					newcourse.setIndex(indexlist);
					enrolledCourses.add(newcourse);
					listOfStudents.get(studentIndex).setCourseEnrolled(enrolledCourses);
					saveStudentsFile();
					System.out.println("Successfully added student to course!");
					return true;
				}
			}
			// If no enrolled courses :
			else {
				if(waitlist) {
					Index newindex = newcourse.findIndexObject(indexID);
					if(currentStudent.checkClash(newindex)) {
						System.out.println("Timetable clashes!");
						return false;
					}
					try {
						if(!cmngr.addStudentToWaitlist(currentStudent, course, indexID)) {
							System.out.println("Failed to add student to waitlist!");
							return false;
						}
						
						ArrayList<Index> indexlist = new ArrayList<Index>();
						indexlist.add(newindex);
						newcourse.setIndex(indexlist);
						ArrayList<Course> newwaitlist = currentStudent.getWaitList();
						if(newwaitlist==null) {
							newwaitlist = new ArrayList<Course>();
						}
						newwaitlist.add(newcourse);
						listOfStudents.get(studentIndex).setWaitList(newwaitlist);
						System.out.println("Succcesfully added student to waitlist!");
						saveStudentsFile();
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				else {
					// Add student to Course Object
					Index newindex = newcourse.findIndexObject(indexID);
					if(currentStudent.checkClash(newindex)) {
						System.out.println("Timetable clashes!");
						return false;
					}
					if(!cmngr.addStudentToCourse(currentStudent, course, indexID)) {
						System.out.println("Failed to add student to course!");
						return false;
					}
					// Add Course to Student Object
					enrolledCourses = new ArrayList<Course>();
					ArrayList<Index> indexlist = new ArrayList<Index>();
					indexlist.add(newindex);
					newcourse.setIndex(indexlist);
					enrolledCourses.add(newcourse);
					listOfStudents.get(studentIndex).setCourseEnrolled(enrolledCourses);
					// Save students
					saveStudentsFile();
					System.out.println("Successfully added student to course!");
					return true;
	
				}
			}
		}
		System.out.println("Course does not exist!");
		return false; // temporary value
	}

	/***
	 * This method drops a course from a student of his choosing
	 * @param course The ID of the course
	 * @return Returns a true/false to let us know if the method is successful.
	 */

	public boolean dropCourse(String course) {

		ArrayList<Course> coursesEnrolled = currentStudent.getCourseEnrolled();
		ArrayList<Course> tempWaitlist = currentStudent.getWaitList();
		if (coursesEnrolled != null  || tempWaitlist !=null) {
			Course courseobject = CourseManager.findCourseObject(course);
			if (courseobject != null) {
				//System.out.println("Course ID of dropped course is : " + courseobject.getCourseID());
				
				if(coursesEnrolled!= null) {
					for (Course courses : coursesEnrolled) {
						if (courses.getCourseID().equals(courseobject.getCourseID())) {
							cmngr.removeStudentFromCourse(currentStudent, course);
							coursesEnrolled.remove(courses);
							listOfStudents.get(studentIndex).setCourseEnrolled(coursesEnrolled);
							saveStudentsFile();
							
							checkWaitlist(course,courses.getIndex().get(0).getIndexID());
							System.out.println("Successfully dropped from course!");
							return true;
						}
					}
					System.out.println("Not found in courses");
				}
				else{
					System.out.println("Courses is Null");
				}
				
				if (tempWaitlist !=null) {
					for (Course courses : tempWaitlist) {
						if (courses.getCourseID().equals(courseobject.getCourseID())) {
							// Drop the class and remove student
							String tempIndex = courses.getIndex().get(0).getIndexID();
							cmngr.removeStudentFromWaitlist(course,tempIndex);
							tempWaitlist.remove(tempWaitlist.lastIndexOf(courses));
							listOfStudents.get(studentIndex).setWaitList(tempWaitlist);
							saveStudentsFile();
							System.out.println("Succesfully removed from waitlist!");
							return true;
						}
					}
				}
				System.out.println("You do not have this course!");
				return false;

			} else {
				System.out.println("Invalid course!");
				return false;
			}
		}
		System.out.println("No courses available to drop!");
		return false; // temporary value
	}

	
	/***
	 * This method prints out the courses registered for a student
	 */
	public void printCourseRegistered() {
		// HELLO
		if (currentStudent.getCourseEnrolled() != null) {
			System.out.println("----------------------------------------------------");
			System.out.println("|  Course ID |  Course Name    | Index:             |");
			System.out.println("----------------------------------------------------");
			for (Course Course : currentStudent.getCourseEnrolled()) {
				System.out.format("| %-11s| %-36s| %-11s\n", Course.getCourseID(), Course.getCourseName(),Course.getIndex().get(0).getIndexID());
			}
		} else {
			System.out.println("No Courses registered!");
		}
	}
	/**
	 * This method prints out the vacancies available in a course's index.
	 * @param courseID The course ID of the course that you want to check the index of.
	 * @param indexID The index ID of the index that you want to check the vacancy of.
	 */
	public void printVacanciesAvaliable(String courseID, String indexID) {
		if(cmngr.checkVacancy(courseID, indexID) == -1) {
			System.out.println("Course does not exist!");
		}
		else {
			System.out.println("Vacancies: " + cmngr.checkVacancy(courseID, indexID) + " Slots");
		}
	}

	/**
	 * This method will change the student's index to another index of his choice if there are no clashes with the new index
	 * @param changeCourseID The course ID of the course that the student wants to change the index for
	 * @param changeIndex The index ID of the index that the student wants to change
	 * @return boolean 
	 */
	
	@SuppressWarnings({ "null", "unused" })
	public boolean changeIndex(String changeCourseID, String changeIndex) {
		
			String oldIndexID = "";
			//find the index object using course
			Index indexToChange = new Index();
			ArrayList<Course> courseList = new ArrayList<Course>();
			
			ArrayList<Index> indexList = new ArrayList<Index>();
			
			courseList = CourseManager.getListOfCourses();
			int courseToChange = 0;
			
			
			for(int i = 0; i < currentStudent.getCourseEnrolled().size();i++)
			{
			
				if(changeCourseID.equals(currentStudent.getCourseEnrolled().get(i).getCourseID()))
				{
					courseToChange = i;
					oldIndexID = currentStudent.getCourseEnrolled().get(i).getIndex().get(0).getIndexID();
					break;
				}
	
			}
			
			for(Course course: courseList)
			{
				if(changeCourseID.equals(course.getCourseID()))
				{
					
					indexList = course.getIndex();
					 break;
				}
			}
			
			
			
			for(Index index: indexList)
			{
				if(changeIndex.equals(index.getIndexID()))
				{
					indexToChange = index;
					break;
				}
			}
		
			

	        //check for timeslot here
			if(currentStudent.getCourseEnrolled().size() > 1)
			{
				 if(currentStudent.checkClash(indexToChange) == true)
			        {
			        	
			        	return false;
			        }
			        
			        else
			        {
			        	ArrayList<Index> newIndex = new ArrayList<Index>();
			 	        newIndex.add(indexToChange);
			 	        //get old index id
			 	        
			 	        currentStudent.getCourseEnrolled().get(courseToChange).setIndex(newIndex);
			 	        saveStudentsFile();
			 	        //now update the index for students enrolled
			 	        ArrayList<Course> listOfCourses = CourseManager.getListOfCourses();
			 	        ArrayList<Index> listOfIndex = new ArrayList<Index>();
			 	        for (int j = 0; j < listOfCourses.size(); j++)
			 	        {
			 	        	if(changeCourseID.equals(listOfCourses.get(j).getCourseID()))
			 	        	{
			 	        		
			 	        		for(int k = 0; k <  listOfCourses.get(j).getIndex().size();k++)
			 	        		{
			 	        			if(changeIndex.equals(listOfCourses.get(j).getIndex().get(k).getIndexID()))
			 	        			{
			 	        				listOfCourses.get(j).getIndex().get(k).addStudentToEnrolled(currentStudent);
			 	        				
			 	        			}
			 	        			if(oldIndexID.equals(listOfCourses.get(j).getIndex().get(k).getIndexID())) 
			 	        			{
			 	        				listOfCourses.get(j).getIndex().get(k).removeStudentFromEnrolled(currentStudent);
			 	        			}
			 	        		}
			 	        		CourseManager.setListOfCourses(listOfCourses);
			 	        		CourseManager.saveCoursesFile();
			 	        		break;
			 	        	}
			 	        }       
			 	        return true;
					}
			}
			else
			{
				ArrayList<Index> newIndex = new ArrayList<Index>();
	 	        newIndex.add(indexToChange);
	 	        //get old index id
	 	       // String oldIndexID = currentStudent.getCourseEnrolled().get(courseToChange).getIndex().get(0).getIndexID();
	 	        currentStudent.getCourseEnrolled().get(courseToChange).setIndex(newIndex);
	 	        saveStudentsFile();
	 	        //now update the index for students enrolled
	 	        ArrayList<Index> listOfIndex = new ArrayList<Index>();
	 	        for (int j = 0; j < courseList.size(); j++)
	 	        {
	 	        	if(changeCourseID.equals(courseList.get(j).getCourseID()))
	 	        	{
	 	        		
	 	        		for(int k = 0; k <  courseList.get(j).getIndex().size();k++)
	 	        		{
	 	        			if(changeIndex.equals(courseList.get(j).getIndex().get(k).getIndexID()))
	 	        			{
	 	        				courseList.get(j).getIndex().get(k).addStudentToEnrolled(currentStudent);
	 	        				
	 	        			}
	 	        			if(oldIndexID.equals(courseList.get(j).getIndex().get(k).getIndexID())) 
	 	        			{
	 	        				courseList.get(j).getIndex().get(k).removeStudentFromEnrolled(currentStudent);
	 	        			}
	 	        		}
	 	        		CourseManager.setListOfCourses(courseList);
	 	        		CourseManager.saveCoursesFile();
	 	        		break;
	 	        	}
	 	        }       
	 	        return true;
			}
	}
	
	/***
	 * This method swaps a student's index of a course of his choosing with another student of his choosing
	 * @param swapCourseID The Course ID of the course that the student wants to swap index for
	 * @param swapStudentName The name of the other student the current student wants to swap with
	 * @return boolean Returns a true/false to let us know if the method is successful.
	 */

	public boolean swapIndex(String swapCourseID, String swapStudentName) {
	   
        //find index object using course
		Index indexToSwap = new Index();
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		ArrayList<Index> indexList = new ArrayList<Index>();
		
		ArrayList<Index> currentStudentIndexList = new ArrayList<Index>();
		
		String swapIndex = "";
		
		String newIndex = "";
		
		courseList = CourseManager.getListOfCourses();
		
		int courseToSwap = 0;
		
		for(int j = 0; j < currentStudent.getCourseEnrolled().size(); j++)
		{
			if(swapCourseID.equals(currentStudent.getCourseEnrolled().get(j).getCourseID()))
			{
				courseToSwap = j;
				currentStudentIndexList = currentStudent.getCourseEnrolled().get(j).getIndex();
				swapIndex = currentStudentIndexList.get(0).getIndexID();
				break;
			}
			
		}
		
	
		
		
		for(int i3 = 0; i3 < courseList.size(); i3++)
		{
			if(swapCourseID.equals(courseList.get(i3).getCourseID()))
			{
				
				 indexList = courseList.get(i3).getIndex();
				 break;
			}
	
		}
		
		
		for(int i4 =0; i4 < indexList.size();i4++)
		{
			if(swapIndex.equals(indexList.get(i4).getIndexID()))
			{
				indexToSwap = indexList.get(i4);
				break;
			}
		}
		
		//get other student's index using course and name
		ArrayList<Student> studentList = getListOfStudents();
		Student otherStudent= new Student();
		
		ArrayList<Course> otherStudentCourseList  = new ArrayList<Course>();
		
		for(int i5 = 0; i5 < studentList.size();i5++)
		{
			if(swapStudentName.equals(studentList.get(i5).getName()))
			{
				otherStudent = studentList.get(i5);
				otherStudentCourseList = studentList.get(i5).getCourseEnrolled();
				break;
			}
		}
		//check if other student is taking the same course or not
		//if he is taking same course, get his index
		ArrayList<Index> otherStudentIndex = new ArrayList<Index>();
		int otherStudentCourse = 0;
		boolean checkIfSameCourse = false;
		for(int i6 =0; i6 < otherStudentCourseList.size();i6++)
		
		{
			if(swapCourseID.equals(otherStudentCourseList.get(i6).getCourseID()))
			{
				otherStudentCourse = i6;
				otherStudentIndex = otherStudentCourseList.get(i6).getIndex();
				newIndex = otherStudentIndex.get(0).getIndexID();
				checkIfSameCourse = true;
				break;
			}
			
			else
			{
				checkIfSameCourse = false;
			}
		
			
		}
		if(checkIfSameCourse == false)
		{
			System.out.println(swapStudentName + " is not taking this course!");
			return false;
		}
		
		//check for timeslot and get current student's index object
		
		//System.out.println(currentStudent.getCourseEnrolled().size());
		//System.out.println(otherStudent.checkClash(currentStudent.getCourseEnrolled().get(courseToSwap).getIndex().get(0)));
		
		//if both students have only 1 course
		if( currentStudent.getCourseEnrolled().size() > 1 && otherStudent.getCourseEnrolled().size() > 1 )
		{
			otherStudent.getCourseEnrolled().get(otherStudentCourse).setIndex(currentStudent.getCourseEnrolled().get(courseToSwap).getIndex()); 
			currentStudent.getCourseEnrolled().get(courseToSwap).setIndex(otherStudentIndex);

            saveStudentsFile();
            
            for(int j = 0; j < courseList.size();j++)
            {
            	if(swapCourseID.equals(courseList.get(j).getCourseID()))
            	{
            		for(int k = 0; k < courseList.get(j).getIndex().size(); k++)
            		{
            			if(swapIndex.equals(courseList.get(j).getIndex().get(k).getIndexID()))
            			{
            				courseList.get(j).getIndex().get(k).addStudentToEnrolled(otherStudent);
            				courseList.get(j).getIndex().get(k).removeStudentFromEnrolled(currentStudent);

            			}
            			
            			if(newIndex.equals(courseList.get(j).getIndex().get(k).getIndexID()))
            			{
            				courseList.get(j).getIndex().get(k).addStudentToEnrolled(currentStudent);
            				courseList.get(j).getIndex().get(k).removeStudentFromEnrolled(otherStudent);
            			}
            		
            		}
            		CourseManager.setListOfCourses(courseList);
            		CourseManager.saveCoursesFile();
            		
            		
            	}
        
            }
            
            return true;
			
		}
		// if student only has 1 course and other student does not have clash
		else if (currentStudent.getCourseEnrolled().size() > 1 && otherStudent.checkClash(currentStudent.getCourseEnrolled().get(courseToSwap).getIndex().get(0)) == false )
		{
			otherStudent.getCourseEnrolled().get(otherStudentCourse).setIndex(currentStudent.getCourseEnrolled().get(courseToSwap).getIndex()); 
			currentStudent.getCourseEnrolled().get(courseToSwap).setIndex(otherStudentIndex);

        
        saveStudentsFile();
        
        for(int j = 0; j < courseList.size();j++)
        {
        	if(swapCourseID.equals(courseList.get(j).getCourseID()))
        	{
        		for(int k = 0; k < courseList.get(j).getIndex().size(); k++)
        		{
        			if(swapIndex.equals(courseList.get(j).getIndex().get(k).getIndexID()))
        			{
        				courseList.get(j).getIndex().get(k).addStudentToEnrolled(otherStudent);
        				courseList.get(j).getIndex().get(k).removeStudentFromEnrolled(currentStudent);

        			}
        			
        			if(newIndex.equals(courseList.get(j).getIndex().get(k).getIndexID()))
        			{
        				courseList.get(j).getIndex().get(k).addStudentToEnrolled(currentStudent);
        				courseList.get(j).getIndex().get(k).removeStudentFromEnrolled(otherStudent);
        			}
        		
        		}
        		CourseManager.setListOfCourses(courseList);
        		CourseManager.saveCoursesFile();
        		
        		
        	}
    
        }
        
        return true;
		}
		else
			//other scenarios so must check if both have clash or not
		{
			if(currentStudent.checkClash(otherStudentIndex.get(0)) == true || otherStudent.checkClash(currentStudent.getCourseEnrolled().get(courseToSwap).getIndex().get(0)) == true)
	        {
				System.out.println("Clash in timetable, swap is not possible");
	        	return false;
	        }
			
			else
			{
				otherStudent.getCourseEnrolled().get(otherStudentCourse).setIndex(currentStudent.getCourseEnrolled().get(courseToSwap).getIndex()); 
				currentStudent.getCourseEnrolled().get(courseToSwap).setIndex(otherStudentIndex);

	            
	            saveStudentsFile();
	            
	            for(int j = 0; j < courseList.size();j++)
	            {
	            	if(swapCourseID.equals(courseList.get(j).getCourseID()))
	            	{
	            		for(int k = 0; k < courseList.get(j).getIndex().size(); k++)
	            		{
	            			if(swapIndex.equals(courseList.get(j).getIndex().get(k).getIndexID()))
	            			{
	            				courseList.get(j).getIndex().get(k).addStudentToEnrolled(otherStudent);
	            				courseList.get(j).getIndex().get(k).removeStudentFromEnrolled(currentStudent);

	            			}
	            			
	            			if(newIndex.equals(courseList.get(j).getIndex().get(k).getIndexID()))
	            			{
	            				courseList.get(j).getIndex().get(k).addStudentToEnrolled(currentStudent);
	            				courseList.get(j).getIndex().get(k).removeStudentFromEnrolled(otherStudent);
	            			}
	            		
	            		}
	            		CourseManager.setListOfCourses(courseList);
	            		CourseManager.saveCoursesFile();
	            	
	            		
	            	}
	        
	            }
	            
	            return true;
				
			}
		}
		}
	
	/***
	 * This method prints out the schedule of a student based on the courses and the index he is enrolled in.
	 *
	 */
	
	public void printSchedule() {
		
		currentStudent.populateSchedule();
		
	    try{
	    	Course[][] schedule = this.currentStudent.getSchedule();
	        int rows = schedule.length;
	        int columns = schedule[0].length;
	        String str = "|\tTime\tMon\tTues\tWed\tThurs\tFri\tSat\tSun\t|\n|\t";

	        for(int i=0;i<rows;i++){
	        	
	        	
	        	switch(i) {
	        	
	        	case 0:
	        		str += "0800\t";
	        		break;
	        	case 1:
	        		str += "0830\t";
	        		break;
	        	case 2:
	        		str += "0930\t";
	        		break;
	        	case 3:
	        		str += "1030\t";
	        		break;
	        	case 4:
	        		str += "1130\t";
	        		break;
	        	case 5:
	        		str += "1230\t";
	        		break;
	        	case 6:
	        		str += "1330\t";
	        		break;
	        	case 7:
	        		str += "1430\t";
	        		break;
	        	case 8:
	        		str += "1530\t";
	        		break;
	        	case 9:
	        		str += "1630\t";
	        		break;
	        	case 10:
	        		str += "1730\t";
	        		break;
	        	}
	        	
	        	for(int j=0;j<columns;j++){
	            	if(schedule[i][j] == null) {
	            		str += "\t";
	            	}
	            	else{
	            		str += schedule[i][j] + "\t";
	            	}
	            }

	            System.out.println(str + "|");
	            str = "|\t";
	        }

	    }catch(Exception e){System.out.println("Matrix is empty!!");}
	}
	
	


	/* ------ Admin Related Methods: End ------ */
	
	
	/**
	 *  Method to add student at the top of a waitlist to a course if there is a slot.
	 * @param course Course that needs to be checked.
	 * @param indexID Index ID of the course that needs to update the waitlist.
	 */
	public void checkWaitlist(String course, String indexID) {
		Course updatingcourse = CourseManager.findCourseObject(course);
		Index tempIndex = updatingcourse.findIndexObject(indexID);
		Queue<Student> tempQueue = tempIndex.getWaitlist();
		Student nextstudent = tempQueue.poll();
		if(nextstudent==null) {
			return;
		}
		for(Student student : listOfStudents) {
			if(student.getMatricNumber().equals(nextstudent.getMatricNumber())) {
				StudentManager stmngr = new StudentManager(student.getLoginID());
				if(!stmngr.dropCourse(course)) {
					System.out.println("Failed to drop course!");
					return;
				}
				if(!stmngr.addCourse(course, indexID)) {
					System.out.println("Failed to add student!");
					return;
				}
				try {
					MailManager.sendMail(currentStudent.getEmail(), currentStudent.getName(), indexID, course);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Sending.....ERROR");
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * This method prints the waitlist of the student that is logged in. 
	 */
	
	public void printStudentWaitlist(){
		System.out.println("Courses in waitlist : ");
		for(Course course: currentStudent.getWaitList() ) {
			for(Index index : course.getIndex()) {
				System.out.println("Courses : " + course.getCourseID() + "Index: " + index.getIndexID());
			}
		}
	}
}
