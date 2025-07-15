package Servlets;

import DAO.ShowAllCoursesDAO;
import Model.Course;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/ShowCourses")
public class ShowAllCoursesServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ShowAllCoursesDAO showAllCoursesDAO=new ShowAllCoursesDAO();
        ArrayList<Course>  courses=showAllCoursesDAO.ShowAllCourses();
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out=servletResponse.getWriter();
        for(Course course:courses){
            System.out.println(course);
           out.println(course);
        }
    }
}
