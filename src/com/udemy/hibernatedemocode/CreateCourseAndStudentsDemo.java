package com.udemy.hibernatedemocode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.udemy.hibernatedemo.entity.Course;
import com.udemy.hibernatedemo.entity.Instructor;
import com.udemy.hibernatedemo.entity.InstructorDetail;
import com.udemy.hibernatedemo.entity.Review;
import com.udemy.hibernatedemo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// start a transaction			
			session.beginTransaction();
			
			// create a course
			Course tempCourse = new Course("Machine Learning");
			
			// save the course
			System.out.println("\n saving the course...");
			session.save(tempCourse);
			System.out.println("Saved the course: " +tempCourse);
			
			// create the students
			Student tempStudent1 = new Student("Forsythe","Jones","jonesf1@gmail.com");
			
			// add students to the course
			tempCourse.addStudent(tempStudent1);
			
			// save the students
			System.out.println("/nSaving students...");
			session.save(tempStudent1);
			
			System.out.println("saved students: " + tempCourse.getStudents());
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
