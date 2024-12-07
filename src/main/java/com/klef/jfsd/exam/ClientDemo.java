package com.klef.jfsd.exam;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {

        
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            
            session.beginTransaction();

            Customer customer1 = new Customer();
            customer1.setName("Sanjana");
            customer1.setEmail("sanjana@gmail.com");
            customer1.setAge(21);
            customer1.setLocation("Vijayawada");

            Customer customer2 = new Customer();
            customer2.setName("Smitha");
            customer2.setEmail("smitha@gmail.com");
            customer2.setAge(25);
            customer2.setLocation("Kurnool");

            Customer customer3 = new Customer();
            customer3.setName("Michael");
            customer3.setEmail("michael@gmail.com");
            customer3.setAge(27);
            customer3.setLocation("Nandigama");

            Customer customer4 = new Customer();
            customer4.setName("Devika");
            customer4.setEmail("devika@gmail.com");
            customer4.setAge(28);
            customer4.setLocation("Himachal Pradesh");

            Customer customer5 = new Customer();
            customer5.setName("Siva");
            customer5.setEmail("siva@gmail.com");
            customer5.setAge(30);
            customer5.setLocation("Gudivada");

            Customer customer6 = new Customer();
            customer6.setName("Renu");
            customer6.setEmail("renu@gmail.com");
            customer6.setAge(22);
            customer6.setLocation("Nellore");

            session.save(customer1);
            session.save(customer2);
            session.save(customer3);
            session.save(customer4);
            session.save(customer5);
            session.save(customer6);

            session.getTransaction().commit();

            
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nCustomers from Gudivada:");
            Criteria criteria1 = session.createCriteria(Customer.class);
            criteria1.add(Restrictions.eq("location", "Gudivada"));
            List<Customer> customers1 = criteria1.list();
            for (Customer c : customers1) {
                System.out.println("Customer: " + c.getName() + ", Location: " + c.getLocation());
            }

            System.out.println("\nCustomers with age > 25:");
            Criteria criteria2 = session.createCriteria(Customer.class);
            criteria2.add(Restrictions.gt("age", 25));
            List<Customer> customers2 = criteria2.list();
            for (Customer c : customers2) {
                System.out.println("Customer: " + c.getName() + ", Age: " + c.getAge());
            }

            System.out.println("\nCustomers with age < 25:");
            Criteria criteria3 = session.createCriteria(Customer.class);
            criteria3.add(Restrictions.lt("age", 25));
            List<Customer> customers3 = criteria3.list();
            for (Customer c : customers3) {
                System.out.println("Customer: " + c.getName() + ", Age: " + c.getAge());
            }

            System.out.println("\nCustomers with age between 25 and 30:");
            Criteria criteria4 = session.createCriteria(Customer.class);
            criteria4.add(Restrictions.between("age", 25, 30));
            List<Customer> customers4 = criteria4.list();
            for (Customer c : customers4) {
                System.out.println("Customer: " + c.getName() + ", Age: " + c.getAge());
            }

            System.out.println("\nCustomers with name starting with 'S':");
            Criteria criteria5 = session.createCriteria(Customer.class);
            criteria5.add(Restrictions.like("name", "S%"));
            List<Customer> customers5 = criteria5.list();
            for (Customer c : customers5) {
                System.out.println("Customer: " + c.getName());
            }

            session.getTransaction().commit();

        } finally {
        	 session.close();
            factory.close();
        }
    }
}
