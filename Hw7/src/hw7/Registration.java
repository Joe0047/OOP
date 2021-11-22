package hw7;

public class Registration {
	private CourseSection courseSection;
	private Student student;

	// Registration needs courseSectio and student
	Registration(CourseSection courseSection, Student student) {
		this.courseSection = courseSection;
		this.courseSection.addToRegistrationList(this);
		this.student = student;
		this.student.addToSchedule(this);
	}

	// Show the information of registration
	public void showInfoOfRegistration() {
		System.out.println("********************");
		System.out.println("Info of this Registration:");
		System.out.println("Course section name is: " + this.courseSection.getCourse().getCourseName());
		System.out.println("Student is: " + this.student.getStudentName());
	}
}
