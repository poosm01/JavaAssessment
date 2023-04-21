package com.generation.service;

import com.generation.model.Course;
import com.generation.model.EnrolledCourse;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService
{
    private final HashMap<String, Student> students = new HashMap<>();

    public void registerStudent( Student student )
    {
        //TODO Add new student to the students hashmap
        students.put(student.getId(), student);

    }

    public Student findStudent( String studentId )
    {
        //TODO Find the student from the Hashmap with the student id
        return students.get(studentId);
    }

    public void enrollToCourse( String studentId, Course course )
    {
        //TODO check if students hashmap contains the studentsId, if not enroll student to the course
        Student student = findStudent(studentId);
        if (student != null) {
            student.enrollToCourse(course);
        }

    }


    public void showSummary()
    {
        //TODO Loop through students hashmap and print out students' details including the enrolled courses
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            System.out.println("Student ID: " + entry.getKey());
            System.out.println("Name: " + entry.getValue().getName());
            System.out.println("Enrolled courses: " + entry.getValue().getEnrolledCourses().keySet());
            System.out.println();
        }
    }


    public HashMap<String, EnrolledCourse> enrolledCourses(Student student)
    {
        //TODO return a HashMap of all the enrolledCourses

        return student.getEnrolledCourses();
    }

    public Course findEnrolledCourse( Student student, String courseId )
    {
        //TODO return the course enrolled by the student from the course Id
        EnrolledCourse enrolledCourse = student.getEnrolledCourses().get(courseId);
        if (enrolledCourse != null) {
            return enrolledCourse;
        }
        return null;
    }

    public static void gradeStudent(Student student, String course, double grade) {
        student.gradeCourse(course ,grade);
    }



    public HashMap<String, EnrolledCourse> getPassedCourses(Student student) {
        return student.findPassedCourses();
    }
}
