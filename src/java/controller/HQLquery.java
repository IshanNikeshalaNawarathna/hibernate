/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

/**
 *
 * @author Ishan
 */
@WebServlet(name = "HQLquery", urlPatterns = {"/HQLquery"})
public class HQLquery extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        //Query query = session.createQuery("FROM User");
        //Query query = session.createQuery("FROM User WHERE name='ISHAN'");
        //Query query = session.createQuery("FROM User ORDER BY name");
        Query query = session.createQuery("SELECT DISTINCT(name) FROM User");
        List<String> list = query.list();
        
        //List<User> list = query.list();
        
        for (String string : list) {
            System.out.println(string);
        }

//        for (User user : list) {
//            System.out.println(user.getName());
//        }

        session.close();

    }

}
