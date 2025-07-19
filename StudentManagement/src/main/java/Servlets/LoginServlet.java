package Servlets;

import Model.Student;
import Service.LoginStudentService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("StudentEmail");
        String pass = req.getParameter("StudentPassword");

        Student student = Student.builder()
                .email(mail)
                .password(pass)
                .build();
        HttpSession session = req.getSession();
        session.setAttribute("email", mail);
        session.setMaxInactiveInterval(600);
        LoginStudentService  loginStudentService = new LoginStudentService();
        boolean flag = loginStudentService.LoginStudent(student);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        if(flag){
            out.println("<h1>logged in Successfully..!</h1>");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Welcome.jsp");
            requestDispatcher.include(req,resp);
        }else{
            out.println("<h1>No such user found..!</h1>");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Login.html");
            requestDispatcher.include(req,resp);
        }

    }
}
