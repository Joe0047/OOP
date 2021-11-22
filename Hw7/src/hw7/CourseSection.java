package hw7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class CourseSection {
	// The many-1 abstraction-occurrence association
	private Course course;

	// The 1-many association to class Registration
	private ArrayList<Registration> registrationList;

	// A flag that allow student to register the course
	// if allowed, assign true; otherwise, assign false
	private boolean allowedOrNot;

	// A flag that verify course is full or not
	// if not full, assign true; otherwise, assign false
	private boolean fullOrNot;

	// Continue the registration or others when key equals 2
	private int key;

	// Prevent the key is modified by more than 2 threads in same time
	private Semaphore semaphore;

	// CourseSection has course and registrations
	CourseSection(Course course) {
		this.course = course;
		this.registrationList = new ArrayList<Registration>(0);
		this.allowedOrNot = false;
		this.fullOrNot = false;
		this.key = 0;
		// only one semaphore
		this.semaphore = new Semaphore(1);
	}

	// Return the course
	public Course getCourse() {
		return this.course;
	}

	// Return whether the student is allowed to register the course
	public boolean getAllowedOrNot() {
		return this.allowedOrNot;
	}

	// Return whether the course is full or not
	public boolean getFullOrNot() {
		return this.fullOrNot;
	}

	// Return the key
	public int getKey() {
		return this.key;
	}

	// Reset the key to 0
	public void resetKey() {
		this.key = 0;
	}

	// Check whether the student is allowed to register course
	public void requestToRegister(Student student) {
		Course prereq = course.getPrerequisite();
		Course special = course.getSpecial();
		if (student.hasPassedCourse(prereq) || prereq == null) {
			allowedOrNot = true;
		} else if (student.hasPassedCourse(special)) {
			allowedOrNot = true;
		} else {
			allowedOrNot = false;
		}
	}

	// Check whether the course is full
	public void checkFull() {
		if (registrationList.size() < course.getMaximum()) {
			fullOrNot = true;
		} else {
			fullOrNot = false;
		}
	}

	// Only allow one thread to modify the key in same time
	public void runIntoSemaphore() {
		try {
			// get semaphore
			semaphore.acquire();
			this.key = this.key + 1;
			// release semaphore
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Complete the registration
	public void completeRegistration(Student student) {
		// Indirectly calls addToRegistrationList
		new Registration(this, student);
	}

	// Called within this package only, by the constructor of
	// Registration to ensure the link is bi-directional
	public void addToRegistrationList(Registration newRegistration) {
		this.registrationList.add(newRegistration);
	}

	// Show the course's students
	public void getAllRegistrationOfCourseSection() {
		Registration registrationOne;
		System.out.println("=================================");
		System.out.println("Course " + this.course.getCourseName() + "'s registration lists:");

		for (Iterator<Registration> iterator = registrationList.iterator(); iterator.hasNext();) {
			registrationOne = (Registration) iterator.next();
			registrationOne.showInfoOfRegistration();
		}
	}
}
