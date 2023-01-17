package com.hibernate.demo;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class CreateDemo {
    public static void main(String[] args) {
        //creating session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Instructor tempInstructor = new Instructor("John", "daddam", "dad.aniket@gmail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("dad", "Anadime");
            //associating the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            session.beginTransaction();

            System.out.println("Saving Instructor: " + tempInstructor);
            session.persist(tempInstructor);
            session.getTransaction().commit();
            System.out.println("Done.");
        } finally {
            //resolve the leak detection
            session.close();
            factory.close();
        }
    }
}
