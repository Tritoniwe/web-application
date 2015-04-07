package core;

import exceptions.AuthorizationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class which obtaining and processing information from DB
 */
public class UserDAO {

    UserDAO() {

    }

    /**
     * Method that gets User data for authorization
     *
     * @param nickname - nickname of User
     * @return null - if no such user in DB,  or User object with nickname, password and user role
     * @throws java.sql.SQLException
     */
    public User getLoginUser(String nickname) throws SQLException, AuthorizationException {
        try (Connection conn = new DataAccessLayer().getConnection()) {

            String selectInstruction = "SELECT password, userrole FROM \"Users\" WHERE nickname = ?";

            try (PreparedStatement preparedStatement = conn.prepareStatement(selectInstruction)) {
                preparedStatement.setString(1, nickname);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    if (!result.next()) return null;
                    return new User(nickname, result.getString("password"), result.getString("userrole"));
                }
            }
        }
    }

    /**
     * Method for searching Users in DB by selected parameters.
     *
     * @param searchedNickName - searched nickname
     * @param searchedPhone    - searched phone
     * @return List of searched Users
     * @throws SQLException
     */
    public ArrayList<User> searchUsers(String searchedNickName, String searchedPhone, boolean forEditing) throws SQLException {
        ArrayList<User> userArrayList = new ArrayList<>();
        try (Connection conn = new DataAccessLayer().getConnection()) {
            PreparedStatement preparedStatement;
            if (!forEditing) {
                String selectInstruction = "SELECT * FROM \"Users\" WHERE nickname LIKE ? AND phone LIKE ?";
                preparedStatement = conn.prepareStatement(selectInstruction);
                preparedStatement.setString(1, searchedNickName);
                preparedStatement.setString(2, searchedPhone);


            } else {
                String selectInstruction = "SELECT * FROM \"Users\" WHERE nickname = ?";
                preparedStatement = conn.prepareStatement(selectInstruction);
                preparedStatement.setString(1, searchedNickName);


            }

            try (ResultSet result = preparedStatement.executeQuery()) {


                while (result.next()) {
                    String nickname = result.getString("nickname");
                    String password = result.getString("password");
                    String userRole = result.getString("userrole");
                    String name = result.getString("name");
                    String surname = result.getString("surname");
                    String sex = result.getString("sex");
                    String email = result.getString("email");
                    String phone = result.getString("phone");
                    String address = result.getString("address");

                    userArrayList.add(new User(nickname, password, userRole, name, surname, sex, email, phone, address));
                }
                preparedStatement.close();
                return userArrayList;
            }
        }
    }

    /**
     * Method for adding Users to DB.
     *
     * @param user - User for adding to DB
     * @return - true if operation successful
     * @throws SQLException
     */
    public void addUser(User user) throws SQLException {
        if (user == null) throw new IllegalArgumentException("User argument must not be null");
        if (user.nickname.isEmpty() || user.password.isEmpty() || user.phone.isEmpty() ||
                user.name.isEmpty() || user.surname.isEmpty() || user.userRole.isEmpty()) {
            throw new IllegalArgumentException("User's nickname,password,phone,name,surname,role can't be empty");
        }
        //if (user.sex==null) user.sex = null;
        if (user.address.isEmpty()) user.address = null;
        if (user.email.isEmpty()) user.email = null;
        try (Connection conn = new DataAccessLayer().getConnection()) {
            conn.setAutoCommit(false);
            String insertInstruction = "INSERT INTO \"Users\" (nickname,password,userrole,name,surname,sex,email,phone,address) VALUES " +
                    "( ? , ? , ? , ? , ? , ? , ? , ? , ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(insertInstruction)) {
                preparedStatement.setString(1, user.nickname);
                preparedStatement.setString(2, user.password);
                preparedStatement.setString(3, user.userRole);
                preparedStatement.setString(4, user.name);
                preparedStatement.setString(5, user.surname);
                preparedStatement.setString(6, user.sex);
                preparedStatement.setString(7, user.email);
                preparedStatement.setString(8, user.phone);
                preparedStatement.setString(9, user.address);

                preparedStatement.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage());
            }
        }
    }

    /**
     * Method for changing User data. It can change any information except user's nickname.
     *
     * @param user - modified user information
     * @return - true if operation successful
     * @throws SQLException
     */
    public void changeUserData(User user) throws SQLException {


        try (Connection conn = new DataAccessLayer().getConnection()) {
            conn.setAutoCommit(false);
            String updateInstruction = "UPDATE \"Users\" SET password = ? , userrole = ? , name = ? , " +
                    "surname = ? , sex = ? , email = ? , phone = ? , address = ? WHERE nickname= ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(updateInstruction)) {
                preparedStatement.setString(1, user.password);
                preparedStatement.setString(2, user.userRole);
                preparedStatement.setString(3, user.name);
                preparedStatement.setString(4, user.surname);
                preparedStatement.setString(5, user.sex);
                preparedStatement.setString(6, user.email);
                preparedStatement.setString(7, user.phone);
                preparedStatement.setString(8, user.address);
                preparedStatement.setString(9, user.nickname);
                preparedStatement.executeUpdate();
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage());
            }
        }
    }

    /**
     * Method for searching one user by it's nickname
     *
     * @param nickname - nickname of searched user
     * @return - searched User
     * @throws SQLException
     */
    public User getUserData(String nickname) throws SQLException {
        return searchUsers(nickname, "", true).get(0);
    }

    /**
     * Method which deletes users from DB
     *
     * @param nickname - user's nickname
     * @return - true if operation successful
     * @throws SQLException
     */
    public void deleteUser(String nickname) throws SQLException {
        try (Connection conn = new DataAccessLayer().getConnection()) {
            conn.setAutoCommit(false);
            String deleteInstruction = "DELETE FROM \"Users\" WHERE nickname = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(deleteInstruction)) {
                preparedStatement.setString(1, nickname);
                preparedStatement.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage());
            }
        }
    }


}
