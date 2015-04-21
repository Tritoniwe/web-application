package core;

import exceptions.AuthorizationException;
import interfaces.IUserDAO;
import interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Main Worker class
 */
@Service
public class UserServiceSpring implements IUserService {
    @Autowired
    IUserDAO iUserDAO;

    public UserServiceSpring() {


    }

    /**
     * Method which checks user password and user role
     *
     * @param nickname
     * @param passwd
     * @throws java.sql.SQLException
     * @throws exceptions.AuthorizationException - can be thrown if there no such user in DB, wrong password, wrong role
     */
    public void login(String nickname, String passwd) throws SQLException, AuthorizationException {

        if (nickname == null) throw
                new IllegalArgumentException("The nickname argument to the setUserStatus method may not be null.");
        if (passwd == null) throw
                new IllegalArgumentException("The passwd argument to the setUserStatus method may not be null.");
        User user = iUserDAO.getLoginUser(nickname);

        if (user == null) throw new AuthorizationException("No such User in DataBase");
        if (!user.password.equals(passwd)) throw new AuthorizationException("Wrong password.");
        if (!user.userRole.equals("Admin"))
            throw new AuthorizationException("Access granted only to users whit Admin role.");

    }

    /**
     * Method for adding User in DB
     *
     * @param user
     * @throws java.sql.SQLException
     */
    @Transactional
    public void addUser(User user) throws SQLException {
        iUserDAO.addUser(user);
    }

    /**
     * Method for searching Users in DB
     *
     * @param searchedName  - searched name
     * @param searchedPhone - searched phone
     * @return - List of searched users
     * @throws java.sql.SQLException
     */
    public ArrayList<User> searchUser(String searchedName, String searchedPhone) throws SQLException {


        return iUserDAO.searchUsers(searchedName, searchedPhone, false);
    }

    /**
     * Method for deleting user
     *
     * @param nickname - nickname of user witch must be deleted
     * @throws java.sql.SQLException
     */
    @Transactional
    public void deleteUser(String nickname) throws SQLException {
        iUserDAO.deleteUser(nickname);
    }

    /**
     * Method for editing User information
     *
     * @param user - modified USer object
     * @throws java.sql.SQLException
     */
    @Transactional
    public void editUser(User user) throws SQLException {
        iUserDAO.changeUserData(user);
    }

    /**
     * Method for getting User data witch will be used in Editing form
     *
     * @param nickname - nickname of User
     * @return - User object
     * @throws java.sql.SQLException
     */
    public User getUserData(String nickname) throws SQLException {
        return iUserDAO.getUserData(nickname);
    }


    public void setiUserDAO(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }
}
