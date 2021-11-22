package hw7;

public class Main {

	public static void main(String[] args) {
		// Create course
		Course c1 = new Course("C language", null, null, 100);
		Course c2 = new Course("C++ language", null, null, 100);
		Course c3 = new Course("Oriented Object Programming", c1, c2, 40);

		// Create course section
		CourseSection cs1 = new CourseSection(c3);

		// New a student
		Student s1 = new Student("Harden");
		s1.addToPassedCourse(c2);

		// Create two threads
		Thread thread1 = new Thread(new ThreadRegistration(cs1, s1, 1));
		Thread thread2 = new Thread(new ThreadRegistration(cs1, s1, 0));

		// Start threads
		thread1.start();
		thread2.start();

		// Can not continue until the threads are finished
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Look all registrations of course section and student
		cs1.getAllRegistrationOfCourseSection();
		s1.getAllScheduleOfStudent();

	}

}
