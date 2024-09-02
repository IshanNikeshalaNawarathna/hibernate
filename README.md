# My Java EE 7 Project with Hibernate

## Project Overview

This project is a Java EE 7 application that utilizes Hibernate for ORM (Object-Relational Mapping). It is a standard project intended to demonstrate the integration of Hibernate into a Java EE environment.

## Features

- Java EE 7 backend
- Hibernate ORM for database interaction
- JPA for persistence
- RESTful services
- Dependency injection using CDI (Contexts and Dependency Injection)

## Technologies Used

- **Java EE 7**
  - JAX-RS for RESTful services
  - CDI for dependency injection
  - JPA for persistence
- **Hibernate** for ORM
- **Maven** for project management and build

## Prerequisites

- **Java Development Kit (JDK) 8 or later**
- **Apache Maven 3.3.9 or later**
- **Application Server** (e.g., WildFly, GlassFish)
- **Database** (e.g., MySQL, PostgreSQL)

## Setup the Hibernate class file

- **in a config the xml document**

 ```bash

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

       private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}

 ```
## Setup the Hibernate xml file

 ```bash
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
    "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
    "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/yourDatabaseName?useSSL=false</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">yourMysqlPassword</property>
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
        <property name="hibernate.show_sql">true</property>
        <mapping class="model.User" />
    </session-factory>
</hibernate-configuration>

 ```

## Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/IshanNikeshalaNawarathna/hibernate.git

