package com.udemy.hibernatedemocode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.udemy.hibernatedemo.entity.Instructor;
import com.udemy.hibernatedemo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// start a transaction			
			session.beginTransaction();
			
			// get the instructor detail object
			int theId = 6;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, + theId); 
			
			// print the instructor detail
			System.out.println("tempInstructorDetail:" + tempInstructorDetail);
			// print the associated instructor
			System.out.println("The associated Instructor" + tempInstructorDetail.getInstructor());
			// deleting the instructor detail
			System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);
			// remove the associated object reference
			// break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(tempInstructorDetail);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();
			factory.close();
		}
	}

}
