package core;

import exceptions.AuthorizationException;
import interfaces.IUserDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Triton on 20.04.2015.
 */
@Repository
public class UserDaoSpring implements IUserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getLoginUser(String nickname) throws SQLException, AuthorizationException {
        User user = null;
        try {
            user = (User) entityManager.createNamedQuery("findSingleUser").setParameter("nickname", nickname).getSingleResult();
        } catch (NoResultException e) {

        }
        return user;

    }

    @Override
    public ArrayList<User> searchUsers(String searchedNickName, String searchedPhone, boolean forEditing) throws SQLException {
        ArrayList<User> list = new ArrayList<>(entityManager.createNamedQuery("findUsersByNicknameAndPhone").setParameter("nickname", searchedNickName).setParameter("phone", searchedPhone).getResultList());
        return list;
    }

    @Override
    public void addUser(User user) throws SQLException {
        entityManager.persist(user);
    }

    @Override
    public void changeUserData(User user) throws SQLException {
        entityManager.merge(user);

    }

    @Override
    public User getUserData(String nickname) throws SQLException {
        User user = null;
        try {
            user = (User) entityManager.createNamedQuery("findSingleUser").setParameter("nickname", nickname).getSingleResult();
        } catch (NoResultException e) {

        }
        return user;
    }

    @Override
    public void deleteUser(String nickname) throws SQLException {
        User user = (User) entityManager.createNamedQuery("findSingleUser").setParameter("nickname", nickname).getSingleResult();

        entityManager.remove(user);
    }
}
