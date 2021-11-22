package hw7;

import java.util.ArrayList;
import java.util.Iterator;

public class Student {
	private String name;

	// The 1-many association to schedule
	private ArrayList<Registration> scheduleList;

	// The 1-many association to passed Course
	private ArrayList<Course> passedCourseList;

	// Student has name, schedule and passed courses
	Student(String name) {
		this.name = name;
		this.scheduleList = new ArrayList<Registration>(0);
		this.passedCourseList = new ArrayList<Course>(0);
	}

	// Return the student name
	public String getStudentName() {
		return this.name;
	}

	// Called within this package only, by the constructor of
	// Registration to ensure the link is bi-directional
	public void addToSchedule(Registration newRegistration) {
		this.scheduleList.add(newRegistration);
	}

	// Add passed courses to student
	public void addToPassedCourse(Course passedCourse) {
		this.passedCourseList.add(passedCourse);
	}

	// Check whether the student has specific course
	// Yes, return true; otherwise, return false
	public boolean hasPassedCourse(Course specificCourse) {
		for (Iterator<Course> iterator = passedCourseList.iterator(); iterator.hasNext();) {
			Course course = (Course) iterator.next();
			if (course == specificCourse) {
				return true;
			}
		}
		return false;
	}

	// Show the student's courses
	public void getAllScheduleOfStudent() {
		Registration scheduleOne;
		System.out.println("=================================");
		System.out.println("Student " + this.getStudentName() + "'s schedule lists:");

		for (Iterator<Registration> iterator = scheduleList.iterator(); iterator.hasNext();) {
			scheduleOne = (Registration) iterator.next();
			scheduleOne.showInfoOfRegistration();
		}
	}
}
