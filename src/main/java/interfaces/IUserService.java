package interfaces;

import core.User;
import exceptions.AuthorizationException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserService {

    public void login (String nickname, String password) throws SQLException, AuthorizationException ;

    public void addUser(User user)throws SQLException;

    public ArrayList<User> searchUser(String searchedName,String searchedPhone)throws SQLException;

    public  void deleteUser(String nickname) throws SQLException ;

    public void editUser(User user) throws SQLException ;

    public User getUserData(String nickname) throws SQLException ;

}



