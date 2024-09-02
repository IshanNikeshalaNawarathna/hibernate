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
import model.Country;
import model.HibernateUtil;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ishan
 */
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
