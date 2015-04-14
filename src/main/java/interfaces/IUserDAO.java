package interfaces;

import core.User;
import exceptions.AuthorizationException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Triton on 08.04.2015.
 */
public interface IUserDAO {
    User getLoginUser(String nickname) throws SQLException, AuthorizationException;

    ArrayList<User> searchUsers(String searchedNickName, String searchedPhone, boolean forEditing) throws SQLException;

    void addUser(User user) throws SQLException;

    void changeUserData(User user) throws SQLException;

    User getUserData(String nickname) throws SQLException;

    void deleteUser(String nickname) throws SQLException;
}
