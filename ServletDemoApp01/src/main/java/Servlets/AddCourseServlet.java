package Servlets;

import Model.Course;
import Service.AddCourseService;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddCourse")
public class AddCourseServlet extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String courseName = req.getParameter("Course");
        int courseFees = Integer.parseInt(req.getParameter("CourseFees"));
        String courseDescription = req.getParameter("CourseDiscription");
        String courseDuration = req.getParameter("CourseDuration");


        Course course = Course.builder()
                . courseName(courseName)
                . courseFees(courseFees)
                . courseDescription(courseDescription)
                . courseDuration(courseDuration).build();
        AddCourseService addCourseService = new AddCourseService();

        System.out.println("Name: " + course.getCourseName());
        System.out.println("Duration: " + course.getCourseDuration());
        System.out.println("Fees: " + course.getCourseFees());
        System.out.println("Description: " + course.getCourseDescription());
        addCourseService.addCourse(course);

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h1>Course Added Successfully</h1>");
    }
}
