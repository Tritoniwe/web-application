package jsp.controller;

import injection.DependencyInjectionServlet;
import injection.Inject;
import core.User;
import interfaces.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class for processing requests from Add|Edit Page
 */
public class ModifUserController extends DependencyInjectionServlet {
    @Inject("UserService")
    IUserService userService;

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect(req.getContextPath()+"/Login.jsp");
//    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> parameters = request.getParameterMap();
        boolean isAddingUser = parameters.containsKey("btnAddUser");
        boolean isEditingUser = parameters.containsKey("btnEditUser");
        boolean isBack = parameters.containsKey("btnBack");
//        boolean isLogged = request.getSession(true).getAttribute("USER")!=null;
        request.getSession(true).setAttribute("isReturning", true);

        //Redirect on login page if user don't pass authorization
//        if (!isLogged) {
//            request.setAttribute("Error", "You need to login first!");
//            request.getRequestDispatcher("/Login.jsp").forward(request, response);
//        }

        if (isAddingUser) {
            ArrayList<String> errorList = new ArrayList<>();
            User user = fillUserData(request);//Creating user from input data

            //Checking for empty attributes
            if (user.nickname.isEmpty()) errorList.add("Nickname");
            if (user.password.isEmpty()) errorList.add("Password");
            if (user.userRole == null) errorList.add("User Role");
            if (user.name.isEmpty()) errorList.add("Name");
            if (user.surname.isEmpty()) errorList.add("Surname");
            if (user.phone.isEmpty()) errorList.add("Phone");
            try {
                if (userService.getUserData(user.nickname) != null) errorList.add("Such nickname is taken");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // If all Ok process data to DB
            if (errorList.isEmpty()) {
                try {
                    userService.addUser(user);
                } catch (SQLException e) {
                    showErrorPage(e.getMessage(), request, response);
                }
                request.getRequestDispatcher("Search").forward(request, response);
            } else {
                // If all bad set attributes and redirect back on page with error string
                request.setAttribute("User", user);
                request.setAttribute("ErrorModel", errorList);
                request.getRequestDispatcher("/adduser.jsp").forward(request, response);

            }
        }

        // Action if button Save pressed
        if (isEditingUser) {
            User user = fillUserData(request); //Creating user from input data
            ArrayList<String> errorList = new ArrayList<>();

            //Checking for empty attributes
            if (user.password.isEmpty()) errorList.add("Password");
            if (user.userRole == null) errorList.add("User Role");
            if (user.name.isEmpty()) errorList.add("Name");
            if (user.surname.isEmpty()) errorList.add("Surname");
            if (user.phone.isEmpty()) errorList.add("Phone");


            // If all Ok process data to DB
            if (errorList.isEmpty()) {
                try {

                    userService.editUser(user);
                } catch (SQLException e) {
                    showErrorPage(e.getMessage(), request, response);
                }
            } else {
                // If all bad set attributes and redirect back on page with error string
                request.setAttribute("User", user);
                request.setAttribute("ErrorModel", errorList);
                request.setAttribute("Edit", true);
                request.getRequestDispatcher("/adduser.jsp").forward(request, response);
            }
            request.getRequestDispatcher("Search").forward(request, response);
        }

        if (isBack) {
            request.getRequestDispatcher("Search").forward(request, response);
        }
    }


    /**
     * Method for filling all user data from request -> object User
     *
     * @param request
     * @return
     */
    private User fillUserData(HttpServletRequest request) {
        String nickname = request.getParameter("nickname").toLowerCase();
        String password = request.getParameter("password");
        String userRole = request.getParameter("userrole");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        return new User(nickname, password, userRole, name, surname, sex, email, phone, address);
    }


    private void showErrorPage(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Error", error);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}