package Servlets;

import Model.Student;
import Service.AddStudentService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/Signup")
public class signupServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String StudentName = req.getParameter("StudentName");
        String Password = req.getParameter("StudentPassword");
        String Email = req.getParameter("StudentEmail");

        Student student = Student.builder()
                .studentName(StudentName)
                .password(Password)
                .email(Email)
                .build();
        AddStudentService addStudentService = new AddStudentService();
        boolean s1=addStudentService.addStudent(student);
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        if(s1){
            out.println("<h1>Signed up successfully!</h1>");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.html");
            requestDispatcher.include(req,resp);
        }else{
            out.println("<h1>Signed up failed due to some error!</h1>");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Signup.html");
            requestDispatcher.include(req,resp);
        }
    }

}
