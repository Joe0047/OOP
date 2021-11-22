package hw7;

public class ThreadRegistration implements Runnable {
	private CourseSection courseSection;
	private Student student;

	// if move equals 1, check whether the course is full or not.
	// Otherwise, check whether the student is allowed or not to register the course
	private int move;

	ThreadRegistration(CourseSection courseSection, Student student, int move) {
		this.courseSection = courseSection;
		this.student = student;
		this.move = move;
	}

	@Override
	public void run() {

		if (this.move == 1) {
			// check whether the course is full or not
			this.courseSection.checkFull();

		} else {
			// check whether the student is allowed or not to register the course
			this.courseSection.requestToRegister(this.student);
		}

		// prevent 2 threads modify the key in same time
		this.courseSection.runIntoSemaphore();

		// while loop breaks until the key equals 2
		while (this.courseSection.getKey() < 2) {
			// busy waiting
		}

		if (this.courseSection.getAllowedOrNot() && this.courseSection.getFullOrNot()) {
			// make sure only register once
			if (move == 1) {
				this.courseSection.completeRegistration(this.student);
				System.out.println("Registration succeed!");
			}
		} else {
			System.out.println("Registration failed!!");
		}

		// reset the key to 0
		this.courseSection.resetKey();
	}

}