## Use to library 
 
   
[Hibernate lib.zip](https://github.com/user-attachments/files/16824021/Hibernate.lib.zip)

## How to INSERT to data in Hibernate

 ```bash
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


@WebServlet(name = "saveUser", urlPatterns = {"/saveUser"})
public class saveUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        User user = new User();
        user.setName("Ishan Nikeshala");
        user.setMobile("0767235819");

        session.save(user);
        session.beginTransaction().commit();
        
        session.close();

    }

}

 ```
## How to create UserBean
 ```bash
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "mobile", length = 10, nullable = false)
    private String mobile;
    
    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}

 ```
## How to UPDATE to data in Hibernate
 ```bash

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@WebServlet(name = "updateUser", urlPatterns = {"/updateUser"})
public class updateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        User user = (User) session.load(User.class, 1);

        user.setName("KASUN");
        session.update(user);
        session.beginTransaction().commit();

        session.close();

    }
}

 ```
## How to DELETE to data in Hibernate
 ```bash

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@WebServlet(name = "deleteUser", urlPatterns = {"/deleteUser"})
public class deleteUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        User user = (User) session.load(User.class, 1);
        session.delete(user);
        session.beginTransaction().commit();

        session.close();

    }
}


 ```

## How to SEARCH to data in Hibernate
 ```bash
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@WebServlet(name = "searchUser", urlPatterns = {"/searchUser"})
public class searchUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        User user = (User) session.load(User.class, 2);
        response.getWriter().write(user.getId());
        response.getWriter().write(user.getName());
        response.getWriter().write(user.getMobile());

    }

}

 ```
## How to CRITERIA SEARCH to data in Hibernate
 ```bash


package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@WebServlet(name = "CriteriaSearch", urlPatterns = {"/CriteriaSearch"})
public class CriteriaSearch extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        Criteria criteria = session.createCriteria(User.class);
        ArrayList<User> arrayList = (ArrayList<User>) criteria.list();
        
        for (User user : arrayList) {
            response.getWriter().write(user.getName());
        }
        
        session.close();
        
    }
    
}


 ```
## How to RESTRICTIONS SEARCH to data in Hibernate
 ```bash

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


@WebServlet(name = "RestrictionsSearch", urlPatterns = {"/RestrictionsSearch"})
public class RestrictionsSearch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(User.class);

        criteria.add(Restrictions.eq("name", "Ishan Nikeshala"));
        criteria.add(Restrictions.eq("mobile", "0767235819"));

        ArrayList<User> arrayList = (ArrayList<User>) criteria.list();

        for (User user : arrayList) {
            response.getWriter().write(user.getName());
        }

        session.close();

    }

}


 ```
## How to RESTRICTIONS LIKE SEARCH to data in Hibernate
 ```bash

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


@WebServlet(name = "RestrictionsLikeSearch", urlPatterns = {"/RestrictionsLikeSearch"})
public class RestrictionsLikeSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(User.class);

        criteria.add(Restrictions.like("mobile", "076", MatchMode.START));

        ArrayList<User> arrayList = (ArrayList<User>) criteria.list();

        for (User user : arrayList) {
            response.getWriter().write(user.getName());
        }

        session.close();

    }

}


 ```
## How to JOIN TABLE SEARCH to data in Hibernate
 ```bash

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


@WebServlet(name = "JoinTableSearch", urlPatterns = {"/JoinTableSearch"})
public class JoinTableSearch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(User.class);

        ArrayList<User> arrayList = (ArrayList<User>) criteria.list();

        for (User user : arrayList) {  
//            response.getWriter().write(user.getName()); this line is comment. and use to line uncomment.
//            response.getWriter().write(user.getCountry().getCountry_name()); this line is comment. and use to line uncomment.
            System.out.println(user.getName());
            System.out.println(user.getCountry().getCountry_name());
        }

        session.close();

    }

}

 ```
## How to create UserBean
 ```bash

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "country_name",length = 45,nullable = false)
    private String country_name;
    
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> userList;
    

    public Country() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}


 ```
## How to JOIN TABLE SEARCH to data in Hibernate (one to many relationshipe)
 ```bash

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Country;
import model.HibernateUtil;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

@WebServlet(name = "OneTomanySearch", urlPatterns = {"/OneTomanySearch"})
public class OneTomanySearch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Country.class);
        criteria.add(Restrictions.eq("country_name", "UK"));

        // Country country = (Country) criteria.list().get(0); this line is comment. and use to line uncomment.
        Country country = (Country) criteria.uniqueResult();

        // List<User> list = country.getUserList(); this line is comment. and use to line uncomment.
        
        Criteria criteria1 = session.createCriteria(User.class);
        criteria1.add(Restrictions.eq("country", country));
        
        List<User> list = criteria1.list();
        
        for (User user : list) {
            System.out.println(user.getName());
        }

        session.close();

    }

}

 ```
## How to SEARCH lot of users between two country in data in Hibernate 
 ```bash


package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Country;
import model.HibernateUtil;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


@WebServlet(name = "moreUserInTwoCountry", urlPatterns = {"/moreUserInTwoCountry"})
public class moreUserInTwoCountry extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Country.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.eq("country_name", "USA"),
                        Restrictions.eq("country_name", "UK")
                )
        );

        List<Country> countryList = criteria.list();

        Criteria criteria1 = session.createCriteria(User.class);
        criteria1.add(
                Restrictions.in("country", countryList)
        );
        List<User> list = criteria1.list();
        for (User user : list) {
            System.out.println(user.getName());
        }

        session.close();

    }

}

 ```
## How to SEARCH distinct in data in Hibernate 
 ```bash

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Country;
import model.HibernateUtil;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;


@WebServlet(name = "DistinctSearch", urlPatterns = {"/DistinctSearch"})
public class DistinctSearch extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(User.class);
        criteria.setProjection(
                Projections.distinct(
                        Projections.property("name")
                )
        );
        
        List<String> list = criteria.list();
        
        for (String string : list) {
            System.out.println(string);
        }
        
        session.close();
        
    }
    
}


 ```
## How to user HSQL in data in Hibernate( Hibernate Query Language ) 
 ```bash

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

@WebServlet(name = "HQLquery", urlPatterns = {"/HQLquery"})
public class HQLquery extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        //Query query = session.createQuery("FROM User"); this line is comment. and use to line uncomment.
        //Query query = session.createQuery("FROM User WHERE name='ISHAN'"); this line is comment. and use to line uncomment.
        //Query query = session.createQuery("FROM User ORDER BY name"); this line is comment. and use to line uncomment.
        Query query = session.createQuery("SELECT DISTINCT(name) FROM User");
        List<String> list = query.list();
        
        //List<User> list = query.list();
        
        for (String string : list) {
            System.out.println(string);
        }

//        for (User user : list) { this line is comment. and use to line uncomment.
//            System.out.println(user.getName()); this line is comment. and use to line uncomment.
//        } this line is comment. and use to line uncomment.

        session.close();

    }

}

 ```
## How to user SQL in data in Hibernate( Structured Query Language ) 
 ```bash

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


@WebServlet(name = "SQLquery", urlPatterns = {"/SQLquery"})
public class SQLquery extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();

      //  SQLQuery query = session.createSQLQuery("SELECT * FROM `user` WHERE `name`='ISHAN'"); this line is comment. and use to line uncomment.
        SQLQuery query = session.createSQLQuery("SELECT * FROM `user` ORDER BY `name` ASC");
        query.addEntity(User.class);

        List<User> list = query.list();

        for (User user : list) {
            System.out.println(user.getName());
        }

        session.close();

    }
}

 ```




