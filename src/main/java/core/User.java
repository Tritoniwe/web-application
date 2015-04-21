package core;

import javax.persistence.*;
@NamedQueries({
        @NamedQuery(
                name = "findSingleUser",
                query = "SELECT u FROM User u WHERE nickname LIKE :nickname"
        ),
        @NamedQuery(
                name = "findUsersByNicknameAndPhone",
                query = "SELECT u FROM User u WHERE nickname LIKE :nickname AND phone LIKE :phone"
        )
})

@Entity
@Table( name = "\"Users\"")
public class User {
    @Id
    @Column
    public String nickname;
    @Column
    public String password;
    @Column(name = "userrole")
    public String userRole;
    @Column
    public String name;
    @Column
    public String surname;
    @Column
    public String sex;
    @Column
    public String email;
    @Column
    public String phone;
    @Column
    public String address;


    public User() {
    }

    /**
     * Small constructor only for authorization checking
     *
     * @param nickname
     * @param password
     * @param userRole
     */

    public User(String nickname, String password, String userRole) {
        this.nickname = nickname;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String nickname, String password, String userRole, String name,
                String surname, String sex, String email, String phone, String address) {
        this.nickname = nickname;
        this.password = password;
        this.userRole = userRole;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void changeInformation (User user){
        this.password = user.password;
        this.userRole = user.userRole;
        this.name = user.name;
        this.surname = user.surname;
        this.sex = user.sex;
        this.email = user.email;
        this.phone = user.phone;
        this.address = user.address;
    }
}
