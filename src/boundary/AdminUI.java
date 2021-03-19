package boundary;

import control.AdminManager;
import control.CourseManager;
import control.StudentManager;
import entity.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is responsible for handing the user interactions between the admin and the application to perform the necessary admin tasks
 *
 * @author Wong Chin Hao
 * @version 1.2
 * @since 2020/10/20
 */
public class AdminUI {

    /* To load Course */
    static CourseManager cmngr = new CourseManager();
    static StudentManager stmngr = new StudentManager();

    /**
     * runs the admin ui for user to interacts with the application and choice the necessary options
     */
    public static void mainAdminUI() {
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        boolean validInput = false;

        do {
            System.out.println();
            System.out.println("Welcome, Admin");
            System.out.println("What do you want to do? ");
            System.out.println("1. Add a new course");
            System.out.println("2. Add a new index group");
            System.out.println("3. Update existing course");
            System.out.println("4. Check vacancy for an existing index group");
            System.out.println("5. Add a new student");
            System.out.println("6. Edit student access periods");
            System.out.println("7. Print list of students by index group number");
            System.out.println("8. Print list of students by course");
            System.out.println("9. Logout");

            do {
                try {
                    choice = sc.nextInt();
                    sc.nextLine();
                    if (choice >= 1) {
                        validInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Enter a valid integer!");
                    sc.nextLine();
                }
            } while (!validInput);

            switch (choice) {
                case 1:
                    System.out.println("1. Add a new course");
                    addCourseUI(sc);
                    break;
                case 2:
                    System.out.println("2. Add a new index group");
                    addIndexUI(sc, null);
                    break;
                case 3:
                    System.out.println("3. Update existing course");
                    updateCourseUI(sc);
                    break;
                case 4:
                    System.out.println("4. Check vacancy for an existing index group");
                    checkVacancyUI(sc);
                    break;
                case 5:
                    System.out.println("5. Add a new student");
                    addStudentUI(sc);
                    break;
                case 6:
                    System.out.println("6. Edit student access periods");
                    updateAccessPeriodUI(sc);
                    break;
                case 7:
                    System.out.println("7. Print list of students by index group number");
                    printIndexStudentListUI(sc);
                    break;
                case 8:
                    System.out.println("8. Print list of students by course");
                    printCourseStudentListUI(sc);
                    break;
                default:
                    System.out.println();
                    break;
            }
        } while (choice > 0 && choice < 9);

    }

    /* Admin UI Course Methods */

    /**
     * UI to handles the adding of course operation done by admin, adding of index
     * to course will be handled by addIndexUI. IndexList will be null in this operation
     *
     * @param sc Scanner to read the admin input
     */
    public static void addCourseUI(Scanner sc) {
        // Get necessary input from the users: name, id, au and index
        System.out.println("Enter the new course's ID: ");
        String courseID = sc.nextLine().toUpperCase();

        System.out.println("Enter the new course's name: ");
        String courseName = sc.nextLine();

        System.out.println("Enter the new course's school: ");
        String courseSchool = sc.nextLine().toUpperCase();

        boolean validInput = false;
        int courseAu = 0;

        do {
            try {
                System.out.println("Enter the new course's AU: ");
                courseAu = sc.nextInt();
                sc.nextLine();
                validInput = true;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Invalid input. Please input integers.");
                sc.nextLine();
            }
        } while (!validInput);

        ArrayList<Index> indexArrayList = new ArrayList<Index>();

        // load variable to course object >>> set index to null for now >>> call add index later
        Course newCourse = new Course(courseID, courseName, courseSchool, courseAu, indexArrayList);

        AdminManager.addNewCourse(newCourse);
    }

    /**
     * To add a new index object to the existing course
     * @param sc Scanner to read the admin input
     * @param courseID to get the course ID for the add index
     */
    public static void addIndexUI(Scanner sc, String courseID) {
        // Get necessary input from the users: name, id, au and index
        // if courseID is null or else use the pass in value
        if (courseID == null) {
            System.out.println("Enter the course's ID for the Index Group: ");
            courseID = sc.nextLine().toUpperCase();
        }

		ArrayList<Index> courseIndex = CourseManager.findCourseObject(courseID).getIndex();
		if (courseIndex.size() > 0) {
			System.out.println("\nThe current index ID are as following: ");
			for (int i = 0; i < courseIndex.size(); i++) {
				System.out.println(courseIndex.get(i).getIndexID());
			}
			System.out.println();
		}

        System.out.println("Enter the new Index ID: ");
        String indexID = sc.nextLine();

        boolean validInput = false;
        int totalSize = 0;

        do {
            try {
                System.out.println("Enter the total size of the Index Group: ");
                totalSize = sc.nextInt();
                sc.nextLine();
                validInput = true;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Invalid input. Please input integers!");
                sc.nextLine();
            }
        } while (!validInput);


        // call add lesson UI
        ArrayList<Lesson> lessonArrayList = createLessonUI(sc);

        // load variable to index object
        Index newIndex = new Index(indexID, totalSize, lessonArrayList);

        AdminManager.addNewIndex(courseID, newIndex);
    }

    /**
     * to create a form for user to create an array list of lesson
     * Mainly used together with add index
     *
     * @param sc Scanner to read the admin input
     * @return an array list of lesson that was inputted by the user
     */
    public static ArrayList<Lesson> createLessonUI(Scanner sc) {
        // create empty array to store
        ArrayList<Lesson> lessonArrayList = new ArrayList<Lesson>();

        System.out.println("Enter the number of lessons (Lecture+Tutorial+Lab) for the Index Group: ");
        int numberOfLessons = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numberOfLessons; i++) {
            // get lesson type
            System.out.println("\nEnter the lesson's type in term of: \n1. Lecture \n2. Tutorial \n3. Lab: ");
            boolean checkInput = false;
            String strLessonType;
            int intLessonType = 0;
            do {
                try {
                    intLessonType = sc.nextInt();
                    sc.nextLine();
                    if (intLessonType > 0 && intLessonType < 4) {
                        checkInput = true;
                    }
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Please enter within the correct range!");
                    sc.nextLine();
                }
            } while (!checkInput);
            if (intLessonType == 1) {
                strLessonType = "Lecture";
            } else if (intLessonType == 2) {
                strLessonType = "Tutorial";
            } else {
                strLessonType = "Laboratory";
            }

            System.out.println("Enter the Group Name: ");
            String groupname = sc.nextLine();

            boolean validInput = false;
            int startTime = 0;
            int duration = 0;
            int lessonDay = 0;

            do{
            	try{
					System.out.println("Enter the lesson's start time: ");
					startTime = sc.nextInt();
					sc.nextLine();
					validInput = true;
				} catch(InputMismatchException inputMismatchException){
            		System.out.println("Invalid input. Please input integers only.");
            		sc.nextLine();
				}
			}while(!validInput);

			validInput = false;
            do{
            	try{
					System.out.println("Enter the lesson's duration: ");
					duration = sc.nextInt();
					sc.nextLine();
					validInput = true;
				}catch(InputMismatchException inputMismatchException){
					System.out.println("Invalid input. Please input integers only.");
					sc.nextLine();
				}
			}while(!validInput);

			validInput = false;
			do{
				try{
					// get lesson day
					System.out.println("\nEnter the lesson's day in term of: \n0. Monday \n1. Tuesday \n2. Wednesday:"
							+ "\n3. Thursday \n4. Friday \n5. Saturday ");
					lessonDay = sc.nextInt();
					sc.nextLine();
					validInput = true;
				}catch(InputMismatchException inputMismatchException){
					System.out.println("Invalid input. Please input integers only.");
					sc.nextLine();
				}
			}while(!validInput);
            // venue
            Lesson lesson = new Lesson(strLessonType, groupname, startTime, duration, lessonDay);
            lessonArrayList.add(lesson);
        }
        return lessonArrayList;
    }

