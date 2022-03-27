package com.suszkolabs.test;

import com.suszkolabs.entity.Ticket;
import com.suszkolabs.entity.Unit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

@Component
public class Main {

    /*
    @Autowired
    private SessionFactory sf;

    public void runTest(){
        Session session = sf.getCurrentSession();

        session.beginTransaction();

        Unit unit = new Unit("Administration", "We sit here, drink some coffee and pretend we do sth");
        Ticket ticket = new Ticket("Buy some rolls", "Rolls are yummy, so buy them!");

        unit.addTicket(ticket);

        session.save(unit);

        session.getTransaction().commit();

        System.out.println("commited!");
    }

     */

    /*
    public static void main(String[] args) {

        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();

        SessionFactory sf = (SessionFactory) context.getBean("sessionFactory");

        Session session = sf.getCurrentSession();

        session.beginTransaction();

        Unit unit = new Unit("Administration", "We sit here, drink some coffee and pretend we do sth");
        Ticket ticket = new Ticket("Buy some rolls", "Rolls are yummy, so buy them!");

        unit.addTicket(ticket);

        session.save(unit);

        session.getTransaction().commit();

        System.out.println("commited!");
    }

     */
}
