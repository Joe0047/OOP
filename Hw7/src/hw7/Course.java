package hw7;

public class Course {
	private String courseName;
	private Course prereqCourse;
	private Course specialCourse;
	private int max;

	// Course has name, prerequisite course, special course and number of maximum
	Course(String courseName, Course prereqCourse, Course specialCourse, int max) {
		this.courseName = courseName;
		this.prereqCourse = prereqCourse;
		this.specialCourse = specialCourse;
		this.max = max;
	}

	// Return the course name
	public String getCourseName() {
		return this.courseName;
	}

	// Return the number of maximum
	public int getMaximum() {
		return this.max;
	}

	// Return the prerequisite course
	public Course getPrerequisite() {
		return this.prereqCourse;
	}

	// Return the special course
	public Course getSpecial() {
		return this.specialCourse;
	}
}
