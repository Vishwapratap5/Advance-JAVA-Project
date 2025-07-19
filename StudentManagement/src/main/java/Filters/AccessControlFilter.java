package Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/welcome.jsp") // 👈 You can also use *.jsp to protect all JSPs
public class AccessControlFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("email") != null) {

            chain.doFilter(request, response);
        } else {

            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>Please login first</h1>");
            RequestDispatcher rd = req.getRequestDispatcher("/Login.html");
            rd.include(req, resp);
        }
    }

    public void init(FilterConfig config) {}
    public void destroy() {}
}

