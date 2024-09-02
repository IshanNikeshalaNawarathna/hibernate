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
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Ishan
 */
@WebServlet(name = "SQLquery", urlPatterns = {"/SQLquery"})
public class SQLquery extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();

      //  SQLQuery query = session.createSQLQuery("SELECT * FROM `user` WHERE `name`='ISHAN'");
        SQLQuery query = session.createSQLQuery("SELECT * FROM `user` ORDER BY `name` ASC");
        query.addEntity(User.class);

        List<User> list = query.list();

        for (User user : list) {
            System.out.println(user.getName());
        }

        session.close();

    }
}
