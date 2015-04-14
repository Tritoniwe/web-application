package jsp.controller;

import springtest.DependencyInjectionServlet;
import springtest.Inject;
import exceptions.AuthorizationException;
import interfaces.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class for processing requests from Login Page
 */
public class LoginController extends DependencyInjectionServlet {
    @Inject("UserService")
    IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath()+"/Login.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nickname = request.getParameter("nickname").toLowerCase();
        String password = request.getParameter("password");

        //Checking for empty strings
        if (nickname.isEmpty() || password.isEmpty()) {
            String errorString = "You should fill in both values - nickname and password.";
            request.setAttribute("Error", errorString);
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }

        //authorization of user
        try {
           userService.login(nickname, password);
            request.setAttribute("LoggedUsername", nickname);
            HttpSession session = request.getSession(true);
            session.setAttribute("USER",nickname);
            request.getRequestDispatcher("/Search.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("Error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (AuthorizationException e) {
            request.setAttribute("Error", e.getMessage());
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }


    }
}
