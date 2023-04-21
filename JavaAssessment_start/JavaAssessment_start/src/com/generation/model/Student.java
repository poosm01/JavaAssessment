package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Student extends Person
{
    public static final double PASS_MIN_GRADE = 3.0;

    private final HashMap<String, EnrolledCourse> enrolledCourses = new HashMap<>();


    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
    }

    public boolean enrollToCourse( Course course )
    {
        //TODO Check if student has already enrolled to the course, if not add the course to enrolledCourses hashmap
        if (enrolledCourses.containsKey(course.getCode())) {
            System.out.println("The student has already enrolled in this course.");
            return false;
        } else {
            EnrolledCourse enrolledCourse = new EnrolledCourse(course);
            enrolledCourses.put(course.getCode(), enrolledCourse);
            return true;
        }
    }

    public HashMap<String, EnrolledCourse> getEnrolledCourses()
    {
        //TODO return a Hashmap of all the enrolledCourses
        return enrolledCourses;
    }

    public void gradeCourse( String courseCode, double grade )
    {
        //TODO set the grade for te enrolled Course
        if (!enrolledCourses.containsKey(courseCode)) {
            System.out.println("This student is not enrolled in the course with code " + courseCode);
            return;
        }
        EnrolledCourse enrolledCourse = enrolledCourses.get(courseCode);
        enrolledCourse.setGrade(grade);
        System.out.println("Grade for course with code " + courseCode + " has been set to " + grade);
    }

    public Course findCourseById(String courseId) {
        //TODO return a Course from the course Id
        Course course = null;
        for (EnrolledCourse enrolledCourse : enrolledCourses.values()) {
            if (enrolledCourse.getCode().equals(courseId)) {
                course = enrolledCourse;
                break;
            }
        }
        return course;
    }

    public HashMap<String, EnrolledCourse> findPassedCourses()
    {
        //TODO Check the enrolled courses grade and compare to the passing grade

        HashMap<String, EnrolledCourse> passedCourses = new HashMap<>();
        for (EnrolledCourse enrolledCourse : enrolledCourses.values()) {
            if (enrolledCourse.getGrade() >= PASS_MIN_GRADE) {
                passedCourses.put(enrolledCourse.getCode(), enrolledCourse);
            }
        }
        return passedCourses;
    }

    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}
