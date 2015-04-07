package jsp.controller;

import SpringTest.DependencyInjectionServlet;
import SpringTest.Inject;
import core.UserService;
import interfaces.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DataBindingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Class for processing requests from Search Page
 */
public class SearchController extends DependencyInjectionServlet {

    @Inject("UserService")
    IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath()+"/Login.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Creating map of parameters and checking what action requested
        Map<String, String[]> parameters = request.getParameterMap();
        boolean isSearching = parameters.containsKey("btnSearch");
        boolean isAdding = parameters.containsKey("btnAdd");
        boolean isEditing = parameters.containsKey("btnEdit");
        boolean isDeleting = parameters.containsKey("btnDelete");


        boolean isLogged = request.getSession(true).getAttribute("USER")!=null;



        //Redirect on login page if user don't pass authorization
        if (!isLogged) {
            request.setAttribute("Error", "You need to login first!");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }


        // Actiong if search button pressed
        if (isSearching) {
            // setting list of searching users as attribute and redirecting to Search page
            try {
                String searchedNickname= request.getParameter("snickname");
                String searchedPhone= request.getParameter("sphone");
                searchedNickname=searchedNickname.isEmpty()?"%":"%"+searchedNickname+"%";
                searchedPhone= searchedPhone.isEmpty()?"%":"%"+searchedPhone+"%";
                request.setAttribute("Model", userService.searchUser(searchedNickname,searchedPhone));
            } catch (SQLException e) {
                showErrorPage(e.getMessage(), request, response);
            }
            request.setAttribute("snickname",request.getParameter("snickname"));
            request.setAttribute("sphone",request.getParameter("sphone"));
            request.getRequestDispatcher("/Search.jsp").forward(request, response);
        }

        if (isAdding) {
            request.setAttribute("snickname",request.getParameter("snickname"));
            request.setAttribute("sphone",request.getParameter("sphone"));
            request.getRequestDispatcher("/adduser.jsp").forward(request, response);
        }

        if (isDeleting) {
            String nickname = request.getParameter("nickname");
            try {
                userService.deleteUser(nickname);
            } catch (SQLException e) {
                showErrorPage(e.getMessage(), request, response);
            }
            request.getRequestDispatcher("/Search.jsp").forward(request, response);
        }


        if (isEditing) {
            request.setAttribute("Edit", true);
            try {
                request.setAttribute("User", userService.getUserData(request.getParameter("nickname")));
            } catch (SQLException e) {
                showErrorPage(e.getMessage(), request, response);
            }
            request.setAttribute("snickname",request.getParameter("snickname"));
            request.setAttribute("sphone",request.getParameter("sphone"));
            request.getRequestDispatcher("/adduser.jsp").forward(request, response);
        }

    }


    private void showErrorPage(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Error", error);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
