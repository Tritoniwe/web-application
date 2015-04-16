package core;

import exceptions.AuthorizationException;
import interfaces.IUserService;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Triton on 31.03.2015.
 */
public class UserServiceMock implements IUserService {


    @Override
    public void login(String nickname, String password) throws SQLException, AuthorizationException {
    }

    @Override
    public void addUser(User user) throws SQLException {

    }

    @Override
    public ArrayList<User> searchUser(String searchedName, String searchedPhone) throws SQLException {
//        if (2%2==0)throw new SQLException("Test");
        return null;
    }

    @Override
    public void deleteUser(String nickname) throws SQLException {

    }

    @Override
    public void editUser(User user) throws SQLException {

    }

    @Override
    public User getUserData(String nickname) throws SQLException {
        return null;
    }
}
