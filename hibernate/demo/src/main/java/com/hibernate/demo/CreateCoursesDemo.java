package com.hibernate.demo;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
    public static void main(String[] args) {
        //creating session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //associating the objects
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, 3);

            Course tempCourse1 = new Course("Maths");

            instructor.addCourse(tempCourse1);
            session.save(tempCourse1);
            session.getTransaction().commit();
            System.out.println("Done.");
        } finally {
            //resolve the leak detection
            session.close();
            factory.close();
        }
    }
}
