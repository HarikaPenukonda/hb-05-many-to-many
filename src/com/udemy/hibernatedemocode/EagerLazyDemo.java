package com.udemy.hibernatedemocode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.udemy.hibernatedemo.entity.Course;
import com.udemy.hibernatedemo.entity.Instructor;
import com.udemy.hibernatedemo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// start a transaction			
			session.beginTransaction();
			
			// get the instructor from db
			int theId = 2;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("luv2Code: Instructor: " + tempInstructor);
			
			// get courses for the instructor
			System.out.println("luv2Code: Courses: " + tempInstructor.getCourses());
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("luv2Code: Done!");
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
