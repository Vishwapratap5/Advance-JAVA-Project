package Servlets;

import Model.Course;
import Service.DeleteCourseService;
import Service.ShowCourseByIDService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/ShowCourseByID")
public class ShowCourseByIDServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId=Integer.parseInt(req.getParameter("courseId"));
        Course course=Course.builder()
                .courseID(courseId)
                .build();
        ShowCourseByIDService showCourseByService=new ShowCourseByIDService();
        Course course2=showCourseByService.getCourseByID(course);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>Course fetched Successfully</h1>");
        out.println(course2);
    }
}
