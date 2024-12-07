package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg=new Configuration().configure();
        SessionFactory sf=cfg.buildSessionFactory();
        Session session=sf.openSession();
        insertD(session);
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Department ID to delete: ");
        int deptId=sc.nextInt();
        deleteD(session,deptId);
        session.close();
        sf.close();
        sc.close();
    }

    public static void insertD(Session session) {
        Department d1=new Department();
        d1.setName("CSIT");
        d1.setLocation("Vaddeswaram");
        d1.setHodName("Dr.Amarendra");
        
        Department d2=new Department();
        d2.setName("CSE");
        d2.setLocation("Hyderabad");
        d2.setHodName("Dr.Venkat");
        Transaction tx=session.beginTransaction();
        session.save(d1);
        tx.commit();

        System.out.println("inserted successfully!");
    }

    public static void deleteD(Session session, int deptId) {
        Transaction tx = session.beginTransaction();
        Query<?> query = session.createQuery("DELETE FROM Department WHERE id = :Id");
        query.setParameter("Id", deptId);
        int result = query.executeUpdate();
        tx.commit();
        if (result > 0) {
            System.out.println("deleted successfully!");
        } else {
            System.out.println("No Department found with ID: "+deptId);
        }
    }
}
