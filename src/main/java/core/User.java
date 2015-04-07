package core;


public class User {
    public String nickname;
    public String password;
    public String userRole;
    public String name;
    public String surname;
    public String sex;
    public String email;
    public  String phone;
    public String address;

    /**
     * Small constructor only for authorization checking
     * @param nickname
     * @param password
     * @param userRole
     */
    public User(String nickname,String password, String userRole) {
        this.nickname=nickname;
        this.password=password;
        this.userRole=userRole;
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
}
