package core;

import exceptions.AuthorizationException;
import interfaces.IUserService;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *  Main Worker class
 */
public class UserService implements IUserService {

    UserDAO userDAO;

    public UserService(){

        userDAO=new UserDAO();

    }

    /**
     *  Method which checks user password and user role
     * @param nickname
     * @param passwd
     * @throws SQLException
     * @throws AuthorizationException - can be thrown if there no such user in DB, wrong password, wrong role
     */
    public void login (String nickname, String passwd) throws SQLException, AuthorizationException {
        if (nickname == null) throw
                new IllegalArgumentException("The nickname argument to the setUserStatus method may not be null.");
        if (passwd == null) throw
                new IllegalArgumentException("The passwd argument to the setUserStatus method may not be null.");
        User user = userDAO.getLoginUser(nickname);

        if (user==null) throw new AuthorizationException("No such User in DataBase");
        if (!user.password.equals(passwd)) throw new AuthorizationException("Wrong password.");
        if (!user.userRole.equals("Admin")) throw new AuthorizationException("Access granted only to users whit Admin role.");

    }

    /**
     * Method for adding User in DB
     * @param user
     * @throws SQLException
     */
    public void addUser(User user)throws SQLException{
        userDAO.addUser(user);
    }

    /**
     * Method for searching Users in DB
     * @param searchedName - searched name
     * @param searchedPhone - searched phone
     * @return - List of searched users
     * @throws SQLException
     */
    public ArrayList<User> searchUser(String searchedName,String searchedPhone)throws SQLException{
        return userDAO.searchUsers(searchedName, searchedPhone, false);
    }

    /**
     * Method for deleting user
     * @param nickname - nickname of user witch must be deleted
     * @throws SQLException
     */
    public  void deleteUser(String nickname) throws SQLException {
        userDAO.deleteUser(nickname);
    }

    /**
     * Method for editing User information
     * @param user - modified USer object
     * @throws SQLException
     */
    public void editUser(User user) throws SQLException {
        userDAO.changeUserData(user);
    }

    /**
     * Method for getting User data witch will be used in Editing form
     * @param nickname - nickname of User
     * @return - User object
     * @throws SQLException
     */
    public User getUserData(String nickname) throws SQLException {
        return userDAO.getUserData(nickname);
    }


}
