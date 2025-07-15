package Servlets;

import Service.DeleteCourseService;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteCourse")
public class DeleteCourseServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        int courseId=Integer.parseInt(req.getParameter("courseId"));
        DeleteCourseService deleteCourseService=new DeleteCourseService();
        deleteCourseService.deleteCourse(courseId);

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h1>Course Deleted Successfully</h1>");
    }

}
