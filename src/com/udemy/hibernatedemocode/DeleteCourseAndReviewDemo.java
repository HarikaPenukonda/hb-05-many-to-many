package com.udemy.hibernatedemocode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.udemy.hibernatedemo.entity.Course;
import com.udemy.hibernatedemo.entity.Instructor;
import com.udemy.hibernatedemo.entity.InstructorDetail;
import com.udemy.hibernatedemo.entity.Review;

public class DeleteCourseAndReviewDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// start a transaction			
			session.beginTransaction();
			
			// get the course
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			// print the course
			System.out.println("Deleting the course...");
			System.out.println(tempCourse);
			// print the course reviews
			System.out.println(tempCourse.getReviews());
			// delete the course
			session.delete(tempCourse);
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
