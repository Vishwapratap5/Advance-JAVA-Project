package Servlets;

import Model.Course;
import Service.UpdateCourseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UpdateCourse")
public class UpdateCourseServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int CourseID = Integer.parseInt(request.getParameter("CourseID"));
        String CourseName = request.getParameter("Course");
        String CourseDescription = request.getParameter("CourseDiscription");
        String CourseDuration = request.getParameter("CourseDuration");
        int CourseFees = Integer.parseInt(request.getParameter("CourseFees"));

        Course course=Course.builder()
                .courseID(CourseID)
                .courseDuration(CourseDuration)
                .courseDescription(CourseDescription)
                .courseName(CourseName)
                .courseFees(CourseFees).build();
        System.out.println(CourseID+""+CourseName+""+CourseDescription+""+CourseDuration+""+CourseFees);
        UpdateCourseService updateCourseService=new UpdateCourseService();
        Course UpdatedCourse=updateCourseService.UpdateCourse(course);
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<h1>Course Updated</h1>");
        out.println(UpdatedCourse);

    }

}
