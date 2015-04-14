package core;

import exceptions.AuthorizationException;
import interfaces.IUserDAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Triton on 08.04.2015.
 */
public class UserDAOMock implements IUserDAO {
    @Override
    public User getLoginUser(String nickname) throws SQLException, AuthorizationException {
        System.out.println("Test Version!!!!");
        return null;
    }

    @Override
    public ArrayList<User> searchUsers(String searchedNickName, String searchedPhone, boolean forEditing) throws SQLException {
        return null;
    }

    @Override
    public void addUser(User user) throws SQLException {

    }

    @Override
    public void changeUserData(User user) throws SQLException {

    }

    @Override
    public User getUserData(String nickname) throws SQLException {
        return null;
    }

    @Override
    public void deleteUser(String nickname) throws SQLException {

    }
}
