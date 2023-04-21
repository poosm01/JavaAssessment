package com.generation.java;

import com.generation.model.Course;
import com.generation.model.EnrolledCourse;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
            throws ParseException {
        //  Student student = new Student("111", "Jean Looi", "jean@generation.com", new Date(2001, 01, 01));
        // StudentService studentService = new StudentService();
        //studentService.registerStudent(student);


        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    registerStudent(studentService, scanner);
                    break;
                case 2:
                    findStudent(studentService, scanner);
                    break;
                case 3:
                    gradeStudent(studentService, scanner);
                    break;
                case 4:
                    enrollStudentToCourse(studentService, courseService, scanner);
                    break;
                case 5:
                    showStudentsSummary(studentService, scanner);
                    break;
                case 6:
                    showCoursesSummary(courseService, scanner);
                    break;
                case 7:
                    showPassedCourses(studentService, scanner);
                    break;
            }
        }
        while (option != 8);
    }

    private static void enrollStudentToCourse(StudentService studentService, CourseService courseService,
                                              Scanner scanner) {
        System.out.println("Insert student ID");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("Invalid Student ID");
            return;
        }
        System.out.println(student);
        System.out.println("Insert course ID");
        String courseId = scanner.next();
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("Invalid Course ID");
            return;
        }
        System.out.println(course);
        studentService.enrollToCourse(studentId, course);
        System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId);

    }

    private static void showCoursesSummary(CourseService courseService, Scanner scanner) {
        courseService.showSummary();
    }

    private static void showStudentsSummary(StudentService studentService, Scanner scanner) {
        studentService.showSummary();
    }

    private static void gradeStudent(StudentService studentService, Scanner scanner) {
        //1) Get the Student Object / e.g Student 111 - from studentService object
        //2) Get all the enrolledCourses for that student 111 - studentService object
        // HashMap<String, EnrolledCourse> getEnrolledCourse = studentService.getEnrolledCourses();
        // For Loop to loop through getEnrolledCourse and print out
        //3) allow user to enter the course code (find the enrolledCourse object - use findEnrolledCourse( student, courseId ))
        //4) allow user to enter the grade
        //5) studentService.gradeStudent(student, course, grade)
        Student student = getStudentInformation(studentService, scanner);
        if (student == null) {
            return;
        }
        // Get all the enrolled courses for the student
        student.getEnrolledCourses();
        //HashMap<String, EnrolledCourse> enrolledCourses = studentService.getEnrolledCourses(student);
        if (student.getEnrolledCourses().isEmpty()) {
            System.out.println("The student has no enrolled courses.");
            return;
        }
        //TODO Loop through the student enrolled courses, and use the scanner object to get the course ID to insert
        // the course grade

        System.out.println("Enrolled courses:");
        String courseCode = scanner.next();
                for (EnrolledCourse course : student.getEnrolledCourses().values()) {
            System.out.println(course.getCode());
        }
        System.out.print("Insert grade (1-6): ");
        int grade = scanner.nextInt();
        // Update the grade for the enrolled course
       // studentService.gradeStudent(student, course.getCode(), grade);
        studentService.gradeStudent(student, courseCode, grade);

        System.out.println("Grades updated successfully!");
    }


    private static Student getStudentInformation(StudentService studentService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found");
        }
        return student;
    }

    private static void findStudent(StudentService studentService, Scanner scanner) {
        Student student = getStudentInformation(studentService, scanner);
        if (student != null) {
            System.out.println("Student Found: ");
            System.out.println(student);
        }
    }

    private static void registerStudent(StudentService studentService, Scanner scanner) throws ParseException {
        Student student = PrinterHelper.createStudentMenu(scanner);
        studentService.registerStudent(student);
    }

    private static void showPassedCourses(StudentService studentService, Scanner scanner) {
        //TODO Loop through the student enrolled courses, and show all the passed courses
        Student student = getStudentInformation(studentService, scanner);
        if (student != null) {
            // EnrolledCourse[] enrolledCourses = student.getEnrolledCourses();
            boolean hasPassedCourses = false;
            for (EnrolledCourse enrolledCourse : student.getEnrolledCourses().values()) {
                if (enrolledCourse.getGrade() >= 4) {
                    hasPassedCourses = true;
                    System.out.println(enrolledCourse.getCode() + " - " + enrolledCourse.getName() + " - Grade: " + enrolledCourse.getGrade());
                }
            }
            if (!hasPassedCourses) {
                System.out.println("Student has not passed any courses.");
            }
        }
    }
}
