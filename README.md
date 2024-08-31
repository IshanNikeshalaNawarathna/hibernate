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

   