    /**
     * UI to handle the updating of an existing course
     * Admin can update a course ID,
     *
     * @param sc Scanner to read the admin input
     */
    public static void updateCourseUI(Scanner sc) {
        // initialise
        Course updateCourse = new Course();

        System.out.println("Enter the Course ID that you wish to update: ");
        String courseID = sc.nextLine().toUpperCase();

        if (CourseManager.findCourseObject(courseID).getCourseID() != null) {
            updateCourse = CourseManager.findCourseObject(courseID);
            ArrayList<Index> updateCourseIndex = updateCourse.getIndex();
            System.out.println("Course record found! Which of the following do you wish to update?: ");
            System.out.println("1. Update Course's ID");
            System.out.println("2. Update Course's Name");
            System.out.println("3. Update Course's au");
            System.out.println("4. Update Course's School");
            System.out.println("5. Add Index's Number");
            System.out.println("6. Update Index's Number");
            System.out.println("7. Update Index's Vacancy");
            System.out.println("8. Go back to main menu");

            int choice = 0;
            do {
                try {
                    choice = sc.nextInt();
                    if (choice > 0 && choice < 9) {
                        sc.nextLine();
                        break;
                    } else {
                        System.out.println("Enter a valid range!");
                        sc.nextLine();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Enter a valid integer!");
                    sc.nextLine();
                }
            } while (true);
            switch (choice) {
                case 1:
                    System.out.println("Current Course's ID: " + updateCourse.getCourseID());
                    System.out.println("Enter the new Course's ID: ");
                    String updateCourseID = sc.nextLine().toUpperCase();
                    if (CourseManager.findCourseObject(updateCourseID).getCourseID() == null) {
                        updateCourse.setCourseID(updateCourseID);
                        if (AdminManager.updateCourse(updateCourse)) {
                            System.out.println("Course's ID updated. Returning to the main UI....\n");
                        } else {
                            System.out.println("Error updating Course's ID, Please contact IT administrator. Returning to main UI....\n");
                        }
                    } else {
                        System.out.println("new Course's ID exists. Returning to the main UI....\n");
                    }
                    break;
                case 2:
                    System.out.println("Current Course's name: " + updateCourse.getCourseName());
                    System.out.println("Enter the new Course's name: ");
                    String updateCourseName = sc.nextLine();
                    updateCourse.setCourseName(updateCourseName);
                    if (AdminManager.updateCourse(updateCourse)) {
                        System.out.println("Course's name updated. Returning to main UI....\n");
                    } else {
                        System.out.println(
                                "Error updating Course's name, Please contact IT administrator. Returning to main UI....\n");
                    }
                    break;
                case 3:
                    System.out.println("Current Course's AU Credits");
                    System.out.println("Enter the new Course's AU Credits");
                    int updateAUCredits = 0;
                    do {
                        try {
                            updateAUCredits = sc.nextInt();
                            if (updateAUCredits > 0) {
                                sc.nextLine();
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Enter a valid integer!");
                            sc.nextLine();
                        }
                    } while (true);
                    updateCourse.setAu(updateAUCredits);
                    if (AdminManager.updateCourse(updateCourse)) {
                        System.out.println("Course's AU updated. Returning to main UI....\n");
                    } else {
                        System.out.println(
                                "Error updating Course's AU, Please contact IT administrator. Returning to main UI....\n");
                    }
                case 4:
                    System.out.println("Current Course's School: " + updateCourse.getCourseSchool());
                    System.out.println("Enter the new Course's School: ");
                    String updateCourseSchool = sc.nextLine().toUpperCase();
                    updateCourse.setCourseSchool(updateCourseSchool);
                    if (AdminManager.updateCourse(updateCourse)) {
                        System.out.println("Course's School updated. Returning to the main UI....\n");
                    } else {
                        System.out.println(
                                "Error updating Course's School, Please contact IT administrator. Returning to main UI....\n");
                    }
                    break;
                case 5:
                    addIndexUI(sc, courseID);
                case 6:
                    // update index number
                    System.out.println("\nThe index ID are as following: ");
                    for (int i = 0; i < updateCourseIndex.size(); i++) {
                        System.out.println(updateCourseIndex.get(i).getIndexID());
                    }
                    System.out.println();
                    System.out.println("Enter the index's ID to update: ");
                    String indexID = sc.nextLine();

                    System.out.println("Enter the new index's ID: ");
                    String updateIndexID = sc.nextLine();

                    if (CourseManager.findIndex(courseID, updateIndexID).getIndexID() == null) {
                        Index updateIndex = CourseManager.findIndex(courseID, indexID);

                        updateIndex.setIndexID(updateIndexID);

                        if (AdminManager.updateIndex(updateIndex, courseID, indexID)) {
                            System.out.println("Index's ID updated. Returning to the main UI....\n");
                        } else {
                            System.out.println(
                                    "Error updating Index's ID, Please contact IT administrator. Returning to main UI....\n");
                        }
                    } else {
                        System.out.println("index ID exists! Returning to the main UI....");
                    }

                    break;
                case 7:
                    // update index number vacancy
                    for (int i = 0; i < updateCourseIndex.size(); i++) {
                        System.out.println(updateCourseIndex.get(i).getIndexID());
                    }
                    System.out.println("Enter the index's ID to update the vacancy: ");
                    String indexID2 = sc.nextLine();

                    System.out.println(
                            "Existing total vacancy " + CourseManager.findIndex(courseID, indexID2).getTotalSize());
                    System.out.println("Enter the new vacancy for " + indexID2 + " : ");
                    int updateIndexTotalSize = sc.nextInt();
                    sc.nextLine();

                    Index updateIndex2 = CourseManager.findIndex(courseID, indexID2);
                    updateIndex2.setTotalSize(updateIndexTotalSize);

                    if (AdminManager.updateIndex(updateIndex2, courseID, indexID2)) {
                        System.out.println("Index's vacancy updated. Returning to the main UI....\n");
                    } else {
                        System.out.println(
                                "Error updating Index's vacancy, Please contact IT administrator. Returning to main UI....\n");
                    }

                    break;
                default:
                    System.out.println("Returning to main UI....\n");
                    break;
            }
        } else {
            System.out.println("Course ID does not exist. Returning to main UI....\n");
        }
    }

    /**
     * To create a form for user to add a new student to MySTARS application
     *
     * @param sc Scanner to read the admin input
     */
    public static void addStudentUI(Scanner sc) {

        // get input from the user
        try {
            System.out.print("Enter the new student's name: ");
            String studentName = sc.nextLine();

            System.out.print("Enter the new student's matriculation number: ");
            String matriculationNumber = sc.nextLine();

            System.out.print("Enter the new student's nationality: ");
            String nationality = sc.nextLine();

            System.out.print("Enter the new student's major: ");
            String major = sc.nextLine();

            boolean validInput = false;
            char gender;
            do{
				System.out.print("Enter the new student's gender(M/F): ");
				gender = sc.next().toUpperCase().charAt(0);
            	if(gender == 'M' || gender == 'F'){
            		validInput = true;
				}
            	else{
            	    System.out.println("Please input M or F only.");
                }
			}while(!validInput);

            int yearOfStudy = 0;
            validInput = false;

            do {
                try {
                    System.out.print("Enter the new student's year of study: ");
                    yearOfStudy = sc.nextInt();
                    sc.nextLine();
                    validInput = true;
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Invalid input. Please input integers.");
                    sc.nextLine();
                }
            } while (!validInput);

            System.out.print("Enter the new student's loginID: ");
            String loginId = sc.nextLine();

            System.out.print("Enter the new student's loginPW: ");
            String loginPW = sc.nextLine();
            loginPW = User.hashString(loginPW);

            System.out.print("Enter the new student's email: ");
            String email = sc.nextLine();

            // insert object into the file
            Student newStudent = new Student(studentName, matriculationNumber, nationality, major, gender, yearOfStudy,
                    loginId, loginPW, email);

            AdminManager.addNewStudent(newStudent);
        } catch (InputMismatchException inputMismatchexception) {
            System.out.println("invalid input. Returning to main UI.");
        }
    }

    /**
     * To get the course ID and Index ID to print a list of students enrolled in a specific index group
     *
     * @param sc Scanner to read the admin input
     */
    public static void printIndexStudentListUI(Scanner sc) {
        System.out.print("Enter the index's Course ID: ");
        String courseID = sc.nextLine().toUpperCase();

        ArrayList<Index> courseIndex = CourseManager.findCourseObject(courseID).getIndex();
        if (courseIndex.size() > 0) {
            System.out.println("\nThe index ID are as following: ");
            for (int i = 0; i < courseIndex.size(); i++) {
                System.out.println(courseIndex.get(i).getIndexID());
            }
            System.out.println();
        }

        System.out.print("Enter the index ID: ");
        String indexID = sc.nextLine();

        AdminManager.printIndexStudentList(courseID, indexID);
    }

    /**
     * To get the course ID to print a list of students enrolled in a specific course
     *
     * @param sc Scanner to read the admin input
     */
    public static void printCourseStudentListUI(Scanner sc) {
        System.out.print("Enter the Course ID: ");
        String courseID = sc.nextLine().toUpperCase();

        AdminManager.printCourseStudentList(courseID);
    }

    /**
     * To get a student matriculation number, start time and end time access period in order to update their access period
     *
     * @param sc Scanner to read the admin input
     */
    public static void updateAccessPeriodUI(Scanner sc) {
        System.out.print("Enter the Student's Matriculation Number: ");
        String matriculationNumber = sc.nextLine();

        System.out.println("Enter the start of the access period (yyyy/MM/dd): ");
        String startTime = sc.nextLine();

        System.out.println("Enter the end of the access period (yyyy/MM/dd): ");
        String endTime = sc.nextLine();

        AdminManager.updateAccessPeriod(matriculationNumber, startTime, endTime);
    }

    /**
     * To get the course ID and Index ID to check the vacancy of that index group
     *
     * @param sc Scanner to read the admin input
     */
    public static void checkVacancyUI(Scanner sc) {
        System.out.println("Enter the course Code for the index that you want to check vacancy: ");
        String courseID = sc.nextLine();

        ArrayList<Index> courseIndex = CourseManager.findCourseObject(courseID).getIndex();
        if (courseIndex.size() > 0) {
            System.out.println("\nThe index ID are as following: ");
            for (int i = 0; i < courseIndex.size(); i++) {
                System.out.println(courseIndex.get(i).getIndexID());
            }
            System.out.println();
        }

        System.out.println("Enter the index Code that you want to check vacancy: ");
        String indexID = sc.nextLine();

        AdminManager.checkVacancy(courseID, indexID);
    }
    /* End of Admin UI Course Methods */
}
