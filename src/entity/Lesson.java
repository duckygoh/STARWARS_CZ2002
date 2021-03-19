/**
 * A lesson object containign the attributes of a lesson in a course/index
 * These includes lesson type, Lectures,Tutorial,Lab, as well as time slots and duration.
 * @author Lim Bing Hong, Jasper
 * @version 1.0
 * @since 2020/10/08
 */
package entity;

import java.io.Serializable;

/**
 * Lesson object containing the details of a Lecture/Tutorial/Lab.
 * @author Jasper Lim
 * @version 1.0
 * @since 2020/10/08
 */
public class Lesson implements Serializable {
	/**
	 * Standardization of serialization 
	 */
    private static final long serialVersionUID = 4L;
/**
 * Type of lesson: Lecture, Lab, Tutorial
 */
	private String type;
	/**
	 * Group of lesson: SSP2,FSP3,SS4 etc.
	 */
	private String group;
	/**
	 * Start time slot of the lesson
	 */
	private int startTime;
	/**
	 * Duration of the lesson, in number of time slots taken up per lesson.
	 */
	private int duration;
	/**
	 * Day of the lesson, starting from 0 as monday
	 */
	private int day;

	/**
	 * Lesson constructor
	 * @param type of lesson such as lab, lecture or tutorial
	 * @param group ID such as SSP2, SSP1 FSP2
	 * @param day of the lesson
	 * @param startTime of time lesson
	 * @param duration of the lesson, the number of timeslots taken up
	 */
	public Lesson(String type, String group, int day, int startTime, int duration) {
		this.type = type;
		this.startTime = startTime;
		this.duration = duration;
		this.day = day;
	}

	/**
	 * Get the lesson type
	 * @return the lesson type
	 */
	public String getType() {
		return type;
	}
/**
 * Set lesson type
 * @param type refers to the type of lesson
 */
	public void setType(String type) {
		this.type = type;
	}
/**
 * Get Start time of lesson
 * @return start time slot of lesson
 */
	public int getStartTime() {
		return startTime;
	}
/**
 * Get end time of lesson
 * @param startTime time slot of lesson
 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
/**
 * Get duration of lesson
 * @return duration of thel sson
 */
	public int getDuration() {
		return duration;
	}
/**
 * Set duration of lesson
 * @param duration of the lesson
 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
/**
 * Get day of the lesson
 * @return day of the lesson
 */
	public int getDay() {
		return day;
	}
/**
 * Set day of the lesson
 * @param day of the lesson
 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * Override toString function to print out lesson objects
	 */
	 @Override public String toString() {
		    StringBuilder result = new StringBuilder();
		    String NL = System.getProperty("line.separator");
		    
		    
		    result.append(this.type + NL);
		    result.append(this.group);
		    
		    return result.toString();
	 }
}
