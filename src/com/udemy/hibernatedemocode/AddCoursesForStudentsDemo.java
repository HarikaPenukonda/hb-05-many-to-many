package com.udemy.hibernatedemocode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.udemy.hibernatedemo.entity.Course;
import com.udemy.hibernatedemo.entity.Instructor;
import com.udemy.hibernatedemo.entity.InstructorDetail;
import com.udemy.hibernatedemo.entity.Review;
import com.udemy.hibernatedemo.entity.Student;

public class AddCoursesForStudentsDemo {

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
			
			// get the student from DB
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId);
			System.out.println("\n Loaded student: " +tempStudent);
 			// create more courses
			Course tempCourse1 = new Course("Advance Programming Data Structures");
			Course tempCourse2 = new Course("Data Communication");
			Course tempCourse3 = new Course("Algorithm Design");
			// add student to courses
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			tempCourse3.addStudent(tempStudent);
			// save the course
			System.out.println("/n saving the courses...");
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
